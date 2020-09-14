package es.ozona.kairos.employee.domain.model.commands;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.employee.interfaces.rest.dto.EmployeeResource;

public class CreateEmployeeCommand {

	private String employeeId;

	private String username;

	private String email;

	private String firstname;
	
	private String lastname;

	public CreateEmployeeCommand() {
		
	}
	
	public CreateEmployeeCommand(String employeeId, String username, String email, String firstname, String lastname) {
		super();
		this.employeeId = employeeId;
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

		return ObjectUtils.nullSafeHashCode(new Object[] { employeeId, username, email, firstname, lastname });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof EmployeeResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
