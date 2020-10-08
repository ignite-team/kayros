package es.ozona.kayros.webapp.vms;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
@ToClientCommand()
public class CurrentTimesheetModel {

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	private List<WorkingTimePeriod> workingTimePeriods;
	


	@Init
	public void init() {

		// buscamos los periodos del dia
		// seachWorkingTimePeriods();
	}

	public List<WorkingTimePeriod> getWorkingTimePeriods() {
		return workingTimePeriods;
	}

	public void setWorkingTimePeriods(List<WorkingTimePeriod> workingTimePeriods) {
		this.workingTimePeriods = workingTimePeriods;
	}

	@GlobalCommand()
	@NotifyChange("workingTimePeriods")
	public void updateEmployee(@BindingParam("employeeId") String employeeId) {
		final ListModelList<WorkingTimePeriod> workingTimePeriods = new ListModelList<WorkingTimePeriod>();

		timesheetService.searchCurrentByEmployeeId(employeeId).stream()
				.forEach(w -> workingTimePeriods.add(w));

		setWorkingTimePeriods(workingTimePeriods);

	}

}