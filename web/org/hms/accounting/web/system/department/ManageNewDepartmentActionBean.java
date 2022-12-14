package org.hms.accounting.web.system.department;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.hms.accounting.common.validation.IDataValidator;
import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.system.department.Department;
import org.hms.java.component.SystemException;
import org.hms.java.component.service.interfaces.IDataRepService;
import org.hms.java.web.common.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named(value = "ManageNewDepartmentActionBean")
@Scope(value = "view")
public class ManageNewDepartmentActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IDataRepService<Department> dataRepService;

	@Autowired
	protected IDataValidator<Department> departmentValidator;

	private boolean createNew;

	private Department department;

	@PostConstruct
	public void init() {
		if (isExistParam("department")) {
			this.department = (Department) getParam("department");
		} else {
			createNewDepartment();
		}
	}

	@PreDestroy
	public void preDestroy() {
		removeParam("department");
	}

	public void createNewDepartment() {
		createNew = true;
		department = new Department();
	}

	public void prepareUpdateDepartment(Department department) {
		createNew = false;
		this.department = department;
	}

	public String addNewDepartment() {
		try {
			dataRepService.insert(department);
			addInfoMessage(null, MessageId.INSERT_SUCCESS, department.getdCode());
			return "departmentCodeEntry";
		} catch (SystemException ex) {

			handleSysException(ex);
		}
		return null;
	}

	public String updateDepartment() {
		try {
			dataRepService.update(department);
			addInfoMessage(null, MessageId.UPDATE_SUCCESS, department.getdCode());
			return "departmentCodeEntry";
		} catch (SystemException ex) {

			handleSysException(ex);
		}
		return null;
	}

	public Department getDepartment() {
		return department;
	}

	public boolean isCreateNew() {
		return createNew;
	}

}
