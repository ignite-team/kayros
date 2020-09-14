package es.ozona.kairos.calendar.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.micro.core.infrastructure.broker.EventSource;

/**
 * Published & Subscribed channels
 */
public interface CalendarEventSource extends EventSource<Calendar> {

	public static final String INPUT = "calendarManagementInputChannel";
	public static final String OUTPUT = "calendarManagementOutputChannel";

	@Output(CalendarEventSource.OUTPUT)
	MessageChannel calendarManagement();

}
