package org.hms.accounting.system.trantype.service.interfaces;

import java.util.List;

import org.hms.accounting.system.trantype.TranCode;
import org.hms.accounting.system.trantype.TranType;

public interface ITranTypeService {
	public List<TranType> findAllTranType();
	public TranType findByTransCode(TranCode tranCode);
}
