package es.ozona.kayros.webapp.vms;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CurrentTimesheetModel {

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	private List<WorkingTimePeriod> workingTimePeriods;

	@Init
	public void init() {

		// buscamos los periodos del dia
	//	seachWorkingTimePeriods();
	}

	public List<WorkingTimePeriod> getWorkingTimePeriods() {
		return workingTimePeriods;
	}

	public void setWorkingTimePeriods(List<WorkingTimePeriod> workingTimePeriods) {
		this.workingTimePeriods = workingTimePeriods;
	}

	@Command
	@NotifyChange("workingTimePeriods")
	public void seachWorkingTimePeriods() {
		final ListModelList<WorkingTimePeriod> workingTimePeriods = new ListModelList<WorkingTimePeriod>();

		timesheetService.searchCurrentByEmployeeId("555B2B05-7AB4-4123-B069-3253DD7D9ACF").stream().forEach(w -> workingTimePeriods.add(w));

		setWorkingTimePeriods(workingTimePeriods);

	}

}
