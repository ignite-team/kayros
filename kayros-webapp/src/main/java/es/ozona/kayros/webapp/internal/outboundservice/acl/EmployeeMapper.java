package es.ozona.kayros.webapp.internal.outboundservice.acl;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;

public class EmployeeMapper {
	private EmployeeMapper() {
		
	}
	
	public static Employee map(EmployeeResource resource) {
		final Employee employee = new Employee(
				resource.getEmployeeId(), 
				resource.getUsername(), 
				resource.getEmail(), 
				resource.getFirstname(), 
				resource.getLastname(),
				null,
				resource.getTelecommuting());
		
		return employee;
	}
}
