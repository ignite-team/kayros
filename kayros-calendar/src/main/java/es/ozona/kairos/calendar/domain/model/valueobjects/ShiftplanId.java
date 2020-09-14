package es.ozona.kairos.calendar.domain.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class ShiftplanId {

	@Column(name = "shiftplan_id", nullable = false, unique = true, length = 500)
	private String shiftplanId;

	public ShiftplanId() {

	}

	public ShiftplanId(String shiftplanId) {
		this.shiftplanId = shiftplanId;
	}

	public String getShiftplanId() {
		return shiftplanId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftplanId);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanId)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
