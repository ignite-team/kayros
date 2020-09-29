package es.ozona.micro.core.application.internal.commandservices;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import es.ozona.micro.core.infrastructure.respository.BaseRepository;

public class BaseCommandServiceImpl<E, T extends Serializable, R extends BaseRepository<E, T>> implements BaseCommandService<E, T, R> {

	@Autowired
	protected R repository;

	@Autowired
	protected ApplicationEventPublisher publisher;

}
