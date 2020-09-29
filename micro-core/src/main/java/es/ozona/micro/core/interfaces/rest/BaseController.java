package es.ozona.micro.core.interfaces.rest;

import java.io.Serializable;

import es.ozona.micro.core.application.internal.commandservices.BaseCommandService;
import es.ozona.micro.core.application.internal.queryservices.BaseQueryService;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;

public interface BaseController<E, T extends Serializable, R extends BaseRepository<E, T>, C extends BaseCommandService<E, T, R>, Q extends BaseQueryService<E, T, R>> {

}
 