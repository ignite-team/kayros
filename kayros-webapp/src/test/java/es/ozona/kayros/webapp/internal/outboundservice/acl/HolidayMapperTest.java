package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kayros.webapp.domain.model.Holiday;
import es.ozona.kayros.webapp.shareddomain.model.HolidayResource;

@SpringBootTest
public class HolidayMapperTest {

	private String holidayString;

	private HolidayResource holidayResource;
	private Holiday holiday;

	@BeforeEach
	public void init() {

		holidayString = "holiday";

		holidayResource = new HolidayResource(holidayString);
		holiday = new Holiday(holidayString);

	}

	@Test
	protected void givenShiftPlanResource_whenShiftPlanMapperMapFromResourceEqualsShiftPlan_thenReturnTrue() {

		assertThat(HolidayMapper.mapFromResource(holidayResource).equals(holiday)).isTrue();

	}

	@Test
	protected void givenShiftPlan_whenShiftPlanMapperMapToResourceEqualsShiftPlanResource_thenReturnTrue() {

		assertThat(HolidayMapper.mapToResource(holiday).equals(holidayResource)).isTrue();

	}

}
