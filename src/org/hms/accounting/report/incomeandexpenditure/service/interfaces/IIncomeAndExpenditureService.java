package org.hms.accounting.report.incomeandexpenditure.service.interfaces;

import java.util.List;

import org.hms.accounting.report.incomeandexpenditure.IncomeAndExpenditureDto;
import org.hms.accounting.report.incomeandexpenditure.IncomeExpenseCriteria;

public interface IIncomeAndExpenditureService {

	/**
	 * Interface to find IncomeAndExpenditure Report for the given criteria
	 * parameter.
	 * 
	 * @param IncomeExpenseCriteria[Criteria
	 *            of user selection].
	 * 
	 * @return List<IncomeAndExpenditure>.
	 * 
	 * @throws SystemException.
	 * 
	 */
	public List<IncomeAndExpenditureDto> findIncomeExpense(IncomeExpenseCriteria criteria);
}
