package org.hms.accounting.report.listingReport.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hms.accounting.dto.CashBookDTO;
import org.hms.accounting.dto.CoaListingDto;
import org.hms.accounting.dto.VLdto;
import org.hms.accounting.dto.VPFTdto;
import org.hms.accounting.dto.VPdto;
import org.hms.accounting.report.CashBookCriteria;
import org.hms.accounting.report.listingReport.persistence.interfaces.IListingReportDAO;
import org.hms.accounting.report.listingReport.service.interfaces.IListingReportService;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.web.listing.TransactionType;
import org.hms.accounting.web.listing.VoucherListingType;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "ListingReportService")
public class ListingReportService extends BaseService implements IListingReportService {

	@Resource(name = "ListingReportDAO")
	private IListingReportDAO listingReportDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CoaListingDto> findAllCoaList() {
		List<CoaListingDto> result = null;
		try {
			result = listingReportDAO.findAllCoaList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find ChartOfAccount", e);
		}
		return result;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<VLdto> findVoucherListingReport(VoucherListingType type, Date startDate, Date endDate, Branch branch, Currency currency, Boolean isOnlyVoucher, boolean reverse) {
		List<VLdto> result;
		try {
			result = listingReportDAO.findVoucherListingReport(type, startDate, endDate, branch, currency, isOnlyVoucher, reverse);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find voucher listing report)", e);
		}
		return result;
	}

	/**
	 * @see org.hms.accounting.report.listingReport.service.interfaces.IListingReportService#findCashbookListingReport(
	 *      org.hms.accounting.report.CashBookCriteria)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<CashBookDTO> findCashbookListingReport(CashBookCriteria criteria) {
		List<CashBookDTO> result;
		try {
			result = listingReportDAO.findCashbookListingReport(criteria);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find cashbook listing report)", e);
		}
		return result;
	}

	public List<VPFTdto> findFromVoucherNo(Date startDate, Date endDate, Branch branch) {
		List<VPFTdto> result = null;
		try {
			result = listingReportDAO.findFromVoucherNo(startDate, endDate, branch);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find voucherno", e);
		}
		return result;

	}

	public List<VPFTdto> findFromVoucherNoForGenJournal(VoucherListingType type, Date startDate, Date endDate, Branch branch, Currency currency, TransactionType tType) {
		List<VPFTdto> result = null;
		try {
			result = listingReportDAO.findFromVoucherNoForGenJournal(type, startDate, endDate, branch, currency, tType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find voucherno", e);
		}
		return result;

	}

	public List<VPdto> findVoucherPrintingByVoucherNo(Date startDate, Date endDate, String fromEno, String toEno) {
		List<VPdto> result = null;
		try {
			result = listingReportDAO.findVoucherPrintingByVoucherNo(startDate, endDate, fromEno, toEno);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to Find Voucher By VoucherNo", e);
		}

		return result;

	}

	public List<VPdto> findVoucherPrinting(Date startDate, Date endDate) {
		List<VPdto> result = null;
		try {
			result = listingReportDAO.findVoucherPrinting(startDate, endDate);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find voucher Printing", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<VLdto> findGenJournalListingReport(VoucherListingType type, Date startDate, Date endDate, Branch branch, Currency currency, Boolean homeCurrencyConverted,
			TransactionType tType) {
		List<VLdto> result;
		try {
			result = listingReportDAO.findGenLournalListingReport(type, startDate, endDate, branch, currency, homeCurrencyConverted, tType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find General Journal listing report)", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<VLdto> findGenJournalListingReportByVoucherNo(VoucherListingType type, Date startDate, Date endDate, String fromEno, String toEno, Branch branch, Currency currency,
			Boolean homeCurrencyConverted, TransactionType tType) {
		List<VLdto> result;
		try {
			result = listingReportDAO.findGenJournalListingReportByVoucherNo(type, startDate, endDate, fromEno, toEno, branch, currency, homeCurrencyConverted, tType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find General Journal listing report)", e);
		}
		return result;
	}

}