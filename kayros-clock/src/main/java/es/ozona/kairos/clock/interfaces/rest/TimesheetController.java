package es.ozona.kairos.clock.interfaces.rest;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.data.inquire.model.query.Queries;
import es.ozona.data.inquire.model.query.QueryObject;
import es.ozona.data.inquire.model.query.QueryObjectBuilder;
import es.ozona.kairos.clock.application.internal.commandservices.TimesheetCommandService;
import es.ozona.kairos.clock.application.internal.queryservices.TimesheetQueryServiceImpl;
import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.domain.model.commands.ClockTimesheetCommand;
import es.ozona.kairos.clock.infrastructure.repositories.TimesheetRepository;
import es.ozona.kairos.clock.interfaces.rest.dto.TimesheetResource;
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
@Api(value = "Clock Service Api", tags = "Clock API")
@SwaggerDefinition(tags = { @Tag(name = "Clock API", description = "Comandos y queries para registro de jornada.") })
public class TimesheetController extends BaseControllerImpl<Timesheet, Long, TimesheetRepository, TimesheetCommandService, TimesheetQueryServiceImpl> {

	@GetMapping(path = "/timesheets/clock", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Clock time", notes = "The value of employeeId can not be null.")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Clocked time", response = TimesheetResource.class),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<TimesheetResource> clock(
			@ApiParam(value = "Employee Id", required = false, example = "FBF844DF-CA8D-4E59-A310-8AC8AED8B439") @RequestParam(required = false) String employeeId,
			@ApiParam(value = "Ldap user", required = true, example = "xose.eijo@ozona.es") @RequestParam String username) {
		
		final TimesheetResource timesheet = modelMapper.map(commandService.clock(new ClockTimesheetCommand(employeeId, username)), TimesheetResource.class);
		
		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(timesheet.getTimesheetId()).toUri();

		return ResponseEntity.created(location).body(timesheet);
	}

	@GetMapping(path = "/timesheets/{timesheet-id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Get employee timesheet by Id", notes = "The value of timesheetId can not be null.")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Clocked time", response = TimesheetResource.class),
			@ApiResponse(code = 404, message = "Timesheet not found"),
			@ApiResponse(code = 400, message = "Bad request") })	
	public ResponseEntity<TimesheetResource> findOne(@ApiParam(value = "Employee Id", required = true, example = "FBF844DF-CA8D-4E59-A310-8AC8AED8B439") @PathVariable("timesheet-id") String id) {

		final TimesheetResource timesheet = modelMapper.map(queryService.findOne(id), TimesheetResource.class);

		return ResponseEntity.ok(timesheet);
	}
	
	@GetMapping(path = "/timesheets", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value = "Search timesheets by applying filters, sorting and page limit", notes = "")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = TimesheetResource.class), 
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<PageResult<TimesheetResource>> search(
			@RequestParam(required = false) String query,
			@RequestParam(required = false, defaultValue = "+date") String sort, 
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1000") int size) {		
		
		QueryObject qo = QueryObjectBuilder.setQuery(query).setSort(sort).setPage(page, size).build();
		
		qo = Queries.map(qo, modelMapper.getMappings(Timesheet.class, TimesheetResource.class), Queries.Syntax.INFIX);

		return ResponseEntity.ok(queryService.search(qo).map(e -> modelMapper.map(e, TimesheetResource.class)));
	}

}
