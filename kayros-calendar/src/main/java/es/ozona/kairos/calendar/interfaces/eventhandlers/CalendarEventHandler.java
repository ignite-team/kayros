package es.ozona.kairos.calendar.interfaces.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;

import es.ozona.kairos.calendar.infrastructure.brokers.ShiftPlanEventSource;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEventData;

//@Service
public class CalendarEventHandler {

	private static final Logger LOG = LoggerFactory.getLogger(CalendarEventHandler.class);

//	@Autowired
//	private CalendarCommandServiceImpl calendarCommandService;

	@StreamListener(value = ShiftPlanEventSource.INPUT)
	public void observeCargoHandledEvent(CalendarDeletedEvent event) {
		CalendarDeletedEventData eventData = event.getCalendarDeleteEventData();
		LOG.debug("Calendario con ID  {} borrado.", eventData.getCalendarId());
		// calendarCommandService.update(event);
	}

}
