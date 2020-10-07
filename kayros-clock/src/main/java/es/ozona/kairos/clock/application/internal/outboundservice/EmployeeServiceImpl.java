package es.ozona.kairos.clock.application.internal.outboundservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ozona.kairos.clock.application.internal.outboundservice.acl.Employee;

@Service
public class EmployeeServiceImpl {

	@Autowired
	private EmployeeService employeeService;
	
	public Employee findEmployeeById(String id) {
		
		Employee employee = employeeService.findEmployeeById(id);
		return employee;

	}

}
