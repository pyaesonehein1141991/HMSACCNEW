package org.hms.accounting.web.system;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.user.User;
import org.hms.accounting.user.service.interfaces.IUserService;
import org.hms.java.component.SystemException;
import org.hms.java.web.common.BaseBean;

@ManagedBean(name = "ChangePasswrodActionBean")
public class ChangePasswrodActionBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private User user;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	@PostConstruct
	public void init() {
		user = (user == null) ? (User) getParam("LoginUser") : user;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void changePassword() {
		try {
			if (!newPassword.equals(confirmPassword)) {
				addInfoMessage(null, MessageId.DOES_NOT_MATCH_CONFIRM_PASSWORD);
			} else {
				userService.changePassword(user.getUserCode(), newPassword, oldPassword);
				addInfoMessage(null, MessageId.SUCCESS_CHANGE_PASSWORD);
			}
		} catch (SystemException ex) {
			if (ex.getErrorCode().equals(MessageId.OLD_PASSWORD_DOES_NOT_MATCH)) {
				addInfoMessage(null, MessageId.OLD_PASSWORD_DOES_NOT_MATCH);
			} else {
				handleException(ex);
			}
		}
	}
}
