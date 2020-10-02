package es.ozona.kayros.webapp.vms;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
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
	
	@WireVariable("authorizedClientService")
	protected OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
	
	private static final int SIGNED_IN_STATE = 1;
	private static final int SIGNED_OUT_STATE = 0;

	private int state = SIGNED_IN_STATE;

	private Employee employee;

	@Init
	public void init() {
//		OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) SecurityContextHolder.getContext()
//				.getAuthentication();
//		
//		OAuth2AccessToken accessToken = (OAuth2AccessToken) oAuth2AuthorizedClientService.loadAuthorizedClient(
//				auth.getAuthorizedClientRegistrationId()
//	            ,auth.getName()
//	            ).getAccessToken();
//		
//		employeeService.findEmployeeByUsername(auth.getName()).ifPresentOrElse(v -> this.employee = v, new Runnable() {
//			@Override
//			public void run() {
//				employee = new Employee();
//				
//			}
//		});
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

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Command
	@NotifyChange("state")
	public void clock() {
		TimesheetResource timesheet = timesheetService.clock("jeijo");
		WorkingTimePeriodResource tp = timesheet.getWorkingTimePeriods()
				.get(timesheet.getWorkingTimePeriods().size() - 1);
		if (tp.getFinishTime() == null) {
			state = SIGNED_IN_STATE;
		} else {
			state = SIGNED_OUT_STATE;
		}
	}

}
