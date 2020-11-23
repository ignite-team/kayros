package es.ozona.kairos.employee.shareddomain.model.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeCreatedEventDataTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private EmployeeModifiedEventData emptyEmployeeCreatedEventData;
	private EmployeeModifiedEventData employeeCreatedEventData;

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

		emptyEmployeeCreatedEventData = new EmployeeModifiedEventData();
		employeeCreatedEventData = new EmployeeModifiedEventData(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetEmployeeID_thenReturnEmployeeId() {

		assertThat(employeeCreatedEventData.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetUsername_thenReturnUsername() {

		assertThat(employeeCreatedEventData.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetFirstName_thenReturnFirstName() {

		assertThat(employeeCreatedEventData.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetLastName_thenReturnLastName() {

		assertThat(employeeCreatedEventData.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetEmail_thenReturnEmail() {

		assertThat(employeeCreatedEventData.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetPreferredLanguage_thenReturnlanguage() {

		assertThat(employeeCreatedEventData.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetTelecommuting_thenReturnTelecommuting() {

		assertThat(employeeCreatedEventData.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetWorkplace_thenReturnWorkplace() {

		assertThat(employeeCreatedEventData.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyEmployeeCreatedEventData.setEmployeeId(employeeId);
		assertThat(emptyEmployeeCreatedEventData.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetUsernameAndGetUsername_thenReturnUsername() {

		emptyEmployeeCreatedEventData.setUsername(username);
		assertThat(emptyEmployeeCreatedEventData.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyEmployeeCreatedEventData.setFirstname(firstName);
		assertThat(emptyEmployeeCreatedEventData.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetLastNameAndGetLastName_thenReturnLastName() {

		emptyEmployeeCreatedEventData.setLastname(lastName);
		assertThat(emptyEmployeeCreatedEventData.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetEmailAndGetEmail_thenReturnEmail() {

		emptyEmployeeCreatedEventData.setEmail(email);
		assertThat(emptyEmployeeCreatedEventData.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyEmployeeCreatedEventData.setPreferredLanguage(preferredLanguage);
		assertThat(emptyEmployeeCreatedEventData.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyEmployeeCreatedEventData.setTelecommuting(telecommuting);
		assertThat(emptyEmployeeCreatedEventData.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyEmployeeCreatedEventData.setWorkplace(workplace);
		assertThat(emptyEmployeeCreatedEventData.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEqualsWithEmptyEmployeeCreatedEventData_thenReturnFalse() {

		assertThat(employeeCreatedEventData).isNotEqualTo(emptyEmployeeCreatedEventData);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEqualsWithNull_thenReturnFalse() {

		assertThat(employeeCreatedEventData).isNotEqualTo(null);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(employeeCreatedEventData).isNotEqualTo(email);

	}

	@Test
	protected void givenEmployeeCreatedEventData_whenEqualsWithEmployeeCreatedEventData_thenReturnTrue() {

		assertThat(employeeCreatedEventData).isEqualTo(employeeCreatedEventData);

	}

}
