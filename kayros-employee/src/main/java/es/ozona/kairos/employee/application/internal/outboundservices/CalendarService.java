package es.ozona.kairos.employee.application.internal.outboundservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.ozona.kairos.employee.shareddomain.model.Calendar;

@FeignClient(name = "kairos-calendar-service", fallback = CalendarServiceFallback.class)
// @FeignClient(name = "gateway", fallback = CalendarServiceFallback.class) llamada interna atraves de zull, nunca en produccion
public interface CalendarService {

	// @GetMapping(path = "/gw/cal/api/v1/calendars/default/{year}") llamada interna atraves de zull, nunca en produccion
	@GetMapping(path = "/api/v1/calendars/default/{year}")
	public Calendar findDefaultCalendarByYear(@PathVariable Integer year);

}
