package org.hms.accounting.web.system.currency;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.hms.accounting.common.validation.ErrorMessage;
import org.hms.accounting.common.validation.IDataValidator;
import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.common.validation.ValidationResult;
import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.system.currency.service.interfaces.ICurrencyService;
import org.hms.java.component.SystemException;
import org.hms.java.web.common.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named(value = "ManageCurrencyActionBean")
@Scope(value = "view")
public class ManageCurrencyActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	protected IDataValidator<Currency> currencyCodeValidator;

	@Autowired
	protected ICurrencyService currencyService;

	private boolean createNew;

	private List<Currency> currencyList;

	@PostConstruct
	public void init() {
		loadCurrency();
	}

	public String createNewCurrency() {
		return "manageNewCurrency.xhtml?faces-redirect=true";
	}

	public void loadCurrency() {
		currencyList = currencyService.findAllCurrency();
	}

	public String prepareUpdateCurrency(Currency currency) {
		putParam("currency", currency);
		return "manageNewCurrency.xhtml?faces-redirect=true";

	}

	public String deleteCurrency(Currency currency) {
		ValidationResult result = currencyCodeValidator.validate(currency, true);
		if (result.isVerified()) {
			try {
				currencyService.deleteCurrency(currency);
				addInfoMessage(null, MessageId.DELETE_SUCCESS, currency.getCurrencyCode());
				createNewCurrency();
				loadCurrency();
			} catch (SystemException ex) {
				handleSysException(ex);
			}
		} else {
			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(null, message.getErrorcode(), message.getParams());
			}
		}
		return null;
	}

	/* Getter & Setter */

	public boolean isCreateNew() {
		return createNew;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

}
