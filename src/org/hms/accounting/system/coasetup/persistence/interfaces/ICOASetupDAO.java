package org.hms.accounting.system.coasetup.persistence.interfaces;

import java.util.List;

import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.coasetup.COASetup;
import org.hms.accounting.system.currency.Currency;
import org.hms.java.component.persistence.exception.DAOException;

public interface ICOASetupDAO {
	public COASetup findCOAByACNameAndCur(String acName, Currency currency, Branch branch) throws DAOException;

	public List<COASetup> findCOAListByACNameAndCur(String acName, Currency currency, Branch branch) throws DAOException;

	public List<COASetup> findAllCashAccount() throws DAOException;
}
