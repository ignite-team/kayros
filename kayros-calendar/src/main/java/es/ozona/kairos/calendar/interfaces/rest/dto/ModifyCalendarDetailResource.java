package es.ozona.kairos.calendar.interfaces.rest.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarDetailCommand;

public class ModifyCalendarDetailResource {
	@NotNull
	private String title;

	@Length(max = 4000)
	private String description;

	/**
	 * Default constructor.
	 */
	public ModifyCalendarDetailResource() {
	}

	/**
	 * Constructor that creates a command for which all parameters are specified.
	 * 
	 * @param calendarId  a calendar ID.
	 * @param title       new title value.
	 * @param description new description value.
	 */
	public ModifyCalendarDetailResource(String title, String description) {
		this.title = title;
		this.description = description;
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

		return ObjectUtils.nullSafeHashCode(new Object[] { title, description });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ModifyCalendarDetailCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
