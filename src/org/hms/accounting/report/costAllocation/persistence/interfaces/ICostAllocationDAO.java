package org.hms.accounting.report.costAllocation.persistence.interfaces;

import java.util.List;

import org.hms.accounting.common.CurrencyType;
import org.hms.accounting.dto.AllocateByDeptDto;
import org.hms.accounting.dto.AllocateProcessByDeptDto;
import org.hms.accounting.dto.AllocateProcessDto;
import org.hms.accounting.dto.CostAllocationReportDto;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.currency.Currency;

public interface ICostAllocationDAO {
	public List<CostAllocationReportDto> findCostAllocationReport(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth,
			boolean currencyConverted);

	public List<AllocateProcessDto> findAllocateProcess(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth,
			boolean currencyConverted);

	public List<AllocateProcessByDeptDto> findAllocateProcessByDept(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth,
			boolean currencyConverted);

	public List<AllocateByDeptDto> getAcCodeByDept(String hacCode);
}
