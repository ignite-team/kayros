package es.ozona.data.inquire.criteria.specification.infix;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class BaseSpecification<E> implements Specification<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SpecSearchCriteria criteria;

	public BaseSpecification(final SpecSearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SpecSearchCriteria getCriteria() {
		return criteria;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		final Path<?> path = nestedPath(criteria.getKey(), root);
		final Class<?> pathType = path.getJavaType();
		final Object value = coerceToObject(criteria.getValue(), pathType);

		switch (criteria.getOperation()) {
		case EQUALITY:
			return buildLogicalPredicate(builder, (Path<Object>) path, (Object) value);
		case NEGATION:
			return buildLogicalPredicate(builder, (Path<Object>) path, (Object) value);
		case GREATER_THAN:
			return buildRelationalPredicate(builder, (Path<Comparable>) path, (Comparable) value);
		case LESS_THAN:
			return buildRelationalPredicate(builder, (Path<Comparable>) path, (Comparable) value);
		case LIKE:
			return buildCharacterPredicate(builder, (Path<String>) path, (String) value);
		case STARTS_WITH:
			return buildCharacterPredicate(builder, (Path<String>) path, value + "%");
		case ENDS_WITH:
			return buildCharacterPredicate(builder, (Path<String>) path, "%" + value);
		case CONTAINS:
			return buildCharacterPredicate(builder, (Path<String>) path, "%" + value + "%");
		default:
			return null;
		}

	}

	private <Y> Predicate buildLogicalPredicate(CriteriaBuilder builder, Path<Y> path, Y value) {

		switch (criteria.getOperation()) {
		case EQUALITY:
			return builder.equal(path, value);
		case NEGATION:
			return builder.notEqual(path, value);
		default:
			return null;
		}
	}

	private <Y extends Comparable<Y>> Predicate buildRelationalPredicate(CriteriaBuilder builder, Path<Y> path, Y value) {

		switch (criteria.getOperation()) {
		case GREATER_THAN:
			return builder.greaterThan(path, value);
		case LESS_THAN:
			return builder.lessThan(path, value);
		default:
			return null;
		}
	}

	private Predicate buildCharacterPredicate(CriteriaBuilder builder, Path<String> path, String value) {

		switch (criteria.getOperation()) {
		case LIKE:
			return builder.like(path, value);
		case STARTS_WITH:
			return builder.like(path, criteria.getValue() + "%");
		case ENDS_WITH:
			return builder.like(path, "%" + criteria.getValue());
		case CONTAINS:
			return builder.like(path, "%" + criteria.getValue() + "%");
		default:
			return null;
		}
	}

	private Path<? extends Comparable<?>> nestedPath(String name, Root<E> root) {

		if (name == null)
			return null;

		final String[] paths = name.split("_");

		Path<String> pathJoin = root.get(paths[0]);

		for (int i = 1; i < paths.length; i++) {
			pathJoin = pathJoin.get(paths[i]);
		}
		return pathJoin;
	}

	private Object coerceToObject(String stringValue, Class<?> clazz) {
		Map<Class<?>, Function<String, Object>> converters = new HashMap<>();

		converters.put(Boolean.class, s -> s == null ? null : Boolean.parseBoolean(s));
		converters.put(Boolean.TYPE, s -> Boolean.parseBoolean(s));
		converters.put(Byte.class, s -> s == null ? null : Byte.parseByte(s));
		converters.put(Byte.TYPE, s -> Byte.parseByte(s));
		converters.put(Short.class, s -> s == null ? null : Short.parseShort(s));
		converters.put(Short.TYPE, s -> Short.parseShort(s));
		converters.put(Integer.class, s -> s == null ? null : Integer.parseInt(s));
		converters.put(Integer.TYPE, s -> Integer.parseInt(s));
		converters.put(Long.class, s -> s == null ? null : Long.parseLong(s));
		converters.put(Long.TYPE, s -> Long.parseLong(s));
		converters.put(Float.class, s -> s == null ? null : Float.parseFloat(s));
		converters.put(Float.TYPE, s -> Float.parseFloat(s));
		converters.put(Double.class, s -> s == null ? null : Double.parseDouble(s));
		converters.put(Double.TYPE, s -> Double.parseDouble(s));
		converters.put(String.class, s -> s);
		converters.put(LocalDate.class, s -> s == null ? null : LocalDate.parse(s, DateTimeFormatter.BASIC_ISO_DATE));
		
		return converters.get(clazz).apply(stringValue);

	}

}
