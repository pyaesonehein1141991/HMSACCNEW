package org.hms.accounting.report.LedgerListingReport.service.interfaces;

import java.util.List;

import org.hms.accounting.dto.AccountLedgerDto;
import org.hms.accounting.dto.GenLedgerDto;
import org.hms.accounting.dto.LedgerDto;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.java.component.SystemException;

public interface ILedgerListingReportService {
	/***
	 * Account Ledger Listing and General Ledger Listing
	 * 
	 * @param coaList
	 */
	public List<AccountLedgerDto> findAccLedgerListing(LedgerDto ledgerDto, List<ChartOfAccount> coaList) throws SystemException;

	public List<GenLedgerDto> findGenLedgerListing(LedgerDto ledgerDto, ChartOfAccount coa) throws SystemException;

	public List<GenLedgerDto> findGenLedgerListingByClone(LedgerDto ledgerDto, ChartOfAccount coa) throws SystemException;

	public List<AccountLedgerDto> findAccLedgerListingByClone(LedgerDto ledgerDto, List<ChartOfAccount> coaList) throws SystemException;
}
