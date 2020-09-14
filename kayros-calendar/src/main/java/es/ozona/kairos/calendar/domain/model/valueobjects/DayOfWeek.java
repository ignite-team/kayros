package es.ozona.kairos.calendar.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DayOfWeek {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

	@JsonValue
	public Integer getDayOfWeek() {
		return ordinal();
	};
}
