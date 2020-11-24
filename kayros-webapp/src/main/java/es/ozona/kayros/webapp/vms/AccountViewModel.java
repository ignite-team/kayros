package es.ozona.kayros.webapp.vms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.Locales;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalEmployeeService;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AccountViewModel {

	private static final Logger LOG = LoggerFactory.getLogger(AccountViewModel.class);

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	@WireVariable("externalEmployeeService")
	protected ExternalEmployeeService employeeService;

	private Employee employee;

	private Locale locale;

	private long timeout;
	private boolean status;

	@Init
	public void init() {

		try {

			this.setEmployee(employeeService.findEmployeeByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
					.orElseGet(() -> employeeService.createEmployeeFromPrincipal()));

		} catch (Exception e) {

			if (LOG.isWarnEnabled()) {

				LOG.warn("El servicio Employee no está disponible.");

			}

			throw e;

		}

		Session session = Sessions.getCurrent();

		if (session.getAttribute(Attributes.PREFERRED_LOCALE) == null) {

			this.setLocale(employee.getPreferredLanguage());

		} else {

			this.setLocale(session.getAttribute(Attributes.PREFERRED_LOCALE).toString());

		}

		updatePageLocale(false);

	}

	public Employee getEmployee() {

		return employee;

	}

	public Locale getLocale() {

		return this.locale;

	}

	public void setLocale(String newStringLocale) {

		String language = newStringLocale.split("_")[0];
		String country = newStringLocale.split("_")[1].toUpperCase();

		this.locale = new Locale(language, country);

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
	@NotifyChange("locale")
	public void changeLang(@BindingParam("lang") String lang) {

		this.setLocale(lang);

		employee.setPreferredlanguage(this.locale.toString());
		employeeService.modifyEmployee(this.employee);

		updatePageLocale(true);

	}

	private void updatePageLocale(boolean forceRedirect) {

		try {

<<<<<<< HEAD
=======
			Locale preferredLocale = new Locale(this.getActualLanguage().split("_")[0],this.getActualLanguage().split("_")[1]);

>>>>>>> d165c5ba5291eb86306e6766e6c55951f1007aae
			Session session = Sessions.getCurrent();

			session.setAttribute(Attributes.PREFERRED_LOCALE, this.locale);

			if (forceRedirect) {

				Executions.getCurrent().sendRedirect("");

			} else {

				Clients.reloadMessages(this.locale);
				Locales.setThreadLocal(this.locale);

			}

			if (LOG.isDebugEnabled()) {

				LOG.debug("Idioma preferido {} establecido con exito.", this.locale);

			}

		} catch (IOException e) {

			if (LOG.isWarnEnabled()) {

				LOG.warn("No se ha podido cargar las preferecias de idioma del empleado.", e);

			}

			Executions.getCurrent().sendRedirect("");

		}

	}

	@Command
	@NotifyChange("employee")
	public void setEmployee(Employee employee) {

		final Map<String, Object> params = new HashMap<String, Object>();

		params.put("employeeId", employee.getEmployeeId());

		this.employee = employee;

		BindUtils.postGlobalCommand(null, null, "updateEmployee", params);

		params.put("language", this.employee.getPreferredLanguage().split("_")[0]);

		BindUtils.postGlobalCommand(null, null, "updateCalendar", params);

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
