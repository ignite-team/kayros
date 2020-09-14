package es.ozona.kairos.calendar.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.domain.model.entities.Holiday;

@SpringBootTest
public class HolidaysTest {
	private static List<Holiday> holidays1 = new ArrayList<Holiday>();
	private static List<Holiday> holidays2 = new ArrayList<Holiday>();
	private static Holidays holidaysA;
	private static Holidays holidaysB;
	private static Holidays holidaysC;

	@BeforeAll
	public static void init() {
		holidays1.add(new Holiday(LocalDate.now()));
		holidaysA = new Holidays(holidays1);
		holidaysB = new Holidays(holidays1);
		holidays2.add(new Holiday(LocalDate.now().plusDays(1)));
		holidaysC = new Holidays(holidays2);
	}

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new Holidays().hashCode()).isEqualTo(new Holidays().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(holidaysA.hashCode()).isEqualTo(holidaysB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(holidaysA.hashCode()).isNotEqualTo(holidaysC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(holidaysA).isEqualTo(holidaysB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(holidaysA).isNotEqualTo(holidaysC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(holidaysA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(holidaysA).isNotEqualTo("A");
	}

	@Test
	public void getterHolidaysWorks() {
		assertThat(holidaysA.getHolidays()).isEqualTo(holidays1);
	}

	@Test
	public void attempToModifyHolidaysRaiseException() {
		Assertions.assertThrows(UnsupportedOperationException.class, () -> {
			holidaysA.getHolidays().add(new Holiday());
		});
	}

}
