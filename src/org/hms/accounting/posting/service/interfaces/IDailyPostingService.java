package org.hms.accounting.posting.service.interfaces;

import java.util.Date;

import org.hms.accounting.system.branch.Branch;
import org.hms.java.component.SystemException;

public interface IDailyPostingService {
	public void processDailyPosting(Branch branch, Date postingDate) throws SystemException;
}
