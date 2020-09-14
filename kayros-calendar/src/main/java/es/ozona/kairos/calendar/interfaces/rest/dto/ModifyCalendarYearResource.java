package es.ozona.kairos.calendar.interfaces.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.util.ObjectUtils;

public class ModifyCalendarYearResource {
	@NotNull
	@Min(value = 1975)
	@Max(value = 2099)
	private Integer year;

	public ModifyCalendarYearResource() {
	}

	public ModifyCalendarYearResource(Integer year) {
		this.year = year;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(year);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ModifyCalendarYearResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
