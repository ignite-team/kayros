package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HolidayTest {

	private String holidayString;

	private Holiday emptyHoliday;
	private Holiday holiday;

	@BeforeEach
	public void init() {

		holidayString = "holiday";

		emptyHoliday = new Holiday();
		holiday = new Holiday(holidayString);

	}

	@Test
	protected void givenHoliday_whenHolidayGetHoliday_thenReturnHoliday() {

		assertThat(holiday.getHoliday()).isEqualTo(holidayString);

	}

	@Test
	protected void givenHoliday_whenEmptHolidaySetHolidayAndGetHoliday_thenReturnHoliday() {

		emptyHoliday.setHoliday(holidayString);
		assertThat(emptyHoliday.getHoliday()).isEqualTo(holidayString);

	}

	@Test
	protected void givenHoliday_whenEqualsWithEmptyHoliday_thenReturnFalse() {

		assertThat(holiday).isNotEqualTo(emptyHoliday);

	}

	@Test
	protected void givenHoliday_whenEqualsWithNull_thenReturnFalse() {

		assertThat(holiday).isNotEqualTo(null);

	}

	@Test
	protected void givenHoliday_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(holiday).isNotEqualTo(holidayString);

	}

	@Test
	protected void givenHoliday_whenEqualsWithHoliday_thenReturnTrue() {

		assertThat(holiday).isEqualTo(holiday);

	}

}
