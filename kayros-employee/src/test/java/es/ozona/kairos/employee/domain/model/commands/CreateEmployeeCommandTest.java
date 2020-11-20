package es.ozona.kairos.employee.domain.model.commands;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateEmployeeCommandTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private CreateEmployeeCommand emptyCreateEmployeeCommand;
	private CreateEmployeeCommand createEmployeeCommand;

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

		emptyCreateEmployeeCommand = new CreateEmployeeCommand();
		createEmployeeCommand = new CreateEmployeeCommand(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting,
				workplace);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenCreateEmployeeCommandGetEmployeeID_thenReturnEmployeeId() {

		assertThat(createEmployeeCommand.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenCreateEmployeeCommandGetUsername_thenReturnUsername() {

		assertThat(createEmployeeCommand.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenCreateEmployeeCommandGetFirstName_thenReturnFirstName() {

		assertThat(createEmployeeCommand.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenCreateEmployeeCommandGetLastName_thenReturnLastName() {

		assertThat(createEmployeeCommand.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenCreateEmployeeCommandGetEmail_thenReturnEmail() {

		assertThat(createEmployeeCommand.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenCreateEmployeeCommandGetPreferredLanguage_thenReturnlanguage() {

		assertThat(createEmployeeCommand.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenCreateEmployeeCommandGetTelecommuting_thenReturnTelecommuting() {

		assertThat(createEmployeeCommand.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenCreateEmployeeCommandGetWorkplace_thenReturnWorkplace() {

		assertThat(createEmployeeCommand.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyCreateEmployeeCommandSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyCreateEmployeeCommand.setEmployeeId(employeeId);
		assertThat(emptyCreateEmployeeCommand.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyCreateEmployeeCommandSetUsernameAndGetUsername_thenReturnUsername() {

		emptyCreateEmployeeCommand.setUsername(username);
		assertThat(emptyCreateEmployeeCommand.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyCreateEmployeeCommandSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyCreateEmployeeCommand.setFirstname(firstName);
		assertThat(emptyCreateEmployeeCommand.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyCreateEmployeeCommandSetLastNameAndGetLastName_thenReturnLastName() {

		emptyCreateEmployeeCommand.setLastname(lastName);
		assertThat(emptyCreateEmployeeCommand.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyCreateEmployeeCommandSetEmailAndGetEmail_thenReturnEmail() {

		emptyCreateEmployeeCommand.setEmail(email);
		assertThat(emptyCreateEmployeeCommand.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyCreateEmployeeCommandSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyCreateEmployeeCommand.setPreferredLanguage(preferredLanguage);
		assertThat(emptyCreateEmployeeCommand.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyCreateEmployeeCommandSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyCreateEmployeeCommand.setTelecommuting(telecommuting);
		assertThat(emptyCreateEmployeeCommand.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyCreateEmployeeCommandSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyCreateEmployeeCommand.setWorkplace(workplace);
		assertThat(emptyCreateEmployeeCommand.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenEqualsWithEmptyCreateEmployeeCommand_thenReturnFalse() {

		assertThat(createEmployeeCommand.equals(emptyCreateEmployeeCommand)).isFalse();

	}

	@Test
	protected void givenCreateEmployeeCommand_whenEqualsWithNull_thenReturnFalse() {

		assertThat(createEmployeeCommand.equals(null)).isFalse();

	}

	@Test
	protected void givenCreateEmployeeCommand_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(createEmployeeCommand.equals(email)).isFalse();

	}

	@Test
	protected void givenCreateEmployeeCommand_whenEqualsWithCreateEmployeeCommand_thenReturnTrue() {

		assertThat(createEmployeeCommand.equals(createEmployeeCommand)).isTrue();

	}

}
