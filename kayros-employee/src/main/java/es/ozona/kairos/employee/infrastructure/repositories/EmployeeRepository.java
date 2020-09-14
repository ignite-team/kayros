package es.ozona.kairos.employee.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.domain.model.valueobjects.EmployeeId;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;
import es.ozona.micro.core.infrastructure.respository.UUIDIdentityGenerator;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long>, UUIDIdentityGenerator {

	Employee findByEmployeeId(EmployeeId employeeId);

}
