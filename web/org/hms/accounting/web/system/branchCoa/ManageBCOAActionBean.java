package org.hms.accounting.web.system.branchCoa;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.hms.accounting.common.validation.MessageId;
import org.hms.accounting.dto.CcoaDto;
import org.hms.accounting.system.chartaccount.CurrencyChartOfAccount;
import org.hms.accounting.system.chartaccount.service.interfaces.ICcoaService;
import org.hms.accounting.system.currency.Currency;
import org.hms.accounting.system.currency.service.interfaces.ICurrencyService;
import org.hms.accounting.system.department.Department;
import org.hms.accounting.system.department.service.interfaces.IDepartmentService;
import org.hms.java.component.SystemException;
import org.hms.java.component.service.interfaces.IDataRepService;
import org.hms.java.web.common.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named(value = "ManageBCOAActionBean")
@Scope(value = "view")
public class ManageBCOAActionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICcoaService ccoaService;

	@Autowired
	private IDataRepService<CurrencyChartOfAccount> dataRepService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private ICurrencyService currencyService;

	private CurrencyChartOfAccount ccoa;
	private List<CcoaDto> ccoaDtoList;
	private List<Department> deptList;
	private List<Currency> currencyList;

	@PostConstruct
	public void init() {
		deptList = departmentService.findAllDepartment();
		currencyList = currencyService.findAllCurrency();
		createNewCcoa();
		loadData();
	}

	private void loadData() {
		ccoaDtoList = ccoaService.findAllCcoaDtos();
	}

	public void prepareUpdateCcoa(CcoaDto dto) {
		this.ccoa = dataRepService.findById(CurrencyChartOfAccount.class, dto.getId());
	}

	public void updateCcoa() {
		try {
			dataRepService.update(ccoa);
			addInfoMessage(null, MessageId.UPDATE_SUCCESS, ccoa.getCoa().getAcCode());
			createNewCcoa();
			loadData();
		} catch (SystemException ex) {
			handleSysException(ex);
		}
	}

	public void createNewCcoa() {
		ccoa = new CurrencyChartOfAccount();
	}

	public CurrencyChartOfAccount getCcoa() {
		return ccoa;
	}

	public List<Department> getDepartments() {
		return deptList;
	}

	public List<CcoaDto> getCcoaDtoList() {
		return ccoaDtoList;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;

	}
}
