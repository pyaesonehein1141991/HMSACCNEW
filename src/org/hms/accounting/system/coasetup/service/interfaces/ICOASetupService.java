package org.hms.accounting.system.coasetup.service.interfaces;

import java.util.List;

import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.coasetup.COASetup;
import org.hms.accounting.system.currency.Currency;

public interface ICOASetupService {
	public COASetup findCOASetup(String acType, Currency currency, Branch branch);

	public List<COASetup> findCOASetupList(String acType, Currency currency, Branch branch);

	public List<COASetup> findAllCashAccount();
}
