package org.hms.accounting.system.setup.service;

import javax.annotation.Resource;

import org.hms.accounting.system.setup.persistence.interfaces.ISetup_HistoryDAO;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "Setup_HistoryService")
public class Setup_HistoryService extends BaseService implements ISetup_HistoryService {

	@Resource(name = "Setup_HistoryDAO")
	private ISetup_HistoryDAO setupHistoryDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public String findSetupHistoryValueByVariable(String variable, String budget) throws DAOException {
		String result = null;
		try {
			result = setupHistoryDAO.findSetupHistoryValueByVariable(variable, budget);
		} catch (DAOException de) {
			throw new SystemException(de.getErrorCode(), "Failed to handle yearly posting.", de);
		}
		return result;
	}
}
