package es.ozona.kairos.employee.domain.model.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class CalendarId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "calendar_id", columnDefinition = "uuid")
	private String calendarId;

	public CalendarId() {

	}

	public CalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId });
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof EmployeeId)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
