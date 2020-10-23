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
	public void givenTimesheetResource_whenTimesheetResourceSetTimesheetIdAndGetTimesheetId_thenReturnTimesheetId() {

		emptyTimesheetResource.setTimesheetId(timesheetId);
		assertThat(emptyTimesheetResource.getTimesheetId()).isEqualTo(timesheetId);

	}

	@Test
	public void givenTimesheetResource_whenTimesheetSetEmployeeIdAndGetEmployeeId_thenReturnEmployeeId() {

		emptyTimesheetResource.setEmployeeId(employeeId);
		assertThat(emptyTimesheetResource.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenTimesheetResource_whenTimesheetResourceSetDateAndGetDate_thenReturnDate() {

		emptyTimesheetResource.setDate(date);
		assertThat(emptyTimesheetResource.getDate()).isEqualTo(date);

	}

	@Test
	public void givenTimesheetResource_whenTimesheetResourceSetTimePeriodsAndGetWorkingTimePeriods_thenReturnWorkingTimePeriods() {

		emptyTimesheetResource.setWorkingTimePeriods(workingTimePeriodsResource);
		assertThat(emptyTimesheetResource.getWorkingTimePeriods()).isEqualTo(workingTimePeriodsResource);

	}

	@Test
	public void givenTimesheetResource_whenEqualsWithEmptyTimesheetResource_thenReturnFalse() {

		assertThat(timesheetResource.equals(emptyTimesheetResource)).isFalse();

	}

	@Test
	public void givenTimesheetResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(timesheetResource.equals(null)).isFalse();

	}

	@Test
	public void givenTimesheetResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(timesheetResource.equals(timesheetId)).isFalse();

	}

	@Test
	public void givenTimesheetResource_whenEqualsWithEmployee_thenReturnTrue() {

		assertThat(timesheetResource.equals(timesheetResource)).isTrue();

	}

}
