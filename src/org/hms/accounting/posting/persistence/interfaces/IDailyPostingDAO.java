package org.hms.accounting.posting.persistence.interfaces;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hms.accounting.dto.dailyPostingDto;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.accounting.system.currency.Currency;
import org.hms.java.component.persistence.exception.DAOException;

public interface IDailyPostingDAO {
	public List<dailyPostingDto> findTlfDetailAccountList(Date curStartDate, Date curEndDate, Branch branch) throws DAOException;

	public void updateMFieldLocalAmount(StringBuffer sf, BigDecimal localAmount, ChartOfAccount coa, Currency currency, Branch branch) throws DAOException;

	public void updateMSRCFieldHomeAmount(StringBuffer sf, BigDecimal homeAmount, ChartOfAccount coa, Currency currency, Branch branch) throws DAOException;

	public void updateMFiledOpeningAmount(StringBuffer sf, Branch branch) throws DAOException;

	public void updateMSRCFiledOpeningAmount(StringBuffer sf, Branch branch) throws DAOException;

}
