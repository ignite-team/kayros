package es.ozona.kairos.clock.application.internal.queryservices;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ozona.kairos.clock.application.internal.exceptions.TimesheetNotFoundException;
import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.domain.model.valueobjects.TimesheetId;
import es.ozona.kairos.clock.infrastructure.repositories.TimesheetRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryServiceImpl;
import es.ozona.micro.core.infrastructure.message.resources.Labels;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TimesheetQueryServiceImpl extends BaseQueryServiceImpl<Timesheet, Long, TimesheetRepository> implements TimesheetQueryService {

	@Override
	public Timesheet findOne(String id) {
		final Timesheet timesheet = repository.findByTimesheetId(new TimesheetId(id));

		if (timesheet == null)
			throw new TimesheetNotFoundException(Labels.getMessage("exception.timesheetNotFoundException.message", id.toString()));

		return timesheet;
	}

	@Override
	public Slice<Timesheet> findEmployeeTimesheets(String employeeId, LocalDate date, Integer offset, Integer limit) {
		final Pageable page = PageRequest.of((offset + 1) / limit, limit, Sort.by("date").ascending());
		return repository.findByEmployeeIdAndDate(employeeId, date, page);
	}

}
