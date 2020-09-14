package es.ozona.kairos.calendar.domain.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class CalendarId {

	@Column(name = "calendar_id", nullable = false, length = 500)
	private String calendarId;

	public CalendarId() {

	}

	public CalendarId(String calendarId) {
		super();
		this.calendarId = calendarId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(calendarId);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarId)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
