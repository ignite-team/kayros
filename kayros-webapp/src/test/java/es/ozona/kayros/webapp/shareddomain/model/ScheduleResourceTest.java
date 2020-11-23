package es.ozona.kayros.webapp.shareddomain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScheduleResourceTest {

	private String calendarId;
	private String scheduleId;
	private String startDate;
	private String endDate;

	private ScheduleResource scheduleResource;
	private ScheduleResource emptyScheduleResource;

	@BeforeEach
	public void init() {

		calendarId = "2b477572-bd4a-4c28-a504-64c9486492cc";
		scheduleId = "2b477572-bd4a-4c28-a504-64c9486492cc";
		startDate = "01/01/1970";
		endDate = "01/01/1970";

		scheduleResource = new ScheduleResource(calendarId, scheduleId, startDate, endDate);
		emptyScheduleResource = new ScheduleResource();

	}

	@Test
	protected void givenScheduleResource_whenScheduleResourceGetScheduleResourceId_thenReturnScheduleResourceId() {

		assertThat(scheduleResource.getScheduleId()).isEqualTo(scheduleId);

	}

	@Test
	protected void givenScheduleResource_whenScheduleResourceGetCalendarId_thenReturnTitle() {

		assertThat(scheduleResource.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	protected void givenScheduleResource_whenScheduleResourceGetStartDate_thenReturnStartDate() {

		assertThat(scheduleResource.getStartDate()).isEqualTo(startDate);

	}

	@Test
	protected void givenScheduleResource_whenScheduleResourceGetEndDate_thenReturnEndDate() {

		assertThat(scheduleResource.getEndDate()).isEqualTo(endDate);

	}

	@Test
	protected void givenEmptyScheduleResource_whenEmptyScheduleResourceSetScheduleResourceIdAndGetScheduleResourceId_thenReturnScheduleResourceId() {

		emptyScheduleResource.setScheduleId(scheduleId);
		assertThat(emptyScheduleResource.getScheduleId()).isEqualTo(scheduleId);

	}

	@Test
	protected void givenEmptyScheduleResource_whenEmptyScheduleResourceSetCalendarIdAndGetCalendarId_thenReturnCalendarId() {

		emptyScheduleResource.setCalendarId(calendarId);
		assertThat(emptyScheduleResource.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	protected void givenEmptyScheduleResource_whenEmptyScheduleResourceSetStartDateAndGetStartDate_thenReturnStartDate() {

		emptyScheduleResource.setStartDate(startDate);
		assertThat(emptyScheduleResource.getStartDate()).isEqualTo(startDate);

	}

	@Test
	protected void givenEmptyScheduleResource_whenEmptyScheduleResourceSetEndDateAndGetEndDate_thenReturnEndDate() {

		emptyScheduleResource.setEndDate(endDate);
		assertThat(emptyScheduleResource.getEndDate()).isEqualTo(endDate);

	}

	@Test
	protected void givenScheduleResource_whenEqualsWithEmptyEmployee_thenReturnFalse() {

		assertThat(scheduleResource).isNotEqualTo(emptyScheduleResource);

	}

	@Test
	protected void givenScheduleResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(scheduleResource).isNotEqualTo(null);
	}

	@Test
	protected void givenScheduleResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(scheduleResource).isNotEqualTo(calendarId);

	}

	@Test
	protected void givenScheduleResource_whenEqualsWithScheduleResource_thenReturnTrue() {

		assertThat(scheduleResource).isEqualTo(scheduleResource);

	}

}
