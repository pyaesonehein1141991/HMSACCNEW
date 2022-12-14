/**
 * 
 */
package org.hms.accounting.report.daybook.persistence.interfaces;

import java.util.List;

import org.hms.accounting.dto.DayBookDto;
import org.hms.accounting.dto.DayBookReportDto;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.java.component.persistence.exception.DAOException;


public interface IDayBookDAO {

	public List<DayBookReportDto> findDayBookList(StringBuffer sf, DayBookDto dto,ChartOfAccount chartOfAccount) throws DAOException;

}
