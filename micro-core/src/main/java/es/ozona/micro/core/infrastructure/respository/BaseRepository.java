package es.ozona.micro.core.infrastructure.respository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.data.inquire.model.query.QueryObject;

@NoRepositoryBean
public interface BaseRepository<E, T extends Serializable> extends JpaRepository<E, T>, JpaSpecificationExecutor<E> {

	List<E> search();

	PageResult<E> seach(QueryObject qo);

}
