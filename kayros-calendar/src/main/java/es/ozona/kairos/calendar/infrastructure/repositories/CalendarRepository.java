package es.ozona.kairos.calendar.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.entities.Holiday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;
import es.ozona.micro.core.infrastructure.respository.UUIDIdentityGenerator;

@Repository
public interface CalendarRepository extends BaseRepository<Calendar, Long>, UUIDIdentityGenerator {

	List<Calendar> findByCalendarId(CalendarId calendarId);

	List<Calendar> findByYear(Integer year);

	@Query("select h from Calendar c inner join c.holidays h where c.calendarId = ?1 order by h.holiday")
	List<Holiday> findAllHolidaysByCalendarId(CalendarId calendarId);

	@Query("select c from Calendar c where year = :year and markedAsDefault = true")
	Calendar findDefaultCalendarByYear(Integer year);

}
