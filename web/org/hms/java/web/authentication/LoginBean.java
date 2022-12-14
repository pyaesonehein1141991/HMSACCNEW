package org.hms.java.web.authentication;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpSession;

import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.process.interfaces.IUserProcessService;
import org.hms.accounting.user.User;
import org.hms.accounting.user.service.interfaces.IUserService;
import org.hms.java.web.common.BaseBean;
import org.hms.java.web.common.ParamId;

@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean extends BaseBean {
	@ManagedProperty(value = "#{UserProcessService}")
	private IUserProcessService userProcessService;

	public void setUserProcessService(IUserProcessService userProcessService) {
		this.userProcessService = userProcessService;
	}

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String authenticate() {
		boolean authenticate = userService.authenticate(username, password);
		if (authenticate) {
			User user = userService.findUser(username);
			putParam(ParamId.LOGIN_USER, user);
			userProcessService.registerUser(user);
			return "home";
		} else {
			addInfoMessage(null, MessageId.LOGIN_FAILED);
			return null;
		}
	}

	public String logout() {
		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
		session.invalidate();
		return "login";
	}

	public String editUser() {
		putParam("key", "editUser");
		return "manageUser";

	}
}
