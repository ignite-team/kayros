package es.ozona.kairos.employee.application.internal.eventservices;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.infrastructure.brokers.EmployeeEventSource;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeCreatedEvent;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeEvents;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeHeaders;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeModifiedEvent;
import es.ozona.micro.core.application.internal.eventservices.BaseEventSourceSeviceImpl;

@Service
public class EmployeeEventServiceImpl extends BaseEventSourceSeviceImpl<Employee, EmployeeEventSource> implements EmployeeEventService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeEventServiceImpl.class);

	@Override
	@TransactionalEventListener
	public void handleEmployeeCreatedEvent(EmployeeCreatedEvent employeeCreatedEvent) {

		final Map<String, String> headers = Collections.singletonMap(EmployeeHeaders.TYPE.name(), EmployeeEvents.EMPLOYEE_CREATED_EVENT.name());

		eventSource.employeeManagement().send(MessageBuilder.withPayload(employeeCreatedEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("Employee created event with ID {} has been sent.", employeeCreatedEvent.getEmployeeCreatedEventData().getEmployeeId());
	}

	@Override
	@TransactionalEventListener
	public void handleEmployeeModifiedEvent(EmployeeModifiedEvent employeeModifiedEvent) {

		final Map<String, String> headers = Collections.singletonMap(EmployeeHeaders.TYPE.name(), EmployeeEvents.EMPLOYEE_CREATED_EVENT.name());

		eventSource.employeeManagement().send(MessageBuilder.withPayload(employeeModifiedEvent).copyHeaders(headers).build());

		if (LOG.isDebugEnabled())
			LOG.debug("Employee modified event with ID {} has been sent.", employeeModifiedEvent.getEmployeeModifiedEventData().getEmployeeId());
	}

}
