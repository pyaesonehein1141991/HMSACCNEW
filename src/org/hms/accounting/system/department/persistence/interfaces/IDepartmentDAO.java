package org.hms.accounting.system.department.persistence.interfaces;

import java.util.List;

import org.hms.accounting.system.department.Department;
import org.hms.java.component.persistence.exception.DAOException;

public interface IDepartmentDAO {
	public List<Department> findAll() throws DAOException;
}
