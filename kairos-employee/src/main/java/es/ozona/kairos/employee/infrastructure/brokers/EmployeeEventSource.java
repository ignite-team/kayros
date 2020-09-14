package es.ozona.kairos.employee.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.micro.core.infrastructure.broker.EventSource;

public interface EmployeeEventSource extends EventSource<Employee> {
	public static final String OUTPUT = "employeeManagementOutChannel";
	public static final String INPUT = "timesheetManagementInputChannel";

	@Output(EmployeeEventSource.OUTPUT)
	MessageChannel employeeManagement();

	@Input(EmployeeEventSource.INPUT)
	MessageChannel timesheetManagement();
}
