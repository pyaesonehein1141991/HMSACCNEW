package org.hms.accounting.system.rateinfo.service.interfaces;

import java.util.Date;
import java.util.List;

import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.system.rateinfo.RateInfo;

public interface IRateInfoService {
	public List<RateInfo> findAllRateInfo();

	public void addNewDailyRateInfo(RateInfo rateInfo);

	public List<RateInfo> findRateInfoByCurrencyID(String currencyID);

	double findCurrentRateInfo(Currency cur, Date voucherDate);
}
