package es.ozona.kairos.calendar.interfaces.rest.dto;

import java.time.LocalDate;

import org.springframework.util.ObjectUtils;

public class HolidayResource {

	private LocalDate holiday;

	public HolidayResource() {

	}

	public HolidayResource(LocalDate holiday) {
		this.holiday = holiday;
	}

	public LocalDate getHoliday() {
		return holiday;
	}

	public void setHoliday(LocalDate holiday) {
		this.holiday = holiday;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { holiday });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof HolidayResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
