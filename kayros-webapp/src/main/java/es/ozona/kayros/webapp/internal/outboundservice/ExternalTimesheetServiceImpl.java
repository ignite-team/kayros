package es.ozona.kayros.webapp.internal.outboundservice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.infrastructure.feingclients.TimesheetService;
import es.ozona.kayros.webapp.internal.outboundservice.acl.TimesheetMapper;
import es.ozona.kayros.webapp.internal.outboundservice.acl.WorkTimePeriodMapper;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

@Service("externalTimesheetService")
public class ExternalTimesheetServiceImpl implements ExternalTimesheetService {

	private static final String SERVICE_DATE_FORMAT = "yyyyMMdd";

	@Autowired
	private TimesheetService timesheetService;

	@Override
	public TimesheetResource clock(Employee employee) {

		TimesheetResource timesheet = timesheetService.clock(employee.getEmployeeId(), employee.getUsername());

		return timesheet;

	}

	@Override
	public List<WorkingTimePeriod> searchCurrentByEmployeeId(String employeeId) {
		final String date = LocalDate.now().format(DateTimeFormatter.ofPattern(SERVICE_DATE_FORMAT));

		final List<TimesheetResource> timesheets = timesheetService.search(String.format("( employeeId:%s and date:%s )", employeeId, date), "+date", 1, 1000)
				.getItems();

		return CollectionUtils.isEmpty(timesheets) ? new ArrayList<WorkingTimePeriod>()
				: CollectionUtils.lastElement(timesheets).getWorkingTimePeriods().stream().map(t -> WorkTimePeriodMapper.mapFromResource(t))
						.collect(Collectors.toList());
	}

	@Override
	public List<Timesheet> searchTimesheetsByEmployeeIdBetweenDates(String startDate, String endDate, String employeeId) {

		final List<TimesheetResource> timesheets = timesheetService
				.search(String.format("( date>%s and date<%s and employeeId:%s )", startDate, endDate, employeeId), "+date", 1, 1000).getItems();

		return CollectionUtils.isEmpty(timesheets) ? null : timesheets.stream().map(t -> TimesheetMapper.mapFromResource(t)).collect(Collectors.toList());

	}

	@Override
	public Timesheet searchCurrentTimesheetByEmployeeId(String employeeId) {
		final String date = LocalDate.now().format(DateTimeFormatter.ofPattern(SERVICE_DATE_FORMAT));

		final List<TimesheetResource> timesheets = timesheetService.search(String.format("( employeeId:%s and date:%s )", employeeId, date), "+date", 1, 1000)
				.getItems();

		return CollectionUtils.isEmpty(timesheets) ? null : TimesheetMapper.mapFromResource(CollectionUtils.lastElement(timesheets));

	}

}
