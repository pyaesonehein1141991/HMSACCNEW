package org.hms.accounting.web.dialog;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hms.accounting.system.chartaccount.ChartOfAccount;
import org.hms.accounting.system.chartaccount.service.interfaces.ICoaService;
import org.hms.java.web.common.BaseBean;
import org.hms.java.web.common.ParamId;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "FormatFileCoaDialogActionBean")
@ViewScoped
public class FormatFileCoaDialogActionBean extends BaseBean {

	@ManagedProperty(value = "#{CoaService}")
	private ICoaService coaService;

	public void setCoaService(ICoaService coaService) {
		this.coaService = coaService;
	}

	private List<ChartOfAccount> coaList;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		if (getParam(ParamId.COAENTRY_COALIST) == null) {
			coaList = coaService.findAllCoa();
			putParam(ParamId.COAENTRY_COALIST, coaList);
		} else {
			coaList = (List<ChartOfAccount>) getParam(ParamId.COAENTRY_COALIST);
		}
	}

	public List<ChartOfAccount> getCoaList() {
		return coaList;
	}

	public void selectCoa(ChartOfAccount coa) {
		PrimeFaces.current().dialog().closeDynamic(coa);
		/* RequestContext.getCurrentInstance().closeDialog(coa); */
	}
}
