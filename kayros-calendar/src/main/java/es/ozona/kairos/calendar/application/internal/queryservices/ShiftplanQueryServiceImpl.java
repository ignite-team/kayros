package es.ozona.kairos.calendar.application.internal.queryservices;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ozona.kairos.calendar.application.internal.exception.ShiftplanNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.ShiftplanId;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftplanRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryServiceImpl;

@Service
public class ShiftplanQueryServiceImpl extends BaseQueryServiceImpl<Shiftplan, Long, ShiftplanRepository> implements ShiftplanQueryService {

	@Override
	public Shiftplan find(String id) {
		final Shiftplan shiftplan = repository.findByShiftplanId(new ShiftplanId(id));

		if (shiftplan == null) {
			throw new ShiftplanNotFoundException(String.format("Shiftplan with Id %s not found.", id));
		}

		return shiftplan;
	}

	@Override
	public List<Workday> findAllWorkdaysByShiftplanId(String shiftplanId) {
		return repository.findAllWorkdaysByShiftplanId(new ShiftplanId(shiftplanId));
	}

}
