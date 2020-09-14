package es.ozona.kairos.calendar.interfaces.rest;

import java.net.URI;
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
import es.ozona.kairos.calendar.application.internal.commandservices.ShiftplanCommandService;
import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.application.internal.exception.ShiftplanNotFoundException;
import es.ozona.kairos.calendar.application.internal.queryservices.ShiftplanQueryService;
import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.domain.model.aggregates.WorkdayNotFoundException;
import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftplanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteShiftplanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyShiftplanPeriodCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftplanRepository;
import es.ozona.kairos.calendar.interfaces.rest.dto.AddWorkdayResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.CreateShiftplanResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ModifyShiftplanPeriodResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ModifyWorkdayResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ShiftplanResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.WorkdayResource;
import es.ozona.micro.core.interfaces.rest.BaseControllerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api/v1")
@Api(value = "Shiftplan Controller", tags = "Shiftplan API")
public class ShiftplanController<D> extends BaseControllerImpl<Shiftplan, Long, ShiftplanRepository, ShiftplanCommandService, ShiftplanQueryService> {

	public ShiftplanController() {
		super();
	}

	@PostMapping(path = "/shiftplans", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create shiftplan", notes = "Value of calendar ID must exist.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Shiftplan created."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "Calendar ID not found.") })
	public ResponseEntity<ShiftplanResource> create(@ApiParam(value = "Shiftplan resource", required = true) @RequestBody @Valid CreateShiftplanResource rsc) {

		try {
			final ShiftplanResource shiftplan = modelMapper.map(commandService.createShiftplan(modelMapper.map(rsc, CreateShiftplanCommand.class)),
					ShiftplanResource.class);
			final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shiftplan.getShiftplanId()).toUri();

			return ResponseEntity.created(location).body(shiftplan);
		} catch (CalendarNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct calendarId.", e);
		}

	}

