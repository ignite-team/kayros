package es.ozona.kairos.employee.interfaces.rest.dto;

import org.springframework.util.ObjectUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A employee resource.")
public class EmployeeResource {

	@ApiModelProperty(value = "The employee ID.", required = false, position = 1, allowEmptyValue = false, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String employeeId;

	@ApiModelProperty(value = "The employee user name.", required = true, position = 2, allowEmptyValue = false, example = "rcastro")
	private String username;

	@ApiModelProperty(value = "The employee first name.", required = true, position = 3, allowEmptyValue = false, example = "Rosalia")
	private String firstname;

	@ApiModelProperty(value = "The employee last name.", required = true, position = 4, allowEmptyValue = false, example = "de Castro")
	private String lastname;

	@ApiModelProperty(value = "The employee email.", required = true, position = 5, allowEmptyValue = false, example = "rosalia.decastro@gmail.com")
	private String email;

	@ApiModelProperty(value = "The employee preferred language.", required = true, position = 6, allowEmptyValue = false, example = "es_ES")
	private String preferredLanguage;

	@ApiModelProperty(value = "The employee is telecommiting.", required = true, position = 7, allowEmptyValue = false)
	private Boolean telecommuting;

	@ApiModelProperty(value = "The employee workplace.", required = true, position = 8, allowEmptyValue = false, example = "San Marcos")
	private String workplace;

	public EmployeeResource() {

	}

	public EmployeeResource(String employeeId, String username, String firstname, String lastname, String email, String preferredLanguage,
			Boolean telecommuting, String workplace) {

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

		if (!(obj instanceof EmployeeResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
