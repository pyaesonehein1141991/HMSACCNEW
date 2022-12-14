package org.hms.accounting.posting.persistence.interfaces;

import java.util.Date;

import org.hms.accounting.dto.YPDto;
import org.hms.accounting.system.branch.Branch;
import org.hms.java.component.SystemException;
import org.hms.java.component.persistence.exception.DAOException;

public interface IYearlyPostingDAO {

	public void moveTlfToHistory(Date postingDate) throws DAOException;

	public void insertTLFHISTByPostingDate(Date postingDate) throws DAOException;

	public void deleteTLFByPostingDate(Date postingDate) throws DAOException;

	public void moveCcoaToHistory(String budgetYear, YPDto plAc, Branch branch) throws DAOException;

	public void insertCcoaHistory() throws DAOException;

	public void updateCcoaBal() throws DAOException;

	public void updateCcoaBalByTacAndPlAcAndACTYPE(YPDto plAc) throws DAOException;

	public void updateCcoaAllZeroAndBudgetYear(String budgetYear) throws DAOException;

	public void increaseBudgetYear() throws DAOException;

	public void moveSetupToHistory() throws DAOException;

	public void increaseSetupBudgetYear() throws DAOException;

	public void createHOClosingForManulProcess(String branchID, Branch ibsBranch, YPDto plAc) throws SystemException;
}
