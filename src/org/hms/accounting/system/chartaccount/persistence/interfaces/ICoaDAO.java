package org.hms.accounting.system.chartaccount.persistence.interfaces;

import java.util.Date;
import java.util.List;

import org.hms.accounting.dto.CoaDialogCriteriaDto;
import org.hms.accounting.dto.GenLedgerSummaryDTO;
import org.hms.accounting.dto.LiabilitiesACDto;
import org.hms.accounting.dto.YPDto;
import org.hms.accounting.system.chartaccount.AccountType;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.java.component.persistence.exception.DAOException;

public interface ICoaDAO {
	public List<ChartOfAccount> findAll() throws DAOException;

	public List<ChartOfAccount> findAllCOAByAccountCodeType() throws DAOException;

	public ChartOfAccount findByAcCode(String acCode) throws DAOException;

	public ChartOfAccount findByAcCode(String acCode, AccountType acType) throws DAOException;

	public List<ChartOfAccount> findCoaByIE() throws DAOException;

	public ChartOfAccount findByIbsbACode(String ibsbACode) throws DAOException;

	public List<String> findAllAcCode() throws DAOException;

	public List<ChartOfAccount> findAllHeadAccount() throws DAOException;

	public List<ChartOfAccount> findAllGroupAccount() throws DAOException;

	public ChartOfAccount findCoaByHeadId(String headId) throws DAOException;

	public ChartOfAccount findCoaByGroupId(String groupId) throws DAOException;

	public void updateGroupAccount(ChartOfAccount chartOfAccount, String groupId) throws DAOException;

	public List<YPDto> findDtosForYearlyPosting() throws DAOException;

	public List<ChartOfAccount> findAllCoaByCriteria(CoaDialogCriteriaDto dto) throws DAOException;

	public List<LiabilitiesACDto> findLiabilitiesACDtos() throws DAOException;

	List<GenLedgerSummaryDTO> findGenLedgerSummaryCOAList(Date startDate, Date endDate, String openingBalanceColumn, String amountColumn) throws DAOException;
	
	List<ChartOfAccount> findForIbsCode(String ibsCode) throws DAOException;

}
