package es.ozona.kairos.employee.application.internal.eventservices;

import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.infrastructure.brokers.EmployeeEventSource;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeCreatedEvent;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeModifiedEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceService;

public interface EmployeeEventService extends BaseEventSourceService<Employee, EmployeeEventSource> {

	void handleEmployeeCreatedEvent(EmployeeCreatedEvent employeeCreatedEvent);

	void handleEmployeeModifiedEvent(EmployeeModifiedEvent employeeModifiedEvent);

}
