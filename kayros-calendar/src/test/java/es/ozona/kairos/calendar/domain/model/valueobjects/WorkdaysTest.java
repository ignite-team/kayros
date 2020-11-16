package es.ozona.kairos.calendar.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.domain.model.entities.Workday;

@SpringBootTest
public class WorkdaysTest {
	private static List<Workday> workdays1 = new ArrayList<Workday>();
	private static List<Workday> workdays2 = new ArrayList<Workday>();
	private static Workdays workdaysA;
	private static Workdays workdaysB;
	private static Workdays workdaysC;

	@BeforeAll
	public static void init() {
		workdaysA = new Workdays(workdays1);
		workdaysB = new Workdays(workdays1);
		workdays2.add(new Workday(null, null, null, null));
		workdaysC = new Workdays(workdays2);
	}

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new Workdays().hashCode()).isEqualTo(new Workdays().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(workdaysA.hashCode()).isEqualTo(workdaysB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(workdaysA.hashCode()).isNotEqualTo(workdaysC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(workdaysA).isEqualTo(workdaysB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(workdaysA).isNotEqualTo(workdaysC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(workdaysA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(workdaysA).isNotEqualTo("A");
	}

	@Test
	public void getterWorkdaysWorks() {
		assertThat(workdaysA.getWorkdays()).isEqualTo(workdays1);
	}

	@Test
	public void attempToModifyWorkdaysRaiseException() {
		Assertions.assertThrows(UnsupportedOperationException.class, () -> {
			workdaysA.getWorkdays().add(new Workday());
		});
	}

}
