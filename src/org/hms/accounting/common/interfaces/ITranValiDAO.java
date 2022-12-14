package org.hms.accounting.common.interfaces;

import org.hms.accounting.system.branch.Branch;
import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.system.department.Department;
import org.hms.java.component.persistence.exception.DAOException;

public interface ITranValiDAO {

	public boolean isCurUsed(Currency cur) throws DAOException;

	public boolean isCoaUsed(String coaId) throws DAOException;

	public boolean isDepartmentUsed(Department department) throws DAOException;

	public boolean isBranchUsed(Branch branch) throws DAOException;
}
