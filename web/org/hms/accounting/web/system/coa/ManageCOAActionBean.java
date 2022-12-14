package org.hms.accounting.web.system.coa;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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

@Named(value = "ManageCOAActionBean")
@Scope(value = "view")
public class ManageCOAActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IDataValidator<ChartOfAccount> accountCodeValidator;

	@Autowired
	private ICoaService coaService;

	private List<ChartOfAccount> coaList;

	@PostConstruct
	public void init() {
		loadData();
	}

	public String createNewCoa() {
		return "manageNewChartOfAccount.xhtml?faces-redirect=true";
	}

	private void loadData() {
		coaList = coaService.findAllCoa();
	}

	public String prepareUpdateCoa(ChartOfAccount coa) {
		putParam("coa", coa);
		return "manageNewChartOfAccount.xhtml?faces-redirect=true";
	}

	public String deleteCoa(ChartOfAccount coa) {
		ValidationResult result = accountCodeValidator.validate(coa, true);
		if (result.isVerified()) {
			try {
				coaService.deleteChartOfAccount(coa);
				coaList.remove(coa);
				addInfoMessage(null, MessageId.DELETE_SUCCESS, coa.getAcCode());
				createNewCoa();
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

	/* Getter & Setter */

	public List<ChartOfAccount> getCoaList() {
		return coaList;
	}

	public AccountType[] getAcTypes() {
		return AccountType.values();
	}

	public AccountCodeType[] getAccountCodeTypes() {
		return AccountCodeType.values();
	}

}
