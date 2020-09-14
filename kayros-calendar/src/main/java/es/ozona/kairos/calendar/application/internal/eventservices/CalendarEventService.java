package es.ozona.kairos.calendar.application.internal.eventservices;

import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.infrastructure.brokers.CalendarEventSource;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarYearModifiedEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceService;

public interface CalendarEventService extends BaseEventSourceService<Calendar, CalendarEventSource> {

	void handleCalendarCreatedEvent(CalendarCreatedEvent calendarCreatedEvent);

	void handleCalendarYearModifiedEvent(CalendarYearModifiedEvent calendarYearModifiedEvent);

	void handleCalendarDeletedEvent(CalendarDeletedEvent calendarDeletedEvent);

}