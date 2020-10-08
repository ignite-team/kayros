package es.ozona.kayros.webapp.internal.outboundservice.acl;

import java.util.stream.Collectors;

import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

public class TimesheetMapper {

	private TimesheetMapper() {

	}

	public static Timesheet mapFromResource(TimesheetResource resource) {

		final Timesheet timesheet = new Timesheet();

		timesheet.setTimesheetId(resource.getTimesheetId());
		timesheet.setEmployeeId(resource.getEmployeeId());
		timesheet.setDate(resource.getDate());
		timesheet
				.setWorkingTimePeriod(resource.getWorkingTimePeriods().stream().map(t -> WorkTimePeriodMapper.mapFromResource(t)).collect(Collectors.toList()));

		return timesheet;

	}

}
