package org.hms.accounting.system.trantype.persistence.interfaces;

import java.util.List;

import org.hms.accounting.system.trantype.TranCode;
import org.hms.accounting.system.trantype.TranType;
import org.hms.java.component.persistence.exception.DAOException;

public interface ITranTypeDAO {
	
	public List<TranType> findAll() throws DAOException;
	
	public TranType findByTransCode(TranCode tranCode) throws DAOException;
}
