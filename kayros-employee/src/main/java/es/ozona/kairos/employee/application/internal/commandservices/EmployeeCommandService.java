package es.ozona.kairos.employee.application.internal.commandservices;

import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.domain.model.commands.AssignScheduleCommand;
import es.ozona.kairos.employee.domain.model.commands.CreateEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.ModifyEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.UnassignScheduleCommand;
import es.ozona.kairos.employee.domain.model.entities.Schedule;
import es.ozona.kairos.employee.infrastructure.repositories.EmployeeRepository;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandService;

public interface EmployeeCommandService extends BaseCommandService<Employee, Long, EmployeeRepository> {

	Employee createEmployee(CreateEmployeeCommand createEmployeeCommand);

	Employee modifyEmployee(ModifyEmployeeCommand modifyEmployeeCommand);

	void unassignSchedule(UnassignScheduleCommand unassignScheduleCommand);

	Schedule assignSchedule(AssignScheduleCommand assignScheduleCommand);

	Employee createEmployeeAuto(CreateEmployeeCommand createEmployeeCommand);
}
