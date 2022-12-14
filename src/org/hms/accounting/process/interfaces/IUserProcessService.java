package org.hms.accounting.process.interfaces;

import org.hms.accounting.user.User;

public interface IUserProcessService {
	public void registerUser(User user);
	public User getLoginUser();
}
