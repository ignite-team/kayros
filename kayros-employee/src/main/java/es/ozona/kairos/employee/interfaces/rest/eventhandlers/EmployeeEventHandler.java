package es.ozona.kairos.employee.interfaces.rest.eventhandlers;

import java.util.Map;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import es.ozona.kairos.employee.application.internal.commandservices.EmployeeCommandServiceImpl;
import es.ozona.kairos.employee.domain.model.commands.CreateEmployeeCommand;
import es.ozona.kairos.employee.infrastructure.brokers.EmployeeEventSource;
import es.ozona.kairos.employee.shareddomain.model.events.TimesheetEvents;
import es.ozona.kairos.employee.shareddomain.model.events.TimesheetHeaders;
import es.ozona.kairos.employee.shareddomain.model.events.UnregisteredTimesheetClockedInEvent;
import es.ozona.kairos.employee.shareddomain.model.events.UnregisteredTimesheetClockedInEventData;

@Component
public class EmployeeEventHandler {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeEventHandler.class);

	@Autowired
	private EmployeeCommandServiceImpl employeeCommandService;

	@Bean
	public Predicate<Map<String, Object>> unregisteredClockInCondition() {
		return m -> {
			LOG.info(m.get(TimesheetHeaders.TYPE.name()).toString());
			return TimesheetEvents.UNREGISTERED_CLOCKIN.name().equals(m.get(TimesheetHeaders.TYPE.name()));
		};
	}

	@Bean
	public Predicate<Map<String, Object>> anyClockInCondition() {
		return m -> !TimesheetEvents.UNREGISTERED_CLOCKIN.name().equals(m.get(TimesheetHeaders.TYPE.name()));
	}

	@StreamListener(target = EmployeeEventSource.INPUT, condition = "@unregisteredClockInCondition.test(headers)")
	public void observeEmployeeUnregisteredClocInEvent(UnregisteredTimesheetClockedInEvent event) {

		UnregisteredTimesheetClockedInEventData eventData = event.getUnregisteredTimesheetClockInEventData();

		LOG.debug("Clock created event received for ID {}", eventData.getEmployeeId());

		final CreateEmployeeCommand createEmployeeCommand = new CreateEmployeeCommand(eventData.getEmployeeId(), eventData.getUseraname(), "autoname",
				"autolastname", "autoemail", "autopreferredlanguage", null, "autoworkplace");

		employeeCommandService.createEmployeeAuto(createEmployeeCommand);

	}

	@StreamListener(target = EmployeeEventSource.INPUT, condition = "@anyClockInCondition.test(headers)")
	public void observeAnyClocInEvent(Object event) {
		// UnregisteredTimesheetClockedInEventData eventData = event.getUnregisteredTimesheetClockInEventData();
		LOG.debug("No hacer nada.");
	}

}
