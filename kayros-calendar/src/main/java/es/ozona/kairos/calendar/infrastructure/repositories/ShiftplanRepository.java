package es.ozona.kairos.calendar.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.calendar.domain.model.valueobjects.ShiftplanId;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;
import es.ozona.micro.core.infrastructure.respository.UUIDIdentityGenerator;

@Repository
public interface ShiftplanRepository extends BaseRepository<Shiftplan, Long>, UUIDIdentityGenerator {

	Shiftplan findByShiftplanId(ShiftplanId shiftplanId);

	List<Shiftplan> findByCalendarId(CalendarId calendarId);
	
	@Query("select w from Shiftplan s inner join s.workdays w where s.shiftplanId = ?1 order by w.day")
	List<Workday> findAllWorkdaysByShiftplanId(ShiftplanId shiftplanId);
}
