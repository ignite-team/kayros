package es.ozona.kairos.employee.application.internal.queryservices;

import java.util.List;

import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.domain.model.entities.Schedule;
import es.ozona.kairos.employee.infrastructure.repositories.EmployeeRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryService;

public interface EmployeeQueryService extends BaseQueryService<Employee, Long, EmployeeRepository> {

	Employee findByEmployeeId(String id);

	List<Schedule> searchSchedules(String employeeId);

}
