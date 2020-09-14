package es.ozona.kairos.calendar.interfaces.rest.dto;

import java.io.Serializable;

import org.springframework.util.ObjectUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A calendar view")
public class CalendarResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3235333419643195533L;

	@ApiModelProperty(value = "The calendar ID, must be unique", required = false, allowEmptyValue = true, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String calendarId;

	@ApiModelProperty(value = "The calendar title", required = true, allowEmptyValue = false, example = "Title 1")
	private String title;

	@ApiModelProperty(value = "The calendar description", required = false, allowEmptyValue = false, example = "This is a calendar for 2020 year")
	private String description;

	@ApiModelProperty(value = "Year of validity", required = true, example = "2020", allowableValues = "range[1975, 2099]")
	private Integer year;

	@ApiModelProperty(value = "It's default calendar", required = true, example = "true")
	private Boolean markedAsDefault;

	public CalendarResource() {
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getMarkedAsDefault() {
		return markedAsDefault;
	}

	public void setMarkedAsDefault(Boolean markedAsDefault) {
		this.markedAsDefault = markedAsDefault;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, year, title, description, markedAsDefault });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
