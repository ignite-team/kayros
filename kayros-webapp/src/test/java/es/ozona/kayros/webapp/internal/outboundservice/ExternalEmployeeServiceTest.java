package es.ozona.kayros.webapp.internal.outboundservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.infrastructure.feingclients.EmployeeService;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;

@SpringBootTest
public class ExternalEmployeeServiceTest {

	@Autowired
	ExternalEmployeeService externalEmployeeService;

	@MockBean
	EmployeeService employeeService;

	private String employeeId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String preferredLanguage;
	private boolean telecommuting;
	private String workplace;

	private String invalidUsername;

	private Employee employee;
	private EmployeeResource employeeResource;
	private PageResult<EmployeeResource> pageResult;
	private PageResult<EmployeeResource> emptyPageResult;

	@BeforeEach
	public void init() {

		employeeId = "e7c1c31b-c936-4a8b-ad9e-46a4a86381cd";
		username = "jeijo";
		firstName = "firstname";
		lastName = "lastname";
		email = "email";
		preferredLanguage = "es_ES";
		telecommuting = false;
		workplace = "workplace";

		invalidUsername = "qwertyuioplkjhgfadszcxvbnnm";

		employee = new Employee(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);
		employeeResource = new EmployeeResource(employeeId, username, firstName, lastName, email, preferredLanguage, telecommuting, workplace);
		pageResult = new PageResult<EmployeeResource>();
		emptyPageResult = new PageResult<EmployeeResource>();

		ArrayList<EmployeeResource> arrayList = new ArrayList<EmployeeResource>();
		arrayList.add(employeeResource);

		pageResult.setItems(arrayList);

	}

	@Test
	public void givenExistingEmpleeUsername_whenCallFindEmployeeByUsername_thenReturnsEmployee() {

		Mockito.when(employeeService.search(String.format("username:%s", username), "+username", 1, 1)).thenReturn(pageResult);
		assertThat(externalEmployeeService.findEmployeeByUsername(username)).isNotEqualTo(Optional.empty());

	}

	@Test
	public void givenExistingEmpleeUsername_whenCallFindEmployeesLikeUsername_thenReturnsEmployees() {

		Mockito.when(employeeService.search(String.format("username:%s*", username), "+username", 1, 15)).thenReturn(pageResult);
		assertThat(externalEmployeeService.findEmployeesLikeUsername(username).size() > 0).isTrue();

	}

	@Test
	public void givenInvalidEmpleeUsername_whenCallFindEmployeeByUsername_thenReturnsNoEmployee() {

		Mockito.when(employeeService.search(String.format("username:%s", invalidUsername), "+username", 1, 1)).thenReturn(emptyPageResult);
		assertThat(externalEmployeeService.findEmployeeByUsername(invalidUsername)).isEqualTo(Optional.empty());

	}

	@Test
	public void givenInvalidEmpleeUsername_whenCallFindEmployeesLikeUsername_thenReturns0Employees() {

		Mockito.when(employeeService.search(String.format("username:%s*", invalidUsername), "+username", 1, 15)).thenReturn(emptyPageResult);
		assertThat(externalEmployeeService.findEmployeesLikeUsername(invalidUsername).size() == 0).isTrue();

	}

	@Test
	public void givenEmployee_whenCallModifyEmployee_thenReturnEmployeeObject() {

		Mockito.when(employeeService.modify(employeeResource, employeeId)).thenReturn(employeeResource);
		assertThat(externalEmployeeService.modifyEmployee(employee) instanceof Employee).isTrue();

	}

}
