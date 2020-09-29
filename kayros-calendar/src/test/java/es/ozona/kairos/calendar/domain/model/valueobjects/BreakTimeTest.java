package es.ozona.kairos.calendar.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BreakTimeTest {
	private LocalTime startDate = LocalTime.now().plusHours(1);
	private LocalTime endDate = LocalTime.now().plusHours(2);
	private BreakTime breakTimeA = new BreakTime(startDate, endDate);
	private BreakTime breakTimeB = new BreakTime(startDate, endDate);
	private BreakTime breakTimeC = new BreakTime(startDate, endDate.plusHours(1));

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new BreakTime().hashCode()).isEqualTo(new BreakTime().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(breakTimeA.hashCode()).isEqualTo(breakTimeB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(breakTimeA.hashCode()).isNotEqualTo(breakTimeC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(breakTimeA).isEqualTo(breakTimeB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(breakTimeA).isNotEqualTo(breakTimeC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(breakTimeA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(breakTimeA).isNotEqualTo("A");
	}

	@Test
	public void getterStartDateWorks() {
		assertThat(breakTimeA.getStart()).isEqualTo(startDate);
	}

	@Test
	public void getterEndDateWorks() {
		assertThat(breakTimeA.getEnd()).isEqualTo(endDate);
	}
}
