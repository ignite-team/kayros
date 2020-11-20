package es.ozona.kayros.webapp.shareddomain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShiftPlanResourceTest {

	private String shiftPlanId;
	private String calendarId;
	private String startDate;
	private String endDate;

	private ShiftPlanResource emptyShiftPlanResource;
	private ShiftPlanResource shiftPlanResource;

	@BeforeEach
	public void init() {

		shiftPlanId = "2B477572-BD4A-4C28-A504-64C948649211";
		calendarId = "2B477572-BD4A-4C28-A504-64C9486492CC";
		startDate = "2020/01/01";
		endDate = "2020/01/01";

		emptyShiftPlanResource = new ShiftPlanResource();
		shiftPlanResource = new ShiftPlanResource(shiftPlanId, calendarId, startDate, endDate);

	}

	@Test
	protected void givenShiftPlanResource_whenShiftPlanResourceGetShiftPlanId_thenReturnShiftPlanId() {

		assertThat(shiftPlanResource.getShiftPlanId()).isEqualTo(shiftPlanId);

	}

	@Test
	protected void givenShiftPlanResource_whenShiftPlanResourceGetCalendarId_thenReturnTitle() {

		assertThat(shiftPlanResource.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	protected void givenShiftPlanResource_whenShiftPlanResourceGetStartDate_thenReturnStartDate() {

		assertThat(shiftPlanResource.getStartDate()).isEqualTo(startDate);

	}

	@Test
	protected void givenShiftPlanResource_whenShiftPlanResourceGetEndDate_thenReturnEndDate() {

		assertThat(shiftPlanResource.getEndDate()).isEqualTo(endDate);

	}

	@Test
	protected void givenEmptyShiftPlanResource_whenEmptyShiftPlanResourceSetShiftPlanIdAndGetShiftPlanId_thenReturnShiftPlanId() {

		emptyShiftPlanResource.setShiftPlanId(shiftPlanId);
		assertThat(emptyShiftPlanResource.getShiftPlanId()).isEqualTo(shiftPlanId);

	}

	@Test
	protected void givenEmptyShiftPlanResource_whenEmptyShiftPlanResourceSetCalendarIdAndGetCalendarId_thenReturnCalendarId() {

		emptyShiftPlanResource.setCalendarId(calendarId);
		assertThat(emptyShiftPlanResource.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	protected void givenEmptyShiftPlanResource_whenEmptyShiftPlanResourceSetStartDateAndGetStartDate_thenReturnStartDate() {

		emptyShiftPlanResource.setStartDate(startDate);
		assertThat(emptyShiftPlanResource.getStartDate()).isEqualTo(startDate);

	}

	@Test
	protected void givenEmptyShiftPlanResource_whenEmptyShiftPlanResourceSetEndDateAndGetEndDate_thenReturnEndDate() {

		emptyShiftPlanResource.setEndDate(endDate);
		assertThat(emptyShiftPlanResource.getEndDate()).isEqualTo(endDate);

	}

	@Test
	protected void givenShiftPlanResource_whenEqualsWithEmptyEmployeeResource_thenReturnFalse() {

		assertThat(shiftPlanResource.equals(emptyShiftPlanResource)).isFalse();

	}

	@Test
	protected void givenShiftPlanResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(shiftPlanResource.equals(null)).isFalse();

	}

	@Test
	protected void givenShiftPlanResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(shiftPlanResource.equals(calendarId)).isFalse();

	}

	@Test
	protected void givenShiftPlanResource_whenEqualsWithShiftPlanResource_thenReturnTrue() {

		assertThat(shiftPlanResource.equals(shiftPlanResource)).isTrue();

	}

}
