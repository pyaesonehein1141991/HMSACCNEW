package org.hms.accounting.report.trialBalanceDetail.service;

import java.util.List;

import javax.annotation.Resource;

import org.hms.accounting.dto.TrialBalanceCriteriaDto;
import org.hms.accounting.dto.TrialBalanceReportDto;
import org.hms.accounting.report.trialBalanceDetail.persistence.interfaces.ITrialBalanceDetailDAO;
import org.hms.accounting.report.trialBalanceDetail.service.interfaces.ITrialBalanceDetailService;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aung
 *
 */
@Service(value = "TrialBalanceDetailService")
public class TrialBalanceDetailService extends BaseService implements ITrialBalanceDetailService {

	@Resource(name = "TrialBalanceDetailDAO")
	private ITrialBalanceDetailDAO trialBalanceDetailDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TrialBalanceReportDto> findTrialBalanceDetailList(TrialBalanceCriteriaDto dto) {
		List<TrialBalanceReportDto> result = null;
		try {
			result = trialBalanceDetailDAO.findTrialBalanceDetailList(dto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find COA By Branch ID.", e);
		}
		return result;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TrialBalanceReportDto> findTrialBalanceDetailListByClone(TrialBalanceCriteriaDto dto) {
		List<TrialBalanceReportDto> result = null;
		try {
			result = trialBalanceDetailDAO.findTrialBalanceDetailListByClone(dto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find COA By Branch ID.", e);
		}
		return result;

	}
}
