package es.ozona.kairos.clock.infrastructure.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.domain.model.valueobjects.EmployeeId;
import es.ozona.kairos.clock.domain.model.valueobjects.TimesheetId;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;
import es.ozona.micro.core.infrastructure.respository.UUIDIdentityGenerator;

@Repository
public interface TimesheetRepository extends BaseRepository<Timesheet, Long>, UUIDIdentityGenerator {

	public Timesheet findByTimesheetId(TimesheetId timesheetId);

	public Timesheet findFirstByEmployeeIdOrderByDateDesc(EmployeeId employeeId);
	
	public Timesheet findFirstByUsernameOrderByDateDesc(String username);

	@Query("select s from Timesheet s where s.employeeId.employeeId = :employeeId and s.date >= :date")
	public Slice<Timesheet> findByEmployeeIdAndDate(@Param("employeeId") String employeeId, @Param("date") LocalDate date, Pageable page);

}