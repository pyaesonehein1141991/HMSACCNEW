package org.hms.accounting.web.system.coa;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.hms.accounting.common.validation.ErrorMessage;
import org.hms.accounting.common.validation.IDataValidator;
import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.common.validation.ValidationResult;
import org.hms.accounting.system.chartaccount.AccountCodeType;
import org.hms.accounting.system.chartaccount.AccountType;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.accounting.system.chartaccount.service.interfaces.ICoaService;
import org.hms.java.component.SystemException;
import org.hms.java.web.common.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named(value = "ManageCreateNewCoaActionBean")
@Scope(value = "view")
public class ManageCreateNewCoaActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IDataValidator<ChartOfAccount> accountCodeValidator;

	@Autowired
	private ICoaService coaService;

	private boolean createNew;
	private ChartOfAccount coa;
	private List<ChartOfAccount> coaList;
	private List<ChartOfAccount> groupList;
	private List<ChartOfAccount> headList;
	private boolean headCodeDisable = false;
	private boolean groupCodeDisable = true;
	private boolean acCodeDisabled = false;

	@PostConstruct
	public void init() {
		if (isExistParam("coa")) {
			this.coa = (ChartOfAccount) getParam("coa");
			acCodeDisabled = true;
		} else {
			loadData();
			createNewCoa();
		}

	}

	@PreDestroy
	public void predistroy() {
		removeParam("coa");
	}

	public void createNewCoa() {
		acCodeDisabled = false;
		createNew = true;
		coa = new ChartOfAccount();
		coa.setAcType(AccountType.A);
		coa.setAcCodeType(AccountCodeType.DETAIL);
		loadHeadList();
		eventAcCodeType();
	}

	private void loadData() {
		coaList = coaService.findAllCoa();
		// sort();
	}

	public void loadHeadList() {
		headList = coaList.stream().filter(
				temp -> temp.getAcCodeType().equals(AccountCodeType.HEAD) && temp.getAcType().equals(coa.getAcType()))
				.collect(Collectors.toList());
	}

	public void eventAcCodeType() {
		switch (coa.getAcCodeType()) {
		case DETAIL:
			headCodeDisable = false;
			groupCodeDisable = false;
			break;
		case GROUP:
			headCodeDisable = false;
			groupCodeDisable = true;
			coa.setGroupId(null);
			break;
		case HEAD:
			headCodeDisable = true;
			groupCodeDisable = true;
			coa.setGroupId(null);
			coa.setHeadId(null);
			break;
		}

	}

	public void loadGroupList() {
		if (coa.getAcCodeType().equals(AccountCodeType.DETAIL)) {
			groupList = coaList.stream().filter(temp -> (temp.getAcCodeType().equals(AccountCodeType.GROUP)
					&& temp.getHeadId().equals(coa.getHeadId()))).collect(Collectors.toList());
			groupCodeDisable = false;
		} else {
			groupCodeDisable = true;
		}
	}

	public String addNewCoa() {
		ValidationResult result = accountCodeValidator.validate(coa, false);
		if (result.isVerified()) {
			try {
				coaService.addNewCoa(coa);
				coaList.add(coa);
				addInfoMessage(null, MessageId.INSERT_SUCCESS, coa.getAcCode());
				return "coaCodeEntry";
			} catch (SystemException ex) {
				handleSysException(ex);
			}
		} else {
			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(message.getId(), message.getErrorcode(), message.getParams());
			}
		}
		return null;
	}

	public String updateCoa() {
		ValidationResult result = accountCodeValidator.validate(coa, false);
		if (result.isVerified()) {
			try {
				coaService.updateChartOfAccount(coa);
				addInfoMessage(null, MessageId.UPDATE_SUCCESS, coa.getAcCode());
				return "coaCodeEntry";
			} catch (SystemException ex) {
				handleSysException(ex);
			}
		} else {

			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(message.getId(), message.getErrorcode(), message.getParams());
			}
		}
		return null;
	}

	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}

	public List<ChartOfAccount> getCoaList() {
		return coaList;
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public ChartOfAccount getCoa() {
		return coa;
	}

	public void setCoa(ChartOfAccount coa) {
		this.coa = coa;
	}

	public AccountType[] getAcTypes() {
		return AccountType.values();
	}

	public AccountCodeType[] getAccountCodeTypes() {
		return AccountCodeType.values();
	}

	public List<ChartOfAccount> getGroupList() {
		return groupList;
	}

	public List<ChartOfAccount> getHeadList() {
		return headList;
	}

	public boolean isHeadCodeDisable() {
		return headCodeDisable;
	}

	public boolean isGroupCodeDisable() {
		return groupCodeDisable;
	}

	public boolean isAcCodeDisabled() {
		return acCodeDisabled;
	}

}
