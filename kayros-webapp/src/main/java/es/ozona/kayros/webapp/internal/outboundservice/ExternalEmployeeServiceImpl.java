package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.infrastructure.feingclients.EmployeeService;
import es.ozona.kayros.webapp.internal.outboundservice.acl.EmployeeMapper;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;

@Service("externalEmployeeService")
public class ExternalEmployeeServiceImpl implements ExternalEmployeeService {

	@Autowired
	protected EmployeeService employeeService;
	
	@Override
	public Optional<Employee> findEmployeeByUsername(String username) {
		final PageResult<EmployeeResource> employees = employeeService.search("username:%s".formatted(username), "+username", 1, 1);
		if (employees.getSize() > 0) {
			return Optional.of(EmployeeMapper.map(employees.getItems().get(0)));
		}
		return Optional.empty();
	}

}
