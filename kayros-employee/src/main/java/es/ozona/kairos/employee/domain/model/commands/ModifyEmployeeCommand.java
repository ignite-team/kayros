package es.ozona.kairos.employee.domain.model.commands;

import org.springframework.util.ObjectUtils;

public class ModifyEmployeeCommand {

	private String employeeId;

	private String username;

	private String firstname;

	private String lastname;

	private String email;

	private String preferredLanguage;

	private Boolean telecommuting;

	private String workplace;

	public ModifyEmployeeCommand() {

	}

	public ModifyEmployeeCommand(String employeeId, String username, String firstname, String lastname, String email, String preferredLanguage,
			Boolean telecommuting, String workplace) {

		super();
		this.employeeId = employeeId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.preferredLanguage = preferredLanguage;
		this.telecommuting = telecommuting;
		this.workplace = workplace;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public Boolean getTelecommuting() {

		return telecommuting;

	}

	public void setTelecommuting(Boolean telecommuting) {

		this.telecommuting = telecommuting;

	}

	public String getWorkplace() {

		return workplace;

	}

	public void setWorkplace(String workplace) {

		this.workplace = workplace;

	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { employeeId, username, firstname, lastname, email, preferredLanguage, telecommuting, workplace });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ModifyEmployeeCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
