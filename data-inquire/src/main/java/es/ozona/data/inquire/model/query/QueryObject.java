package es.ozona.data.inquire.model.query;

public interface QueryObject {

	String getQuery();

	void setQuery(String query);

	String getSort();

	void setSort(String sort);

	int getPage();

	void setPage(int page);

	int getSize();

	void setSize(int size);

}
