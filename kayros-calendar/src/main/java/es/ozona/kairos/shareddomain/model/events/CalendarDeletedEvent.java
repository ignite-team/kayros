package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class CalendarDeletedEvent extends AbstractEvent {
	
	private CalendarDeletedEventData calendarDeleteEventData = new CalendarDeletedEventData();

	public CalendarDeletedEvent() {

	}

	public CalendarDeletedEvent(CalendarDeletedEventData calendarDeletedEventData) {
		this.calendarDeleteEventData = calendarDeletedEventData;
	}

	public CalendarDeletedEventData getCalendarDeleteEventData() {
		return calendarDeleteEventData;
	}

	public void setCalendarDeleteEventData(CalendarDeletedEventData calendarDeleteEventData) {
		this.calendarDeleteEventData = calendarDeleteEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(calendarDeleteEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarDeletedEvent)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
