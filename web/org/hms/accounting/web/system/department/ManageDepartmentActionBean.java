package org.hms.accounting.web.system.department;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.hms.accounting.common.validation.ErrorMessage;
import org.hms.accounting.common.validation.IDataValidator;
import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.common.validation.ValidationResult;
import org.hms.accounting.system.department.Department;
import org.hms.accounting.system.department.service.interfaces.IDepartmentService;
import org.hms.java.component.SystemException;
import org.hms.java.component.service.interfaces.IDataRepService;
import org.hms.java.web.common.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named(value = "ManageDepartmentActionBean")
@Scope(value = "view")
public class ManageDepartmentActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IDataRepService<Department> dataRepService;

	@Autowired
	protected IDataValidator<Department> departmentValidator;

	private Department department;

	private List<Department> departmentList;

	@PostConstruct
	public void init() {
		rebindData();
	}

	private void rebindData() {
		departmentList = departmentService.findAllDepartment();
	}

	public String createNewDepartment() {
		return "manageNewDepartment.xhtml?faces-redirect=true";
	}

	public String prepareUpdateDepartment(Department department) {
		putParam("department", department);
		return "manageNewDepartment.xhtml?faces-redirect=true";
	}

	public String deleteDepartment(Department department) {
		ValidationResult result = departmentValidator.validate(department, true);
		if (result.isVerified()) {
			try {
				dataRepService.delete(department);
				addInfoMessage(null, MessageId.DELETE_SUCCESS, department.getdCode());
				departmentList.remove(department);
			} catch (SystemException ex) {
				handleSysException(ex);
			}

		} else {
			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(message.getId(), message.getErrorcode(), message.getParams());
			}
		}

		return null;
	}

	public Department getDepartment() {
		return department;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

}
