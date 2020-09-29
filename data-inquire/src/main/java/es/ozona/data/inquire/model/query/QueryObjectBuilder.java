package es.ozona.data.inquire.model.query;

public class QueryObjectBuilder {
	protected QueryObject qo;

	private QueryObjectBuilder(QueryObject qo) {
		this.qo = qo;
	}

	public static SortingAndPagingBuilder setQuery(String query) {
		QueryObject qo = new SimpleQueryObject();
		qo.setQuery(query);

		return new SortingAndPagingBuilder(qo);
	}

	public static PagingBuilder setSort(String sort) {
		QueryObject qo = new SimpleQueryObject();
		qo.setSort(sort);

		return new PagingBuilder(qo);
	}

	public static QueryObjectBuilder setPage(int page, int size) {
		QueryObject qo = new SimpleQueryObject();
		qo.setPage(page);
		qo.setSize(size);

		return new QueryObjectBuilder(qo);
	}

	public QueryObject build() {
		return qo;
	}

	public static class SortingAndPagingBuilder {
		protected QueryObject qo;

		private SortingAndPagingBuilder(QueryObject qo) {
			this.qo = qo;
		}

		public PagingBuilder setSort(String sort) {
			this.qo.setSort(sort);
			return new PagingBuilder(qo);
		}

		public QueryObjectBuilder setPage(int page, int size) {
			return new PagingBuilder(qo).setPage(page, size);
		}

		public QueryObject build() {
			return qo;
		}

	}

	public static class PagingBuilder {
		protected QueryObject qo;

		private PagingBuilder(QueryObject qo) {
			this.qo = qo;
		}

		public QueryObjectBuilder setPage(int page, int size) {
			this.qo.setPage(page);
			this.qo.setSize(size);
			return new QueryObjectBuilder(qo);
		}

		public QueryObject build() {
			return qo;
		}
	}

}
