package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;
import es.ozona.kayros.webapp.shareddomain.model.WorkingTimePeriodResource;

@SpringBootTest
public class TimesheetMapperTest {

	private String timesheetId;
	private String employeeId;
	private LocalDate date;

	private ZonedDateTime startTime;
	private ZonedDateTime finishTime;

	private Boolean generatedStartTime;
	private Boolean editedStartTime;
	private Boolean generatedFinishTime;
	private Boolean editedFinishTime;
	private Boolean telecommuting;
	private String workplace;

	private List<WorkingTimePeriod> workingTimePeriods;
	private List<WorkingTimePeriodResource> workingTimePeriodsResource;
	private List<WorkingTimePeriodResource> workingTimePeriodsResource2;

	private Timesheet timesheet;
	private TimesheetResource timesheetResource;
	private TimesheetResource timesheetResource2;

	@BeforeEach
	public void init() {

		startTime = ZonedDateTime.now();
		finishTime = ZonedDateTime.now();

		generatedStartTime = false;
		editedStartTime = false;
		generatedFinishTime = false;
		editedFinishTime = false;
		telecommuting = false;
		workplace = "workplace";

		timesheetId = "F8524E52-595D-4842-842D-31DE1CAA8CDC";
		employeeId = "e7c1c31b-c936-4a8b-ad9e-46a4a86381cd";
		date = LocalDate.now();

		workingTimePeriods = new ArrayList<WorkingTimePeriod>();
		workingTimePeriodsResource = new ArrayList<WorkingTimePeriodResource>();
		workingTimePeriodsResource2 = new ArrayList<WorkingTimePeriodResource>();

		WorkingTimePeriodResource wtp = new WorkingTimePeriodResource(startTime, generatedStartTime, editedStartTime, finishTime, generatedFinishTime,
				editedFinishTime, telecommuting, workplace);
		workingTimePeriodsResource2.add(wtp);

		timesheet = new Timesheet();
		timesheet.setTimesheetId(timesheetId);
		timesheet.setEmployeeId(employeeId);
		timesheet.setDate(date);
		timesheet.setWorkingTimePeriods(workingTimePeriods);

		timesheetResource = new TimesheetResource();
		timesheetResource.setTimesheetId(timesheetId);
		timesheetResource.setEmployeeId(employeeId);
		timesheetResource.setDate(date);
		timesheetResource.setWorkingTimePeriods(workingTimePeriodsResource);

		timesheetResource2 = new TimesheetResource();
		timesheetResource2.setTimesheetId(timesheetId);
		timesheetResource2.setEmployeeId(employeeId);
		timesheetResource2.setDate(date);
		timesheetResource2.setWorkingTimePeriods(workingTimePeriodsResource2);

	}

	@Test
	protected void givenTimesheetResource_whenTimesheetMapperMapFromResourceEqualsTimesheet_thenReturnTrue() {

		assertThat(TimesheetMapper.mapFromResource(timesheetResource)).isEqualTo(timesheet);

	}

	@Test
	protected void givenTimesheetResource2_whenTimesheetMapperMapFromResourceEqualsTimesheet_thenReturnFalse() {

		assertThat(TimesheetMapper.mapFromResource(timesheetResource2)).isNotEqualTo(timesheet);

	}

}
