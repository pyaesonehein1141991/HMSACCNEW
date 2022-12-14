package org.hms.accounting.report.LedgerListingReport.persistence.interfaces;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hms.accounting.dto.AccountLedgerDto;
import org.hms.accounting.dto.GLDBDto;
import org.hms.accounting.dto.LedgerDto;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.accounting.system.currency.Currency;
import org.hms.java.component.persistence.exception.DAOException;

public interface ILedgerListingReportDAO {
	/***
	 * Account Ledger Listing and General Ledger Listing
	 */

	public BigDecimal finddblBalance(StringBuffer sf, ChartOfAccount coa, String budgetYear, Currency currency, Branch branch) throws DAOException;

	public List<AccountLedgerDto> findDebitCreditByStartDate(StringBuffer sf, ChartOfAccount coa, Date startDate, Date endDate) throws DAOException;

	public List<AccountLedgerDto> findDebitCreditBySDateAndEDate(StringBuffer sf, ChartOfAccount coa, Date startDate, Date endDate) throws DAOException;

	List<GLDBDto> findGeneralLedgerList(LedgerDto ledgerDto, ChartOfAccount coa, Date startDate, Date endDate) throws DAOException;

	List<GLDBDto> findGeneralLedgerListByClone(LedgerDto ledgerDto, ChartOfAccount coa, Date startDate, Date endDate) throws DAOException;

}
