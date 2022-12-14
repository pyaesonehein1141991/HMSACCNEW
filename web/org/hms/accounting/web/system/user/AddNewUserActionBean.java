package org.hms.accounting.web.system.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.hms.accounting.common.ContactInfo;
import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.role.Role;
import org.hms.accounting.user.User;
import org.hms.accounting.user.service.interfaces.IUserService;
import org.hms.java.component.SystemException;
import org.hms.java.web.common.BaseBean;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named(value = "AddNewUserActionBean")
@Scope(value = "view")
public class AddNewUserActionBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;

	private String userId;
	private boolean createNew;
	private User user;

	@PostConstruct
	public void init() {
		userId = (String) getParam("UserId");
		if (userId == null) {
			createNewUser();
		} else {
			createNew = false;
			user = userService.findById(userId);
			if (user.getContactInfo() == null) {
				user.setContactInfo(new ContactInfo());
			}
			user.setPassword(userService.getDecodedPassword(user));
		}
	}

	public void createNewUser() {
		createNew = true;
		user = new User();
		removeParam("UserId");
	}

	public String addNewUser() {
		try {
			return "addNewUser";
		} catch (SystemException ex) {
			handleSysException(ex);
		}
		return "";
	}

	public void saveUser() {
		try {
			if (createNew) {
				userService.addNewUser(user);
			} else {
				userService.updateUser(user);
			}
			addInfoMessage(null, MessageId.INSERT_SUCCESS, user.getUserCode());
			createNewUser();
		} catch (SystemException ex) {
			handleSysException(ex);
		}
	}

	public String navManageUser() {
		return "manageUser";
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public User getUser() {
		return user;
	}

	@SuppressWarnings("rawtypes")
	public void returnRole(SelectEvent event) {
		Role role = (Role) event.getObject();
		user.setRole(role);
	}

	@PreDestroy
	public void destroy() {
		removeParam("UserId");
	}

}
