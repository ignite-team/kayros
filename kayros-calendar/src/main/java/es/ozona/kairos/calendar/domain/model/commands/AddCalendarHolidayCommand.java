package es.ozona.kairos.calendar.domain.model.commands;

import java.time.LocalDate;

import org.springframework.util.ObjectUtils;

public class AddCalendarHolidayCommand {
	private String calendarId;
	private LocalDate holiday;

	public AddCalendarHolidayCommand() {

	}

	public AddCalendarHolidayCommand(String calendarId, LocalDate holiday) {
		this.holiday = holiday;
		this.calendarId = calendarId;
	}

	public LocalDate getHoliday() {
		return holiday;
	}

	public void setHoliday(LocalDate holiday) {
		this.holiday = holiday;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, holiday });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof AddCalendarHolidayCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
