package es.ozona.kayros.webapp.vms;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
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

	private static final int SIGNED_IN_STATE = 1;
	private static final int SIGNED_OUT_STATE = 0;

	private int state = SIGNED_IN_STATE;

	private Employee employee;
	private String actualLanguage;

	@Init
	public void init() {

		this.setEmployee(employeeService.findEmployeeByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
				.orElseGet(() -> employeeService.createEmployeeFromPrincipal()));

		Session session = Sessions.getCurrent();

		if (session.getAttribute(Attributes.PREFERRED_LOCALE) == null) {

			this.setActualLanguage(Executions.getCurrent().getHeader("Accept-Language").split(",")[0].replace("-", "_"));

		} else {

			this.setActualLanguage(session.getAttribute(Attributes.PREFERRED_LOCALE).toString());

		}

		System.err.println(this.getActualLanguage());

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

	public String getActualLanguage() {

		return actualLanguage;

	}

	public void setActualLanguage(String actualLanguage) {

		String finalLanguage;

		switch (actualLanguage.toLowerCase()) {
		case "es_es":

			finalLanguage = "es_ES";
			break;

		case "gl_es":

			finalLanguage = "gl_ES";
			break;

		case "en_en":

			finalLanguage = "en_EN";
			break;

		default:

			finalLanguage = "es_ES";

			break;

		}

		this.actualLanguage = finalLanguage;

	}

	@Command
	@NotifyChange("actualLanguage")
	public void changeLang(@BindingParam("lang") String lang) {

		this.setActualLanguage(lang);
		Locale preferredLocale = new Locale(this.getActualLanguage());

		Session session = Sessions.getCurrent();
		session.setAttribute(Attributes.PREFERRED_LOCALE, preferredLocale);

		Executions.getCurrent().sendRedirect("");

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
		WorkingTimePeriodResource tp = timesheet.getWorkingTimePeriods().get(timesheet.getWorkingTimePeriods().size() - 1);
		if (tp.getFinishTime() == null) {
			state = SIGNED_IN_STATE;
		} else {
			state = SIGNED_OUT_STATE;
		}

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeId", employee.getEmployeeId());
		BindUtils.postGlobalCommand(null, null, "updateEmployee", params);
	}

	@Command
	public void updateTelecommuting() {
		employee = employeeService.modifyEmployee(employee);
	}

}
