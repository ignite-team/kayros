package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
		if (employees.getItems().size() > 0) {
			return Optional.of(EmployeeMapper.mapFromResource(employees.getItems().get(0)));
		}
		return Optional.empty();
	}

	@Override
	public List<Employee> findEmployeesLikeUsername(String username) {

		return employeeService.search("username:%s*".formatted(username), "+username", 1, 15).getItems().stream().map(e -> EmployeeMapper.mapFromResource(e))
				.collect(Collectors.toList());

	}

	@Override
	public Employee createEmployeeFromPrincipal() {
		final OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

		EmployeeResource employeeResource = new EmployeeResource(null, auth.getName(), auth.getPrincipal().getAttribute("cn"),
				auth.getPrincipal().getAttribute("sn"), auth.getPrincipal().getAttribute("mail"), "es_ES", false,
				auth.getPrincipal().getAttribute("physicalDeliveryOfficeName"));

		employeeResource = employeeService.create(employeeResource);

		return EmployeeMapper.mapFromResource(employeeResource);

	}

	@Override
	public Employee modifyEmployee(Employee employee) {

		return EmployeeMapper.mapFromResource(employeeService.modify(EmployeeMapper.mapToResource(employee), employee.getEmployeeId()));
	}

}
