package org.hms.accounting.system.setup.service;

import org.hms.java.component.persistence.exception.DAOException;

public interface ISetupService {
	public String findSetupValueByVariable(String variable) throws DAOException;

	public void insert(String variable) throws DAOException;
}
