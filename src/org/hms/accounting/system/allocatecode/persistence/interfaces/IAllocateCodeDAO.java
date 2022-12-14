package org.hms.accounting.system.allocatecode.persistence.interfaces;

import java.util.List;

import org.hms.accounting.system.allocatecode.AllocateCode;
import org.hms.java.component.persistence.exception.DAOException;

public interface IAllocateCodeDAO {
	public List<AllocateCode> findAll() throws DAOException;
	public List<AllocateCode> findAllocateCodeBy(String budgetYear)throws DAOException;
	public List<String> findCoaIDBy(String budgetYear) throws DAOException;
	public List<AllocateCode> findAllocateCodeByNative(String budgetYear)throws DAOException;
	public void deleteAllocateCodeBy(String budgetYear)throws DAOException;
	public void addAllocateCodeBy(AllocateCode allocateCode,String coaID)throws DAOException;
}
