package org.hms.accounting.system.webPage.persistence.interfaces;

import java.util.List;

import org.hms.accounting.system.webPage.WebPage;
import org.hms.java.component.persistence.exception.DAOException;

public interface IWebPageDAO {
	public List<WebPage> findAll() throws DAOException;
}
