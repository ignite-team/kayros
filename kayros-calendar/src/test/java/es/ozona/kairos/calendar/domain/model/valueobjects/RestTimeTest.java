package es.ozona.kairos.calendar.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestTimeTest {
	private LocalTime restTime = LocalTime.now();
	private RestTime restTimeA = new RestTime(restTime);
	private RestTime restTimeB = new RestTime(restTime);
	private RestTime restTimeC = new RestTime(LocalTime.now().plusHours(1));

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new RestTime().hashCode()).isEqualTo(new RestTime().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(restTimeA.hashCode()).isEqualTo(restTimeB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(restTimeA.hashCode()).isNotEqualTo(restTimeC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(restTimeA).isEqualTo(restTimeB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(restTimeA).isNotEqualTo(restTimeC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(restTimeA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(restTimeA).isNotEqualTo("A");
	}

	@Test
	public void getterRestTimeWorks() {
		assertThat(restTimeA.getRestTime()).isEqualTo(restTime);
	}
}
