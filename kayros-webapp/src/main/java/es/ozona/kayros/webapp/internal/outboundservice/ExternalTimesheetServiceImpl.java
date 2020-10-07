package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.infrastructure.feingclients.TimesheetService;
import es.ozona.kayros.webapp.internal.outboundservice.acl.WorkTimePeriodMapper;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

@Service("externalTimesheetService")
public class ExternalTimesheetServiceImpl implements ExternalTimesheetService {

	@Autowired
	private TimesheetService timesheetService;

	@Override
	public TimesheetResource clock(Employee employee) {

		TimesheetResource timesheet = timesheetService.clock(employee.getEmployeeId(), employee.getUsername());

		return timesheet;

	}

	@Override
	public List<WorkingTimePeriod> searchCurrentByEmployeeId(String employeeId) {

		final List<TimesheetResource> timesheets = timesheetService.search("employeeId:%s".formatted(employeeId), "+date", 1, 1000).getItems();
		
		return CollectionUtils.isEmpty(timesheets) ? new ArrayList<WorkingTimePeriod>() : CollectionUtils.lastElement(timesheets).getWorkingTimePeriods().stream() 
				.map(t -> WorkTimePeriodMapper.mapFromResource(t)).collect(Collectors.toList());
	}

	@Override
	public List<WorkingTimePeriod> searchTimesheetsByEmployeeIdBetweenDates(String startDate, String endDate, String employeeId) {
		
		final List<TimesheetResource> timesheets = timesheetService.search("( date>%s and date<%s and employeeId:%s )".formatted(startDate, endDate, employeeId), "+date", 1, 1000).getItems();
		
		return CollectionUtils.isEmpty(timesheets) ? null : CollectionUtils.lastElement(timesheets).getWorkingTimePeriods().stream() .map(t -> WorkTimePeriodMapper.mapFromResource(t)).collect(Collectors.toList());

	}

}
