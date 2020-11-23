package es.ozona.kairos.employee.domain.model.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.ObjectUtils;

@Embeddable
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Length(min = 3, max = 50)
	@Column(name = "username", nullable = false, unique = true, length = 50)
	private String username;

	@NotEmpty
	@Length(min = 3, max = 50)
	@Column(name = "firstname", nullable = false, unique = false, length = 50)
	private String firstname;

	@NotEmpty
	@Length(min = 3, max = 50)
	@Column(name = "lastname", nullable = false, unique = false, length = 50)
	private String lastname;

	@NotEmpty
	@Email
	@Length(min = 3, max = 50)
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;

	@NotEmpty
	@Length(min = 5, max = 5)
	@Column(name = "preferredLanguage", nullable = false, unique = false, length = 5)
	private String preferredLanguage;

	public UserAccount() {

	}

	public UserAccount(String username, String firstname, String lastname, String email, String preferredLanguage) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.preferredLanguage = preferredLanguage;
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

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { username, firstname, lastname, email, preferredLanguage });
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof UserAccount)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
