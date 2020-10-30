package es.ozona.kayros.webapp.infrastructure.feingclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.shareddomain.model.CalendarResource;
import es.ozona.kayros.webapp.shareddomain.model.HolidayResource;
import es.ozona.kayros.webapp.shareddomain.model.ShiftPlanResource;
import es.ozona.kayros.webapp.shareddomain.model.WorkdayResource;

@FeignClient(name = "kairos-calendar-service") // , fallback = CalendarServiceFallback.class)
public interface CalendarService {

	@GetMapping(path = "/api/v1/calendars/{calendar-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public CalendarResource findCalendarById(@PathVariable("calendar-id") String id);

	@GetMapping(path = "/api/v1/calendars/{calendar-id}/holidays", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public List<HolidayResource> findAllHolidaysByCalendarId(@PathVariable("calendar-id") String id);

	@GetMapping(path = "/api/v1/calendars", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PageResult<CalendarResource> searchCalendars(@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+year") String sort, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size);

	@GetMapping(path = "/api/v1/shiftPlans/{shiftPlan-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ShiftPlanResource findShiftPlanById(@PathVariable("shiftPlan-id") String id);

	@GetMapping(path = "/api/v1/shiftPlans/{shiftPlan-id}/workdays", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public  List<WorkdayResource> findAllWorkdaysByShiftplanId(@PathVariable("shiftPlan-id") String id);

	@GetMapping(path = "/api/v1/shiftPlans", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PageResult<ShiftPlanResource> searchShiftPlans(@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+startDate") String sort, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size);

}
