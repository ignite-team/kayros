package es.ozona.kayros.webapp.vms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalEmployeeService;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;
import es.ozona.kayros.webapp.vms.filters.WorkingTimePeriodFilter;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EditorViewModel extends CrudViewModel<WorkingTimePeriod, WorkingTimePeriodFilter> {

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	@WireVariable("externalEmployeeService")
	protected ExternalEmployeeService employeeService;
	
	private Employee employee;
	
	@Override
	@Init
	public void init() {		
		this.setEmployee(employeeService.findEmployeeByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
				.orElseGet(() -> employeeService.createEmployeeFromPrincipal()));
		super.init();
	}
	
	public EditorViewModel() throws InstantiationException, IllegalAccessException {
		super();
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public void initFilters() {
		
	}

	@Override
	public List<WorkingTimePeriod> findAll(String rsql) {
		final Timesheet currentTimesheet = timesheetService.searchCurrentTimesheetByEmployeeId(employee.getEmployeeId());
		return currentTimesheet == null? new ArrayList<>(): currentTimesheet.getWorkingTimePeriods();
	}

	@Override
	public WorkingTimePeriod saveEntity(WorkingTimePeriod entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntity(WorkingTimePeriod entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doAfterModalWindow(WorkingTimePeriod entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void afterEditing(WorkingTimePeriod item) {
		item.setEditedFinishTime(true);
		super.afterEditing(item);
	}
}
