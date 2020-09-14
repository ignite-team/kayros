package es.ozona.kairos.calendar.application.internal.eventservices;

import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.infrastructure.brokers.ShiftplanEventSource;
import es.ozona.kairos.shareddomain.model.events.ShiftplanCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftplanDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftplanPeriodModifiedEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceService;

public interface ShiftplanEventService extends BaseEventSourceService<Shiftplan, ShiftplanEventSource>{

	void handleShiftplanCreatedEvent(ShiftplanCreatedEvent shiftplanCreatedEvent);

	void handleShiftplanPeriodoModifiedEvent(ShiftplanPeriodModifiedEvent shiftplanModifiedEvent);

	void handleShiftplanDeletedEvent(ShiftplanDeletedEvent shiftplanDeletedEvent);

}