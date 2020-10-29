package es.ozona.kairos.calendar.domain.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class ShiftPlanId {

	@Column(name = "shiftplan_id", nullable = false, unique = true, length = 500)
	private String shiftPlanId;

	public ShiftPlanId() {

	}

	public ShiftPlanId(String shiftPlanId) {
		this.shiftPlanId = shiftPlanId;
	}

	public String getShiftPlanId() {
		return shiftPlanId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftPlanId);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanId)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
