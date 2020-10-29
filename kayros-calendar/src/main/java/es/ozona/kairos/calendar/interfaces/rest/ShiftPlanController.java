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
import es.ozona.kairos.calendar.application.internal.commandservices.ShiftPlanCommandService;
import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.application.internal.exception.ShiftPlanNotFoundException;
import es.ozona.kairos.calendar.application.internal.queryservices.ShiftPlanQueryService;
import es.ozona.kairos.calendar.domain.model.aggregates.ShiftPlan;
import es.ozona.kairos.calendar.domain.model.aggregates.WorkdayNotFoundException;
import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyShiftPlanPeriodCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftPlanRepository;
import es.ozona.kairos.calendar.interfaces.rest.dto.AddWorkdayResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.CreateShiftPlanResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ModifyShiftPlanPeriodResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ModifyWorkdayResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ShiftPlanResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.WorkdayResource;
import es.ozona.micro.core.interfaces.rest.BaseControllerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api/v1")
@Api(value = "ShiftPlan Controller", tags = "ShiftPlan API")
public class ShiftPlanController<D> extends BaseControllerImpl<ShiftPlan, Long, ShiftPlanRepository, ShiftPlanCommandService, ShiftPlanQueryService> {

	public ShiftPlanController() {
		super();
	}

