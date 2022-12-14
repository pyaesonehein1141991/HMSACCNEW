package org.hms.accounting.user.persistence.interfaces;

import java.util.List;

import org.hms.accounting.user.USR001;
import org.hms.accounting.user.User;
import org.hms.java.component.persistence.exception.DAOException;

public interface IUserDAO {
	public List<USR001> findAll() throws DAOException;

	public User find(String username) throws DAOException;

	public User findById(String id) throws DAOException;

	public void deleteById(String id) throws DAOException;

}
