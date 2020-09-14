package es.ozona.kairos.clock.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.micro.core.infrastructure.broker.EventSource;

public interface TimesheetEventSource extends EventSource<Timesheet> {

	public static final String OUTPUT = "timesheetManagementOutChannel";

	@Output(TimesheetEventSource.OUTPUT)
	MessageChannel timesheetManagement();

}
