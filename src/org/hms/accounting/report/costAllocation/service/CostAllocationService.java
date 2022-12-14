package org.hms.accounting.report.costAllocation.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hms.accounting.common.CurrencyType;
import org.hms.accounting.common.utils.BusinessUtil;
import org.hms.accounting.dto.AllocateByDeptDto;
import org.hms.accounting.dto.AllocateProcessByDeptDto;
import org.hms.accounting.dto.AllocateProcessDto;
import org.hms.accounting.dto.CostAllocationReportDto;
import org.hms.accounting.report.costAllocation.persistence.interfaces.ICostAllocationDAO;
import org.hms.accounting.report.costAllocation.service.interfaces.ICostAllocationService;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.branch.service.interfaces.IBranchService;
import org.hms.accounting.system.chartaccount.AccountType;
import org.hms.accounting.system.chartaccount.persistence.interfaces.ICcoaDAO;
import org.hms.accounting.system.chartaccount.persistence.interfaces.ICoaDAO;
import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.system.currency.service.interfaces.ICurrencyService;
import org.hms.accounting.system.tlf.TLF;
import org.hms.accounting.system.tlf.service.interfaces.ITLFService;
import org.hms.accounting.system.trantype.TranCode;
import org.hms.accounting.system.trantype.TranType;
import org.hms.accounting.system.trantype.persistence.interfaces.ITranTypeDAO;
import org.hms.java.component.SystemException;
import org.hms.java.component.idgen.service.interfaces.ICustomIDGenerator;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.hms.java.component.service.interfaces.IDataRepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "CostAllocationService")
public class CostAllocationService extends BaseService implements ICostAllocationService {
	@Resource(name = "CostAllocationDAO")
	private ICostAllocationDAO costAllocationDAO;

	@Resource(name = "CustomIDGenerator")
	private ICustomIDGenerator customIDGenerator;

	@Resource(name = "DataRepService")
	private IDataRepService<TLF> dataRepService;

	@Resource(name = "TranTypeDAO")
	private ITranTypeDAO tranTypeDAO;

	@Resource(name = "CcoaDAO")
	private ICcoaDAO ccoaDAO;

	@Resource(name = "CoaDAO")
	private ICoaDAO coaDAO;

	@Resource(name = "TLFService")
	private ITLFService tlfService;

	@Resource(name = "BranchService")
	private IBranchService branchService;

	@Resource(name = "CurrencyService")
	private ICurrencyService currencyService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CostAllocationReportDto> findCostAllocationReport(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth,
			boolean currencyConverted) {
		List<CostAllocationReportDto> result = null;
		try {
			result = costAllocationDAO.findCostAllocationReport(currencyType, branch, currency, allocateYear, allocateMonth, currencyConverted);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find Cost Allocation", e);
		}
		return result;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void allocateProcess(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth, boolean currencyConverted) {
		allocateProcessByDept(currencyType, branch, currency, allocateYear, allocateMonth, currencyConverted);
		allocateProcessForGroup(currencyType, branch, currency, allocateYear, allocateMonth, currencyConverted);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void allocateProcessByDept(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth, boolean currencyConverted) {
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(allocateYear);
		int m = Integer.parseInt(allocateMonth);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, m);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date reportDate = cal.getTime();

		String budgetYear = BusinessUtil.getBudgetInfo(reportDate, 2);
		List<AllocateProcessByDeptDto> dtoList = costAllocationDAO.findAllocateProcessByDept(currencyType, branch, currency, allocateYear, allocateMonth, currencyConverted);
		String voucherNo = tlfService.getVoucherNo();
		for (AllocateProcessByDeptDto mainDto : dtoList) {
			List<AllocateByDeptDto> list = costAllocationDAO.getAcCodeByDept(mainDto.getHacCode());
			for (AllocateByDeptDto subDto : list) {
				TranType tranType = null;
				if (subDto.getAcType().equals("I")) {
					tranType = tranTypeDAO.findByTransCode(TranCode.TRCREDIT);
				} else if (subDto.getAcType().equals("E")) {
					tranType = tranTypeDAO.findByTransCode(TranCode.TRDEBIT);
				}
				Currency dtoCurrency = currencyService.findCurrencyByCurrencyCode(mainDto.getCurrencyCode());
				BigDecimal amount = mainDto.getDepartmentAmountMap().get(subDto.getdCode());
				if (tranType != null && amount.doubleValue() != 0) {
					TLF tlf = new TLF(voucherNo,
							ccoaDAO.findCCOAByIdAndBranch(coaDAO.findByAcCode(subDto.getAcCode()).getId(), branchService.findBranchByBranchCode("001"), dtoCurrency),
							mainDto.getDepartmentAmountMap().get(subDto.getdCode()).multiply(mainDto.getRate()), mainDto.getDepartmentAmountMap().get(subDto.getdCode()),
							dtoCurrency, "Cost Allocation for Departments", tranType, branchService.findBranchByBranchCode("001"), mainDto.getRate(), new Date(), false, budgetYear,
							"Allocate_Cost", true);
					dataRepService.insert(tlf);
				}
			}

		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void allocateProcessForGroup(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth, boolean currencyConverted) {
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(allocateYear);
		int m = Integer.parseInt(allocateMonth);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, m);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date reportDate = cal.getTime();

		String budgetYear = BusinessUtil.getBudgetInfo(reportDate, 2);
		String voucherNo = tlfService.getVoucherNo();

		// TODO fixing this
		List<AllocateProcessDto> list = costAllocationDAO.findAllocateProcess(currencyType, branch, currency, allocateYear, allocateMonth, currencyConverted);
		for (AllocateProcessDto dto : list) {
			TranType tranType = null;
			if (dto.getAcType().equals("I")) {
				tranType = tranTypeDAO.findByTransCode(TranCode.TRDEBIT);
			} else if (dto.getAcType().equals("E")) {
				tranType = tranTypeDAO.findByTransCode(TranCode.TRCREDIT);
			}
			Currency dtoCurrency = currencyService.findCurrencyByCurrencyCode(dto.getCurrencyCode());
			if (tranType != null) {
				TLF tlf = new TLF(voucherNo, ccoaDAO.findCCOAByIdAndBranch(coaDAO.findByAcCode(dto.getAcCode()).getId(), branchService.findBranchByBranchCode("001"), dtoCurrency),
						dto.getBalance().multiply(dto.getRate()), dto.getBalance(), dtoCurrency, "Cost Allocation", tranType, branchService.findBranchByBranchCode("001"),
						dto.getRate(), new Date(), false, budgetYear, "Allocate_Cost", true);
				dataRepService.insert(tlf);
			}
		}
	}

}
