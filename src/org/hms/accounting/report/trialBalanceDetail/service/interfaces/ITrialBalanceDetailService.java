package org.hms.accounting.report.trialBalanceDetail.service.interfaces;

import java.util.List;

import org.hms.accounting.dto.TrialBalanceCriteriaDto;
import org.hms.accounting.dto.TrialBalanceReportDto;

public interface ITrialBalanceDetailService {

	public List<TrialBalanceReportDto> findTrialBalanceDetailList(TrialBalanceCriteriaDto dto);

	public List<TrialBalanceReportDto> findTrialBalanceDetailListByClone(TrialBalanceCriteriaDto dto);
}