	@PostMapping(path = "/shiftPlans", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create shiftPlan", notes = "Value of calendar ID must exist.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "ShiftPlan created."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "Calendar ID not found.") })
	public ResponseEntity<ShiftPlanResource> create(@ApiParam(value = "ShiftPlan resource", required = true) @RequestBody @Valid CreateShiftPlanResource rsc) {

		try {
			final ShiftPlanResource shiftPlan = modelMapper.map(commandService.createShiftPlan(modelMapper.map(rsc, CreateShiftPlanCommand.class)),
					ShiftPlanResource.class);
			final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shiftPlan.getShiftPlanId()).toUri();

			return ResponseEntity.created(location).body(shiftPlan);
		} catch (CalendarNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct calendarId.", e);
		}

	}

	@PutMapping(path = "/shiftPlans/{shiftPlan-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Update shiftPlan", notes = "Value of calendar ID must exist and it's owner of shiftPlan.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ShiftPlan updated."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "ShiftPlan ID not found.") })
	public ResponseEntity<ShiftPlanResource> update(
			@ApiParam(value = "ShiftPlan resource", required = true) @RequestBody @Valid ModifyShiftPlanPeriodResource rsc,
			@ApiParam(value = "ShiftPlan Id", required = true) @PathVariable("calendar-id") String id) {

		final ModifyShiftPlanPeriodCommand cmd = modelMapper.map(rsc, ModifyShiftPlanPeriodCommand.class);
		cmd.setShiftPlanId(id);

		ShiftPlanResource shiftPlan;
		try {
			shiftPlan = modelMapper.map(commandService.modifyShiftPlanPeriod(cmd), ShiftPlanResource.class);

		} catch (ShiftPlanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftPlan ID.", e);
		}

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shiftPlan).toUri();

		return ResponseEntity.created(location).body(shiftPlan);
	}

	@GetMapping(path = "/shiftPlans/{shiftPlan-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Get a ShiftPlan by Id", notes = "Value of shiftPlan ID must exist.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "ShiftPlan ID not found.") })
	public ResponseEntity<ShiftPlanResource> find(
			@ApiParam(value = "ShiftPlan ID to find.", required = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD") @PathVariable("shiftPlan-id") String id) {

		try {
			return ResponseEntity.ok(modelMapper.map(queryService.find(id), ShiftPlanResource.class));
		} catch (ShiftPlanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftPlan id.", e);
		}

	}

	@DeleteMapping(path = "/shiftPlans/{shiftPlan-id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete shiftPlan", notes = "Value of calendar ID must exist and it's owner of shiftPlan.")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "ShiftPlan deleted."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "ShiftPlan ID not found.") })
	public ResponseEntity<?> delete(
			@ApiParam(value = "ShiftPlan resource", required = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD") @PathVariable("shiftPlan-id") String id) {

		DeleteShiftPlanCommand deleteShiftPlanCommand = new DeleteShiftPlanCommand();
		deleteShiftPlanCommand.setShiftPlanId(id);

		try {
			commandService.deleteShiftPlan(deleteShiftPlanCommand);

			return ResponseEntity.noContent().build();
		} catch (ShiftPlanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftPlan id.", e);
		}

	}

	@PostMapping(path = "/shiftPlans/{shiftPlan-id}/workdays", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create a shiftPlan workday", notes = "Value of workday ID must exist.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "ShiftPlan workday created."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "ShiftPlan ID not found.") })
	public ResponseEntity<WorkdayResource> addWorkday(
			@ApiParam(value = "ShiftPlan resource", required = true) @RequestBody AddWorkdayResource addWorkdayResource,
			@ApiParam(value = "ShiftPlan ID", required = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD") @PathVariable("shiftPlan-id") String id) {

		try {
			final AddWorkdayCommand cmd = modelMapper.map(addWorkdayResource, AddWorkdayCommand.class);

			cmd.setShiftPlanId(id);
			final WorkdayResource resource = modelMapper.map(commandService.addWorkday(cmd), WorkdayResource.class);

			final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resource.getDay().ordinal()).toUri();

			return ResponseEntity.created(location).body(resource);

		} catch (ShiftPlanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftPlan id.", e);
		}

	}

	@DeleteMapping(path = "/shiftPlans/{shiftPlan-id}/workdays/{day}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a shiftPlan workday", notes = "Value of workday ID must exist.")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "ShiftPlan workday deleted."), @ApiResponse(code = 400, message = "Bad request."),
			@ApiResponse(code = 404, message = "ShiftPlan ID not found.") })
	public ResponseEntity<WorkdayResource> deleteWorkday(
			@ApiParam(value = "ShiftPlan ID", required = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD") @PathVariable("shiftPlan-id") String sid,
			@ApiParam(value = "Day of week in ordinal value", required = true, example = "1") @PathVariable int day) {

		try {

			final DeleteWorkdayCommand cmd = new DeleteWorkdayCommand(sid, DayOfWeek.values()[day]);

			commandService.deleteWorkday(cmd);

			return ResponseEntity.noContent().build();

		} catch (ShiftPlanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftPlan id.", e);
		}

	}

	@GetMapping(path = "/shiftPlans", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Search calendars by applying filters, sorting and page limit", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ShiftPlanResource.class), @ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<PageResult<ShiftPlanResource>> search(@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+startDate") String sort, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size) {

		QueryObject qo = QueryObjectBuilder.setQuery(query).setSort(sort).setPage(page, size).build();

		// Transformamos la query sobre el Resource a una query sobre el Entity
		qo = Queries.map(qo, modelMapper.getMappings(ShiftPlan.class, ShiftPlanResource.class), Queries.Syntax.INFIX);

		return ResponseEntity.ok(queryService.search(qo).map(e -> modelMapper.map(e, ShiftPlanResource.class)));
	}

	@GetMapping(path = "/shiftPlans/{shiftPlan-id}/workdays", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "List workdays sorted by day", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ShiftPlanResource.class), @ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<List<WorkdayResource>> listWorkdays(@PathVariable("shiftPlan-id") String sid) {

		final List<WorkdayResource> workdays = queryService.findAllWorkdaysByShiftPlanId(sid).stream().map(s -> modelMapper.map(s, WorkdayResource.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(workdays);
	}

	@PutMapping(path = "/shiftPlans/{shiftPlan-id}/workdays/{day}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Replace a worday with another given.", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ShiftPlanResource.class), @ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "ShiftPlan Id or Workday not found.") })
	public ResponseEntity<WorkdayResource> modifyWorkday(@PathVariable("shiftPlan-id") String sid, @PathVariable int day,
			@RequestBody ModifyWorkdayResource modifyWorkdayResource) {

		final ModifyWorkdayCommand cmd = modelMapper.map(modifyWorkdayResource, ModifyWorkdayCommand.class);
		cmd.setShiftPlanId(sid);
		cmd.setDay(DayOfWeek.values()[day]);
		try {

			final WorkdayResource workdays = modelMapper.map(commandService.modifyWorkday(cmd), WorkdayResource.class);

			return ResponseEntity.ok(workdays);

		} catch (ShiftPlanNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct shiftPlan id.", e);
		} catch (WorkdayNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct workday id.", e);
		}
	}

}
