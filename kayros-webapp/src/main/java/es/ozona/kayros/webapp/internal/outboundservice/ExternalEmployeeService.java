package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.Optional;

import es.ozona.kayros.webapp.domain.model.Employee;

public interface ExternalEmployeeService {

	Optional<Employee> findEmployeeByUsername(String username);

}
