package es.ozona.kairos.employee.interfaces.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModifyEmployeeCommandResourceTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private ModifyEmployeeCommandResource emptyModifyEmployeeCommandResource;
	private ModifyEmployeeCommandResource modifyEmployeeCommandResource;

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

		emptyModifyEmployeeCommandResource = new ModifyEmployeeCommandResource();
		modifyEmployeeCommandResource = new ModifyEmployeeCommandResource(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenModifyEmployeeCommandResourceGetEmployeeID_thenReturnEmployeeId() {

		assertThat(modifyEmployeeCommandResource.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenModifyEmployeeCommandResourceGetUsername_thenReturnUsername() {

		assertThat(modifyEmployeeCommandResource.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenModifyEmployeeCommandResourceGetFirstName_thenReturnFirstName() {

		assertThat(modifyEmployeeCommandResource.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenModifyEmployeeCommandResourceGetLastName_thenReturnLastName() {

		assertThat(modifyEmployeeCommandResource.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenModifyEmployeeCommandResourceGetEmail_thenReturnEmail() {

		assertThat(modifyEmployeeCommandResource.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenModifyEmployeeCommandResourceGetPreferredLanguage_thenReturnlanguage() {

		assertThat(modifyEmployeeCommandResource.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenModifyEmployeeCommandResourceGetTelecommuting_thenReturnTelecommuting() {

		assertThat(modifyEmployeeCommandResource.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenModifyEmployeeCommandResourceGetWorkplace_thenReturnWorkplace() {

		assertThat(modifyEmployeeCommandResource.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommandResource_whenEmptyModifyEmployeeCommandResourceSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyModifyEmployeeCommandResource.setEmployeeId(employeeId);
		assertThat(emptyModifyEmployeeCommandResource.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommandResource_whenEmptyModifyEmployeeCommandResourceSetUsernameAndGetUsername_thenReturnUsername() {

		emptyModifyEmployeeCommandResource.setUsername(username);
		assertThat(emptyModifyEmployeeCommandResource.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommandResource_whenEmptyModifyEmployeeCommandResourceSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyModifyEmployeeCommandResource.setFirstname(firstName);
		assertThat(emptyModifyEmployeeCommandResource.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommandResource_whenEmptyModifyEmployeeCommandResourceSetLastNameAndGetLastName_thenReturnLastName() {

		emptyModifyEmployeeCommandResource.setLastname(lastName);
		assertThat(emptyModifyEmployeeCommandResource.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommandResource_whenEmptyModifyEmployeeCommandResourceSetEmailAndGetEmail_thenReturnEmail() {

		emptyModifyEmployeeCommandResource.setEmail(email);
		assertThat(emptyModifyEmployeeCommandResource.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommandResource_whenEmptyModifyEmployeeCommandResourceSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyModifyEmployeeCommandResource.setPreferredLanguage(preferredLanguage);
		assertThat(emptyModifyEmployeeCommandResource.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommandResource_whenEmptyModifyEmployeeCommandResourceSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyModifyEmployeeCommandResource.setTelecommuting(telecommuting);
		assertThat(emptyModifyEmployeeCommandResource.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommandResource_whenEmptyModifyEmployeeCommandResourceSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyModifyEmployeeCommandResource.setWorkplace(workplace);
		assertThat(emptyModifyEmployeeCommandResource.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmployeeModifyEventData_whenEqualsWithEmptyModifyEmployeeCommandResource_thenReturnFalse() {

		assertThat(modifyEmployeeCommandResource).isNotEqualTo(emptyModifyEmployeeCommandResource);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(modifyEmployeeCommandResource).isNotEqualTo(null);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(modifyEmployeeCommandResource).isNotEqualTo(email);

	}

	@Test
	protected void givenModifyEmployeeCommandResource_whenEqualsWithModifyEmployeeCommandResource_thenReturnTrue() {

		assertThat(modifyEmployeeCommandResource).isEqualTo(modifyEmployeeCommandResource);

	}

}
