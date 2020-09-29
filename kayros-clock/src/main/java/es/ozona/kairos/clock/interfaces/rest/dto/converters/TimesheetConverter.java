package es.ozona.kairos.clock.interfaces.rest.dto.converters;

import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;

import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.interfaces.rest.dto.TimesheetResource;

public class TimesheetConverter extends AbstractConverter<Timesheet, TimesheetResource> {

	@Override
	protected TimesheetResource convert(Timesheet source) {
		
		if (source == null) return null;
		
		final TimesheetResource timesheetResource = new TimesheetResource();
		timesheetResource.setDate(source.getDate());
		timesheetResource.setTimesheetId(source.getTimesheetId().getTimesheetId());
		timesheetResource.setEmployeeId(source.getEmployeeId().getEmployeeId());
		
		timesheetResource.setWorkingTimePeriods(source.getWorkingTimePeriod().stream().map(m -> new WorkingTimePeriodConverter().convert(m)).collect(Collectors.toList()));
		
		return timesheetResource;
	}

}
