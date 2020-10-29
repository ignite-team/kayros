package es.ozona.kairos.employee.interfaces.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateEmployeeCommandResourceTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private CreateEmployeeCommandResource emptyCreateEmployeeCommandResource;
	private CreateEmployeeCommandResource createEmployeeCommandResource;

	@BeforeEach
	public void init() {

		employeeId = "e7c1c31b-c936-4a8b-ad9e-46a4a86381cd";
		username = "username";
		firstName = "firstname";
		lastName = "lastname";
		email = "email";
		preferredLanguage = "es_ES";
		telecommuting = false;
		workplace = "workplace";

		emptyCreateEmployeeCommandResource = new CreateEmployeeCommandResource();
		createEmployeeCommandResource = new CreateEmployeeCommandResource(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenCreateEmployeeCommandResourceGetEmployeeID_thenReturnEmployeeId() {

		assertThat(createEmployeeCommandResource.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenCreateEmployeeCommandResourceGetUsername_thenReturnUsername() {

		assertThat(createEmployeeCommandResource.getUsername()).isEqualTo(username);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenCreateEmployeeCommandResourceGetFirstName_thenReturnFirstName() {

		assertThat(createEmployeeCommandResource.getFirstname()).isEqualTo(firstName);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenCreateEmployeeCommandResourceGetLastName_thenReturnLastName() {

		assertThat(createEmployeeCommandResource.getLastname()).isEqualTo(lastName);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenCreateEmployeeCommandResourceGetEmail_thenReturnEmail() {

		assertThat(createEmployeeCommandResource.getEmail()).isEqualTo(email);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenCreateEmployeeCommandResourceGetPreferredLanguage_thenReturnlanguage() {

		assertThat(createEmployeeCommandResource.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenCreateEmployeeCommandResourceGetTelecommuting_thenReturnTelecommuting() {

		assertThat(createEmployeeCommandResource.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenCreateEmployeeCommandResourceGetWorkplace_thenReturnWorkplace() {

		assertThat(createEmployeeCommandResource.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenEmptyCreateEmployeeCommandResource_whenEmptyCreateEmployeeCommandResourceSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyCreateEmployeeCommandResource.setEmployeeId(employeeId);
		assertThat(emptyCreateEmployeeCommandResource.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenEmptyCreateEmployeeCommandResource_whenEmptyCreateEmployeeCommandResourceSetUsernameAndGetUsername_thenReturnUsername() {

		emptyCreateEmployeeCommandResource.setUsername(username);
		assertThat(emptyCreateEmployeeCommandResource.getUsername()).isEqualTo(username);

	}

	@Test
	public void givenEmptyCreateEmployeeCommandResource_whenEmptyCreateEmployeeCommandResourceSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyCreateEmployeeCommandResource.setFirstname(firstName);
		assertThat(emptyCreateEmployeeCommandResource.getFirstname()).isEqualTo(firstName);

	}

	@Test
	public void givenEmptyCreateEmployeeCommandResource_whenEmptyCreateEmployeeCommandResourceSetLastNameAndGetLastName_thenReturnLastName() {

		emptyCreateEmployeeCommandResource.setLastname(lastName);
		assertThat(emptyCreateEmployeeCommandResource.getLastname()).isEqualTo(lastName);

	}

	@Test
	public void givenEmptyCreateEmployeeCommandResource_whenEmptyCreateEmployeeCommandResourceSetEmailAndGetEmail_thenReturnEmail() {

		emptyCreateEmployeeCommandResource.setEmail(email);
		assertThat(emptyCreateEmployeeCommandResource.getEmail()).isEqualTo(email);

	}

	@Test
	public void givenEmptyCreateEmployeeCommandResource_whenEmptyCreateEmployeeCommandResourceSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyCreateEmployeeCommandResource.setPreferredLanguage(preferredLanguage);
		assertThat(emptyCreateEmployeeCommandResource.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	public void givenEmptyCreateEmployeeCommandResource_whenEmptyCreateEmployeeCommandResourceSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyCreateEmployeeCommandResource.setTelecommuting(telecommuting);
		assertThat(emptyCreateEmployeeCommandResource.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenEmptyCreateEmployeeCommandResource_whenEmptyCreateEmployeeCommandResourceSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyCreateEmployeeCommandResource.setWorkplace(workplace);
		assertThat(emptyCreateEmployeeCommandResource.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenEqualsWithEmptyCreateEmployeeCommandResource_thenReturnFalse() {

		assertThat(createEmployeeCommandResource.equals(emptyCreateEmployeeCommandResource)).isFalse();

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(createEmployeeCommandResource.equals(null)).isFalse();

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(createEmployeeCommandResource.equals(email)).isFalse();

	}

	@Test
	public void givenCreateEmployeeCommandResource_whenEqualsWithCreateEmployeeCommandResource_thenReturnTrue() {

		assertThat(createEmployeeCommandResource.equals(createEmployeeCommandResource)).isTrue();

	}

}
