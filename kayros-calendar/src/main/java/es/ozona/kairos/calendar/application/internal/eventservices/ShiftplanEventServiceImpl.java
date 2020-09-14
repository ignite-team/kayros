package es.ozona.kairos.calendar.application.internal.eventservices;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.infrastructure.brokers.ShiftplanEventSource;
import es.ozona.kairos.shareddomain.model.events.ShiftplanCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftplanDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftplanEvents;
import es.ozona.kairos.shareddomain.model.events.ShiftplanHeaders;
import es.ozona.kairos.shareddomain.model.events.ShiftplanPeriodModifiedEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceSeviceImpl;

@Service
public class ShiftplanEventServiceImpl extends BaseEventSourceSeviceImpl<Shiftplan, ShiftplanEventSource> implements ShiftplanEventService {
	private static final Logger LOG = LoggerFactory.getLogger(ShiftplanEventServiceImpl.class);

	private ShiftplanEventSource shiftplanEventSource;

	public ShiftplanEventServiceImpl(ShiftplanEventSource shiftplanEventSource) {
		this.shiftplanEventSource = shiftplanEventSource;
	}

	@Override
	@TransactionalEventListener
	public void handleShiftplanCreatedEvent(ShiftplanCreatedEvent shiftplanCreatedEvent) {
		final Map<String, String> headers = Collections.singletonMap(ShiftplanHeaders.TYPE.name(), ShiftplanEvents.SHIFTPLAN_CREATED_EVENT.name());

		shiftplanEventSource.shiftplanManagement().send(MessageBuilder.withPayload(shiftplanCreatedEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("Shiftplan created event send with ID {}.", shiftplanCreatedEvent.getShiftplanCreatedEventData().getShiftplanId());
	}

	@Override
	@TransactionalEventListener
	public void handleShiftplanPeriodoModifiedEvent(ShiftplanPeriodModifiedEvent shiftplanModifiedEvent) {
		final Map<String, String> headers = Collections.singletonMap(ShiftplanHeaders.TYPE.name(), ShiftplanEvents.SHIFTPLAN_MODIFIED_EVENT.name());

		shiftplanEventSource.shiftplanManagement().send(MessageBuilder.withPayload(shiftplanModifiedEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("Shiftplan period modified event send with ID {}.", shiftplanModifiedEvent.getShiftplanPeriodModifiedEventData().getShiftplanId());
	}

	@Override
	@TransactionalEventListener
	public void handleShiftplanDeletedEvent(ShiftplanDeletedEvent shiftplanDeletedEvent) {
		final Map<String, String> headers = Collections.singletonMap(ShiftplanHeaders.TYPE.name(), ShiftplanEvents.SHIFTPLAN_DELETED_EVENT.name());

		shiftplanEventSource.shiftplanManagement().send(MessageBuilder.withPayload(shiftplanDeletedEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("Shiftplan deleted event send with ID {}.", shiftplanDeletedEvent.getShiftplanDeleteEventData().getShiftplanId());
	}

}
