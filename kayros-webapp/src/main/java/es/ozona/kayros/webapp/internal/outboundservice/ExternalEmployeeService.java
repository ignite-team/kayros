package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.List;
import java.util.Optional;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.Schedule;

public interface ExternalEmployeeService {

	Optional<Employee> findEmployeeByUsername(String username);

	List<Employee> findEmployeesLikeUsername(String username);

	List<Schedule> findSchedulesByEmployeeId(String id);

	Employee createEmployeeFromPrincipal();

	Employee modifyEmployee(Employee employee);

}
