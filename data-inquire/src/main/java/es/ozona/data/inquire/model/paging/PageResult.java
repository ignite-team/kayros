package es.ozona.data.inquire.model.paging;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageResult<T> {

	private List<T> items;
	private Long totalCount;
	private int page;
	private int size;

	public PageResult() {

	}

	public PageResult(List<T> list, Long totalCount, int page, int size) {
		super();
		this.items = list;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
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

	public <U> PageResult<U> map(Function<T, U> converter) {
		final PageResult<U> mappedPage = new PageResult<U>();
		mappedPage.setPage(this.getPage());
		mappedPage.setSize(this.getSize());
		mappedPage.setTotalCount((this.getTotalCount()));
		mappedPage.setItems(this.items.stream().map(converter).collect(Collectors.toList()));
		return mappedPage;
	}

}
