package es.ozona.micro.core.infrastructure.respository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import es.ozona.data.inquire.criteria.specification.infix.BaseSpecification;
import es.ozona.data.inquire.criteria.specification.infix.CriteriaParser;
import es.ozona.data.inquire.criteria.specification.infix.GenericSpecificationsBuilder;
import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.data.inquire.model.query.QueryObject;
import es.ozona.data.inquire.model.sort.SortParser;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	final static Logger LOGGER = LoggerFactory.getLogger(BaseRepositoryImpl.class);

	protected JpaEntityInformation<T, ?> entityInformation;
	protected EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;

	}

	@Override
	public List<T> search() {
		return this.findAll();
	}

	@Override
	public PageResult<T> seach(QueryObject qo) {

		final CriteriaParser parser = new CriteriaParser();
		final GenericSpecificationsBuilder<T> specBuilder = new GenericSpecificationsBuilder<>();
		final Specification<T> spec = specBuilder.build(parser.parse(qo.getQuery()), BaseSpecification<T>::new);

		final Page<T> page = this.findAll(spec, PageRequest.of(qo.getPage() - 1, qo.getSize(), SortParser.parse(qo.getSort())));

		final PageResult<T> pageResult = new PageResult<T>(page.toList(), page.getTotalElements(), page.getNumber() + 1, page.getSize());

		return pageResult;
	}

}
