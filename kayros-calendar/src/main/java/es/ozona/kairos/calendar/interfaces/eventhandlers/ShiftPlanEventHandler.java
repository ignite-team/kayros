package es.ozona.kairos.calendar.interfaces.eventhandlers;

import java.util.Map;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import es.ozona.kairos.calendar.application.internal.commandservices.ShiftPlanCommandServiceImpl;
import es.ozona.kairos.calendar.infrastructure.brokers.ShiftPlanEventSource;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEventData;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEventData;
import es.ozona.kairos.shareddomain.model.events.CalendarDetailModifiedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarDetailModifiedEventData;
import es.ozona.kairos.shareddomain.model.events.CalendarEvents;
import es.ozona.kairos.shareddomain.model.events.CalendarHeaders;

@Component
public class ShiftPlanEventHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ShiftPlanEventHandler.class);

	@Autowired
	private ShiftPlanCommandServiceImpl shiftPlanCommandService;

	@Bean
	public Predicate<Map<String, Object>> calendarCreatedEventCondition() {
		return m -> CalendarEvents.CALENDAR_CREATED_EVENT.name().equals(m.get(CalendarHeaders.TYPE.name()));
	}

	@Bean
	public Predicate<Map<String, Object>> calendarModifiedEventCondition() {
		return m -> CalendarEvents.CALENDAR_MODIFIED_EVENT.name().equals(m.get(CalendarHeaders.TYPE.name()));
	}

	@Bean
	public Predicate<Map<String, Object>> calendarDeletedEventCondition() {
		return m -> CalendarEvents.CALENDAR_DELETED_EVENT.name().equals(m.get(CalendarHeaders.TYPE.name()));
	}

	@StreamListener(target = ShiftPlanEventSource.INPUT, condition = "@calendarCreatedEventCondition.test(headers)")
	public void observeCalendarCreatedEvent(CalendarCreatedEvent event) {
		CalendarCreatedEventData eventData = event.getCalendarCreatedEventData();
		LOG.debug("Calendar created event received for ID {}", eventData.getCalendarId());
		// calendarCommandService.update(event);
	}

	@StreamListener(target = ShiftPlanEventSource.INPUT, condition = "@calendarModifiedEventCondition.test(headers)")
	public void observeCalendarDetailModifiedEvent(CalendarDetailModifiedEvent event) {
		CalendarDetailModifiedEventData eventData = event.getCalendarDetailModifiedEventData();
		LOG.debug("Calendar modified event received for ID {}", eventData.getCalendarId());
		// calendarCommandService.update(event);
	}

	@StreamListener(target = ShiftPlanEventSource.INPUT, condition = "@calendarDeletedEventCondition.test(headers)")
	public void observeCalendarDeletedEvent(CalendarDeletedEvent event) {
		CalendarDeletedEventData eventData = event.getCalendarDeleteEventData();
		LOG.debug("Calendar deleted event received for ID {}", eventData.getCalendarId());
		shiftPlanCommandService.updateEvent(event);
	}

}
