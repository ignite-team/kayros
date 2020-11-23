package es.ozona.kairos.employee.domain.model.valueobjects;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class ScheduleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "schedule_id", columnDefinition = "uuid", unique = true, updatable = false, insertable = true)
	private UUID scheduleId;

	public ScheduleId() {

	}

	public ScheduleId(String scheduleId) {
		this.scheduleId = UUID.fromString(scheduleId);
	}

	public String getScheduleId() {
		return scheduleId.toString();
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = UUID.fromString(scheduleId);
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { scheduleId });

	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof ScheduleId)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
