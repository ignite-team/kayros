package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

/**
 * Class that represents the data involved in the calendar details modification command and is associated with the modification event.
 * 
 * @author Xose
 * @since 1.0.0
 */
public class CalendarDetailModifiedEventData extends AbstractEvent {
	private String calendarId;
	private String title;
	private String description;

	/**
	 * Default constructor
	 */
	public CalendarDetailModifiedEventData() {

	}

	/**
	 * A constructor that initializes the state of all the elements of the class.
	 * 
	 * @param calendarId  ID of modified calendar
	 * @param title       calendar title new value
	 * @param description calendar description new value
	 */
	public CalendarDetailModifiedEventData(String calendarId, String title, String description) {
		this.calendarId = calendarId;
		this.title = title;
	}

	/**
	 * Get the calendar ID value.
	 * 
	 * @return the value.
	 */
	public String getCalendarId() {
		return calendarId;
	}

	/**
	 * Set the calendar ID value.
	 * 
	 * @param calendarId new calendar ID value.
	 */
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	/**
	 * Get the calendar title value.
	 * 
	 * @return the value.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the calendar title value.
	 * 
	 * @param title new title value.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the calendar description value.
	 * 
	 * @return the description value.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the calendar description value.
	 * 
	 * @param description new description value.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, title, description });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarDetailModifiedEventData)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
