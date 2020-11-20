package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private Employee emptyEmployee;
	private Employee employee;

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

		emptyEmployee = new Employee();
		employee = new Employee(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetEmployeeID_thenReturnEmployeeId() {

		assertThat(employee.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetUsername_thenReturnUsername() {

		assertThat(employee.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetFirstName_thenReturnFirstName() {

		assertThat(employee.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetLastName_thenReturnLastName() {

		assertThat(employee.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetEmail_thenReturnEmail() {

		assertThat(employee.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetPreferredLanguage_thenReturnlanguage() {

		assertThat(employee.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetTelecommuting_thenReturnTelecommuting() {

		assertThat(employee.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetWorkplace_thenReturnWorkplace() {

		assertThat(employee.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyEmployee.setEmployeeId(employeeId);
		assertThat(emptyEmployee.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetUsernameAndGetUsername_thenReturnUsername() {

		emptyEmployee.setUsername(username);
		assertThat(emptyEmployee.getUsername()).isEqualTo(username);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyEmployee.setFirstname(firstName);
		assertThat(emptyEmployee.getFirstname()).isEqualTo(firstName);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetLastNameAndGetLastName_thenReturnLastName() {

		emptyEmployee.setLastname(lastName);
		assertThat(emptyEmployee.getLastname()).isEqualTo(lastName);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetEmailAndGetEmail_thenReturnEmail() {

		emptyEmployee.setEmail(email);
		assertThat(emptyEmployee.getEmail()).isEqualTo(email);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetPreferredLanguageAndGetPreferredLanguage_thenReturnPreferredLanguage() {

		emptyEmployee.setPreferredlanguage(preferredLanguage);
		assertThat(emptyEmployee.getPreferredLanguage()).isEqualTo(preferredLanguage);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyEmployee.setTelecommuting(telecommuting);
		assertThat(emptyEmployee.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyEmployee.setWorkplace(workplace);
		assertThat(emptyEmployee.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	protected void givenEmployee_whenEqualsWithEmptyEmployee_thenReturnFalse() {

		assertThat(employee.equals(emptyEmployee)).isFalse();

	}

	@Test
	protected void givenEmployee_whenEqualsWithNull_thenReturnFalse() {

		assertThat(employee.equals(null)).isFalse();

	}

	@Test
	protected void givenEmployee_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(employee.equals(email)).isFalse();

	}

	@Test
	protected void givenEmployee_whenEqualsWithEmployee_thenReturnTrue() {

		assertThat(employee.equals(employee)).isTrue();

	}

}
