package es.ozona.kayros.webapp.internal.outboundservice.acl;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;

public class EmployeeMapper {

	private EmployeeMapper() {

	}

	public static Employee mapFromResource(EmployeeResource resource) {

		final Employee employee = new Employee(resource.getEmployeeId(), resource.getUsername(), resource.getEmail(), resource.getFirstname(),
				resource.getLastname(), resource.getTelecommuting(), resource.getWorkplace());

		return employee;

	}

	public static EmployeeResource mapToResource(Employee resource) {

		final EmployeeResource employee = new EmployeeResource(resource.getEmployeeId(), resource.getUsername(), resource.getEmail(), resource.getFirstname(),
				resource.getLastname(), resource.getTelecommuting(), resource.getWorkplace());

		return employee;

	}

}
