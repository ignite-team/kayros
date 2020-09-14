package es.ozona.kairos.clock.domain.model.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.ObjectUtils;

/**
 * The {@code EmployeeId} class represents a value object that encapsulates the employee's identifier.
 * 
 * @author Xose
 *
 */
public class TimesheetId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "timesheet_id", nullable = false)
	private String timesheetId;

	/**
	 * Creates a default instance of class {@code TimesheetId}
	 */
	public TimesheetId() {

	}

	/**
	 * Create an instance of the class {@code TimesheetId} with the given arguments
	 * 
	 * @param timesheetId
	 */
	public TimesheetId(String timesheetId) {
		this.timesheetId = timesheetId;
	}

	public String getTimesheetId() {
		return timesheetId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { timesheetId });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof TimesheetId)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
