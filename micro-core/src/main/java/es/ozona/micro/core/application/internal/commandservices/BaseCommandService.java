package es.ozona.micro.core.application.internal.commandservices;

import java.io.Serializable;

import es.ozona.micro.core.infrastructure.respository.BaseRepository;

public interface BaseCommandService<E, T extends Serializable, R extends BaseRepository<E, T>> {

}
