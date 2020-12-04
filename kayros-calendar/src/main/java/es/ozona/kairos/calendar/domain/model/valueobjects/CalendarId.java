package es.ozona.kairos.calendar.domain.model.valueobjects;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class CalendarId {

	@Column(name = "calendar_id", nullable = false, columnDefinition = "uuid")
	private UUID calendarId;

	public CalendarId() {

	}

	public CalendarId(String calendarId) {
		super();
		this.calendarId = calendarId != null ? UUID.fromString(calendarId) : null;
	}

	public String getCalendarId() {
		return calendarId != null ? calendarId.toString() : null;
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
