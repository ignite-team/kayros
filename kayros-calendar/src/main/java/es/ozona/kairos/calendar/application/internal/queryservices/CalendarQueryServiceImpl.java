package es.ozona.kairos.calendar.application.internal.queryservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.data.inquire.model.query.QueryObject;
import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.application.internal.exception.DefaultCalendarNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.entities.Holiday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryServiceImpl;

@Service
public class CalendarQueryServiceImpl extends BaseQueryServiceImpl<Calendar, Long, CalendarRepository> implements CalendarQueryService {

	@Autowired
	private CalendarRepository calendarRepository;

	@Override
	public Calendar findByCalendarId(String calendarId) throws CalendarNotFoundException {
		final List<Calendar> list = calendarRepository.findByCalendarId(new CalendarId(calendarId));
		if (list.size() == 0) {
			throw new CalendarNotFoundException();
		}
		return list.get(0);
	}

	@Override
	public PageResult<Calendar> seach(QueryObject qo) {
		return repository.seach(qo);
	}

	@Override
	public List<Holiday> findAllHolidayByCanlendarId(String calendarId) {
		return repository.findAllHolidaysByCalendarId(new CalendarId(calendarId));
	}

	@Override
	public Calendar findDefaultCalendarByYear(Integer year) {
		final Calendar calendar = repository.findDefaultCalendarByYear(year);
		if (calendar == null) {
			throw new DefaultCalendarNotFoundException(year);
		}
		return calendar;
	}

}
