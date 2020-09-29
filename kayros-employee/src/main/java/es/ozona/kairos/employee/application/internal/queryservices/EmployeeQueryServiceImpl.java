package es.ozona.kairos.employee.application.internal.queryservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ozona.kairos.employee.application.internal.exceptions.EmployeeNotFoundException;
import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.domain.model.entities.Schedule;
import es.ozona.kairos.employee.domain.model.valueobjects.EmployeeId;
import es.ozona.kairos.employee.infrastructure.repositories.EmployeeRepository;
import es.ozona.kairos.employee.infrastructure.repositories.ScheduleRepository;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryServiceImpl;

@Service
public class EmployeeQueryServiceImpl extends BaseQueryServiceImpl<Employee, Long, EmployeeRepository> implements EmployeeQueryService {

	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public Employee findByEmployeeId(String id) {
		final Employee employeeId = repository.findByEmployeeId(new EmployeeId(id));
		if (employeeId == null)
			throw new EmployeeNotFoundException(id);

		return employeeId;
	}

	@Override
	public List<Schedule> searchSchedules(String employeeId) {

		return scheduleRepository.findAllSchedulesByEmployeeId(new EmployeeId(employeeId));
	}

}
