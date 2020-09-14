package es.ozona.kairos.calendar.application.internal.queryservices;

import java.util.List;

import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftplanRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryService;

public interface ShiftplanQueryService extends BaseQueryService<Shiftplan, Long, ShiftplanRepository> {

	Shiftplan find(String id);

	List<Workday> findAllWorkdaysByShiftplanId(String sid);

}
