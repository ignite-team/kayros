package es.ozona.kayros.webapp.vms;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
@ToClientCommand()
public class CurrentTimesheetModel {

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	private List<WorkingTimePeriod> workingTimePeriods;
	private Timesheet timesheet;

	@Init
	public void init() {

	}

	public List<WorkingTimePeriod> getWorkingTimePeriods() {

		return workingTimePeriods;

	}

	public void setWorkingTimePeriods(List<WorkingTimePeriod> workingTimePeriods) {

		this.workingTimePeriods = workingTimePeriods;

	}

	public Timesheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;

		if (this.timesheet != null) {

			setWorkingTimePeriods(timesheet.getWorkingTimePeriods());

		}

	}

	@GlobalCommand
	@NotifyChange({ "timesheet", "workingTimePeriods" })
	public void updateEmployee(@BindingParam("employeeId") String employeeId) {

		setTimesheet(timesheetService.searchCurrentTimesheetByEmployeeId(employeeId));

	}

}