package es.ozona.kayros.webapp.shareddomain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeResourceTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private EmployeeResource emptyEmployeeResource;
	private EmployeeResource employeeResource;

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

		emptyEmployeeResource = new EmployeeResource();
		employeeResource = new EmployeeResource(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceGetEmployeeID_thenReturnEmployeeId() {

		assertThat(employeeResource.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceGetUsername_thenReturnUsername() {

		assertThat(employeeResource.getUsername()).isEqualTo(username);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceGetFirstName_thenReturnFirstName() {

		assertThat(employeeResource.getFirstname()).isEqualTo(firstName);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceGetLastName_thenReturnLastName() {

		assertThat(employeeResource.getLastname()).isEqualTo(lastName);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceGetEmail_thenReturnEmail() {

		assertThat(employeeResource.getEmail()).isEqualTo(email);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceGetPreferredLanguage_thenReturnPreferredLanguage() {

		assertThat(employeeResource.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceGetTelecommuting_thenReturnTelecommuting() {

		assertThat(employeeResource.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceGetWorkplace_thenReturnWorkplace() {

		assertThat(employeeResource.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyEmployeeResource.setEmployeeId(employeeId);
		assertThat(emptyEmployeeResource.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceSetUsernameAndGetUsername_thenReturnUsername() {

		emptyEmployeeResource.setUsername(username);
		assertThat(emptyEmployeeResource.getUsername()).isEqualTo(username);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyEmployeeResource.setFirstname(firstName);
		assertThat(emptyEmployeeResource.getFirstname()).isEqualTo(firstName);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeSetLastNameAndGetLastName_thenReturnLastName() {

		emptyEmployeeResource.setLastname(lastName);
		assertThat(emptyEmployeeResource.getLastname()).isEqualTo(lastName);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceSetEmailAndGetEmail_thenReturnEmail() {

		emptyEmployeeResource.setEmail(email);
		assertThat(emptyEmployeeResource.getEmail()).isEqualTo(email);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyEmployeeResource.setPreferredLanguage(preferredLanguage);
		assertThat(emptyEmployeeResource.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyEmployeeResource.setTelecommuting(telecommuting);
		assertThat(emptyEmployeeResource.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenEmployeeResource_whenEmployeeResourceSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyEmployeeResource.setWorkplace(workplace);
		assertThat(emptyEmployeeResource.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenEmployeeResource_whenEqualsWithEmptyEmployeeResource_thenReturnFalse() {

		assertThat(employeeResource.equals(emptyEmployeeResource)).isFalse();

	}

	@Test
	public void givenEmployeeResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(employeeResource.equals(null)).isFalse();

	}

	@Test
	public void givenEmployeeResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(employeeResource.equals(email)).isFalse();

	}

	@Test
	public void givenEmployeeResource_whenEqualsWithEmployeeResource_thenReturnTrue() {

		assertThat(employeeResource.equals(employeeResource)).isTrue();

	}
}
