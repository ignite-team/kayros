package es.ozona.data.inquire.model.query;

public class SimpleQueryObject implements QueryObject {
	protected String query;
	protected String sort;
	protected int page;
	protected int size;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return String.format("[ query: %s, sort: %s, page: [page: %d, size: %d] ]", query, sort, page, size);
	}




}
