package es.ozona.kairos.calendar.application.internal.eventservices;

import es.ozona.kairos.calendar.domain.model.aggregates.ShiftPlan;
import es.ozona.kairos.calendar.infrastructure.brokers.ShiftPlanEventSource;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanPeriodModifiedEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceService;

public interface ShiftPlanEventService extends BaseEventSourceService<ShiftPlan, ShiftPlanEventSource>{

	void handleShiftPlanCreatedEvent(ShiftPlanCreatedEvent shiftPlanCreatedEvent);

	void handleShiftPlanPeriodoModifiedEvent(ShiftPlanPeriodModifiedEvent shiftPlanModifiedEvent);

	void handleShiftPlanDeletedEvent(ShiftPlanDeletedEvent shiftPlanDeletedEvent);

}