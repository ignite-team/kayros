package es.ozona.kairos.calendar.domain.model.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.domain.model.valueobjects.BreakTime;
import es.ozona.kairos.calendar.domain.model.valueobjects.Day;
import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;
import es.ozona.kairos.calendar.domain.model.valueobjects.RestTime;
import es.ozona.kairos.calendar.domain.model.valueobjects.WorkTime;

@SpringBootTest
public class WorkdayTest {

	private Day day = new Day(DayOfWeek.MONDAY);
	private WorkTime workTime = new WorkTime(LocalTime.now(), LocalTime.now().plusHours(1));
	private BreakTime breakTime = new BreakTime(LocalTime.now(), LocalTime.now().plusHours(1));
	private RestTime restTime = new RestTime(LocalTime.now());
	private Workday workdayA = new Workday(day, workTime, breakTime, restTime);
	private Workday workdayB = new Workday(day, workTime, breakTime, restTime);
	private Workday workdayC = new Workday(day, workTime, breakTime, new RestTime(LocalTime.now().plusHours(1)));

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new Holiday().hashCode()).isEqualTo(new Holiday().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(workdayA.hashCode()).isEqualTo(workdayB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(workdayA.hashCode()).isNotEqualTo(workdayC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(workdayA).isEqualTo(workdayB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(workdayA).isNotEqualTo(workdayC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(workdayA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(workdayA).isNotEqualTo("A");
	}

	@Test
	public void getterDayTimeWorks() {
		assertThat(workdayA.getDay()).isEqualTo(day);
	}

	@Test
	public void getterWorkTimeWorks() {
		assertThat(workdayA.getWorkTime()).isEqualTo(workTime);
	}

	@Test
	public void getterBreakTimeWorks() {
		assertThat(workdayA.getBreakTime()).isEqualTo(breakTime);
	}

	@Test
	public void getterRestTimeWorks() {
		assertThat(workdayA.getRestTime()).isEqualTo(restTime);
	}

}
