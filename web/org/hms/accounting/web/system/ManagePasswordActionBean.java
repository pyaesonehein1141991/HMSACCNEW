package org.hms.accounting.web.system;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.system.setup.service.ISetupService;
import org.hms.java.component.service.PasswordCodecHandler;
import org.hms.java.web.common.BaseBean;

@ManagedBean(name = "ManagePasswordActionBean")
@ViewScoped
public class ManagePasswordActionBean extends BaseBean {

	@ManagedProperty(value = "#{SetupService}")
	private ISetupService setupService;

	public void setSetupService(ISetupService setupService) {
		this.setupService = setupService;
	}	
	

	@ManagedProperty( value = "#{PasswordCodecHandler}")
	private PasswordCodecHandler codecHandler;

	public void setCodecHandler(PasswordCodecHandler codecHandler) {
		this.codecHandler = codecHandler;
	}
	
	private String editpassword;
	
	
	public void updatePassword() {
		String variable=codecHandler.encode(this.editpassword);
		setupService.insert(variable);
		addInfoMessage(null, MessageId.INSERT_SUCCESS, "Password");
	}

	public String getEditpassword() {
		return editpassword;
	}

	public void setEditpassword(String editpassword) {
		this.editpassword = editpassword;
	}

	


}
