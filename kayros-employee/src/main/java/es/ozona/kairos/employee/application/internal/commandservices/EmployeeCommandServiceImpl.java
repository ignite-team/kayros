package es.ozona.kairos.employee.application.internal.commandservices;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ozona.kairos.employee.application.internal.exceptions.EmployeeNotFoundException;
import es.ozona.kairos.employee.application.internal.exceptions.ExternalServiceException;
import es.ozona.kairos.employee.application.internal.outboundservices.ExternalCalendarService;
import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.domain.model.commands.AssignScheduleCommand;
import es.ozona.kairos.employee.domain.model.commands.CreateEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.ModifyEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.UnassignScheduleCommand;
import es.ozona.kairos.employee.domain.model.entities.Schedule;
import es.ozona.kairos.employee.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.employee.domain.model.valueobjects.EmployeeId;
import es.ozona.kairos.employee.infrastructure.repositories.EmployeeRepository;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandServiceImpl;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class EmployeeCommandServiceImpl extends BaseCommandServiceImpl<Employee, Long, EmployeeRepository> implements EmployeeCommandService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeCommandServiceImpl.class);
	
	@Autowired
	private ExternalCalendarService calendarService;

	@Override
	public Employee createEmployee(CreateEmployeeCommand createEmployeeCommand) {

		if (createEmployeeCommand.getEmployeeId() == null) {
			createEmployeeCommand.setEmployeeId(repository.nextId());
		}

		final Employee employee = new Employee(createEmployeeCommand);
		
		repository.save(employee);

		return employee;
	}
	
	@Override
	public Employee createEmployeeAuto(CreateEmployeeCommand createEmployeeCommand) {
		
		// TODO: trasladar este metodo al service domain.
		CalendarId calendarId = null;
		
		try {
			 calendarId = calendarService.fetchDefaultCalendar();
		} catch (ExternalServiceException e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Failed to fetch default calendar.", e);
			}			
		}		

		if (createEmployeeCommand.getEmployeeId() == null) {
			createEmployeeCommand.setEmployeeId(repository.nextId());
		}
		
		Employee employee = null;		
		if (calendarId == null) {
			employee = new Employee(createEmployeeCommand);
			repository.save(employee);
		} else {
			employee = new Employee(createEmployeeCommand);
			final LocalDate date = LocalDate.of(LocalDate.now().getYear(), 1, 1);			
			
			AssignScheduleCommand assignScheduleCommand = new AssignScheduleCommand(
					employee.getEmployeeId().getEmployeeId(),
					repository.nextId(),
					calendarId.getCalendarId(),
					date,
					date.plusYears(1).minusDays(1));
			
			repository.save(employee);
			assignSchedule(assignScheduleCommand);
		}

		return employee;
	}


	@Override
	public Employee modifyEmployee(ModifyEmployeeCommand modifyEmployeeCommand) {
		final Employee employee = repository.findByEmployeeId(new EmployeeId(modifyEmployeeCommand.getEmployeeId()));
		if (employee == null)
			throw new EmployeeNotFoundException(modifyEmployeeCommand.getEmployeeId());

		employee.modify(modifyEmployeeCommand);

		repository.save(employee);

		return employee;
	}

	@Override
	public Schedule assignSchedule(AssignScheduleCommand assignScheduleCommand) {
		final Employee employee = repository.findByEmployeeId(new EmployeeId(assignScheduleCommand.getEmployeeId()));

		if (employee == null)
			throw new EmployeeNotFoundException(assignScheduleCommand.getEmployeeId());

		if (assignScheduleCommand.getScheduleId() == null) {
			assignScheduleCommand.setScheduleId(repository.nextId());
		}

		final Schedule schedule = employee.assignSchedule(assignScheduleCommand);

		repository.save(employee);

		return schedule;
	}

	@Override
	public void unassignSchedule(UnassignScheduleCommand unassignScheduleCommand) {
		final Employee employee = repository.findByEmployeeId(new EmployeeId(unassignScheduleCommand.getEmployeeId()));
		if (employee == null)
			throw new EmployeeNotFoundException(unassignScheduleCommand.getEmployeeId());

		employee.unassignSchedule(unassignScheduleCommand);

		repository.save(employee);

	}

}
