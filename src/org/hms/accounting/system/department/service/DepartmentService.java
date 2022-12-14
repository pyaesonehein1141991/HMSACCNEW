package org.hms.accounting.system.department.service;

import java.util.List;

import javax.annotation.Resource;

import org.hms.accounting.system.department.Department;
import org.hms.accounting.system.department.persistence.interfaces.IDepartmentDAO;
import org.hms.accounting.system.department.service.interfaces.IDepartmentService;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.hms.java.component.service.interfaces.IDataRepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "DepartmentService")
public class DepartmentService extends BaseService implements IDepartmentService {

	@Resource(name = "DepartmentDAO")
	private IDepartmentDAO departmentDAO;

	@Resource(name = "DataRepService")
	private IDataRepService<Department> dataRepService;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Department> findAllDepartment() {
		List<Department> result = null;
		try {
			result = departmentDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of Department)", e);
		}
		return result;
	}
}