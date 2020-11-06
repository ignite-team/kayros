package es.ozona.kayros.webapp.infrastructure.feingclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;
import es.ozona.kayros.webapp.shareddomain.model.ScheduleResource;

@FeignClient(name = "kairos-employee-service") // , fallback = EmployeeServiceFallback.class)
public interface EmployeeService {

	@GetMapping(path = "/api/v1/employees/{employee-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public EmployeeResource find(@PathVariable("employee-id") String id);

	@GetMapping(path = "/api/v1/employees/{employee-id}/schedules", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<ScheduleResource> searchSchedules(@PathVariable("employee-id") String id);

	@GetMapping(path = "/api/v1/employees", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PageResult<EmployeeResource> search(@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+firstname") String sort, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size);

	@PostMapping(path = "/api/v1/employees", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public EmployeeResource create(@RequestBody EmployeeResource employeeResource);

	@PutMapping(path = "/api/v1/employees/{employee-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public EmployeeResource modify(@RequestBody EmployeeResource modifyEmployeeCommandResource, @PathVariable("employee-id") String id);

}
