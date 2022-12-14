package org.hms.accounting.report.incomeandexpenditure.presistence.interfaces;

import java.util.List;

import org.hms.accounting.report.incomeandexpenditure.IncomeAndExpenditureDto;
import org.hms.accounting.report.incomeandexpenditure.IncomeExpenseCriteria;
import org.hms.java.component.persistence.exception.DAOException;

public interface IIncomeAndExpenditureDAO {

	public List<IncomeAndExpenditureDto> find(IncomeExpenseCriteria criteria) throws DAOException;
}
