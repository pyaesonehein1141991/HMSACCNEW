package org.hms.accounting.role.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hms.accounting.role.ROL001;
import org.hms.accounting.role.persistence.interfaces.IRoleDAO;
import org.hms.java.component.persistence.BasicDAO;
import org.hms.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("RoleDAO")
public class RoleDAO extends BasicDAO implements IRoleDAO {
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ROL001> findAll() throws DAOException {
		List<ROL001> result = null;
		try {
			StringBuffer buffer = new StringBuffer(
					"SELECT NEW " + ROL001.class.getName() + "(r.id, r.name) FROM Role r ORDER BY r.name ASC");
			Query query = em.createQuery(buffer.toString());
			result = query.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Role.", pe);
		}
		return result;
	}

}
