package org.hms.accounting.posting.service;

import java.util.Date;

import javax.annotation.Resource;

import org.hms.accounting.common.utils.DateUtils;
import org.hms.accounting.posting.persistence.interfaces.IDataCloningDAO;
import org.hms.accounting.posting.service.interfaces.IDataCloningService;
import org.hms.accounting.system.chartaccount.CcoaHistory;
import org.hms.accounting.system.chartaccount.CurrencyChartOfAccount;
import org.hms.accounting.system.chartaccount.service.interfaces.ICcoaService;
import org.hms.accounting.system.chartaccount.service.interfaces.ICoaService;
import org.hms.accounting.system.systempost.SystemPost;
import org.hms.accounting.system.systempost.service.interfaces.ISystemPostService;
import org.hms.accounting.system.tlf.TLF;
import org.hms.accounting.system.tlf.service.interfaces.ITLFService;
import org.hms.accounting.system.tlfhist.TLFHIST;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;
import org.hms.java.component.service.BaseService;
import org.hms.java.component.service.interfaces.IDataRepService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "DataCloningService")
public class DataCloningService extends BaseService implements IDataCloningService {
	@Resource(name = "DataRepService")
	private IDataRepService<SystemPost> systemPostDataService;

	@Resource(name = "SystemPostService")
	private ISystemPostService systemPostService;

	@Resource(name = "TLFService")
	private ITLFService tlfService;

	@Resource(name = "DataRepService")
	private IDataRepService<TLF> tlfDataService;

	@Resource(name = "DataRepService")
	private IDataRepService<TLFHIST> tlfHistDataService;

	@Resource(name = "CcoaService")
	private ICcoaService ccoaService;

	@Resource(name = "DataRepService")
	private IDataRepService<CcoaHistory> ccoaHistDataService;

	@Resource(name = "DataRepService")
	private IDataRepService<CurrencyChartOfAccount> ccoaDataService;

	@Resource(name = "CoaService")
	private ICoaService coaService;

	@Resource(name = "DataCloningDAO")
	private IDataCloningDAO dataCloningDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public void handleDataCloning(Date postingDate) {
		try {
			postingDate = DateUtils.resetEndDate(postingDate);
			dataCloningDAO.insertCCOAClone();
			dataCloningDAO.insertTLFCLONEByPostingDate(postingDate);

		} catch (DAOException de) {
			throw new SystemException(de.getErrorCode(), "Failed to handle yearly posting.", de);
		}
	}

}