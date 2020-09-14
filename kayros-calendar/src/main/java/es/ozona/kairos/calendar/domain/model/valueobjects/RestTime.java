package es.ozona.kairos.calendar.domain.model.valueobjects;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class RestTime extends ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3007415456374800361L;

	@Column(name = "rest_time")
	private LocalTime restTime;

	public RestTime() {

	}

	public RestTime(LocalTime restTime) {
		super();
		this.restTime = restTime;
	}

	public LocalTime getRestTime() {
		return restTime;
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(restTime);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof RestTime)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
