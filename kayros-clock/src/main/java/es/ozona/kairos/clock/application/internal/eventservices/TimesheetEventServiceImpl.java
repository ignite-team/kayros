package es.ozona.kairos.clock.application.internal.eventservices;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.infrastructure.brokers.TimesheetEventSource;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetClockedInEvent;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetClockedOutEvent;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetEvents;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetHeaders;
import es.ozona.kairos.clock.shareddomain.model.events.UnregisteredTimesheetClockedInEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceSeviceImpl;

@Service
public class TimesheetEventServiceImpl extends BaseEventSourceSeviceImpl<Timesheet, TimesheetEventSource> implements TimesheetEventService {

	private static final Logger LOG = LoggerFactory.getLogger(TimesheetEventServiceImpl.class);

	@TransactionalEventListener
	@Override
	public void handleClockIn(TimesheetClockedInEvent timesheetedClockInEvent) {

		final Map<String, String> headers = Collections.singletonMap(TimesheetHeaders.TYPE.name(), TimesheetEvents.CLOCKIN.name());

		eventSource.timesheetManagement().send(MessageBuilder.withPayload(timesheetedClockInEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("Clock In event sent with ID {}.", timesheetedClockInEvent.getTimesheetClockInEventData().getTimesheetId());

	}

	@TransactionalEventListener
	@Override
	public void handleClockOut(TimesheetClockedOutEvent timesheetedClockOutEvent) {

		final Map<String, String> headers = Collections.singletonMap(TimesheetHeaders.TYPE.name(), TimesheetEvents.CLOCKOUT.name());

		eventSource.timesheetManagement().send(MessageBuilder.withPayload(timesheetedClockOutEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("Clock Out event sent with ID {}.", timesheetedClockOutEvent.getTimesheetClockOutEventData().getTimesheetId());

	}

	@TransactionalEventListener
	@Override
	public void handleUnregisteredClockIn(UnregisteredTimesheetClockedInEvent unregisteredTimesheetedClockOutEvent) {

		final Map<String, String> headers = Collections.singletonMap(TimesheetHeaders.TYPE.name(), TimesheetEvents.UNREGISTERED_CLOCKIN.name());

		eventSource.timesheetManagement().send(MessageBuilder.withPayload(unregisteredTimesheetedClockOutEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("Unregistered user clock In event sent with username <{}>.", unregisteredTimesheetedClockOutEvent.getUnregisteredTimesheetClockInEventData().getUseraname());

	}

}
