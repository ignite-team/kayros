package es.ozona.kairos.clock.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ozona.kairos.clock.application.internal.outboundservice.EmployeeService;
import es.ozona.kairos.clock.application.internal.outboundservice.acl.Employee;
import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.domain.model.commands.ClockTimesheetCommand;
import es.ozona.kairos.clock.domain.model.commands.CreateTimesheetCommand;
import es.ozona.kairos.clock.domain.model.valueobjects.EmployeeId;
import es.ozona.kairos.clock.infrastructure.repositories.TimesheetRepository;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandServiceImpl;

@Service
public class TimesheetCommandServiceImpl extends BaseCommandServiceImpl<Timesheet, Long, TimesheetRepository> implements TimesheetCommandService {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public Timesheet clock(ClockTimesheetCommand clockTimesheetCommand) {

		Timesheet timesheet = null;

		// TODO: mover a capa domain service.
		if (clockTimesheetCommand.getEmployeeId() == null) { // employee service is not available.

			timesheet = repository.findFirstByUsernameOrderByDateDesc(clockTimesheetCommand.getUsername());

			if (timesheet == null) {

				clockTimesheetCommand.setEmployeeId(repository.nextId());
				timesheet = new Timesheet(buildCreateTimesheetCommand(clockTimesheetCommand), true); // Unregistered user

			} else {

				clockTimesheetCommand.setEmployeeId(timesheet.getEmployeeId().getEmployeeId());
				timesheet.clock(clockTimesheetCommand);

			}

		} else {

			Employee employee = employeeService.findEmployeeById(clockTimesheetCommand.getEmployeeId());

			clockTimesheetCommand.setTelecommuting(employee.getTelecommuting());
			clockTimesheetCommand.setWorkplace(employee.getWorkplace());

			timesheet = repository.findFirstByEmployeeIdOrderByDateDesc(new EmployeeId(clockTimesheetCommand.getEmployeeId()));
			
			if (timesheet != null) {

				timesheet.clock(clockTimesheetCommand);

			} else {
				
				timesheet = new Timesheet(buildCreateTimesheetCommand(clockTimesheetCommand));

			}

		}

		repository.save(timesheet);

		return timesheet;

	}

	private CreateTimesheetCommand buildCreateTimesheetCommand(ClockTimesheetCommand clockTimesheetCommand) {

		final String timesheetId = repository.nextId();
		final String employeeId = clockTimesheetCommand.getEmployeeId();
		final CreateTimesheetCommand createTimesheetCommand = new CreateTimesheetCommand(timesheetId, employeeId, clockTimesheetCommand.getUsername(), clockTimesheetCommand.getClockTime().toLocalDate(), clockTimesheetCommand.getTelecommuting(), clockTimesheetCommand.getWorkplace());

		return createTimesheetCommand;

	}

}
