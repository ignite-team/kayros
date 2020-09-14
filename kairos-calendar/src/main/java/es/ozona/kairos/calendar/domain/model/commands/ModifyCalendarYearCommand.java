package es.ozona.kairos.calendar.domain.model.commands;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * Class that represents a calendar year modification operation.
 * 
 * @author Xose
 * @since 1.0.0
 */
public class ModifyCalendarYearCommand {

	@NotEmpty
	private String calendarId;

	@NotNull
	@Min(value = 1975)
	@Max(value = 2099)
	private Integer year;

	/**
	 * Default constructor.
	 */
	public ModifyCalendarYearCommand() {
	}

	/**
	 * Constructor that creates a command for which all parameters are specified.
	 * 
	 * @param calendarId a calendar ID.
	 * @param year       new year value.
	 */
	public ModifyCalendarYearCommand(String calendarId, Integer year) {
		this.calendarId = calendarId;
		this.year = year;
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
		Assert.hasText(calendarId, "Calendar ID must be not null and not empty.");
		this.calendarId = calendarId;
	}

	/**
	 * Gets the year value.
	 * 
	 * @return the year value.
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year value.
	 * 
	 * @param year a new year value.
	 */
	public void setYear(Integer year) {
		Assert.isTrue(year > 1974 && year < 2100, "Celandar year must be in range [1975..2099].");
		this.year = year;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { year, calendarId });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ModifyCalendarDetailCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
