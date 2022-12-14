package org.hms.accounting.system.formatfile.persistence.interfaces;

import java.util.List;

import org.hms.accounting.dto.ReportFormatDto;
import org.hms.accounting.report.ReportType;
import org.hms.accounting.system.formatfile.ColType;
import org.hms.accounting.system.formatfile.FormatFile;
import org.hms.java.component.persistence.exception.DAOException;

public interface IFormatFileDAO {
	public List<FormatFile> findAll() throws DAOException;

	public List<ReportFormatDto> findByReportType(ReportType reportType) throws DAOException;

	public List<FormatFile> findByTypeAndName(String formatType, String formatName) throws DAOException;

	public List<FormatFile> findLR(String formatType, ReportType reportType) throws DAOException;

	public List<FormatFile> findOther(ColType colType,String formatType, ReportType reportType) throws DAOException;
	
}
