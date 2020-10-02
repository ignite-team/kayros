package es.ozona.kayros.webapp.infrastructure.feingclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;

@FeignClient(name = "kairos-employee-service", fallback = EmployeeServiceFallback.class)
public interface EmployeeService {

	@GetMapping(path = "/api/v1/employees/{employee-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeResource> find(@PathVariable("employee-id") String id);
	
	@GetMapping(path = "/api/v1/employees", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PageResult<EmployeeResource> search(
			@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+firstname") String sort, 
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size);
	
}
