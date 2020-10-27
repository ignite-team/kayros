package es.ozona.kayros.webapp.vms;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
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

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AccountViewModel {

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	@WireVariable("externalEmployeeService")
	protected ExternalEmployeeService employeeService;

	private Employee employee;
	private String actualLanguage;

	private long timeout;
	private boolean status;

	@Init
	public void init() {

		this.setEmployee(employeeService.findEmployeeByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
				.orElseGet(() -> employeeService.createEmployeeFromPrincipal()));

		Session session = Sessions.getCurrent();

		if (session.getAttribute(Attributes.PREFERRED_LOCALE) == null) {

			this.setActualLanguage(employee.getPreferredLanguage());

			Locale preferredLocale = new Locale(this.getActualLanguage());

			session.setAttribute(Attributes.PREFERRED_LOCALE, preferredLocale);

			Executions.getCurrent().sendRedirect("");

		} else {

			this.setActualLanguage(session.getAttribute(Attributes.PREFERRED_LOCALE).toString());

		}

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

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Command
	@NotifyChange("actualLanguage")
	public void changeLang(@BindingParam("lang") String lang) {

		this.setActualLanguage(lang);

		employee.setPreferredlanguage(actualLanguage);
		employeeService.modifyEmployee(this.employee);

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
	public void clock() {

		timesheetService.clock(employee);

		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("employeeId", employee.getEmployeeId());
		BindUtils.postGlobalCommand(null, null, "updateEmployee", params);

	}

	@Command
	@NotifyChange({ "timeout" })
	public void resetTimeout() {

		this.setTimeout(0);

	}

	@Command
	public void updateTelecommuting() {
		employee = employeeService.modifyEmployee(employee);
	}

	@GlobalCommand
	@NotifyChange({ "timeout", "status" })
	public void updateStatus(@BindingParam("status") Boolean status, @BindingParam("timeToTimeout") long timeToTimeout) {

		this.status = status;
		this.timeout = timeToTimeout;

	}

}