	@PutMapping(path = "/shiftplans/{shiftplan-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Update shiftplan", notes = "Value of calendar ID must exist and it's owner of shiftplan.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Shiftplan updated."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "Shiftplan ID not found.") })
	public ResponseEntity<ShiftplanResource> update(
			@ApiParam(value = "Shiftplan resource", required = true) @RequestBody @Valid ModifyShiftplanPeriodResource rsc,
			@ApiParam(value = "Shiftplan Id", required = true) @PathVariable("calendar-id") String id) {

		final ModifyShiftplanPeriodCommand cmd = modelMapper.map(rsc, ModifyShiftplanPeriodCommand.class);
		cmd.setShiftplanId(id);

		ShiftplanResource shiftplan;
		try {
			shiftplan = modelMapper.map(commandService.modifyShiftplanPeriod(cmd), ShiftplanResource.class);

		} catch (ShiftplanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftplan ID.", e);
		}

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shiftplan).toUri();

		return ResponseEntity.created(location).body(shiftplan);
	}

	@GetMapping(path = "/shiftplans/{shiftplan-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Get a Shiftplan by Id", notes = "Value of shiftplan ID must exist.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "Shiftplan ID not found.") })
	public ResponseEntity<ShiftplanResource> find(
			@ApiParam(value = "Shiftplan ID to find.", required = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD") @PathVariable("shiftplan-id") String id) {

		try {
			return ResponseEntity.ok(modelMapper.map(queryService.find(id), ShiftplanResource.class));
		} catch (ShiftplanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftplan id.", e);
		}

	}

	@DeleteMapping(path = "/shiftplans/{shiftplan-id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete shiftplan", notes = "Value of calendar ID must exist and it's owner of shiftplan.")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Shiftplan deleted."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "Shiftplan ID not found.") })
	public ResponseEntity<?> delete(
			@ApiParam(value = "Shiftplan resource", required = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD") @PathVariable("shiftplan-id") String id) {

		DeleteShiftplanCommand deleteShiftplanCommand = new DeleteShiftplanCommand();
		deleteShiftplanCommand.setShiftplanId(id);

		try {
			commandService.deleteShiftplan(deleteShiftplanCommand);

			return ResponseEntity.noContent().build();
		} catch (ShiftplanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftplan id.", e);
		}

	}

	@PostMapping(path = "/shiftplans/{shiftplan-id}/workdays", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create a shiftplan workday", notes = "Value of workday ID must exist.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Shiftplan workday created."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "Shiftplan ID not found.") })
	public ResponseEntity<WorkdayResource> addWorkday(
			@ApiParam(value = "Shiftplan resource", required = true) @RequestBody AddWorkdayResource addWorkdayResource,
			@ApiParam(value = "Shiftplan ID", required = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD") @PathVariable("shiftplan-id") String id) {

		try {
			final AddWorkdayCommand cmd = modelMapper.map(addWorkdayResource, AddWorkdayCommand.class);

			cmd.setShiftplanId(id);
			final WorkdayResource resource = modelMapper.map(commandService.addWorkday(cmd), WorkdayResource.class);

			final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resource.getDay().ordinal()).toUri();

			return ResponseEntity.created(location).body(resource);

		} catch (ShiftplanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftplan id.", e);
		}

	}

	@DeleteMapping(path = "/shiftplans/{shiftplan-id}/workdays/{day}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a shiftplan workday", notes = "Value of workday ID must exist.")
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Shiftplan workday deleted."), 
			@ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "Shiftplan ID not found.") })
	public ResponseEntity<WorkdayResource> deleteWorkday(
			@ApiParam(value = "Shiftplan ID", required = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD") @PathVariable("shiftplan-id") String sid,
			@ApiParam(value = "Day of week in ordinal value", required = true, example = "1") @PathVariable int day) {

		try {

			final DeleteWorkdayCommand cmd = new DeleteWorkdayCommand(sid, DayOfWeek.values()[day]);

			commandService.deleteWorkday(cmd);

			return ResponseEntity.noContent().build();

		} catch (ShiftplanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftplan id.", e);
		}

	}

	@GetMapping(path = "/shiftplans", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Search calendars by applying filters, sorting and page limit", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ShiftplanResource.class), @ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<PageResult<ShiftplanResource>> search(@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+startDate") String sort, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size) {

		QueryObject qo = QueryObjectBuilder.setQuery(query).setSort(sort).setPage(page, size).build();

		// Transformamos la query sobre el Resource a una query sobre el Entity
		qo = Queries.map(qo, modelMapper.getMappings(Shiftplan.class, ShiftplanResource.class), Queries.Syntax.INFIX);

		return ResponseEntity.ok(queryService.search(qo).map(e -> modelMapper.map(e, ShiftplanResource.class)));
	}
	
	@GetMapping(path = "/shiftplans/{shiftplan-id}/workdays", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "List workdays sorted by day", notes = "")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = ShiftplanResource.class), 
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<List<WorkdayResource>> listWorkdays(@PathVariable("shiftplan-id") String sid) {

		final List<WorkdayResource> workdays = queryService.findAllWorkdaysByShiftplanId(sid).stream().map(s -> modelMapper.map(s, WorkdayResource.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(workdays);
	}
	
	@PutMapping(path = "/shiftplans/{shiftplan-id}/workdays/{day}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Replace a worday with another given.", notes = "")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = ShiftplanResource.class), 
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Shiftplan Id or Workday not found.") })
	public ResponseEntity<WorkdayResource> modifyWorkday(@PathVariable("shiftplan-id") String sid, @PathVariable int day,
			@RequestBody ModifyWorkdayResource modifyWorkdayResource) {

		final ModifyWorkdayCommand cmd = modelMapper.map(modifyWorkdayResource, ModifyWorkdayCommand.class);
		cmd.setShiftplanId(sid);
		cmd.setDay(DayOfWeek.values()[day]);
		try {

			final WorkdayResource workdays = modelMapper.map(commandService.modifyWorkday(cmd), WorkdayResource.class);

			return ResponseEntity.ok(workdays);

		} catch (ShiftplanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftplan id.", e);
		} catch (WorkdayNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct workday id.", e);
		}
	}

}
