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

	private List<WorkingTimePeriod> workingTimePeriods1;
	private List<WorkingTimePeriod> workingTimePeriods2;
	private List<WorkingTimePeriod> workingTimePeriods3;

	private Timesheet emptyTimesheet;
	private Timesheet timesheet;
	private Timesheet timesheet2;
	private Timesheet timesheet3;

	private int latency;
	private int latency2;

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

		workingTimePeriods1 = new ArrayList<WorkingTimePeriod>();
		workingTimePeriods1.add(wtp1);
		workingTimePeriods1.add(wtp2);

		workingTimePeriods2 = new ArrayList<WorkingTimePeriod>();
		workingTimePeriods2.add(wtp1);

		workingTimePeriods3 = new ArrayList<WorkingTimePeriod>();

		emptyTimesheet = new Timesheet();
		timesheet = new Timesheet(timesheetId, employeeId, date, workingTimePeriods1);
		timesheet2 = new Timesheet(timesheetId, employeeId, date, workingTimePeriods2);
		timesheet3 = new Timesheet(timesheetId, employeeId, date, workingTimePeriods3);

		latency = 1;
		latency2 = -10;

	}

	@Test
	public void givenEmptyTimesheet_whenTimesheetSetTimesheetIdAndGetTimesheetId_thenReturnTimesheetId() {

		emptyTimesheet.setTimesheetId(timesheetId);
		assertThat(emptyTimesheet.getTimesheetId()).isEqualTo(timesheetId);

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetEmployeeIdAndGetEmployeeId_thenReturnEmployeeId() {

		emptyTimesheet.setEmployeeId(employeeId);
		assertThat(emptyTimesheet.getEmployeeId()).isEqualTo(employeeId);

	}

	@Test
	public void givenTimesheet_whenEmptyTimesheetSetDateAndGetDate_thenReturnDate() {

		emptyTimesheet.setDate(date);
		assertThat(emptyTimesheet.getDate()).isEqualTo(date);

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetTimePeriodsAndGetWorkingTimePeriods_thenReturnWorkingTimePeriods() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods1);
		assertThat(emptyTimesheet.getWorkingTimePeriods()).isEqualTo(workingTimePeriods1);

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetTimePeriodsAndGetStartDate_thenReturnStartDate() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods1);
		assertThat(emptyTimesheet.getStartDate()).isEqualTo(startDate);

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSettTimePeriodsAndGetEndtDate_thenReturnEndDate() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods1);
		assertThat(emptyTimesheet.getEndDate()).isEqualTo(endDate);

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetTimePeriodsAndGetTotalTime_thenReturnTotalTime() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods1);
		assertThat(emptyTimesheet.getTotalTime()).isEqualTo(totalTime);

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetTimePeriods2AndGetTotalTime_thenReturnTotalTime() {

		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods2);
		assertThat(emptyTimesheet.getTotalTime()).isEqualTo(totalTime);

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetNullTimePeriodsAndGetTotalTime_thenReturnNull() {

		emptyTimesheet.setWorkingTimePeriods(null);
		assertThat(emptyTimesheet.getTotalTime()).isNull();

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetEmptyTimePeriodsAndGetTotalTime_thenReturnNull() {

		emptyTimesheet.setWorkingTimePeriods(new ArrayList<WorkingTimePeriod>());
		assertThat(emptyTimesheet.getTotalTime()).isNull();

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetSetTimePeriodsAndGetTotalTime_thenReturnNotTotalTime() {

		workingTimePeriods1.add(wtp3);
		emptyTimesheet.setWorkingTimePeriods(workingTimePeriods1);
		assertThat(emptyTimesheet.getTotalTime()).isNotEqualTo(totalTime);

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetGetStatus_thenReturnNull() {

		assertThat(emptyTimesheet.getStatus()).isFalse();

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetIsLatencyTimeout_thenReturnTrue() {

		assertThat(emptyTimesheet.isLatencityTimeout(latency)).isTrue();

	}

	@Test
	public void givenTimesheet_whenTimesheetIsLatencyTimeout_thenReturnFalse() {

		assertThat(timesheet.isLatencityTimeout(latency)).isFalse();

	}

	@Test
	public void givenTimesheet2_whenTimesheetIsLatencyTimeout_thenReturnFalse() {

		assertThat(timesheet2.isLatencityTimeout(latency)).isFalse();

	}

	@Test
	public void givenTimesheet3_whenTimesheetIsLatencyTimeout_thenReturnTrue() {

		assertThat(timesheet3.isLatencityTimeout(latency2)).isTrue();

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyTimesheetIsLatency2Timeout_thenReturnTrue() {

		assertThat(emptyTimesheet.isLatencityTimeout(latency2)).isTrue();

	}

	@Test
	public void givenTimesheet_whenTimesheetIsLatency2Timeout_thenReturnTrue() {

		assertThat(timesheet.isLatencityTimeout(latency2)).isTrue();

	}

	@Test
	public void givenTimesheet2_whenTimesheet2IsLatency2Timeout_thenReturnTrue() {

		assertThat(timesheet2.isLatencityTimeout(latency2)).isTrue();

	}

	@Test
	public void givenTimesheet3_whenTimesheet3IsLatency2Timeout_thenReturnTrue() {

		assertThat(timesheet3.isLatencityTimeout(latency2)).isTrue();

	}

	@Test
	public void givenEmptyTimesheet_whenEmptyGetTimeToLatencyTimeout_thenReturnTrue() {

		assertThat(emptyTimesheet.getTimeToLatencyTimeout(latency)).isEqualTo(0);

	}

	@Test
	public void givenTimesheet_whenTimesheetGetTimeToLatencyTimeout_thenReturnTrue() {

		assertThat(timesheet.getTimeToLatencyTimeout(latency)).isEqualTo(0);

	}

	@Test
	public void givenTimesheet2_whenTimesheet2GetTimeToLatencyTimeout_thenReturnTrue() {

		assertThat(timesheet2.getTimeToLatencyTimeout(latency)).isNotEqualTo(0);

	}

	@Test
	public void givenTimesheet2_whenTimesheet2GetTimeToLatencyTimeoutWithLatency2_thenReturnTrue() {

		assertThat(timesheet2.getTimeToLatencyTimeout(latency2)).isEqualTo(0);

	}

	@Test
	public void givenTimesheet3_whenTimesheet3GetTimeToLatencyTimeout_thenReturnTrue() {

		assertThat(timesheet3.getTimeToLatencyTimeout(latency)).isEqualTo(0);

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
