package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeTest {

	private String employeeId;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private Boolean telecommuting;
	private String workplace;

	private Employee emptyEmployee;
	private Employee employee;

	@BeforeEach
	public void init() {

		employeeId = "e7c1c31b-c936-4a8b-ad9e-46a4a86381cd";
		username = "username";
		email = "email";
		firstName = "firstname";
		lastName = "lastname";
		telecommuting = false;
		workplace = "workplace";

		emptyEmployee = new Employee();
		employee = new Employee(employeeId, username, email, firstName, lastName, telecommuting, workplace);

	}

	@Test
	public void givenEmployee_whenEmployeeGetEmployeeID_thenReturnEmployeeId() {

		assertThat(employee.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenEmployee_whenEmployeeGetUsername_thenReturnUsername() {

		assertThat(employee.getUsername()).isEqualTo(username);

	}

	@Test
	public void givenEmployee_whenEmployeeGetEmail_thenReturnEmail() {

		assertThat(employee.getEmail()).isEqualTo(email);

	}

	@Test
	public void givenEmployee_whenEmployeeGetFirstName_thenReturnFirstName() {

		assertThat(employee.getFirstname()).isEqualTo(firstName);

	}

	@Test
	public void givenEmployee_whenEmployeeGetLastName_thenReturnLastName() {

		assertThat(employee.getLastname()).isEqualTo(lastName);

	}

	@Test
	public void givenEmployee_whenEmployeeGetTelecommuting_thenReturnTelecommuting() {

		assertThat(employee.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenEmployee_whenEmployeeGetWorkplace_thenReturnWorkplace() {

		assertThat(employee.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenEmployee_whenEmployeeSetEmployeeIdAndGetEmployee_thenReturnEmployeeId() {

		emptyEmployee.setEmployeeId(employeeId);
		assertThat(emptyEmployee.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenEmployee_whenEmployeeSetUsernameAndGetUsername_thenReturnUsername() {

		emptyEmployee.setUsername(username);
		assertThat(emptyEmployee.getUsername()).isEqualTo(username);

	}

	@Test
	public void givenEmployee_whenEmployeeSetEmailAndGetEmail_thenReturnEmail() {

		emptyEmployee.setEmail(email);
		assertThat(emptyEmployee.getEmail()).isEqualTo(email);

	}

	@Test
	public void givenEmployee_whenEmployeeSetFirstNameAndGetFirstName_thenReturnFirstName() {

		emptyEmployee.setFirstname(firstName);
		assertThat(emptyEmployee.getFirstname()).isEqualTo(firstName);

	}

	@Test
	public void givenEmployee_whenEmployeeSetLastNameAndGetLastName_thenReturnLastName() {

		emptyEmployee.setLastname(lastName);
		assertThat(emptyEmployee.getLastname()).isEqualTo(lastName);

	}

	@Test
	public void givenEmployee_whenEmployeeSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		emptyEmployee.setTelecommuting(telecommuting);
		assertThat(emptyEmployee.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenEmployee_whenEmployeeSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		emptyEmployee.setWorkplace(workplace);
		assertThat(emptyEmployee.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenEmployee_whenEqualsWithEmptyEmployee_thenReturnFalse() {

		assertThat(employee.equals(emptyEmployee)).isFalse();

	}

	@Test
	public void givenEmployee_whenEqualsWithNull_thenReturnFalse() {

		assertThat(employee.equals(null)).isFalse();

	}

	@Test
	public void givenEmployee_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(employee.equals(email)).isFalse();

	}

	@Test
	public void givenEmployee_whenEqualsWithEmployee_thenReturnTrue() {

		assertThat(employee.equals(employee)).isTrue();

	}

}
