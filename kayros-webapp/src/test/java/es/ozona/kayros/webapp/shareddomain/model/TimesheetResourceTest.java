package es.ozona.kayros.webapp.shareddomain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimesheetResourceTest {

	private String timesheetId;
	private String employeeId;
	private LocalDate date;

	private List<WorkingTimePeriodResource> workingTimePeriodsResource;

	private TimesheetResource emptyTimesheetResource;
	private TimesheetResource timesheetResource;

	@BeforeEach
	public void init() {

		timesheetId = "F8524E52-595D-4842-842D-31DE1CAA8CDC";
		employeeId = "e7c1c31b-c936-4a8b-ad9e-46a4a86381cd";
		date = LocalDate.now();

		workingTimePeriodsResource = new ArrayList<WorkingTimePeriodResource>();

		emptyTimesheetResource = new TimesheetResource();
		timesheetResource = new TimesheetResource(timesheetId, employeeId, date, workingTimePeriodsResource);

	}

	@Test
	protected void givenTimesheetResource_whenTimesheetResourceSetTimesheetIdAndGetTimesheetId_thenReturnTimesheetId() {

		emptyTimesheetResource.setTimesheetId(timesheetId);
		assertThat(emptyTimesheetResource.getTimesheetId()).isEqualTo(timesheetId);

	}

	@Test
	protected void givenTimesheetResource_whenTimesheetSetEmployeeIdAndGetEmployeeId_thenReturnEmployeeId() {

		emptyTimesheetResource.setEmployeeId(employeeId);
		assertThat(emptyTimesheetResource.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	protected void givenTimesheetResource_whenTimesheetResourceSetDateAndGetDate_thenReturnDate() {

		emptyTimesheetResource.setDate(date);
		assertThat(emptyTimesheetResource.getDate()).isEqualTo(date);

	}

	@Test
	protected void givenTimesheetResource_whenTimesheetResourceSetTimePeriodsAndGetWorkingTimePeriods_thenReturnWorkingTimePeriods() {

		emptyTimesheetResource.setWorkingTimePeriods(workingTimePeriodsResource);
		assertThat(emptyTimesheetResource.getWorkingTimePeriods()).isEqualTo(workingTimePeriodsResource);

	}

	@Test
	protected void givenTimesheetResource_whenEqualsWithEmptyTimesheetResource_thenReturnFalse() {

		assertThat(timesheetResource).isNotEqualTo(emptyTimesheetResource);

	}

	@Test
	protected void givenTimesheetResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(timesheetResource).isNotEqualTo(null);

	}

	@Test
	protected void givenTimesheetResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(timesheetResource).isNotEqualTo(timesheetId);

	}

	@Test
	protected void givenTimesheetResource_whenEqualsWithEmployee_thenReturnTrue() {

		assertThat(timesheetResource).isEqualTo(timesheetResource);

	}

}
