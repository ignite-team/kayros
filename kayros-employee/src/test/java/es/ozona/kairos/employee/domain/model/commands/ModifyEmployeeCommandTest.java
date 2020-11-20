package es.ozona.kairos.employee.domain.model.commands;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModifyEmployeeCommandTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private ModifyEmployeeCommand emptyModifyEmployeeCommand;
	private ModifyEmployeeCommand modifyEmployeeCommand;

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

		emptyModifyEmployeeCommand = new ModifyEmployeeCommand();
		modifyEmployeeCommand = new ModifyEmployeeCommand(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

	}

	@Test
	protected void givenModifyEmployeeCommand_whenModifyEmployeeCommandGetEmployeeID_thenReturnEmployeeId() {

		assertThat(modifyEmployeeCommand.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenModifyEmployeeCommand_whenModifyEmployeeCommandGetUsername_thenReturnUsername() {

		assertThat(modifyEmployeeCommand.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenModifyEmployeeCommand_whenModifyEmployeeCommandGetFirstName_thenReturnFirstName() {

		assertThat(modifyEmployeeCommand.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenModifyEmployeeCommand_whenModifyEmployeeCommandGetLastName_thenReturnLastName() {

		assertThat(modifyEmployeeCommand.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenModifyEmployeeCommand_whenModifyEmployeeCommandGetEmail_thenReturnEmail() {

		assertThat(modifyEmployeeCommand.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenModifyEmployeeCommand_whenModifyEmployeeCommandGetPreferredLanguage_thenReturnlanguage() {

		assertThat(modifyEmployeeCommand.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenModifyEmployeeCommand_whenModifyEmployeeCommandGetTelecommuting_thenReturnTelecommuting() {

		assertThat(modifyEmployeeCommand.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenModifyEmployeeCommand_whenModifyEmployeeCommandGetWorkplace_thenReturnWorkplace() {

		assertThat(modifyEmployeeCommand.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommand_whenEmptyModifyEmployeeCommandSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyModifyEmployeeCommand.setEmployeeId(employeeId);
		assertThat(emptyModifyEmployeeCommand.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommand_whenEmptyModifyEmployeeCommandSetUsernameAndGetUsername_thenReturnUsername() {

		emptyModifyEmployeeCommand.setUsername(username);
		assertThat(emptyModifyEmployeeCommand.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommand_whenEmptyModifyEmployeeCommandSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyModifyEmployeeCommand.setFirstname(firstName);
		assertThat(emptyModifyEmployeeCommand.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmptyCreateEmployeeCommand_whenEmptyModifyEmployeeCommandSetLastNameAndGetLastName_thenReturnLastName() {

		emptyModifyEmployeeCommand.setLastname(lastName);
		assertThat(emptyModifyEmployeeCommand.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommand_whenEmptyModifyEmployeeCommandSetEmailAndGetEmail_thenReturnEmail() {

		emptyModifyEmployeeCommand.setEmail(email);
		assertThat(emptyModifyEmployeeCommand.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommand_whenEmptyModifyEmployeeCommandSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyModifyEmployeeCommand.setPreferredLanguage(preferredLanguage);
		assertThat(emptyModifyEmployeeCommand.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommand_whenEmptyModifyEmployeeCommandSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyModifyEmployeeCommand.setTelecommuting(telecommuting);
		assertThat(emptyModifyEmployeeCommand.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmptyModifyEmployeeCommand_whenEmptyModifyEmployeeCommandSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyModifyEmployeeCommand.setWorkplace(workplace);
		assertThat(emptyModifyEmployeeCommand.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmployeeModifyEventData_whenEqualsWithEmptyModifyEmployeeCommand_thenReturnFalse() {

		assertThat(modifyEmployeeCommand.equals(emptyModifyEmployeeCommand)).isFalse();

	}

	@Test
	protected void givenModifyEmployeeCommand_whenEqualsWithNull_thenReturnFalse() {

		assertThat(modifyEmployeeCommand.equals(null)).isFalse();

	}

	@Test
	protected void givenModifyEmployeeCommand_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(modifyEmployeeCommand.equals(email)).isFalse();

	}

	@Test
	protected void givenModifyEmployeeCommand_whenEqualsWithModifyEmployeeCommand_thenReturnTrue() {

		assertThat(modifyEmployeeCommand.equals(modifyEmployeeCommand)).isTrue();

	}

}
