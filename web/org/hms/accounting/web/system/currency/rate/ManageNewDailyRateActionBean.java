package org.hms.accounting.web.system.currency.rate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.system.currency.service.interfaces.ICurrencyService;
import org.hms.accounting.system.rateinfo.RateInfo;
import org.hms.accounting.system.rateinfo.RateType;
import org.hms.accounting.system.rateinfo.service.interfaces.IRateInfoService;
import org.hms.java.component.SystemException;
import org.hms.java.web.common.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named(value = "ManageNewDailyRateActionBean")
@Scope(value = "view")
public class ManageNewDailyRateActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICurrencyService currencyService;

	@Autowired
	private IRateInfoService rateInfoService;

	private RateInfo rateInfo;
	private List<Currency> currencyList;
	private boolean createNew;
	private List<RateInfo> filterList;

	@PostConstruct
	public void init() {
		createNewRateInfo();
		loadCurrency();
	}

	public void createNewRateInfo() {
		createNew = true;
		rateInfo = new RateInfo();
	}

	public void loadCurrency() {
		currencyList = currencyService.findForeignCurrency();
	}

	public RateType[] getRateType() {
		return RateType.values();
	}

	public String addNewRateInfo() {
		if (ValidateRateInfo()) {
			try {
				rateInfoService.addNewDailyRateInfo(rateInfo);
				addInfoMessage(null, MessageId.INSERT_SUCCESS, rateInfo.getCurrency().getCurrencyCode());
				return "dailyRate";
			} catch (SystemException e) {
				handleSysException(e);
			}
		}
		return null;
	}

	public boolean ValidateRateInfo() {
		Date maxDate = new Date();
		Date rDate = rateInfo.getrDate();
		boolean validate = true;
		if (rateInfo.getExchangeRate().equals(BigDecimal.ZERO)) {
			addErrorMessage("dailyCurrencyRateForm" + ":exchangeRate", MessageId.REQUIRED_EXCHANGERATE);
			validate = false;
		}
		if (rDate.after(maxDate)) {
			validate = false;
			addErrorMessage(null, MessageId.DATE_EXCEEDED);

		}
		return validate;
	}

	public RateInfo getRateInfo() {
		return rateInfo;
	}

	public void setRateInfo(RateInfo rateInfo) {
		this.rateInfo = rateInfo;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public boolean isCreateNew() {
		return createNew;
	}

	public List<RateInfo> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<RateInfo> filterList) {
		this.filterList = filterList;
	}

	public Date getMaxDate() {
		return new Date();
	}

}
