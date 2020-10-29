package es.ozona.kairos.calendar.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.ozona.kairos.calendar.domain.model.aggregates.ShiftPlan;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.calendar.domain.model.valueobjects.ShiftPlanId;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;
import es.ozona.micro.core.infrastructure.respository.UUIDIdentityGenerator;

@Repository
public interface ShiftPlanRepository extends BaseRepository<ShiftPlan, Long>, UUIDIdentityGenerator {

	ShiftPlan findByShiftPlanId(ShiftPlanId shiftPlanId);

	List<ShiftPlan> findByCalendarId(CalendarId calendarId);
	
	@Query("select w from ShiftPlan s inner join s.workdays w where s.shiftPlanId = ?1 order by w.day")
	List<Workday> findAllWorkdaysByShiftPlanId(ShiftPlanId shiftPlanId);
}
