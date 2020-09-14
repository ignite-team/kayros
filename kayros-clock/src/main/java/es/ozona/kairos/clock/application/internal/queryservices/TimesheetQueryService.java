package es.ozona.kairos.clock.application.internal.queryservices;

import java.time.LocalDate;

import org.springframework.data.domain.Slice;

import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.infrastructure.repositories.TimesheetRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryService;

public interface TimesheetQueryService extends BaseQueryService<Timesheet, Long, TimesheetRepository> {

	Timesheet findOne(String id);

	Slice<Timesheet> findEmployeeTimesheets(String employeeId, LocalDate date, Integer offset, Integer limit);

}