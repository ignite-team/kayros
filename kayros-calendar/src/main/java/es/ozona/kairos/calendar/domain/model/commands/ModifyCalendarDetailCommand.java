package es.ozona.kairos.calendar.domain.model.commands;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.ObjectUtils;

/**
 * Class that represents a calendar detail modification operation.
 * 
 * @author Xose
 * @since 1.0.0
 */
public class ModifyCalendarDetailCommand {

	@NotEmpty
	private String calendarId;

	@NotNull
	private String title;

	@Length(max = 255)
	private String description;

	/**
	 * Default constructor.
	 */
	public ModifyCalendarDetailCommand() {
	}

	/**
	 * Constructor that creates a command for which all parameters are specified.
	 * 
	 * @param calendarId  a calendar ID.
	 * @param title       new title value.
	 * @param description new description value.
	 */
	public ModifyCalendarDetailCommand(String calendarId, String title, String description) {
		this.calendarId = calendarId;
		this.title = title;
		this.description = description;
	}

	/**
	 * Gets the calendar ID.
	 * 
	 * @return the ID value.
	 */
	public String getCalendarId() {
		return calendarId;
	}

	/**
	 * Sets a calendar ID.
	 * 
	 * @param calendarId new calendar ID.
	 */
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title value.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets a title.
	 * 
	 * @param title new title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description value.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets a description.
	 * 
	 * @param description new description value.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { title, description, calendarId });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ModifyCalendarDetailCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
