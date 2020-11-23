package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;

@SpringBootTest
public class EmployeeMapperTest {

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private Boolean telecommuting;
	private String workplace;

	private Employee employee;
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

		employeeResource = new EmployeeResource(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);
		employee = new Employee(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);

	}

	@Test
	protected void givenEmployeeResource_whenEmployeeMapperMapFromResourceEqualsEmployee_thenReturnTrue() {

		assertThat(EmployeeMapper.mapFromResource(employeeResource)).isEqualTo(employee);

	}

	@Test
	protected void givenEmployee_whenEmployeeMapperMapToResourceEqualsEmployeeResource_thenReturnTrue() {

		assertThat(EmployeeMapper.mapToResource(employee)).isEqualTo(employeeResource);

	}

}
