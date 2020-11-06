package es.ozona.kayros.webapp.shareddomain.model;

import org.springframework.util.ObjectUtils;

public class HolidayResource {

	private String holiday;

	public HolidayResource() {

	}

	public HolidayResource(String holiday) {

		super();
		this.holiday = holiday;

	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
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
