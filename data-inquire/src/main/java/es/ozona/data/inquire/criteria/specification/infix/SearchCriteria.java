package es.ozona.data.inquire.criteria.specification.infix;

public class SearchCriteria {

	private String key;
	private String operation;
	private String value;

	public SearchCriteria() {

	}

	public SearchCriteria(final String key, final String operation, final String value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(final String operation) {
		this.operation = operation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
