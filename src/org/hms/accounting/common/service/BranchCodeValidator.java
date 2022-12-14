package org.hms.accounting.common.service;

import javax.annotation.Resource;

import org.hms.accounting.common.interfaces.ITranValiDAO;
import org.hms.accounting.common.validation.IDataValidator;
import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.common.validation.ValidationResult;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.branch.persistence.interfaces.IBranchDAO;
import org.springframework.stereotype.Service;

@Service(value = "BranchCodeValidator")
public class BranchCodeValidator implements IDataValidator<Branch> {
	@Resource(name = "TranValiDAO")
	private ITranValiDAO tranValiDAO;

	@Resource(name = "BranchDAO")
	private IBranchDAO branchDAO;

	@Override
	public ValidationResult validate(Branch branch, boolean transaction) {
		ValidationResult result = new ValidationResult();
		String formId = "branchEntryForm";
		if (transaction) {
			if (tranValiDAO.isBranchUsed(branch)) {
				result.addErrorMessage(formId + ":panelBranch", MessageId.BRANCH_USED_TRANS);
			}
		}
		return result;
	}
}
