package es.ozona.kairos.calendar.interfaces.rest;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.data.inquire.model.query.Queries;
import es.ozona.data.inquire.model.query.QueryObject;
import es.ozona.data.inquire.model.query.QueryObjectBuilder;
import es.ozona.kairos.calendar.application.internal.commandservices.CalendarCommandService;
import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.application.internal.exception.DefaultCalendarNotFoundException;
import es.ozona.kairos.calendar.application.internal.queryservices.CalendarQueryService;
import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.commands.AddCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarDetailCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarYearCommand;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;
import es.ozona.kairos.calendar.interfaces.rest.dto.AddCalendarHolidayResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.CalendarResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.CreateCalendarResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.HolidayResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ModifyCalendarDetailResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ModifyCalendarYearResource;
import es.ozona.micro.core.interfaces.rest.BaseControllerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Controller // This means that this class is a Controller
@RequestMapping("/api/v1")
@Api(value = "Calendar Service Api", tags = "Calendar API")
@SwaggerDefinition(tags = { @Tag(name = "Calendar API", description = "Comandos y queries de gestión de calendario.") })
public class CalendarController extends BaseControllerImpl<Calendar, Long, CalendarRepository, CalendarCommandService, CalendarQueryService> {

	@PostMapping(path = "/calendars", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create a new calendar", notes = "The value of calenderId must be unique or null.")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Calendar created", response = CalendarResource.class),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<CalendarResource> create(
			@ApiParam(required = true, value = "Calendar create resource") @RequestBody @Valid CreateCalendarResource calendarResource) {

		final CalendarResource calendar = modelMapper.map(commandService.createCalendar(modelMapper.map(calendarResource, CreateCalendarCommand.class)),
				CalendarResource.class);

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(calendar.getCalendarId()).toUri();

		return ResponseEntity.created(location).body(calendar);
	}

	@PutMapping(path = "/calendars/{calendar-id}/detail/change", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Modify a calendar by Id", notes = "The calendar ID must exist")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Calendar updated", response = Object.class), @ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<?> modifyDetail(
			@ApiParam(required = true, value = "Calendar modify resource") @RequestBody @Valid ModifyCalendarDetailResource modifyCalendarDetailResource,
			@ApiParam(required = true, value = "The calendar ID to modify") @PathVariable("calendar-id") String id) {
		CalendarResource calendar;

		try {
			final ModifyCalendarDetailCommand cmd = modelMapper.map(modifyCalendarDetailResource, ModifyCalendarDetailCommand.class);
			cmd.setCalendarId(id);
			calendar = modelMapper.map(commandService.modifyCalendarDetail(cmd), CalendarResource.class);
		} catch (CalendarNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct calendarId.", e);
		}

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/calendar/{id}").buildAndExpand(calendar.getCalendarId()).toUri();

		return ResponseEntity.noContent().location(location).build();
	}

