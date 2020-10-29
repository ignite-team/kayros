package es.ozona.micro.core.application.internal.queryservices;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.data.inquire.model.query.QueryObject;
import es.ozona.micro.core.infrastructure.respository.BaseRepository;

public class BaseQueryServiceImpl<E, T extends Serializable, R extends BaseRepository<E, T>> implements BaseQueryService<E, T, R> {

	@Autowired
	protected R repository;

	@Override
	public List<E> search() {
		return repository.search();
	}

	@Override
	public PageResult<E> search(QueryObject qo) {
		return repository.search(qo);
	}

}
