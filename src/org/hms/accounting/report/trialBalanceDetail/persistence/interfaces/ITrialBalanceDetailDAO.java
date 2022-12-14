package org.hms.accounting.report.trialBalanceDetail.persistence.interfaces;

import java.util.List;

import org.hms.accounting.dto.TrialBalanceCriteriaDto;
import org.hms.accounting.dto.TrialBalanceReportDto;
import org.hms.java.component.persistence.exception.DAOException;

public interface ITrialBalanceDetailDAO {

	public List<TrialBalanceReportDto> findTrialBalanceDetailList(TrialBalanceCriteriaDto dto) throws DAOException;

	public List<TrialBalanceReportDto> findTrialBalanceDetailListByClone(TrialBalanceCriteriaDto dto) throws DAOException;
}
