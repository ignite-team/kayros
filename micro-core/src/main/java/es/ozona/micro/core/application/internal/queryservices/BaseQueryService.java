package es.ozona.micro.core.application.internal.queryservices;

import java.io.Serializable;
import java.util.List;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.data.inquire.model.query.QueryObject;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;

public interface BaseQueryService<E, T extends Serializable, R extends BaseRepository<E, T>> {

	List<E> search();

	PageResult<E> search(QueryObject qo);

}
