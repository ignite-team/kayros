package es.ozona.kairos.calendar.domain.model.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HolidayTest {

	private Long id = 1L;
	private LocalDate holiday1 = LocalDate.now();
	private LocalDate holiday2 = LocalDate.now().plusDays(1);
	private Holiday holidayA = new Holiday(holiday1);
	private Holiday holidayB = new Holiday(holiday1);
	private Holiday holidayC = new Holiday(holiday2);

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new Holiday().hashCode()).isEqualTo(new Holiday().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(holidayA.hashCode()).isEqualTo(holidayB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(holidayA.hashCode()).isNotEqualTo(holidayC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(holidayA).isEqualTo(holidayB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(holidayA).isNotEqualTo(holidayC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(holidayA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(holidayA).isNotEqualTo("A");
	}

	@Test
	public void getterIdsWorks() {
		assertThat(holidayA.getId()).isEqualTo(id);
	}

	@Test
	public void getterHolidayWorks() {
		assertThat(holidayA.getHoliday()).isEqualTo(holiday1);
	}

}
