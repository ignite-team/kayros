package es.ozona.kairos.employee.domain.model.valueobjects;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class EmployeeId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "employee_id", columnDefinition = "uuid")
	private UUID employeeId;

	public EmployeeId() {

	}

	public EmployeeId(String employeeId) {
		this.employeeId = employeeId != null ? UUID.fromString(employeeId) : null;
	}

	public String getEmployeeId() {
		return employeeId != null ? employeeId.toString() : null;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { employeeId });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof EmployeeId)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
