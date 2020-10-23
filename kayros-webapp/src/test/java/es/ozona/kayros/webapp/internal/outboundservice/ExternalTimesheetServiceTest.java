package es.ozona.kayros.webapp.internal.outboundservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.infrastructure.feingclients.TimesheetService;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;
import es.ozona.kayros.webapp.shareddomain.model.WorkingTimePeriodResource;

@SpringBootTest
public class ExternalTimesheetServiceTest {

	private static final String SERVICE_DATE_FORMAT = "yyyyMMdd";

	private ZonedDateTime startTime;
	private Boolean generatedStartTime;
	private Boolean editedStartTime;
	private ZonedDateTime finishTime;
	private Boolean generatedFinishTime;
	private Boolean editedFinishTime;

	private String timesheetId;
	private String employeeId;
	private String invalidEmployeeId;
	private LocalDate date;
	private String workplace;

	private String startDate;
	private String endDate;

	private String email;
	private String firstName;
	private String lastName;
	private boolean telecommuting;
	private String username;

	private Employee employee;

	private List<WorkingTimePeriodResource> workingTimePeriodsResource;
	private WorkingTimePeriodResource workingTimePeriodResource;

	private TimesheetResource timesheetResource;

	private PageResult<TimesheetResource> pageResult;
	private PageResult<TimesheetResource> emptyPageResult;

	@Autowired
	ExternalTimesheetService externalTimesheetService;

	@MockBean
	TimesheetService timesheetService;

	@BeforeEach
	public void init() {

		startTime = ZonedDateTime.now();
		generatedStartTime = false;
		editedStartTime = true;
		finishTime = ZonedDateTime.now();
		generatedFinishTime = false;
		editedFinishTime = true;

		timesheetId = "F8524E52-595D-4842-842D-31DE1CAA8CDC";
		employeeId = "e7c1c31b-c936-4a8b-ad9e-46a4a86381cd";
		date = LocalDate.now();
		workplace = "Casa";

		startDate = LocalDate.now().format(DateTimeFormatter.ofPattern(SERVICE_DATE_FORMAT));
		endDate = LocalDate.now().format(DateTimeFormatter.ofPattern(SERVICE_DATE_FORMAT));

		invalidEmployeeId = "e7c1c31b-c936-4a8b-ad9e-46a4a863814d";

		employee = new Employee(employeeId, username, email, firstName, lastName, telecommuting, workplace);

		workingTimePeriodResource = new WorkingTimePeriodResource(startTime, generatedStartTime, editedStartTime, finishTime, generatedFinishTime,
				editedFinishTime, telecommuting, workplace);

		workingTimePeriodsResource = new ArrayList<WorkingTimePeriodResource>();

		workingTimePeriodsResource.add(workingTimePeriodResource);

		pageResult = new PageResult<TimesheetResource>();
		emptyPageResult = new PageResult<TimesheetResource>();

		timesheetResource = new TimesheetResource(timesheetId, employeeId, date, workingTimePeriodsResource);

		ArrayList<TimesheetResource> arrayList = new ArrayList<TimesheetResource>();
		arrayList.add(timesheetResource);
		pageResult.setItems(arrayList);

	}

	@Test
	public void givenExistingEmpleeUsernameAndEmployeeId_whenCallCLock_thenReturnsTimesheetResource() {

		Mockito.when(timesheetService.clock(employeeId, username)).thenReturn(timesheetResource);
		assertThat(externalTimesheetService.clock(employee) instanceof TimesheetResource).isTrue();

	}

	@Test
	public void givenExistingEmployeeId_whenCallSearchCurrentByEmployeeId_thenReturnsWorkingTimePeriods() {

		Mockito.when(timesheetService.search("( employeeId:%s and date:%s )".formatted(employeeId, startDate), "+date", 1, 1000)).thenReturn(pageResult);
		assertThat(externalTimesheetService.searchCurrentByEmployeeId(employeeId).size() > 0).isTrue();

	}

	@Test
	public void givenInvalidEmployeeId_whenCallSearchCurrentByEmployeeId_thenReturnsNoWorkingTimePeriods() {

		Mockito.when(timesheetService.search("( employeeId:%s and date:%s )".formatted(invalidEmployeeId, startDate), "+date", 1, 1000))
				.thenReturn(emptyPageResult);
		assertThat(externalTimesheetService.searchCurrentByEmployeeId(invalidEmployeeId).size() == 0).isTrue();

	}

	@Test
	public void givenExistingEmployeeId_whenCallSearchTimesheetsByEmployeeIdBetweenDates_thenReturnsTimesheets() {

		Mockito.when(timesheetService.search("( date>%s and date<%s and employeeId:%s )".formatted(startDate, endDate, employeeId), "+date", 1, 1000))
				.thenReturn(pageResult);
		assertThat(externalTimesheetService.searchTimesheetsByEmployeeIdBetweenDates(startDate, endDate, employeeId).size() > 0).isTrue();

	}

	@Test
	public void givenInvalidEmployeeId_whenCallSearchTimesheetsByEmployeeIdBetweenDates_thenReturnsNull() {

		Mockito.when(timesheetService.search("( date>%s and date<%s and employeeId:%s )".formatted(startDate, endDate, invalidEmployeeId), "+date", 1, 1000))
				.thenReturn(emptyPageResult);
		assertThat(externalTimesheetService.searchTimesheetsByEmployeeIdBetweenDates(startDate, endDate, invalidEmployeeId)).isNull();

	}

	@Test
	public void givenExistingEmployeeId_whenCallSearchCurrentTimesheetByEmployeeId_thenReturnsTimesheet() {

		Mockito.when(timesheetService.search("( employeeId:%s and date:%s )".formatted(employeeId, startDate), "+date", 1, 1000)).thenReturn(pageResult);
		assertThat(externalTimesheetService.searchCurrentTimesheetByEmployeeId(employeeId) instanceof Timesheet).isTrue();

	}

	@Test
	public void givenInvalidEmployeeId_whenCallSearchCurrentTimesheetByEmployeeId_thenReturnsNull() {

		Mockito.when(timesheetService.search("( employeeId:%s and date:%s )".formatted(invalidEmployeeId, startDate), "+date", 1, 1000))
				.thenReturn(emptyPageResult);
		assertThat(externalTimesheetService.searchCurrentTimesheetByEmployeeId(invalidEmployeeId)).isNull();

	}

}
