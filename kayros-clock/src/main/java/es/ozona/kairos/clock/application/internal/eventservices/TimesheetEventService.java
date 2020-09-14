package es.ozona.kairos.clock.application.internal.eventservices;

import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.infrastructure.brokers.TimesheetEventSource;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetClockedInEvent;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetClockedOutEvent;
import es.ozona.kairos.clock.shareddomain.model.events.UnregisteredTimesheetClockedInEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceService;

public interface TimesheetEventService extends BaseEventSourceService<Timesheet, TimesheetEventSource> {

	void handleClockIn(TimesheetClockedInEvent timesheetClockInEvent);

	void handleClockOut(TimesheetClockedOutEvent timesheetClockOutEvent);

	void handleUnregisteredClockIn(UnregisteredTimesheetClockedInEvent unregisteredTimesheetedClockOutEvent);

}
