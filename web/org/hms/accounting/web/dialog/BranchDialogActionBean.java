package org.hms.accounting.web.dialog;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.branch.service.interfaces.IBranchService;
import org.hms.java.web.common.BaseBean;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "BranchDialogActionBean")
@ViewScoped
public class BranchDialogActionBean extends BaseBean {

	@ManagedProperty(value = "#{BranchService}")
	protected IBranchService branchService;

	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
	}
	
	private List<Branch> branchList;

	@PostConstruct
	public void init() {
		branchList = branchService.findAllBranch();
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void selectBranch(Branch branch) {
		PrimeFaces.current().dialog().closeDynamic(branch);
		/* RequestContext.getCurrentInstance().closeDialog(branch); */
	}

}