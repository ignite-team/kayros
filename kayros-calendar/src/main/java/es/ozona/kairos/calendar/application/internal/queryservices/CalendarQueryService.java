package es.ozona.kairos.calendar.application.internal.queryservices;

import java.util.List;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.data.inquire.model.query.QueryObject;
import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.entities.Holiday;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryService;

public interface CalendarQueryService extends BaseQueryService<Calendar, Long, CalendarRepository> {

	Calendar findByCalendarId(String calendarId) throws CalendarNotFoundException;

	PageResult<Calendar> search(QueryObject q);

	List<Holiday> findAllHolidaysByCalendarId(String calendarId);

	Calendar findDefaultCalendarByYear(Integer year);
}
