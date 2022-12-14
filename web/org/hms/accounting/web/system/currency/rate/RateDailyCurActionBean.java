package org.hms.accounting.web.system.currency.rate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.system.currency.service.interfaces.ICurrencyService;
import org.hms.accounting.system.rateinfo.RateInfo;
import org.hms.accounting.system.rateinfo.RateType;
import org.hms.accounting.system.rateinfo.service.interfaces.IRateInfoService;
import org.hms.java.web.common.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named(value = "RateDailyCurActionBean")
@Scope(value = "view")
public class RateDailyCurActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICurrencyService currencyService;

	@Autowired
	private IRateInfoService rateInfoService;

	private List<Currency> currencyList;
	private List<RateInfo> rateInfoList;
	private List<RateInfo> filterList;

	@PostConstruct
	public void init() {
		loadCurrency();
		rebindData();
	}

	public void loadCurrency() {
		currencyList = currencyService.findForeignCurrency();
	}

	public String createNewRate() {
		return "manageNewDailyRateActionBean.xhtml?faces-redirect=true";
	}

	public void rebindData() {
		rateInfoList = rateInfoService.findAllRateInfo();
	}

	public RateType[] getRateType() {
		return RateType.values();
	}

	public List<RateInfo> getRateInfoList() {
		return rateInfoList;
	}

	public List<RateInfo> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<RateInfo> filterList) {
		this.filterList = filterList;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public Date getMaxDate() {
		return new Date();
	}
}
