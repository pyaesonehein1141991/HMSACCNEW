package org.hms.accounting.system.tlfhist.service;

import java.util.List;

import javax.annotation.Resource;

import org.hms.accounting.system.tlfhist.TLFHIST;
import org.hms.accounting.system.tlfhist.persistence.interfaces.ITLFHISTDAO;
import org.hms.accounting.system.tlfhist.service.interfaces.ITLFHISTService;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.hms.java.component.service.interfaces.IDataRepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "TLFHISTService")
public class TLFHISTService extends BaseService implements ITLFHISTService {

	@Resource(name = "TLFHISTDAO")
	private ITLFHISTDAO tlfHistDAO;

	@Resource(name = "DataRepService")
	private IDataRepService<TLFHIST> dataRepService;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<TLFHIST> findAllTLFHIST() {
		List<TLFHIST> result = null;
		try {
			result = tlfHistDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find all of TLFHIST)", e);
		}
		return result;
	}
}