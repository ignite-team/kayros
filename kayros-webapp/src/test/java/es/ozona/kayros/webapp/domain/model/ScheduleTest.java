package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScheduleTest {

	private String calendarId;
	private String scheduleId;
	private Date startDate;
	private Date endDate;

	private Schedule schedule;
	private Schedule emptySchedule;

	@BeforeEach
	public void init() {

		calendarId = "2b477572-bd4a-4c28-a504-64c9486492cc";
		scheduleId = "2b477572-bd4a-4c28-a504-64c9486492cc";
		startDate = new Date(-3600000);
		endDate = new Date(-3600000);

		schedule= new Schedule(calendarId, scheduleId, startDate, endDate);
		emptySchedule= new Schedule();

	}

	@Test
	public void givenSchedule_whenScheduleGetScheduleId_thenReturnScheduleId() {

		assertThat(schedule.getScheduleId()).isEqualTo(scheduleId);

	}

	@Test
	public void givenSchedule_whenScheduleGetCalendarId_thenReturnTitle() {

		assertThat(schedule.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	public void givenSchedule_whenScheduleGetStartDate_thenReturnStartDate() {

		assertThat(schedule.getStartDate()).isEqualTo(startDate);

	}

	@Test
	public void givenSchedule_whenScheduleGetEndDate_thenReturnEndDate() {

		assertThat(schedule.getEndDate()).isEqualTo(endDate);

	}

	@Test
	public void givenEmptySchedule_whenEmptyScheduleSetScheduleIdAndGetScheduleId_thenReturnScheduleId() {

		emptySchedule.setScheduleId(scheduleId);
		assertThat(emptySchedule.getScheduleId()).isEqualTo(scheduleId);

	}

	@Test
	public void givenEmptySchedule_whenEmptyScheduleSetCalendarIdAndGetCalendarId_thenReturnCalendarId() {

		emptySchedule.setCalendarId(calendarId);
		assertThat(emptySchedule.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	public void givenEmptySchedule_whenEmptyScheduleSetStartDateAndGetStartDate_thenReturnStartDate() {

		emptySchedule.setStartDate(startDate);
		assertThat(emptySchedule.getStartDate()).isEqualTo(startDate);

	}

	@Test
	public void givenEmptySchedule_whenEmptyScheduleSetEndDateAndGetEndDate_thenReturnEndDate() {

		emptySchedule.setEndDate(endDate);
		assertThat(emptySchedule.getEndDate()).isEqualTo(endDate);

	}

	@Test
	public void givenSchedule_whenEqualsWithEmptyEmployee_thenReturnFalse() {

		assertThat(schedule.equals(emptySchedule)).isFalse();

	}

	@Test
	public void givenSchedule_whenEqualsWithNull_thenReturnFalse() {

		assertThat(schedule.equals(null)).isFalse();

	}

	@Test
	public void givenSchedule_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(schedule.equals(calendarId)).isFalse();

	}

	@Test
	public void givenSchedule_whenEqualsWithSchedule_thenReturnTrue() {

		assertThat(schedule.equals(schedule)).isTrue();

	}

}
