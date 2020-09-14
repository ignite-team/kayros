package es.ozona.kairos.calendar.domain.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.util.ObjectUtils;

@Embeddable
public class Day {

	// TODO: crear indice unico para los dias de las semana.
	@Column(name = "day_of_week", nullable = false)
	@Enumerated(value = EnumType.ORDINAL)
	private DayOfWeek day;

	public Day() {

	}

	public Day(DayOfWeek day) {
		this.day = day;
	}

	public DayOfWeek getDay() {
		return day;
	}

	@Override
	public String toString() {
		return day.name();
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(day);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Day)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
