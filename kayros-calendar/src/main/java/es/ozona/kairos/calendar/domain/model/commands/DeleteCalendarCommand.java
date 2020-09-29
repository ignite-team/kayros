package es.ozona.kairos.calendar.domain.model.commands;

import org.springframework.util.ObjectUtils;

public class DeleteCalendarCommand {
	private String calendarId;

	public DeleteCalendarCommand() {
	}

	public DeleteCalendarCommand(String calendarId) {
		this.calendarId = calendarId;
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

		if (obj == null || !(obj instanceof DeleteCalendarCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
