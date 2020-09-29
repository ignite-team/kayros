package es.ozona.kairos.calendar.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.micro.core.infrastructure.broker.EventSource;

/**
 * Published & Subscribed channels
 */
public interface ShiftplanEventSource extends EventSource<Shiftplan> {

	public static final String INPUT = "calendarManagementInputChannel";
	public static final String OUTPUT = "shiftplanManagementOutputChannel";

	@Input(ShiftplanEventSource.INPUT)
	SubscribableChannel calendarManagementSource();

	@Output(ShiftplanEventSource.OUTPUT)
	MessageChannel shiftplanManagement();

}
