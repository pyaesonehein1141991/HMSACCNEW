package org.hms.accounting.posting.service;

import java.util.Date;

import javax.annotation.Resource;

import org.hms.accounting.common.SystemConstants;
import org.hms.accounting.common.utils.BusinessUtil;
import org.hms.accounting.common.utils.DateUtils;
import org.hms.accounting.dto.YPDto;
import org.hms.accounting.posting.persistence.interfaces.IYearlyPostingDAO;
import org.hms.accounting.posting.service.interfaces.IYearlyPostingService;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.chartaccount.CcoaHistory;
import org.hms.accounting.system.chartaccount.CurrencyChartOfAccount;
import org.hms.accounting.system.chartaccount.persistence.interfaces.ICcoaDAO;
import org.hms.accounting.system.chartaccount.service.interfaces.ICcoaService;
import org.hms.accounting.system.chartaccount.service.interfaces.ICoaService;
import org.hms.accounting.system.rateinfo.persistence.interfaces.IRateInfoDAO;
import org.hms.accounting.system.systempost.SystemPost;
import org.hms.accounting.system.systempost.service.interfaces.ISystemPostService;
import org.hms.accounting.system.tlf.TLF;
import org.hms.accounting.system.tlf.service.interfaces.ITLFService;
import org.hms.accounting.system.tlfhist.TLFHIST;
import org.hms.accounting.system.trantype.persistence.interfaces.ITranTypeDAO;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.hms.java.component.service.interfaces.IDataRepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "YearlyPostingService")
public class YearlyPostingService extends BaseService implements IYearlyPostingService {
	@Resource(name = "DataRepService")
	private IDataRepService<SystemPost> systemPostDataService;

	@Resource(name = "SystemPostService")
	private ISystemPostService systemPostService;

	@Resource(name = "TLFService")
	private ITLFService tlfService;

	@Resource(name = "DataRepService")
	private IDataRepService<TLF> tlfDataService;

	@Resource(name = "DataRepService")
	private IDataRepService<TLFHIST> tlfHistDataService;

	@Resource(name = "CcoaService")
	private ICcoaService ccoaService;

	@Resource(name = "DataRepService")
	private IDataRepService<CcoaHistory> ccoaHistDataService;

	@Resource(name = "DataRepService")
	private IDataRepService<CurrencyChartOfAccount> ccoaDataService;

	@Resource(name = "CoaService")
	private ICoaService coaService;

	@Resource(name = "YearlyPostingDAO")
	private IYearlyPostingDAO yearlyPostingDAO;

	@Resource(name = "CcoaDAO")
	private ICcoaDAO ccoaDAO;

	@Resource(name = "RateInfoDAO")
	private IRateInfoDAO rateInfoDAO;

	@Resource(name = "TranTypeDAO")
	private ITranTypeDAO tranTypeDAO;

	@Resource(name = "TLFService")
	private ITLFService tLFService;

	@Transactional(propagation = Propagation.REQUIRED)
	public void handleYearlyPosting(Date postingDate, YPDto plAc, Branch branch) {
		try {
			String budgetYear = BusinessUtil.getBudgetInfo(DateUtils.plusYears(postingDate, 1), 2);
			SystemPost sysPost = systemPostService.findbyPostingName(SystemConstants.YEARPOST);

			yearlyPostingDAO.createHOClosingForManulProcess(branch.getId(), branch, plAc);

			sysPost.setPostingDate(BusinessUtil.getBudgetEndDate());

			systemPostDataService.update(sysPost);

			yearlyPostingDAO.moveTlfToHistory(postingDate);
			yearlyPostingDAO.moveCcoaToHistory(budgetYear, plAc, branch);
			yearlyPostingDAO.moveSetupToHistory();
			// yearlyPostingDAO.increaseBudgetYear();

			/* For setup table update after yearly posting */
			yearlyPostingDAO.increaseSetupBudgetYear();

		} catch (DAOException de) {
			throw new SystemException(de.getErrorCode(), "Failed to handle yearly posting.", de);
		}
	}

}