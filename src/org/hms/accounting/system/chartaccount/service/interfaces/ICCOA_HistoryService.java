package org.hms.accounting.system.chartaccount.service.interfaces;

import java.util.List;

import org.hms.accounting.system.chartaccount.CurrencyChartOfAccount;

public interface ICCOA_HistoryService{
	public List<CurrencyChartOfAccount> findAll();
	public List<String> findAllBudgetYear();
	public void updateYearlyBudget(List<CurrencyChartOfAccount> yearlyBudgetList);
}
