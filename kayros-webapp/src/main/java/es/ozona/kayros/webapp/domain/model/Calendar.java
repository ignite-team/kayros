package es.ozona.kayros.webapp.domain.model;

import org.springframework.util.ObjectUtils;

public class Calendar {

	private String calendarId;

	private String title;

	private String description;

	private int year;

	private boolean markedAsDefault;

	public Calendar() {

	}

	public Calendar(String calendarId, String title, String description, int year, boolean markedAsDefault) {

		super();
		this.calendarId = calendarId;
		this.title = title;
		this.description = description;
		this.year = year;
		this.markedAsDefault = markedAsDefault;

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean getMarkedAsDefault() {
		return markedAsDefault;
	}

	public void setMarkedAsDefault(boolean markedAsDefault) {
		this.markedAsDefault = markedAsDefault;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, title, description, year, markedAsDefault });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Calendar)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
