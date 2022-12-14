/**
 * 
 */
package org.hms.accounting.report.daybook.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hms.accounting.common.utils.BusinessUtil;
import org.hms.accounting.common.utils.DateUtils;
import org.hms.accounting.dto.DayBookDto;
import org.hms.accounting.dto.DayBookReportDto;
import org.hms.accounting.report.daybook.persistence.interfaces.IDayBookDAO;
import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.java.component.persistence.BasicDAO;
import org.hms.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aung
 *
 */
@Repository("DayBookDAO")
public class DayBookDAO extends BasicDAO implements IDayBookDAO {

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<DayBookReportDto> findDayBookList(StringBuffer sf, DayBookDto dto,ChartOfAccount ccoa) throws DAOException {
		List<DayBookReportDto> result = null;
		try {
			Query q = em.createQuery(sf.toString());
			q.setParameter("cashAccount", ccoa);
			q.setParameter("requiredDate",DateUtils.resetStartDate( dto.getRequiredDate()));
			if (dto.getCurrency() != null) {
				q.setParameter("currency", dto.getCurrency());
			}
			if (dto.getBranch() != null) {
				q.setParameter("branch", dto.getBranch());
			}
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to findDayBookList", pe);
		}
		return result;
	}


}
