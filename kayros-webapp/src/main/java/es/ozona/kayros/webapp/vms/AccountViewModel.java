package es.ozona.kayros.webapp.vms;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;
import es.ozona.kayros.webapp.shareddomain.model.WorkingTimePeriodResource;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AccountViewModel {

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	private static final int SIGNED_IN_STATE = 1;
	private static final int SIGNED_OUT_STATE = 0;

	private int state = SIGNED_IN_STATE;

	@Init
	public void init() {

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Command
	@NotifyChange("state")
	public void clock() {
		TimesheetResource timesheet = timesheetService.clock("jeijo");
		WorkingTimePeriodResource tp = timesheet.getWorkingTimePeriods().get(timesheet.getWorkingTimePeriods().size() - 1);
		if (tp.getFinishTime() == null) {
			state = SIGNED_IN_STATE;
		} else {
			state = SIGNED_OUT_STATE;
		}
	}

}
