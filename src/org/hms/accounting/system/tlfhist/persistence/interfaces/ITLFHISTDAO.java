package org.hms.accounting.system.tlfhist.persistence.interfaces;

import java.util.Date;
import java.util.List;

import org.hms.accounting.dto.AccountLedgerDto;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.accounting.system.tlf.TLF;
import org.hms.accounting.system.tlfhist.TLFHIST;
import org.hms.java.component.persistence.exception.DAOException;

public interface ITLFHISTDAO {
	public List<TLFHIST> findAll() throws DAOException;
	public List<TLF> findVoucherListByReverseZero(String voucherNo)throws DAOException;
	/***
	 * Account Ledger Listing and General Ledger Listing
	 */
	public List<AccountLedgerDto> findDebitCreditBy(StringBuffer sf,ChartOfAccount coa, Date sDate)throws DAOException;
	public List<AccountLedgerDto> findDebitCreditBy(StringBuffer sf,ChartOfAccount coa, Date startDate, Date endDate)throws DAOException;
}
