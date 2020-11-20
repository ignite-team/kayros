package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShiftPlanTest {

	private String shiftPlanId;
	private String calendarId;
	private Date startDate;
	private Date endDate;

	private ShiftPlan emptyShiftPlan;
	private ShiftPlan shiftPlan;

	@BeforeEach
	public void init() {

		shiftPlanId = "2B477572-BD4A-4C28-A504-64C948649211";
		calendarId = "2B477572-BD4A-4C28-A504-64C9486492CC";
		startDate = new Date();
		endDate = new Date();

		emptyShiftPlan = new ShiftPlan();
		shiftPlan = new ShiftPlan(shiftPlanId, calendarId, startDate, endDate);

	}

	@Test
	protected void givenShiftPlan_whenShiftPlanGetShiftPlanId_thenReturnShiftPlanId() {

		assertThat(shiftPlan.getShiftPlanId()).isEqualTo(shiftPlanId);

	}

	@Test
	protected void givenShiftPlan_whenShiftPlanGetCalendarId_thenReturnTitle() {

		assertThat(shiftPlan.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	protected void givenShiftPlan_whenShiftPlanGetStartDate_thenReturnStartDate() {

		assertThat(shiftPlan.getStartDate()).isEqualTo(startDate);

	}

	@Test
	protected void givenShiftPlan_whenShiftPlanGetEndDate_thenReturnEndDate() {

		assertThat(shiftPlan.getEndDate()).isEqualTo(endDate);

	}

	@Test
	protected void givenEmptyShiftPlan_whenEmptyShiftPlanSetShiftPlanIdAndGetShiftPlanId_thenReturnShiftPlanId() {

		emptyShiftPlan.setShiftPlanId(shiftPlanId);
		assertThat(emptyShiftPlan.getShiftPlanId()).isEqualTo(shiftPlanId);

	}

	@Test
	protected void givenEmptyShiftPlan_whenEmptyShiftPlanSetCalendarIdAndGetCalendarId_thenReturnCalendarId() {

		emptyShiftPlan.setCalendarId(calendarId);
		assertThat(emptyShiftPlan.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	protected void givenEmptyShiftPlan_whenEmptyShiftPlanSetStartDateAndGetStartDate_thenReturnStartDate() {

		emptyShiftPlan.setStartDate(startDate);
		assertThat(emptyShiftPlan.getStartDate()).isEqualTo(startDate);

	}

	@Test
	protected void givenEmptyShiftPlan_whenEmptyShiftPlanSetEndDateAndGetEndDate_thenReturnEndDate() {

		emptyShiftPlan.setEndDate(endDate);
		assertThat(emptyShiftPlan.getEndDate()).isEqualTo(endDate);

	}

	@Test
	protected void givenShiftPlan_whenEqualsWithEmptyEmployee_thenReturnFalse() {

		assertThat(shiftPlan.equals(emptyShiftPlan)).isFalse();

	}

	@Test
	protected void givenShiftPlan_whenEqualsWithNull_thenReturnFalse() {

		assertThat(shiftPlan.equals(null)).isFalse();

	}

	@Test
	protected void givenShiftPlan_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(shiftPlan.equals(calendarId)).isFalse();

	}

	@Test
	protected void givenShiftPlan_whenEqualsWithShiftPlan_thenReturnTrue() {

		assertThat(shiftPlan.equals(shiftPlan)).isTrue();

	}

}
