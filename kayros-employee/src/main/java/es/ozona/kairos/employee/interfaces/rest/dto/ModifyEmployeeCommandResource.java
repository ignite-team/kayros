package es.ozona.kairos.employee.interfaces.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.ObjectUtils;

import io.swagger.annotations.ApiModelProperty;

public class ModifyEmployeeCommandResource {

	@Length(min = 3, max = 50)
	@ApiModelProperty(value = "The employee identifier.", required = true, position = 1, allowEmptyValue = false, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String employeeId;

	@NotEmpty
	@Length(min = 3)
	@ApiModelProperty(value = "The employee identifier.", required = true, position = 2, allowEmptyValue = false, example = "mqueizan")
	private String username;

	@NotEmpty
	@Email
	@Length(min = 3, max = 50)
	@ApiModelProperty(value = "The employee identifier.", required = true, position = 3, allowEmptyValue = false, example = "maria.queizan@gmail.com")
	private String email;

	@NotEmpty
	@Length(min = 3, max = 50)
	@ApiModelProperty(value = "The employee identifier.", required = true, position = 4, allowEmptyValue = false, example = "Maria Xose")
	private String firstname;

	@NotEmpty
	@Length(min = 3, max = 50)
	@ApiModelProperty(value = "The employee identifier.", required = true, position = 5, allowEmptyValue = false, example = "Queizan")
	private String lastname;

	public ModifyEmployeeCommandResource() {
		
	}
	
	public ModifyEmployeeCommandResource(String employeeId, String username, String email, String firstname, String lastname) {
		super();
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { username, email, firstname, lastname });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof EmployeeResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
