package es.ozona.kayros.webapp.infrastructure.feingclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

@FeignClient(name = "kairos-clock-service") // , fallback = TimesheetServiceFallback.class)
public interface TimesheetService {

	@GetMapping(path = "/api/v1/timesheets/clock", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public TimesheetResource clock(@RequestParam String employeeId, @RequestParam String username);

	@GetMapping(path = "/api/v1/timesheets", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PageResult<TimesheetResource> search(@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+date") String sort, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size);

}
