package es.ozona.micro.core.interfaces.rest;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import es.ozona.micro.core.application.internal.commandservices.BaseCommandService;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryService;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;

public class BaseControllerImpl<E, T extends Serializable, R extends BaseRepository<E, T>, C extends BaseCommandService<E, T, R>, Q extends BaseQueryService<E, T, R>> {

	@Autowired
	protected C commandService;

	@Autowired
	protected Q queryService;

	@Autowired
	protected MCModelMapper modelMapper;

}
