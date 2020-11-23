package es.ozona.kairos.calendar.domain.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.util.ObjectUtils;

@Entity
@Table(name = "calendar_holidays")
public class Holiday implements Comparable<Holiday>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate holiday;

	public Holiday() {

	}

	public Holiday(LocalDate holiday) {
		super();
		this.id = 1L;
		this.holiday = holiday;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getHoliday() {
		return holiday;
	}

	@Override
	public int compareTo(Holiday o) {

		return this.compareTo(o);
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { holiday });
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Holiday other = (Holiday) obj;

		return new EqualsBuilder().append(this.holiday, other.holiday).build();
	}

}
