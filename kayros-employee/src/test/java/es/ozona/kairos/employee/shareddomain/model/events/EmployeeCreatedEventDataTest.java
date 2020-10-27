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
	public void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetEmployeeID_thenReturnEmployeeId() {

		assertThat(employeeCreatedEventData.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetUsername_thenReturnUsername() {

		assertThat(employeeCreatedEventData.getUsername()).isEqualTo(username);

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetFirstName_thenReturnFirstName() {

		assertThat(employeeCreatedEventData.getFirstname()).isEqualTo(firstName);

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetLastName_thenReturnLastName() {

		assertThat(employeeCreatedEventData.getLastname()).isEqualTo(lastName);

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetEmail_thenReturnEmail() {

		assertThat(employeeCreatedEventData.getEmail()).isEqualTo(email);

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetPreferredLanguage_thenReturnlanguage() {

		assertThat(employeeCreatedEventData.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetTelecommuting_thenReturnTelecommuting() {

		assertThat(employeeCreatedEventData.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEmployeeCreatedEventDataGetWorkplace_thenReturnWorkplace() {

		assertThat(employeeCreatedEventData.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyEmployeeCreatedEventData.setEmployeeId(employeeId);
		assertThat(emptyEmployeeCreatedEventData.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetUsernameAndGetUsername_thenReturnUsername() {

		emptyEmployeeCreatedEventData.setUsername(username);
		assertThat(emptyEmployeeCreatedEventData.getUsername()).isEqualTo(username);

	}

	@Test
	public void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyEmployeeCreatedEventData.setFirstname(firstName);
		assertThat(emptyEmployeeCreatedEventData.getFirstname()).isEqualTo(firstName);

	}

	@Test
	public void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetLastNameAndGetLastName_thenReturnLastName() {

		emptyEmployeeCreatedEventData.setLastname(lastName);
		assertThat(emptyEmployeeCreatedEventData.getLastname()).isEqualTo(lastName);

	}

	@Test
	public void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetEmailAndGetEmail_thenReturnEmail() {

		emptyEmployeeCreatedEventData.setEmail(email);
		assertThat(emptyEmployeeCreatedEventData.getEmail()).isEqualTo(email);

	}

	@Test
	public void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyEmployeeCreatedEventData.setPreferredLanguage(preferredLanguage);
		assertThat(emptyEmployeeCreatedEventData.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	public void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyEmployeeCreatedEventData.setTelecommuting(telecommuting);
		assertThat(emptyEmployeeCreatedEventData.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenEmptyEmployeeCreatedEventData_whenEmptyEmployeeCreatedEventDataSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyEmployeeCreatedEventData.setWorkplace(workplace);
		assertThat(emptyEmployeeCreatedEventData.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEqualsWithEmptyEmployeeCreatedEventData_thenReturnFalse() {

		assertThat(employeeCreatedEventData.equals(emptyEmployeeCreatedEventData)).isFalse();

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEqualsWithNull_thenReturnFalse() {

		assertThat(employeeCreatedEventData.equals(null)).isFalse();

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(employeeCreatedEventData.equals(email)).isFalse();

	}

	@Test
	public void givenEmployeeCreatedEventData_whenEqualsWithEmployeeCreatedEventData_thenReturnTrue() {

		assertThat(employeeCreatedEventData.equals(employeeCreatedEventData)).isTrue();

	}

}
