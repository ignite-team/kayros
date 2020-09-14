package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class CalendarCreatedEventData extends AbstractEvent {
	private String calendarId;
	private Integer year;

	public CalendarCreatedEventData() {
	}

	public CalendarCreatedEventData(String calendarId, Integer year) {
		this.calendarId = calendarId;
		this.year = year;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, year });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarCreatedEventData)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
