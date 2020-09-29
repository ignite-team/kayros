package es.ozona.kairos.calendar.domain.model.commands;

import java.time.LocalDate;

import org.springframework.util.ObjectUtils;

public class DeleteCalendarHolidayCommand {
	private String calendarId;
	private LocalDate date;

	public DeleteCalendarHolidayCommand() {

	}

	public DeleteCalendarHolidayCommand(String calendarId, LocalDate date) {
		super();
		this.calendarId = calendarId;
		this.date = date;
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

		if (obj == null || !(obj instanceof DeleteCalendarHolidayCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
