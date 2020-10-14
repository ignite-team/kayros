package es.ozona.kairos.clock.application.internal.outboundservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.ozona.kairos.clock.application.internal.outboundservice.acl.Employee;

@FeignClient(name = "kairos-employee-service") //fallback = EmployeeServiceFallback.class)
//@FeignClient(name = "gateway", fallback = EmployeeServiceFallback.class) llamada interna atraves de zull, nunca en produccion
public interface EmployeeService {

	// @GetMapping(path = "/gw/cal/api/v1/employees/{id}") llamada interna atraves de zull, nunca en produccion
	@GetMapping(path = "/api/v1/employees/{id}")
	public Employee findEmployeeById(@PathVariable String id);
		
}
