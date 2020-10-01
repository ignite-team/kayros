package es.ozona.kayros.webapp.outboundservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ozona.kayros.webapp.shareddomain.model.Timesheet;

@FeignClient(name = "kairos-clock-service")//, fallback = TimesheetServiceFallback.class)
// @FeignClient(name = "gateway", fallback = CalendarServiceFallback.class) llamada interna atraves de zull, nunca en produccion
public interface TimesheetService {

	//  @GetMapping(path = "/gw/cal/api/v1/calendars/default/{year}") llamada interna atraves de zull, nunca en produccion
	//	@GetMapping(path = "/api/v1/calendars/default/{year}")
	//	public Calendar findDefaultCalendarByYear(@PathVariable Integer year);
	@GetMapping(path = "/api/v1/timesheets/clock", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public Timesheet clock(@RequestParam String username);

}
