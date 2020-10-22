package es.ozona.kayros.webapp.internal.outboundservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.infrastructure.feingclients.EmployeeService;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;

@SpringBootTest
public class ExternalEmployeeServiceTest {

	@Autowired
	ExternalEmployeeService externalEmployeeService;

	@MockBean
	EmployeeService employeeService;

	@Test
	public void givenExistingEmpleeUsername_whenCallFindEmployeeByUsername_thenReturnsEmployee() {
		Mockito.when(employeeService.search("username:jeijo", "+username", 1, 1)).thenReturn(new PageResult<EmployeeResource>());
		assertThat(externalEmployeeService.findEmployeeByUsername("jeijo")).isNotNull();
	}

}
