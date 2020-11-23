package es.ozona.kairos.employee.shareddomain.model.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeModifiedEventDataTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private EmployeeModifiedEventData emptyEmployeeModifiedEventData;
	private EmployeeModifiedEventData employeeModifiedEventData;

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

		emptyEmployeeModifiedEventData = new EmployeeModifiedEventData();
		employeeModifiedEventData = new EmployeeModifiedEventData(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting,
				workplace);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEmployeeModifiedEventDataGetEmployeeID_thenReturnEmployeeId() {

		assertThat(employeeModifiedEventData.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEmployeeModifiedEventDataGetUsername_thenReturnUsername() {

		assertThat(employeeModifiedEventData.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEmployeeModifiedEventDataGetFirstName_thenReturnFirstName() {

		assertThat(employeeModifiedEventData.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEmployeeModifiedEventDataGetLastName_thenReturnLastName() {

		assertThat(employeeModifiedEventData.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEmployeeModifiedEventDataGetEmail_thenReturnEmail() {

		assertThat(employeeModifiedEventData.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEmployeeModifiedEventDataGetPreferredLanguage_thenReturnlanguage() {

		assertThat(employeeModifiedEventData.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEmployeeModifiedEventDataGetTelecommuting_thenReturnTelecommuting() {

		assertThat(employeeModifiedEventData.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEmployeeModifiedEventDataGetWorkplace_thenReturnWorkplace() {

		assertThat(employeeModifiedEventData.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmptyEmployeeModifiedEventData_whenEmptyEmployeeModifiedEventDataSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyEmployeeModifiedEventData.setEmployeeId(employeeId);
		assertThat(emptyEmployeeModifiedEventData.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmptyEmployeeModifiedEventData_whenEmptyEmployeeModifiedEventDataSetUsernameAndGetUsername_thenReturnUsername() {

		emptyEmployeeModifiedEventData.setUsername(username);
		assertThat(emptyEmployeeModifiedEventData.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmptyEmployeeModifiedEventData_whenEmptyEmployeeModifiedEventDataSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyEmployeeModifiedEventData.setFirstname(firstName);
		assertThat(emptyEmployeeModifiedEventData.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmptyEmployeeModifiedEventData_whenEmptyEmployeeModifiedEventDataSetLastNameAndGetLastName_thenReturnLastName() {

		emptyEmployeeModifiedEventData.setLastname(lastName);
		assertThat(emptyEmployeeModifiedEventData.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmptyEmployeeModifiedEventData_whenEmptyEmployeeModifiedEventDataSetEmailAndGetEmail_thenReturnEmail() {

		emptyEmployeeModifiedEventData.setEmail(email);
		assertThat(emptyEmployeeModifiedEventData.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmptyEmployeeModifiedEventData_whenEmptyEmployeeModifiedEventDataSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyEmployeeModifiedEventData.setPreferredLanguage(preferredLanguage);
		assertThat(emptyEmployeeModifiedEventData.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmptyEmployeeModifiedEventData_whenEmptyEmployeeModifiedEventDataSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyEmployeeModifiedEventData.setTelecommuting(telecommuting);
		assertThat(emptyEmployeeModifiedEventData.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmptyEmployeeModifiedEventData_whenEmptyEmployeeModifiedEventDataSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyEmployeeModifiedEventData.setWorkplace(workplace);
		assertThat(emptyEmployeeModifiedEventData.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEqualsWithEmptyEmployeeModifiedEventData_thenReturnFalse() {

		assertThat(employeeModifiedEventData).isNotEqualTo(emptyEmployeeModifiedEventData);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEqualsWithNull_thenReturnFalse() {

		assertThat(employeeModifiedEventData).isNotEqualTo(null);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(employeeModifiedEventData).isNotEqualTo(email);

	}

	@Test
	protected void givenEmployeeModifiedEventData_whenEqualsWithmployeeModifiedEventData_thenReturnTrue() {

		assertThat(employeeModifiedEventData).isEqualTo(employeeModifiedEventData);

	}

}
