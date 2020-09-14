package es.ozona.kairos.calendar.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkTimeTest {
	private LocalTime startDate = LocalTime.now().plusHours(1);
	private LocalTime endDate = LocalTime.now().plusHours(2);
	private WorkTime workTimeA = new WorkTime(startDate, endDate);
	private WorkTime workTimeB = new WorkTime(startDate, endDate);
	private WorkTime workTimeC = new WorkTime(startDate, endDate.plusHours(1));

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new WorkTime().hashCode()).isEqualTo(new WorkTime().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(workTimeA.hashCode()).isEqualTo(workTimeB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(workTimeA.hashCode()).isNotEqualTo(workTimeC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(workTimeA).isEqualTo(workTimeB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(workTimeA).isNotEqualTo(workTimeC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(workTimeA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(workTimeA).isNotEqualTo("A");
	}

	@Test
	public void getterStartDateWorks() {
		assertThat(workTimeA.getEntry()).isEqualTo(startDate);
	}

	@Test
	public void getterEndDateWorks() {
		assertThat(workTimeA.getExit()).isEqualTo(endDate);
	}
}
