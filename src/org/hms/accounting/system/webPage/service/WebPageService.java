package org.hms.accounting.system.webPage.service;

import java.util.List;

import javax.annotation.Resource;

import org.hms.accounting.system.webPage.WebPage;
import org.hms.accounting.system.webPage.persistence.interfaces.IWebPageDAO;
import org.hms.accounting.system.webPage.service.interfaces.IWebPageService;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "WebPageService")
public class WebPageService extends BaseService implements IWebPageService {
	@Resource(name = "WebPageDAO")
	private IWebPageDAO webPageDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<WebPage> findAllWebPage() {
		List<WebPage> result = null;
		try {
			result = webPageDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of WebPages)", e);
		}
		return result;
	}
}
