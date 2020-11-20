package es.ozona.kairos.employee.shareddomain.model;

import java.io.Serializable;

import org.springframework.util.ObjectUtils;

public class Calendar implements Serializable {

	private static final long serialVersionUID = 1L;

	private String calendarId;

	private String title;

	private String description;

	private Integer year;

	private Boolean markedAsDefault;
	
	public Calendar() {
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Boolean getMarkedAsDefault() {
		return markedAsDefault;
	}

	public void setMarkedAsDefault(Boolean markedAsDefault) {
		this.markedAsDefault = markedAsDefault;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, year, title, description, markedAsDefault });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Calendar)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
