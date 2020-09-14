package es.ozona.kairos.employee.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.ozona.kairos.employee.domain.model.entities.Schedule;
import es.ozona.kairos.employee.domain.model.valueobjects.EmployeeId;
import es.ozona.kairos.employee.domain.model.valueobjects.ScheduleId;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;
import es.ozona.micro.core.infrastructure.respository.UUIDIdentityGenerator;

@Repository
public interface ScheduleRepository extends BaseRepository<Schedule, Long>, UUIDIdentityGenerator {

	Schedule findByScheduleId(ScheduleId scheduleId);

	@Query("select s from Employee e inner join e.schedules s where e.employeeId = ?1")
	List<Schedule> findAllSchedulesByEmployeeId(EmployeeId employeeId);

}
