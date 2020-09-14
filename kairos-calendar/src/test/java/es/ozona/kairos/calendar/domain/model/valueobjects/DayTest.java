package es.ozona.kairos.calendar.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DayTest {
	private Day dayA = new Day(DayOfWeek.MONDAY);
	private Day dayB = new Day(DayOfWeek.MONDAY);
	private Day dayC = new Day(DayOfWeek.TUESDAY);

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new Day().hashCode()).isEqualTo(new Day().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(dayA.hashCode()).isEqualTo(dayB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(dayA.hashCode()).isNotEqualTo(dayC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(dayA).isEqualTo(dayB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(dayA).isNotEqualTo(dayC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(dayA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(dayA).isNotEqualTo("A");
	}

	@Test
	public void getterDayWorks() {
		assertThat(dayA.getDay()).isEqualTo(DayOfWeek.MONDAY);
	}

	@Test
	public void toStringReturnsDayName() {
		assertThat(dayA.toString()).isEqualTo("MONDAY");
	}

}
