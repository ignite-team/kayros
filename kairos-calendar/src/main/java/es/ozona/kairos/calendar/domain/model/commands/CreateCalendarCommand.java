package es.ozona.kairos.calendar.domain.model.commands;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.ObjectUtils;

/**
 * This class represents a create calendar command.
 * 
 * @author Xose
 * 
 */
public class CreateCalendarCommand {
	@Length(min = 16)
	private String calendarId;

	@NotNull
	@Min(value = 1975)
	@Max(value = 2099)
	private Integer year;

	@NotEmpty
	private String title;

	@Length(max = 4000)
	private String description;

	@NotNull
	private Boolean markedAsDefault;

	/**
	 * Default constructor.
	 */
	public CreateCalendarCommand() {
	}

	/**
	 * Creates a Calendar specifying all parameters.
	 * 
	 * @param calendarId  ID value
	 * @param year        calendar year
	 * @param title       calendar title
	 * @param description calendar description
	 */
	public CreateCalendarCommand(String calendarId, Integer year, String title, String description, Boolean markedAsDefault) {
		this.calendarId = calendarId;
		this.year = year;
		this.title = title;
		this.description = description;
		this.markedAsDefault = markedAsDefault;
	}

	/**
	 * Gets the calendar ID
	 * 
	 * @return ID value
	 */
	public String getCalendarId() {
		return calendarId;
	}

	/**
	 * Sets a calendar ID
	 * 
	 * @param calendarId new calendar ID
	 */
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	/**
	 * Gets calendar year
	 * 
	 * @return year value
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets a calendar year
	 * 
	 * @param year new year value
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Gets calendar title
	 * 
	 * @return the calendar value
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets a calendar title
	 * 
	 * @param title a new calendar value
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets a calendar description
	 * 
	 * @return the calendar description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets a calendar description
	 * 
	 * @param description a new calendar description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets a calendar markedasdefault.
	 * 
	 * @return the calendar markedadefult
	 */
	public Boolean getMarkedAsDefault() {
		return markedAsDefault;
	}	
	
	/**
	 * Sets a calendar markedasdefault
	 * 
	 * @param description new value to markedAsDefault
	 */
	public void setMarkedAsDefault(Boolean markedAsDefault) {
		this.markedAsDefault = markedAsDefault;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, year, title, description, markedAsDefault });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CreateCalendarCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
