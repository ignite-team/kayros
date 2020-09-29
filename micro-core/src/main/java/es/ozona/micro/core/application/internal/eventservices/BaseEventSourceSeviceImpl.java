package es.ozona.micro.core.application.internal.eventservices;

import org.springframework.beans.factory.annotation.Autowired;

import es.ozona.micro.core.infrastructure.broker.EventSource;

public abstract class BaseEventSourceSeviceImpl<E, Q extends EventSource<E>> implements BaseEventSourceService<E, Q> {

	@Autowired
	protected Q eventSource;

}
