package org.hms.accounting.role.persistence.interfaces;

import java.util.List;

import org.hms.accounting.role.ROL001;
import org.hms.java.component.persistence.exception.DAOException;

public interface IRoleDAO {
	public List<ROL001> findAll() throws DAOException;
}
