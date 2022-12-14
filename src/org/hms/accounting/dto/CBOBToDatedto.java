package org.hms.accounting.dto;

import java.math.BigDecimal;

import org.hms.accounting.system.chartaccount.AccountType;

public class CBOBToDatedto {
	private BigDecimal debit;
	private BigDecimal credit;
	private AccountType acType;

	public CBOBToDatedto(BigDecimal debit, BigDecimal credit, AccountType acType) {
		this.debit = debit;
		this.credit = credit;
		this.acType = acType;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public AccountType getAcType() {
		return acType;
	}

	public void setAcType(AccountType acType) {
		this.acType = acType;
	}

}
