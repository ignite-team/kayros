package es.ozona.kairos.employee.domain.model.aggregates;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.employee.domain.model.commands.CreateEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.ModifyEmployeeCommand;
import es.ozona.kairos.employee.domain.model.entities.Schedule;
import es.ozona.kairos.employee.domain.model.valueobjects.EmployeeId;
import es.ozona.kairos.employee.domain.model.valueobjects.UserAccount;

@SpringBootTest
public class EmployeeTest {

	private String employeeIdString;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private EmployeeId employeeId;

	private UserAccount userAccount;

	private Set<Schedule> emptySchedules;

	private CreateEmployeeCommand createEmployeeCommand;

	private ModifyEmployeeCommand modifyEmployeeCommand;

	private Employee emptyEmployee;
	private Employee employee;

	@BeforeEach
	public void init() {

		employeeIdString = "e7c1c31b-c936-4a8b-ad9e-46a4a86381cd";
		username = "username";
		firstName = "firstname";
		lastName = "lastname";
		email = "email";
		preferredLanguage = "es_ES";
		telecommuting = false;
		workplace = "workplace";

		employeeId = new EmployeeId(employeeIdString);

		userAccount = new UserAccount(username, firstName, lastName, email, preferredLanguage);

		emptySchedules = new HashSet<Schedule>();

		createEmployeeCommand = new CreateEmployeeCommand(employeeIdString, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

		modifyEmployeeCommand = new ModifyEmployeeCommand(employeeIdString, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

		emptyEmployee = new Employee();
		employee = new Employee(createEmployeeCommand);

	}

	@Test
	protected void givenEmployee_whenEmployeeModify_thenEmployeeEqualsEmployee() {

		employee.modify(modifyEmployeeCommand);
		assertThat(employee).isEqualTo(employee);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetEmployeeID_thenReturnEmployeeId() {

		assertThat(employee.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetAccount_thenReturnUserAccount() {

		assertThat(employee.getAccount()).isEqualTo(userAccount);

	}

	@Test
	protected void givenEmployee_whenEmployeeGetSchedules_thenReturnSchedules() {

		assertThat(employee.getSchedules().size()).isEqualTo(emptySchedules.size());

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
	protected void givenEmptyEmployee_whenEmptyEmployeeSetUserAccountAndGetUserAccount_thenReturnUserAccount() {

		emptyEmployee.setAccount(userAccount);
		assertThat(emptyEmployee.getAccount()).isEqualTo(userAccount);

	}

	@Test
	protected void givenEmptyEmployee_whenEmptyEmployeeSetSchedulesAndGetSchedules_thenReturnSchedules() {

		emptyEmployee.setSchedules(emptySchedules);
		assertThat(emptyEmployee.getSchedules().size()).isEqualTo(emptySchedules.size());

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
	protected void givenCreateEmployeeCommand_whenEqualsWithEmptyCreateEmployeeCommand_thenReturnFalse() {

		assertThat(employee).isNotEqualTo(emptyEmployee);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenEqualsWithNull_thenReturnFalse() {

		assertThat(employee).isNotEqualTo(null);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(employee).isNotEqualTo(email);

	}

	@Test
	protected void givenCreateEmployeeCommand_whenEqualsWithCreateEmployeeCommand_thenReturnTrue() {

		assertThat(employee).isEqualTo(employee);

	}

}
