package es.ozona.kayros.webapp.shareddomain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkdayResourceTest {

	private int day;
	private String worktimeEntry;
	private String worktimeExit;
	private String breaktimeStart;
	private String breaktimeEnd;
	private String restTime;

	private WorkdayResource emptyWorkdayResource;
	private WorkdayResource workdayResource;

	@BeforeEach
	public void init() {

		day = 0;
		worktimeEntry = "worktimeEntry";
		worktimeExit = "worktimeExit";
		breaktimeStart = "breaktimeStart";
		breaktimeEnd = "breaktimeEnd";
		restTime = "restTime";

		emptyWorkdayResource = new WorkdayResource();
		workdayResource = new WorkdayResource(day, worktimeEntry, worktimeExit, breaktimeStart, breaktimeEnd, restTime);

	}

	@Test
	public void givenWorkDayResource_whenWorkDayResourceGetDay_thenReturnDay() {

		assertThat(workdayResource.getDay()).isEqualTo(day);

	}

	@Test
	public void givenWorkDayResource_whenWorkDayResourceGetWorktimeEntry_thenReturnWorktimeEntry() {

		assertThat(workdayResource.getWorkTimeEntry()).isEqualTo(worktimeEntry);

	}

	@Test
	public void givenWorkDayResource_whenWorkDayResourceGetWorktimeExit_thenReturnWorktimeExit() {

		assertThat(workdayResource.getWorkTimeExit()).isEqualTo(worktimeExit);

	}

	@Test
	public void givenWorkDayResource_whenWorkDayResourceGetBreaktimeStart_thenReturnBreaktimeStart() {

		assertThat(workdayResource.getBreakTimeStart()).isEqualTo(breaktimeStart);

	}

	@Test
	public void givenWorkDayResource_whenWorkDayResourceGetBreaktimeEnd_thenReturnBreaktimeEnd() {

		assertThat(workdayResource.getBreakTimeEnd()).isEqualTo(breaktimeEnd);

	}

	@Test
	public void givenWorkDayResource_whenWorkDayResourceGetRestTime_thenReturnRestTime() {

		assertThat(workdayResource.getRestTime()).isEqualTo(restTime);

	}

	@Test
	public void givenEmptyWorkDayResource_whenEmptyWorkDayResourceSetDayAndGetDay_thenReturnDay() {

		emptyWorkdayResource.setDay(day);
		assertThat(emptyWorkdayResource.getDay()).isEqualTo(day);

	}

	@Test
	public void givenEmptyWorkDayResource_whenEmptyWorkDayResourceSetWorktimeEntryAndGetWorktimeEntry_thenReturnWorktimeEntry() {

		emptyWorkdayResource.setWorkTimeEntry(worktimeEntry);
		assertThat(emptyWorkdayResource.getWorkTimeEntry()).isEqualTo(worktimeEntry);

	}

	@Test
	public void givenEmptyWorkDayResource_wheEmptynWorkDayResourceSetWorktimeExiAndGetWorktimeExit_thenReturnWorktimeExit() {

		emptyWorkdayResource.setWorkTimeExit(worktimeExit);
		assertThat(emptyWorkdayResource.getWorkTimeExit()).isEqualTo(worktimeExit);

	}

	@Test
	public void givenEmptyWorkDayResource_whenEmptyWorkDayResourceSetBreaktimeStartAndGetBreaktimeStart_thenReturnBreaktimeStart() {

		emptyWorkdayResource.setBreakTimeStart(breaktimeStart);
		assertThat(emptyWorkdayResource.getBreakTimeStart()).isEqualTo(breaktimeStart);

	}

	@Test
	public void givenEmptyWorkDayResource_whenEmptyWorkDayResourceSetBreaktimeEndAndGetBreaktimeEnd_thenReturnBreaktimeEnd() {

		emptyWorkdayResource.setBreakTimeEnd(breaktimeEnd);
		assertThat(emptyWorkdayResource.getBreakTimeEnd()).isEqualTo(breaktimeEnd);

	}

	@Test
	public void givenEmptyWorkDayResource_whenEmptyWorkDayResourceSetRestTimeAndGetRestTime_thenReturnRestTime() {

		emptyWorkdayResource.setRestTime(restTime);
		assertThat(emptyWorkdayResource.getRestTime()).isEqualTo(restTime);

	}

	@Test
	public void givenHolidayResource_whenEqualsWithEmptyHolidayResource_thenReturnFalse() {

		assertThat(workdayResource.equals(emptyWorkdayResource)).isFalse();

	}

	@Test
	public void givenHolidayResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(workdayResource.equals(null)).isFalse();

	}

	@Test
	public void givenHolidayResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(workdayResource.equals(day)).isFalse();

	}

	@Test
	public void givenHolidayResource_whenEqualsWithHolidayResource_thenReturnTrue() {

		assertThat(workdayResource.equals(workdayResource)).isTrue();

	}

}
