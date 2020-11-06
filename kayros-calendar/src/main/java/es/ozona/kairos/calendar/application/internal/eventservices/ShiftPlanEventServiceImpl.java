package es.ozona.kairos.calendar.application.internal.eventservices;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import es.ozona.kairos.calendar.domain.model.aggregates.ShiftPlan;
import es.ozona.kairos.calendar.infrastructure.brokers.ShiftPlanEventSource;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanEvents;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanHeaders;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanPeriodModifiedEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceSeviceImpl;

@Service
public class ShiftPlanEventServiceImpl extends BaseEventSourceSeviceImpl<ShiftPlan, ShiftPlanEventSource> implements ShiftPlanEventService {
	private static final Logger LOG = LoggerFactory.getLogger(ShiftPlanEventServiceImpl.class);

	private ShiftPlanEventSource shiftPlanEventSource;

	public ShiftPlanEventServiceImpl(ShiftPlanEventSource shiftPlanEventSource) {
		this.shiftPlanEventSource = shiftPlanEventSource;
	}

	@Override
	@TransactionalEventListener
	public void handleShiftPlanCreatedEvent(ShiftPlanCreatedEvent shiftPlanCreatedEvent) {
		final Map<String, String> headers = Collections.singletonMap(ShiftPlanHeaders.TYPE.name(), ShiftPlanEvents.SHIFTPLAN_CREATED_EVENT.name());

		shiftPlanEventSource.shiftPlanManagement().send(MessageBuilder.withPayload(shiftPlanCreatedEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("ShiftPlan created event send with ID {}.", shiftPlanCreatedEvent.getShiftPlanCreatedEventData().getShiftPlanId());
	}

	@Override
	@TransactionalEventListener
	public void handleShiftPlanPeriodoModifiedEvent(ShiftPlanPeriodModifiedEvent shiftPlanModifiedEvent) {
		final Map<String, String> headers = Collections.singletonMap(ShiftPlanHeaders.TYPE.name(), ShiftPlanEvents.SHIFTPLAN_MODIFIED_EVENT.name());

		shiftPlanEventSource.shiftPlanManagement().send(MessageBuilder.withPayload(shiftPlanModifiedEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("ShiftPlan period modified event send with ID {}.", shiftPlanModifiedEvent.getShiftPlanPeriodModifiedEventData().getShiftPlanId());
	}

	@Override
	@TransactionalEventListener
	public void handleShiftPlanDeletedEvent(ShiftPlanDeletedEvent shiftPlanDeletedEvent) {
		final Map<String, String> headers = Collections.singletonMap(ShiftPlanHeaders.TYPE.name(), ShiftPlanEvents.SHIFTPLAN_DELETED_EVENT.name());

		shiftPlanEventSource.shiftPlanManagement().send(MessageBuilder.withPayload(shiftPlanDeletedEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("ShiftPlan deleted event send with ID {}.", shiftPlanDeletedEvent.getShiftPlanDeleteEventData().getShiftPlanId());
	}

}
