package org.hms.accounting.system.chartaccount.persistence.interfaces;

import java.math.BigDecimal;
import java.util.List;

import org.hms.accounting.dto.CCOADialogDTO;
import org.hms.accounting.dto.CcoaDto;
import org.hms.accounting.dto.EnquiryLedgerDto;
import org.hms.accounting.dto.MonthlyBudgetDto;
import org.hms.accounting.dto.MonthlyBudgetHomeCurDto;
import org.hms.accounting.dto.ObalCriteriaDto;
import org.hms.accounting.dto.ObalDto;
import org.hms.accounting.dto.YearlyBudgetDto;
import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.accounting.system.chartaccount.CurrencyChartOfAccount;
import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.system.department.Department;
import org.hms.java.component.persistence.exception.DAOException;

public interface ICcoaDAO {

	public List<CurrencyChartOfAccount> findAll() throws DAOException;

	public List<CurrencyChartOfAccount> findCCOAByCOAid(String coaid) throws DAOException;

	public CurrencyChartOfAccount findSpecificCCOA(String coaId, String currencyId, String branchId, String optionalDepartmentId) throws DAOException;

	public List<String> findAllBudgetYear() throws DAOException;

	public void updateYearlyBudget(YearlyBudgetDto dto) throws DAOException;

	public List<CcoaDto> findAllCcoaDtos() throws DAOException;

	public List<ObalDto> findObal(ObalCriteriaDto dto) throws DAOException;

	public List<CurrencyChartOfAccount> findBudgetFigure(String branchId) throws DAOException;

	public void updateCcoaByObalDtos(List<ObalDto> dtoList) throws DAOException;

	public List<CurrencyChartOfAccount> findCOAByBranchID(String branchID) throws DAOException;

	/*
	 * Enquiry Account Ledger Information
	 */
	public List<EnquiryLedgerDto> findAllEnquiryLedger(EnquiryLedgerDto dto) throws DAOException;

	/*
	 * Monthly Budget Entry
	 */
	public List<MonthlyBudgetDto> findAllMonthlyBudget(MonthlyBudgetDto dto) throws DAOException;

	/*
	 * Monthly Budget Entry
	 */
	public void updateMonthlyBudget(List<MonthlyBudgetDto> dto) throws DAOException;

	/*
	 * Monthly Budget Home Currency Entry
	 */
	public List<MonthlyBudgetHomeCurDto> findAllMonthlyBudgetHomeCur(MonthlyBudgetHomeCurDto dto) throws DAOException;

	/*
	 * Monthly Budget Home Currency Entry
	 */
	public void updateMonthlyBudgetHomeCur(List<MonthlyBudgetHomeCurDto> dtoList) throws DAOException;

	public CurrencyChartOfAccount findCCOAByIdAndBranch(String coaId, Branch branch, Currency currency) throws DAOException;

	/*
	 * Account Ledger Listing
	 */
	public BigDecimal finddblBalance(StringBuffer sf, ChartOfAccount coa, String budgetYear, Currency currency, Branch branch) throws DAOException;

	/**
	 * The method to retrieve the list of CurrencyChartOfAccount.
	 * 
	 * @param String
	 *            branchId
	 * @return {@link List} of {@link CCOADialogDTO} instances
	 */
	public List<CCOADialogDTO> findAllCCOADialogDTO(Currency currency, Branch branch) throws DAOException;

	public List<CurrencyChartOfAccount> findCOAByCurrencyID(String currencyID) throws DAOException;

	public List<ChartOfAccount> findAllCOAByAccountCodeType() throws DAOException;

	public List<CurrencyChartOfAccount> findCCOAByBranchID(String branchID) throws DAOException;

	public List<YearlyBudgetDto> findAllYearlyBudget(YearlyBudgetDto dto) throws DAOException;

	void updateByBranch(String acName, String acCode, String branchCode, Department departmentId) throws DAOException;

	List<CurrencyChartOfAccount> findCCOAforBranchClosing(String branchId) throws DAOException;

	List<CurrencyChartOfAccount> findCCOAByCOAIDAndBranchId(String coaId, String branchId) throws DAOException;

	public List<CurrencyChartOfAccount> findCCOAforHOClosing(String branchId) throws DAOException;

	public List<CurrencyChartOfAccount> findCCOAByCOAIDAndBranchIdForHOClosing(String coaId, String branchId) throws DAOException;
}
