package es.ozona.kayros.webapp.vms;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
@ToClientCommand()
public class CurrentTimesheetModel {

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	private List<Timesheet> timesheets;

	public List<Timesheet> getTimesheets() {

		return timesheets;

	}

	public void setTimesheets(List<Timesheet> timesheets) {

		this.timesheets = timesheets;

	}

	@GlobalCommand()
	@NotifyChange("timesheets")
	public void updateEmployee(@BindingParam("employeeId") String employeeId) {

		setTimesheets(timesheetService.searchTimesheetsByEmployeeId(employeeId));

	}

}
