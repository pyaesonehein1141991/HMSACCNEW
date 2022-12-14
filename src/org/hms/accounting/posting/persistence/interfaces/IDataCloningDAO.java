package org.hms.accounting.posting.persistence.interfaces;

import java.util.Date;

import org.hms.java.component.persistence.exception.DAOException;

public interface IDataCloningDAO {

	public void insertCCOAClone() throws DAOException;

	public void insertTLFCLONEByPostingDate(Date postingDate) throws DAOException;

}
