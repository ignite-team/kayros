package es.ozona.kairos.calendar.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShiftPlanIdTest {
	private String shiftplanId = "ID-001";
	private ShiftPlanId shiftplanIdA = new ShiftPlanId(shiftplanId);
	private ShiftPlanId shiftplanIdB = new ShiftPlanId(shiftplanId);
	private ShiftPlanId shiftplanIdC = new ShiftPlanId("ID-002");

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new ShiftPlanId().hashCode()).isEqualTo(new ShiftPlanId().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(shiftplanIdA.hashCode()).isEqualTo(shiftplanIdB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(shiftplanIdA.hashCode()).isNotEqualTo(shiftplanIdC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(shiftplanIdA).isEqualTo(shiftplanIdB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(shiftplanIdA).isNotEqualTo(shiftplanIdC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(shiftplanIdA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(shiftplanIdA).isNotEqualTo("A");
	}

	@Test
	public void getterHolidaysWorks() {
		assertThat(shiftplanIdA.getShiftPlanId()).isEqualTo(shiftplanId);
	}

}
