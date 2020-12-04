package es.ozona.kairos.clock.domain.model.valueobjects;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.ObjectUtils;

/**
 * The {@code EmployeeId} class represents a value object that encapsulates the employee's identifier.
 * 
 * @author Xose
 *
 */
@Embeddable
public class EmployeeId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "employee_id", columnDefinition = "uuid")
	private UUID employeeId;

	/**
	 * Creates a default instance of class {@code EmployeeId}
	 */
	public EmployeeId() {

	}

	/**
	 * Create an instance of the class {@code EmployeeId} with the given arguments
	 * 
	 * @param employeeId
	 */
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
