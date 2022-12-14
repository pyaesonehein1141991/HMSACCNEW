package org.hms.accounting.report.costAllocation.service.interfaces;

import java.util.List;

import org.hms.accounting.common.CurrencyType;
import org.hms.accounting.dto.CostAllocationReportDto;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.currency.Currency;

public interface ICostAllocationService {
	public List<CostAllocationReportDto> findCostAllocationReport(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth,
			boolean currencyConverted);

	public void allocateProcess(CurrencyType currencyType, Branch branch, Currency currency, String allocateYear, String allocateMonth, boolean currencyConverted);
}
