package org.hms.accounting.report.cleanCashReport.service.interfaces;

import java.util.Map;

import org.hms.accounting.report.ReportCriteria;
import org.hms.accounting.report.cleanCashReport.CleanCashReport;

public interface ICleanCashService {
	/**
	 * Interface to find CleanCashReport for the given criteria parameter.
	 * 
	 * @param ReportCriteria[Criteria
	 *            of user selection].
	 * 
	 * @return Map<String, CleanCashReport>[Map of cleanCashReport with account
	 *         code key and CleanCashReport value].
	 * 
	 * @throws SystemException.
	 * 
	 */
	public Map<String, CleanCashReport> findCleanCashReport(ReportCriteria criteria);
}
