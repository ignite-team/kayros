package es.ozona.kayros.webapp.vms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalEmployeeService;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;
import es.ozona.kayros.webapp.shareddomain.model.WorkingTimePeriodResource;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AccountViewModel {

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	@WireVariable("externalEmployeeService")
	protected ExternalEmployeeService employeeService;

	@WireVariable("jwtDecoder")
	protected JwtDecoder jwtDecoder;

	private static final int SIGNED_IN_STATE = 1;
	private static final int SIGNED_OUT_STATE = 0;

	private int state = SIGNED_IN_STATE;

	private Employee employee;

	@Init
	public void init() {
		// obtenemos el empleado, si no lo encontramos lo creamos a partir de los datos
		// de login.
		this.setEmployee(employeeService
				.findEmployeeByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
				.orElseGet(() -> employeeService.createEmployeeFromPrincipal()));
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Employee getEmployee() {
		return employee;
	}

	@Command
	@NotifyChange("employee")
	public void setEmployee(Employee employee) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeId", employee.getEmployeeId());
		this.employee = employee;
		BindUtils.postGlobalCommand(null, null, "updateEmployee", params);
	}

	@Command
	@NotifyChange("state")
	public void clock() {
		TimesheetResource timesheet = timesheetService.clock(employee);
		WorkingTimePeriodResource tp = timesheet.getWorkingTimePeriods()
				.get(timesheet.getWorkingTimePeriods().size() - 1);
		if (tp.getFinishTime() == null) {
			state = SIGNED_IN_STATE;
		} else {
			state = SIGNED_OUT_STATE;
		}
		
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeId", employee.getEmployeeId());
		BindUtils.postGlobalCommand(null, null, "updateEmployee", params);
	}

}
