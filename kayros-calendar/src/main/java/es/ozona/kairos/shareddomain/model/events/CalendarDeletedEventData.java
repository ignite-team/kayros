package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class CalendarDeletedEventData {
	private String calendarId;

	public CalendarDeletedEventData() {

	}

	public CalendarDeletedEventData(String calendarId) {
		this.setCalendarId(calendarId);
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(calendarId);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarDeletedEventData)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
