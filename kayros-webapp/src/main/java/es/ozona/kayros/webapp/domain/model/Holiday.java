package es.ozona.kayros.webapp.domain.model;

import org.springframework.util.ObjectUtils;

public class Holiday {

	private String holiday;

	public Holiday() {

	}

	public Holiday(String holiday) {

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

		if (obj == null || !(obj instanceof Holiday)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}
}
