package es.ozona.kayros.webapp.shareddomain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HolidayResourceTest {

	private String holiday;

	private HolidayResource emptyHolidayResource;
	private HolidayResource holidayResource;

	@BeforeEach
	public void init() {

		holiday = "holiday";

		emptyHolidayResource = new HolidayResource();
		holidayResource = new HolidayResource(holiday);

	}

	@Test
	public void givenHolidayResource_whenHolidayResourceGetHoliday_thenReturnHoliday() {

		assertThat(holidayResource.getHoliday()).isEqualTo(holiday);

	}

	@Test
	public void givenHolidayResource_whenEmptHolidayResourceSetHolidayAndGetHoliday_thenReturnHoliday() {

		emptyHolidayResource.setHoliday(holiday);
		assertThat(emptyHolidayResource.getHoliday()).isEqualTo(holiday);

	}

	@Test
	public void givenHolidayResource_whenEqualsWithEmptyHolidayResource_thenReturnFalse() {

		assertThat(holidayResource.equals(emptyHolidayResource)).isFalse();

	}

	@Test
	public void givenHolidayResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(holidayResource.equals(null)).isFalse();

	}

	@Test
	public void givenHolidayResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(holidayResource.equals(holiday)).isFalse();

	}

	@Test
	public void givenHolidayResource_whenEqualsWithHolidayResource_thenReturnTrue() {

		assertThat(holidayResource.equals(holidayResource)).isTrue();

	}

}