	@PutMapping(path = "/calendars/{calendar-id}/year/change", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Change calendar year by Id", notes = "The calendar ID must exist")
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Calendar updated", response = Object.class), 
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<?> changeYear(
			@ApiParam(required = true, value = "Calendar modify resource") @RequestBody @Valid ModifyCalendarYearResource modifyCalendarYearResource,
			@ApiParam(required = true, value = "The calendar ID to modify") @PathVariable("calendar-id") String id) {
		CalendarResource calendar;

		try {
			final ModifyCalendarYearCommand cmd = modelMapper.map(modifyCalendarYearResource, ModifyCalendarYearCommand.class);
			cmd.setCalendarId(id);
			calendar = modelMapper.map(commandService.modifyCalendarYear(cmd), CalendarResource.class);
		} catch (CalendarNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct calendarId.", e);
		}

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/calendar/{id}").buildAndExpand(calendar.getCalendarId()).toUri();

		return ResponseEntity.noContent().location(location).build();
	}

	@DeleteMapping(path = "/calendars/{calendar-id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete calendar by Id", notes = "The calendar ID must exist")
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Calendar deleted", response = Object.class), 
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<?> delete(@ApiParam(required = true, value = "Calendar ID to delete") @PathVariable("calendar-id") String cid) {
		try {
			commandService.deleteCalendar(new DeleteCalendarCommand(cid));
		} catch (CalendarNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct calendarId.", e);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/calendars/{calendar-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get a calendar by Id", notes = "The calendar ID must exist")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = CalendarResource.class), 
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<CalendarResource> find(@ApiParam(required = true, value = "Calendar ID to find") @PathVariable("calendar-id") String id) {
		try {
			return ResponseEntity.ok(modelMapper.map(queryService.findByCalendarId(id), CalendarResource.class));
		} catch (CalendarNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct ID.");
		}
	}

	@GetMapping(path = "/calendars", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Search calendars by applying filters, sorting and page limit", notes = "")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = CalendarResource.class), 
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<PageResult<CalendarResource>> search(
			@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+year") String sort, 
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "1000") Integer size) {		
		
		QueryObject qo = QueryObjectBuilder.setQuery(query).setSort(sort).setPage(page, size).build();
		
		qo = Queries.map(qo, modelMapper.getMappings(Calendar.class, CalendarResource.class), Queries.Syntax.INFIX);

		return ResponseEntity.ok(queryService.search(qo).map(e -> modelMapper.map(e, CalendarResource.class)));
	}
	
	@PostMapping(path = "/calendars/{calendar-id}/holidays", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Add new holiday to calendar", notes = "If holiday exists nothing is done")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Holiday created", response = Object.class), 
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Calendar not found") })
	public ResponseEntity<HolidayResource> addHoliday(
			@ApiParam(value = "The add calendar resource to create", required = true) @RequestBody AddCalendarHolidayResource holidayResource,
			@ApiParam(value = "The target calendar") @PathVariable("calendar-id") String id) {

		try {
			final AddCalendarHolidayCommand addHolidayCmd = modelMapper.map(holidayResource, AddCalendarHolidayCommand.class);
			addHolidayCmd.setCalendarId(id);
			final HolidayResource holiday = modelMapper.map(commandService.addCalendarHoliday(addHolidayCmd), HolidayResource.class);

			return ResponseEntity.created(null).body(holiday);

		} catch (CalendarNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct calendarId.", e);
		}
	}

	@DeleteMapping(path = "/calendars/{calendar-id}/holidays/{date}")	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Remove holiday from calendar", notes = "Calendar must exist")
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Calendar deleted", response = Object.class), 
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<?> deleteHoliday(@PathVariable("calendar-id") String id, @PathVariable String date) {
		
		final DeleteCalendarHolidayCommand cmd = new DeleteCalendarHolidayCommand(id, LocalDate.parse(date, DateTimeFormatter.ofPattern("ddMMyyyy")));
		commandService.deleteCalendarHoliday(cmd);

		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/calendars/{calendar-id}/holidays", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "List holidays in calendar", notes = "Calendar ID must exists")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Holiday asigned to calendar", response = HolidayResource.class),
			@ApiResponse(code = 400, message = "Bad request"), 
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<List<HolidayResource>> listHolidays(@PathVariable("calendar-id") String calendarId) {

		final List<HolidayResource> holidays = queryService.findAllHolidaysByCalendarId(calendarId).stream().map(s -> modelMapper.map(s, HolidayResource.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(holidays);

	}
	
	@GetMapping(path = "/calendars/default/{year}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get default calendar for year", notes = "")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = CalendarResource.class), 
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Not found") })
	public ResponseEntity<CalendarResource> findDefaultCalendarByYear(@ApiParam(value = "Calendar year", example="2020", required = true) @PathVariable Integer year) {
		try {
			return ResponseEntity.ok(modelMapper.map(queryService.findDefaultCalendarByYear(year), CalendarResource.class));
		} catch (DefaultCalendarNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct ID.");
		}
	}

}
