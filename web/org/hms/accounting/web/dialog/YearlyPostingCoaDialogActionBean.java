package org.hms.accounting.web.dialog;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hms.accounting.dto.YPDto;
import org.hms.accounting.system.chartaccount.service.interfaces.ICoaService;
import org.hms.java.web.common.BaseBean;
import org.hms.java.web.common.ParamId;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "YearlyPostingCoaDialogActionBean")
@ViewScoped
public class YearlyPostingCoaDialogActionBean extends BaseBean {

	@ManagedProperty(value = "#{CoaService}")
	private ICoaService coaService;

	public void setCoaService(ICoaService coaService) {
		this.coaService = coaService;
	}
	
	private List<YPDto> dtoList;
	private List<YPDto> filteredDtoList;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		if (getParam(ParamId.YPDTOLIST) == null) {
			dtoList = coaService.findDtosForYearlyPosting();
			putParam("YPDTOLIST", dtoList);
		} else {
			dtoList = (List<YPDto>) getParam(ParamId.YPDTOLIST);
		}		
	}
	
	public List<YPDto> getDtoList() {
		return dtoList;
	}
	
	public List<YPDto> getFilteredDtoList() {
		return filteredDtoList;
	}

	public void setFilteredDtoList(List<YPDto> filteredDtoList) {
		this.filteredDtoList = filteredDtoList;
	}

	public void returnDto(YPDto dto) {
		PrimeFaces.current().dialog().closeDynamic(dto);
		/* RequestContext.getCurrentInstance().closeDialog(dto); */
	}
}
