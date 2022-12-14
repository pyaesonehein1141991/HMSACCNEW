package org.hms.accounting.system.setup.service;

import org.hms.java.component.persistence.exception.DAOException;

public interface ISetup_HistoryService {
	public String findSetupHistoryValueByVariable(String variable, String budget) throws DAOException;
}
