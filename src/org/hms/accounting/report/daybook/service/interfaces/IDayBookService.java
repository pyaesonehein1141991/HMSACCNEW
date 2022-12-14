/**
 * 
 */
package org.hms.accounting.report.daybook.service.interfaces;

import java.util.List;

import org.hms.accounting.dto.DayBookDto;
import org.hms.accounting.dto.DayBookReportDto;
import org.hms.accounting.dto.DayBookReportDto1;

/**
 * @author Aung
 *
 */
public interface IDayBookService {
	public List<DayBookReportDto> findDayBookList(DayBookDto dto);

	public List<DayBookReportDto1> findDayBookListWithGrandTotal(DayBookDto dto);

	public DayBookReportDto getGrandTotal(List<DayBookReportDto> detailList);
}
