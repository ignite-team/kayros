package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimesheetTest {

	private String timesheetId;
	private String employeeId;
	private LocalDate date;
	private String workplace;
	private Date startDate;
	private Date endDate;
	private String totalTime;
	private WorkingTimePeriod wtp3;

	private List<WorkingTimePeriod> workingTimePeriods;

	private Timesheet emptyTimesheet;
	private Timesheet timesheet;

	@BeforeEach
	public void init() {

		timesheetId = "F8524E52-595D-4842-842D-31DE1CAA8CDC";
		employeeId = "e7c1c31b-c936-4a8b-ad9e-46a4a86381cd";
		date = LocalDate.now();
		workplace = "Casa";
		startDate = new Date();
		endDate = new Date();
		totalTime = "Menos de un minuto";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date endDate2 = calendar.getTime();

		WorkingTimePeriod wtp1 = new WorkingTimePeriod(startDate, false, false, null, false, false, true, workplace);
		WorkingTimePeriod wtp2 = new WorkingTimePeriod(startDate, false, false, endDate, false, false, true, workplace);
		wtp3 = new WorkingTimePeriod(startDate, false, false, endDate2, false, false, true, workplace);

		workingTimePeriods = new ArrayList<WorkingTimePeriod>();
		workingTimePeriods.add(wtp1);
		workingTimePeriods.add(wtp2);

		emptyTimesheet = new Timesheet();
		timesheet = new Timesheet(timesheetId, employeeId, date, workingTimePeriods);

	}

	@Test
	public void givenTimesheet_whenTimesheetSetTimesheetIdAndGetTimesheetId_thenReturnTimesheetId() {

		emptyTimesheet.setTimesheetId(timesheetId);
		assertThat(emptyTimesheet.getTimesheetId()).isEqualTo(timesheetId);

	}

	@Test
	public void givenTimesheet_whenTimesheetSetEmployeeIdAndGetEmployeeId_thenReturnEmployeeId() {

		emptyTimesheet.setEmployeeId(employeeId);
		assertThat(emptyTimesheet.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenTimesheet_whenTimesheetSetDateAndGetDate_thenReturnDate() {

		emptyTimesheet.setDate(date);
		assertThat(emptyTimesheet.getDate()).isEqualTo(date);

	}

	@Test
	public void givenTimesheet_whenTimesheetSetTimePeriodsAndGetWorkingTimePeriods_thenReturnWorkingTimePeriods() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods);
		assertThat(emptyTimesheet.getWorkingTimePeriods()).isEqualTo(workingTimePeriods);

	}

	@Test
	public void givenTimesheet_whenTimesheetSetTimePeriodsAndGetStartDate_thenReturnStartDate() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods);
		assertThat(emptyTimesheet.getStartDate()).isEqualTo(startDate);

	}

	@Test
	public void givenTimesheet_whenTimesheetSettTimePeriodsAndGetEndtDate_thenReturnEndDate() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods);
		assertThat(emptyTimesheet.getEndDate()).isEqualTo(endDate);

	}

	@Test
	public void givenTimesheet_whenTimesheetSetTimePeriodsAndGetTotalTime_thenReturnTotalTime() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods);
		assertThat(emptyTimesheet.getTotalTime()).isEqualTo(totalTime);

	}

	@Test
	public void givenTimesheet_whenTimesheetSetNullTimePeriodsAndGetTotalTime_thenReturnNull() {

		emptyTimesheet.setWorkingTimePeriods(null);
		assertThat(emptyTimesheet.getTotalTime()).isNull();

	}

	@Test
	public void givenTimesheet_whenTimesheetSetEmptyTimePeriodsAndGetTotalTime_thenReturnNull() {

		emptyTimesheet.setWorkingTimePeriods(new ArrayList<WorkingTimePeriod>());
		assertThat(emptyTimesheet.getTotalTime()).isNull();

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetTimePeriodsAndGetTotalTime_thenReturnNotTotalTime() {

		workingTimePeriods.add(wtp3);
		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods);
		assertThat(emptyTimesheet.getTotalTime()).isNotEqualTo(totalTime);

	}

	@Test
	public void givenTimesheet_whenEqualsWithEmptyTimesheet_thenReturnFalse() {

		assertThat(timesheet.equals(emptyTimesheet)).isFalse();

	}

	@Test
	public void givenTimesheet_whenEqualsWithNull_thenReturnFalse() {

		assertThat(timesheet.equals(null)).isFalse();

	}

	@Test
	public void givenTimesheet_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(timesheet.equals(timesheetId)).isFalse();

	}

	@Test
	public void givenTimesheet_whenEqualsWithEmployee_thenReturnTrue() {

		assertThat(timesheet.equals(timesheet)).isTrue();

	}

}
