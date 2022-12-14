package org.hms.accounting.system.gainloss.persistence.interfaces;

import java.util.List;

import org.hms.accounting.system.gainloss.ExchangeConfig;
import org.hms.java.component.persistence.exception.DAOException;

public interface IExchangeConfigDAO {

	List<ExchangeConfig> findAllExchangeConfig() throws DAOException;

}