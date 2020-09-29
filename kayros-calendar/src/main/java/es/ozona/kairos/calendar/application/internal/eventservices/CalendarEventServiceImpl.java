package es.ozona.kairos.calendar.application.internal.eventservices;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import es.ozona.kairos.calendar.infrastructure.brokers.CalendarEventSource;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarEvents;
import es.ozona.kairos.shareddomain.model.events.CalendarHeaders;
import es.ozona.kairos.shareddomain.model.events.CalendarYearModifiedEvent;

@Service
public class CalendarEventServiceImpl implements CalendarEventService {
		
	private static final Logger LOG = LoggerFactory.getLogger(CalendarEventServiceImpl.class); 
	
	private CalendarEventSource calendarEventSource;

	public CalendarEventServiceImpl(CalendarEventSource calendarEventSource) {
		this.calendarEventSource = calendarEventSource;
	}

	@Override
	@TransactionalEventListener
	public void handleCalendarCreatedEvent(CalendarCreatedEvent calendarCreatedEvent) {
		final Map<String,String> headers = Collections.singletonMap(CalendarHeaders.TYPE.name(), CalendarEvents.CALENDAR_CREATED_EVENT.name()) ;
		
		calendarEventSource.calendarManagement()
		.send(MessageBuilder
				.withPayload(calendarCreatedEvent)
				.copyHeaders(headers)
				.build());
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("A calendar created event has been sent with calendar Id {}.", calendarCreatedEvent.getCalendarCreatedEventData().getCalendarId());
		}
	}

	@Override
	@TransactionalEventListener
	public void handleCalendarDeletedEvent(CalendarDeletedEvent calendarDeletedEvent) {
		final Map<String,String> headers = Collections.singletonMap(CalendarHeaders.TYPE.name(), CalendarEvents.CALENDAR_DELETED_EVENT.name()) ;
		
		calendarEventSource.calendarManagement()
		.send(MessageBuilder
				.withPayload(calendarDeletedEvent)
				.copyHeaders(headers)
				.build());
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("A calendar deleted event has been sent with calendar Id {}.", calendarDeletedEvent.getCalendarDeleteEventData().getCalendarId());
		}
	}

	@Override
	@TransactionalEventListener
	public void handleCalendarYearModifiedEvent(CalendarYearModifiedEvent calendarYearModifiedEvent) {
		
		final Map<String,String> headers = Collections.singletonMap(CalendarHeaders.TYPE.name(), CalendarEvents.CALENDAR_MODIFIED_EVENT.name()) ;
		
		calendarEventSource.calendarManagement()
		.send(MessageBuilder
				.withPayload(calendarYearModifiedEvent)
				.copyHeaders(headers)
				.build());
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("A calendar detail modified event has been sent with calendar Id {}.", calendarYearModifiedEvent.getCalendarYearModifiedEventData().getCalendarId());
		}
		
	}

}
