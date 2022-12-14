package org.hms.accounting.report.listingReport.persistence.interfaces;

import java.util.Date;
import java.util.List;

import org.hms.accounting.dto.CashBookDTO;
import org.hms.accounting.dto.CoaListingDto;
import org.hms.accounting.dto.VLdto;
import org.hms.accounting.dto.VPFTdto;
import org.hms.accounting.dto.VPdto;
import org.hms.accounting.report.CashBookCriteria;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.web.listing.TransactionType;
import org.hms.accounting.web.listing.VoucherListingType;
import org.hms.java.component.persistence.exception.DAOException;

/**
 * This interface serves as the DAO to manipulate the <code>CashBookDTO</code>
 * object.
 * 
 * @author HS
 * @since 1.0.0
 * @date 2015/04/08
 */

public interface IListingReportDAO {
	public List<CoaListingDto> findAllCoaList() throws DAOException;

	/**
	 * 
	 * @param criteria
	 * @return {@link List} of {@link CashBookDTO} instance
	 * @throws DAOException
	 *             An exception occurs during the DB operation
	 */
	public List<CashBookDTO> findCashbookListingReport(CashBookCriteria criteria);

	public List<VPFTdto> findFromVoucherNo(Date startDate, Date endDate, Branch branch);

	public List<VPFTdto> findFromVoucherNoForGenJournal(VoucherListingType type, Date startDate, Date endDate, Branch branch, Currency currency, TransactionType tType);

	public List<VPdto> findVoucherPrintingByVoucherNo(Date startDate, Date endDate, String fromId, String toId);

	public List<VPdto> findVoucherPrinting(Date startDate, Date endDate);

	List<VLdto> findVoucherListingReport(VoucherListingType type, Date startDate, Date endDate, Branch branch, Currency currency, Boolean isOnlyVoucher, boolean reverse);

	List<VLdto> findGenLournalListingReport(VoucherListingType type, Date startDate, Date endDate, Branch branch, Currency currency, Boolean homeCurrencyConverted,
			TransactionType tType);

	List<VLdto> findGenJournalListingReportByVoucherNo(VoucherListingType type, Date startDate, Date endDate, String fromEno, String toEno, Branch branch, Currency currency,
			Boolean homeCurrencyConverted, TransactionType tType);

}
