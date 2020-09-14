package es.ozona.micro.core.application.internal.queryservices;

import org.junit.jupiter.api.Test;

import es.ozona.data.inquire.model.query.QueryObjectBuilder;


public class QueryObjectBuilderTest {

	@Test
	public void givenQuery_whenBuild_thenReturnsQueryObjectWithThatQuery() {
		System.out.println(QueryObjectBuilder.setQuery("hola: hola").build());
		System.out.println(QueryObjectBuilder.setSort("+hola").build());
		System.out.println(QueryObjectBuilder.setPage(1, 10).build());
		System.out.println(QueryObjectBuilder.setQuery("query").setPage(1, 10).build());
		System.out.println(QueryObjectBuilder.setQuery("query").setSort("-kaka").setPage(1, 10).build());
		System.out.println(QueryObjectBuilder.setQuery("query").setSort("-kaka").build());

		System.out.println(QueryObjectBuilder.setSort("-kaka").setPage(1, 10).build());
		System.out.println(QueryObjectBuilder.setSort("-kaka").build());
	}

}
