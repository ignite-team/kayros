package es.ozona.kairos.calendar.interfaces.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.ObjectUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A create calendar resource")
public class CreateCalendarResource {

	@Length(min = 16, max = 36)
	@ApiModelProperty(value = "The calendar ID, must be unique", required = false, allowEmptyValue = true, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String calendarId;

	@NotEmpty
	@ApiModelProperty(value = "The calendar title", required = true, allowEmptyValue = false, example = "Title 1")
	private String title;

	@Length(max = 4000)
	@ApiModelProperty(value = "The calendar description", required = false, allowEmptyValue = false, example = "This is a calendar for 2020 year")
	private String description;

	@Min(value = 1978)
	@Max(value = 2099)
	@NotNull
	@ApiModelProperty(value = "Year of validity", required = true, example = "2020", allowableValues = "range[1975, 2099]")
	private Integer year;

	@NotNull
	@ApiModelProperty(value = "It's default calendar", required = true, example = "true")
	private Boolean markedAsDefault;

	public CreateCalendarResource() {
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

		if (obj == null || !(obj instanceof CreateCalendarResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
