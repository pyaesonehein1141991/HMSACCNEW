package org.hms.accounting.posting.ibsclosing.service.interfaces;

import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.java.component.SystemException;

public interface IibsClosingService {

	void createIbsClosing(Branch closingBranch, Branch ibsBranch, ChartOfAccount iCOA) throws SystemException;

	void createIbsConsolidation(Branch closingBranch, Branch ibsBranch, ChartOfAccount iCOA) throws SystemException;

}
