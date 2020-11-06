package es.ozona.kairos.calendar.application.internal.queryservices;

import java.util.List;

import es.ozona.kairos.calendar.domain.model.aggregates.ShiftPlan;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftPlanRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryService;

public interface ShiftPlanQueryService extends BaseQueryService<ShiftPlan, Long, ShiftPlanRepository> {

	ShiftPlan findByShiftPlanId(String id);

	List<Workday> findAllWorkdaysByShiftPlanId(String sid);

}
