package org.hms.accounting.process.service;

import org.hms.accounting.process.interfaces.IUserProcessService;
import org.hms.accounting.user.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service(value = "UserProcessService")
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class UserProcessService implements IUserProcessService {

	private User user;

	@Override
	public void registerUser(User user) {
		this.user = user;
	}

	@Override
	public User getLoginUser() {
		return user;
	}
}
