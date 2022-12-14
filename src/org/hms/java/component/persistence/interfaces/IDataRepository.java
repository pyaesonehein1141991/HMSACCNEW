package org.hms.java.component.persistence.interfaces;

import org.hms.java.component.persistence.exception.DAOException;

public interface IDataRepository<T> {
	
	public void insert(Object object) throws DAOException;
	
	public T update(T param) throws DAOException;
	
	public void delete(Object object) throws DAOException;
	
	public T findById(Class<T> paramClass,Object paramObject) throws DAOException;
}
