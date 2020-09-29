package es.ozona.rest.inquiry.sort;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import es.ozona.data.inquire.model.sort.SortParser;

public class SortTest {
	private static final String NAME = "name";
	private static final String FIRSTNAME = "firstname";
	private static final String ASC = "+";
	private static final String DESC = "-";

	@Test
	public void givenAscSortExp_whenParsing_thenReturnsSort() {
		final String sortExpression = ASC + NAME;
		final Sort sort = SortParser.parse(sortExpression);
		assertThat(sort.iterator().next().getDirection()).isEqualTo(Direction.ASC);
		assertThat(sort.iterator().next().getProperty()).isEqualTo(NAME);
	}

	@Test
	public void givenDefaultSortExp_whenParsing_thenReturnsSort() {
		final String sortExpression = NAME;
		final Sort sort = SortParser.parse(sortExpression);
		assertThat(sort.iterator().next().getDirection()).isEqualTo(Direction.ASC);
		assertThat(sort.iterator().next().getProperty()).isEqualTo(NAME);
	}

	@Test
	public void givenComposeSort_whenParsing_thenReturnsComposeSort() {
		final String sortExpression = DESC + NAME + ',' + FIRSTNAME;
		final Sort sort = SortParser.parse(sortExpression);

		Order order = sort.iterator().next();

		if (order.getProperty().equals(NAME)) {
			assertThat(sort.iterator().next().getDirection()).isEqualTo(Direction.DESC);
		} else if (order.getProperty().equals(FIRSTNAME)) {
			assertThat(order.getDirection()).isEqualTo(Direction.ASC);
		} else {
			assertThat(true).isEqualTo(false);
		}

		order = sort.iterator().next();

		if (order.getProperty().equals(NAME)) {
			assertThat(sort.iterator().next().getDirection()).isEqualTo(Direction.DESC);
		} else if (order.getProperty().equals(FIRSTNAME)) {
			assertThat(order.getDirection()).isEqualTo(Direction.ASC);
		} else {
			assertThat(true).isEqualTo(false);
		}
	}

}
