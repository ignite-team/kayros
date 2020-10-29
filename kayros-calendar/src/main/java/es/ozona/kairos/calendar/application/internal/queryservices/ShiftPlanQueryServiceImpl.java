package es.ozona.kairos.calendar.application.internal.queryservices;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ozona.kairos.calendar.application.internal.exception.ShiftPlanNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.ShiftPlan;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.ShiftPlanId;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftPlanRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryServiceImpl;

@Service
public class ShiftPlanQueryServiceImpl extends BaseQueryServiceImpl<ShiftPlan, Long, ShiftPlanRepository> implements ShiftPlanQueryService {

	@Override
	public ShiftPlan find(String id) {
		final ShiftPlan shiftPlan = repository.findByShiftPlanId(new ShiftPlanId(id));

		if (shiftPlan == null) {
			throw new ShiftPlanNotFoundException("ShiftPlan with Id %s not found.".formatted(id));
		}

		return shiftPlan;
	}

	@Override
	public List<Workday> findAllWorkdaysByShiftPlanId(String shiftPlanId) {
		return repository.findAllWorkdaysByShiftPlanId(new ShiftPlanId(shiftPlanId));
	}

}
