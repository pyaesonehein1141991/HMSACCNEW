package org.hms.accounting.system.chartaccount.service.interfaces;

import java.util.List;

import org.hms.accounting.dto.CoaDialogCriteriaDto;
import org.hms.accounting.dto.GenLedgerCriteria;
import org.hms.accounting.dto.GenLedgerSummaryDTO;
import org.hms.accounting.dto.LiabilitiesACDto;
import org.hms.accounting.dto.YPDto;
import org.hms.accounting.system.chartaccount.AccountType;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.java.component.SystemException;

public interface ICoaService {

	public List<ChartOfAccount> findAllCoa();

	public List<ChartOfAccount> findAllCoaByAccountCodeType();

	public List<String> findAllAcCode();

	public ChartOfAccount findCoaByAcCode(String acCode);

	public ChartOfAccount findCoaByAcCode(String acCode, AccountType acType);

	public ChartOfAccount findCoaByHeadId(String headId);

	public ChartOfAccount findCoaByGroupId(String groupId);

	public List<ChartOfAccount> findCoaByIE();

	public void addNewCoa(ChartOfAccount chartOfAccount);

	public ChartOfAccount updateChartOfAccount(ChartOfAccount chartOfAccount);

	public void deleteChartOfAccount(ChartOfAccount chartOfAccount);

	public ChartOfAccount findCoaByibsbAcCode(String ibsbACode);

	public List<ChartOfAccount> findAllHeadAccount();

	public List<ChartOfAccount> findAllGroupAccount();

	public void updateGroupAccount(ChartOfAccount chartOfAccount, String groupId);

	public List<YPDto> findDtosForYearlyPosting();

	public List<ChartOfAccount> findAllCoaByCriteria(CoaDialogCriteriaDto dto);

	public List<LiabilitiesACDto> findLiabilitiesACDtos();

	List<GenLedgerSummaryDTO> findGenLedgerSummaryCOAList(GenLedgerCriteria criteria) throws SystemException;
	
	List<ChartOfAccount> findForIbsCode(String ibsCode) throws SystemException;
}
