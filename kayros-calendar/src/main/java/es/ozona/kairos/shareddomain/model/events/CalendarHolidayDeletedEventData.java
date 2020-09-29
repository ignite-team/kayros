package es.ozona.kairos.shareddomain.model.events;

import java.time.LocalDate;

import org.springframework.util.ObjectUtils;

public class CalendarHolidayDeletedEventData {

	private String calendarId;
	private LocalDate date;

	public CalendarHolidayDeletedEventData() {

	}

	public CalendarHolidayDeletedEventData(String calendarId, LocalDate date) {
		this.date = date;
		this.calendarId = calendarId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, date });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarHolidayDeletedEventData)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
