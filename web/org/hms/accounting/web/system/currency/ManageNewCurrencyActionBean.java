package org.hms.accounting.web.system.currency;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

@Named(value = "ManageNewCurrencyActionBean")
@Scope(value = "view")
public class ManageNewCurrencyActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	protected IDataValidator<Currency> currencyCodeValidator;

	@Autowired
	protected ICurrencyService currencyService;

	private boolean createNew;
	private Currency currency;
	private boolean homeCurDisable;

	private List<Currency> currencyList;

	@PostConstruct
	public void init() {
		if (isExistParam("currency")) {
			this.currency = (Currency) getParam("currency");
		} else {
			createNewCurrency();
		}

		loadCurrency();
	}

	@PreDestroy
	public void preDestroy() {
		removeParam("currency");
	}

	public void createNewCurrency() {
		createNew = true;
		currency = new Currency();
		currency.setIsHomeCur(false);
	}

	public void loadCurrency() {
		currencyList = currencyService.findAllCurrency();
		Currency currency = currencyService.findHomeCurrency();
		homeCurDisable = currency == null ? false : true;
	}

	public String addNewCurrency() {
		ValidationResult result = currencyCodeValidator.validate(currency, false);
		if (result.isVerified()) {
			try {
				currencyService.addNewCurrency(currency);
				addInfoMessage(null, MessageId.INSERT_SUCCESS, currency.getCurrencyCode());
				return "currencyCodeEntry";
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

	public String updateCurrency() {
		ValidationResult result = currencyCodeValidator.validate(currency, false);
		if (result.isVerified()) {
			try {
				currencyService.updateCurrency(currency);
				addInfoMessage(null, MessageId.UPDATE_SUCCESS, currency.getCurrencyCode());
				return "currencyCodeEntry";
			} catch (SystemException ex) {
				handleSysException(ex);
			}
		} else {
			for (ErrorMessage message : result.getErrorMeesages()) {
				addErrorMessage(message.getId(), message.getErrorcode(), message.getParams());
			}
			loadCurrency();
		}
		return null;
	}

	public Currency getCurrency() {
		return currency;
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public boolean isHomeCurDisable() {
		return homeCurDisable;
	}

}
