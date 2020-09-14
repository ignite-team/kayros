package es.ozona.kairos.shareddomain.model.events;

import java.time.LocalDate;

import org.springframework.util.ObjectUtils;

public class CalendarHolidayAddedEventData extends AbstractEvent {
	private String calendarId;
	private LocalDate holiday;

	public CalendarHolidayAddedEventData() {

	}

	public CalendarHolidayAddedEventData(String calendarId, LocalDate holiday) {
		this.holiday = holiday;
		this.calendarId = calendarId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public LocalDate getHoliday() {
		return holiday;
	}

	public void setHoliday(LocalDate holiday) {
		this.holiday = holiday;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, holiday });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarHolidayAddedEventData)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
