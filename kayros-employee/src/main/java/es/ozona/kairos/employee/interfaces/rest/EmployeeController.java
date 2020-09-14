package es.ozona.kairos.employee.interfaces.rest;

import java.net.URI;
import java.util.List;
import java.util.Map;
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
import es.ozona.kairos.employee.application.internal.commandservices.EmployeeCommandService;
import es.ozona.kairos.employee.application.internal.exceptions.EmployeeNotFoundException;
import es.ozona.kairos.employee.application.internal.queryservices.EmployeeQueryService;
import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.domain.model.commands.AssignScheduleCommand;
import es.ozona.kairos.employee.domain.model.commands.CreateEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.ModifyEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.UnassignScheduleCommand;
import es.ozona.kairos.employee.infrastructure.repositories.EmployeeRepository;
import es.ozona.kairos.employee.interfaces.rest.dto.AssignScheduleCommandResource;
import es.ozona.kairos.employee.interfaces.rest.dto.CreateEmployeeCommandResource;
import es.ozona.kairos.employee.interfaces.rest.dto.EmployeeResource;
import es.ozona.kairos.employee.interfaces.rest.dto.ModifyEmployeeCommandResource;
import es.ozona.kairos.employee.interfaces.rest.dto.ScheduleResource;
import es.ozona.micro.core.interfaces.rest.BaseControllerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Controller
@RequestMapping("/api/v1")
@Api(value = "Employee service API.", tags = "Employee API.")
@SwaggerDefinition(tags = { @Tag(name = "Employee API.", description = "Commands and Queries to managing Employee.") })
public class EmployeeController extends BaseControllerImpl<Employee, Long, EmployeeRepository, EmployeeCommandService, EmployeeQueryService> {

	@PostMapping(path = "/employees", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ResponseBody
	@ApiOperation(value = "Create Employee", notes = "Username cannot exist.")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Employee created", response = EmployeeResource.class),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<EmployeeResource> create(@ApiParam(required = true) @Valid @RequestBody CreateEmployeeCommandResource createEmployeeResource) {
		try {
			final Employee employee = commandService.createEmployee(modelMapper.map(createEmployeeResource, CreateEmployeeCommand.class));
			final EmployeeResource employeeResource = modelMapper.map(employee, EmployeeResource.class);
			final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeResource.getEmployeeId()).toUri();

			return ResponseEntity.created(location).body(employeeResource);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct employee ID.", e);
		}
	}

	@PutMapping(path = "/employees/{employee-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ResponseBody
	@ApiOperation(value = "Modify Employee", notes = "Value of ModifyEmployeeCommandResource.employeeId will be ignored.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Employee modified", response = EmployeeResource.class),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<EmployeeResource> modify(@ApiParam(required = true) @Valid @RequestBody ModifyEmployeeCommandResource modifyEmployeeCommandResource,
			@ApiParam(required = true, example = "6E222863-874E-4587-B685-ADF68A4581A8") @PathVariable("employee-id") String id) {
		try {
			final ModifyEmployeeCommand cmd = modelMapper.map(modifyEmployeeCommandResource, ModifyEmployeeCommand.class);
			cmd.setEmployeeId(id);
			final EmployeeResource employeeResource = modelMapper.map(commandService.modifyEmployee(cmd), EmployeeResource.class);
			final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeResource.getEmployeeId()).toUri();

			return ResponseEntity.ok().location(location).body(employeeResource);
		} catch (EmployeeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct employee ID.", e);
		}
	}

	@GetMapping(path = "/employees/{employee-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Get employee by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clocked time", response = EmployeeResource.class),
			@ApiResponse(code = 404, message = "Employee not found"), @ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<EmployeeResource> find(@ApiParam(required = true, example = "6E222863-874E-4587-B685-ADF68A4581A8") @PathVariable("employee-id") String id) {

		try {
			final Employee employee = queryService.findByEmployeeId(id);
			final EmployeeResource employeeResource = modelMapper.map(employee, EmployeeResource.class);
			final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeResource.getEmployeeId()).toUri();

			return ResponseEntity.ok().location(location).body(employeeResource);

		} catch (EmployeeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct employee ID.", e);
		}

	}

	@PostMapping(path = "/employees/{employee-id}/schedules", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	@ResponseBody
	@ApiOperation(value = "Assign calendar to employee", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Employee modified", response = ScheduleResource.class),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<ScheduleResource> assignSchedule(
			@ApiParam(required = true) @Valid @RequestBody AssignScheduleCommandResource assignScheduleCommandResource,
			@ApiParam(required = true, example = "6E222863-874E-4587-B685-ADF68A4581A8") @PathVariable("employee-id") String id) {
		try {
			final AssignScheduleCommand cmd = modelMapper.map(assignScheduleCommandResource, AssignScheduleCommand.class);
			cmd.setEmployeeId(id);

			final ScheduleResource scheduleResource = modelMapper.map(commandService.assignSchedule(cmd), ScheduleResource.class);
			final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(scheduleResource.getScheduleId()).toUri();

			return ResponseEntity.ok().location(location).body(scheduleResource);
		} catch (EmployeeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct employee ID.", e);
		}
	}

	@DeleteMapping(path = "/employees/{employee-id}/schedules/{schedule-id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ResponseBody
	@ApiOperation(value = "Unassign calendar from employee", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Employee modified", response = Object.class),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<?> unassignSchedule(
			@ApiParam(required = true, example = "6E222863-874E-4587-B685-ADF68A4581A8") @PathVariable(name = "employee-id", required = true) String employeeId,
			@ApiParam(required = true, example = "6E222863-874E-4587-B685-ADF68A4581A8") @PathVariable(name = "schedule-id", required = true) String scheduleId) {
		try {
			final UnassignScheduleCommand cmd = new UnassignScheduleCommand(employeeId, scheduleId);

			commandService.unassignSchedule(cmd);

			return ResponseEntity.noContent().build();
		} catch (EmployeeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct employee ID.", e);
		}
	}

	@GetMapping(path = "/employees", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Search for employees using filters", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = EmployeeResource.class), @ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<PageResult<EmployeeResource>> search(
			@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+firstname") String sort, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size) {

		QueryObject qo = QueryObjectBuilder.setQuery(query).setSort(sort).setPage(page, size).build();
		
		final Map<String,String> mappings = modelMapper.getMappings(Employee.class, EmployeeResource.class);
		mappings.put("schedules_startDate", "schedules_validity_startDate");
		
		qo = Queries.map(qo, mappings, Queries.Syntax.INFIX);

		return ResponseEntity.ok(queryService.search(qo).map(e -> modelMapper.map(e, EmployeeResource.class)));
	}

	@GetMapping(path = "/employees/{employee-id}/schedules", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "List employee schedules", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ScheduleResource.class), @ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<List<ScheduleResource>> searhSchedules(@PathVariable(name = "employee-id") String employeeId) {

		// TODO: sustituir por un search con query, hay que tener en cuenta que habrá que usar un prefijo.
		return ResponseEntity.ok(queryService.searchSchedules(employeeId).stream().map(e -> modelMapper.map(e, ScheduleResource.class)).collect(Collectors.toList()));
	}

}
