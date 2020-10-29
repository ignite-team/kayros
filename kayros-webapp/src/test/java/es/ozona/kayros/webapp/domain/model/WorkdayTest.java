package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkdayTest {

	private int day;
	private String worktimeEntry;
	private String worktimeExit;
	private String breaktimeStart;
	private String breaktimeEnd;
	private String restTime;

	private Workday emptyWorkday;
	private Workday workday;

	@BeforeEach
	public void init() {

		day = 0;
		worktimeEntry = "worktimeEntry";
		worktimeExit = "worktimeExit";
		breaktimeStart = "breaktimeStart";
		breaktimeEnd = "breaktimeEnd";
		restTime = "restTime";

		emptyWorkday = new Workday();
		workday = new Workday(day, worktimeEntry, worktimeExit, breaktimeStart, breaktimeEnd, restTime);

	}

	@Test
	public void givenWorkDay_whenWorkDayGetDay_thenReturnDay() {

		assertThat(workday.getDay()).isEqualTo(day);

	}

	@Test
	public void givenWorkDay_whenWorkDayGetWorktimeEntry_thenReturnWorktimeEntry() {

		assertThat(workday.getWorkTimeEntry()).isEqualTo(worktimeEntry);

	}

	@Test
	public void givenWorkDay_whenWorkDayGetWorktimeExit_thenReturnWorktimeExit() {

		assertThat(workday.getWorkTimeExit()).isEqualTo(worktimeExit);

	}

	@Test
	public void givenWorkDay_whenWorkDayGetBreaktimeStart_thenReturnBreaktimeStart() {

		assertThat(workday.getBreakTimeStart()).isEqualTo(breaktimeStart);

	}

	@Test
	public void givenWorkDay_whenWorkDayGetBreaktimeEnd_thenReturnBreaktimeEnd() {

		assertThat(workday.getBreakTimeEnd()).isEqualTo(breaktimeEnd);

	}

	@Test
	public void givenWorkDay_whenWorkDayGetRestTime_thenReturnRestTime() {

		assertThat(workday.getRestTime()).isEqualTo(restTime);

	}

	@Test
	public void givenEmptyWorkDay_whenEmptyWorkDaySetDayAndGetDay_thenReturnDay() {

		emptyWorkday.setDay(day);
		assertThat(emptyWorkday.getDay()).isEqualTo(day);

	}

	@Test
	public void givenEmptyWorkDay_whenEmptyWorkDaySetWorktimeEntryAndGetWorktimeEntry_thenReturnWorktimeEntry() {

		emptyWorkday.setWorkTimeEntry(worktimeEntry);
		assertThat(emptyWorkday.getWorkTimeEntry()).isEqualTo(worktimeEntry);

	}

	@Test
	public void givenEmptyWorkDay_wheEmptynWorkDaySetWorktimeExiAndGetWorktimeExit_thenReturnWorktimeExit() {

		emptyWorkday.setWorkTimeExit(worktimeExit);
		assertThat(emptyWorkday.getWorkTimeExit()).isEqualTo(worktimeExit);

	}

	@Test
	public void givenEmptyWorkDay_whenEmptyWorkDaySetBreaktimeStartAndGetBreaktimeStart_thenReturnBreaktimeStart() {

		emptyWorkday.setBreakTimeStart(breaktimeStart);
		assertThat(emptyWorkday.getBreakTimeStart()).isEqualTo(breaktimeStart);

	}

	@Test
	public void givenEmptyWorkDay_whenEmptyWorkDaySetBreaktimeEndAndGetBreaktimeEnd_thenReturnBreaktimeEnd() {

		emptyWorkday.setBreakTimeEnd(breaktimeEnd);
		assertThat(emptyWorkday.getBreakTimeEnd()).isEqualTo(breaktimeEnd);

	}

	@Test
	public void givenEmptyWorkDay_whenEmptyWorkDaySetRestTimeAndGetRestTime_thenReturnRestTime() {

		emptyWorkday.setRestTime(restTime);
		assertThat(emptyWorkday.getRestTime()).isEqualTo(restTime);

	}

	@Test
	public void givenHoliday_whenEqualsWithEmptyHoliday_thenReturnFalse() {

		assertThat(workday.equals(emptyWorkday)).isFalse();

	}

	@Test
	public void givenHoliday_whenEqualsWithNull_thenReturnFalse() {

		assertThat(workday.equals(null)).isFalse();

	}

	@Test
	public void givenHoliday_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(workday.equals(day)).isFalse();

	}

	@Test
	public void givenHoliday_whenEqualsWithHoliday_thenReturnTrue() {

		assertThat(workday.equals(workday)).isTrue();

	}
}
