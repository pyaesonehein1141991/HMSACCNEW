package org.hms.accounting.posting.service.interfaces;

import java.util.Date;

import org.hms.accounting.dto.YPDto;
import org.hms.accounting.system.branch.Branch;

public interface IYearlyPostingService {
	public void handleYearlyPosting(Date postingDate, YPDto plDto, Branch branch);

	// void createHOClosingForManulProcess(String branchID, Branch ibsBranch,
	// YPDto plAc) throws SystemException;
}
