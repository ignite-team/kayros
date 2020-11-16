package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kayros.webapp.domain.model.Schedule;
import es.ozona.kayros.webapp.shareddomain.model.ScheduleResource;

@SpringBootTest
public class ScheduleMapperTest {

	private String calendarId;
	private String scheduleId;
	private Date startDate;
	private Date endDate;

	private String startDateString;
	private String endDateString;

	private ScheduleResource scheduleResource;
	private Schedule schedule;

	@BeforeEach
	public void init() {

		calendarId = "2b477572-bd4a-4c28-a504-64c9486492cc";
		scheduleId = "2b477572-bd4a-4c28-a504-64c9486492cc";
		startDate = new Date(-3600000);
		endDate = new Date(-3600000);
		startDateString = "01/01/1970";
		endDateString = "01/01/1970";

		scheduleResource = new ScheduleResource(calendarId, scheduleId, startDateString, endDateString);
		schedule = new Schedule(calendarId, scheduleId, startDate, endDate);

	}

	@Test
	public void test() {

		assertThat(ScheduleMapper.mapFromResource(scheduleResource).getCalendarId()).isEqualTo(schedule.getCalendarId());
		assertThat(ScheduleMapper.mapFromResource(scheduleResource).getScheduleId()).isEqualTo(schedule.getScheduleId());
		assertThat(ScheduleMapper.mapFromResource(scheduleResource).getStartDate()).isEqualTo(schedule.getStartDate());
		assertThat(ScheduleMapper.mapFromResource(scheduleResource).getEndDate()).isEqualTo(schedule.getEndDate());

	}

	@Test
	public void givenScheduleResource_whenScheduleMapperMapFromResourceEqualsSchedule_thenReturnTrue() {

		assertThat(ScheduleMapper.mapFromResource(scheduleResource).equals(schedule)).isTrue();

	}

	@Test
	public void givenSchedule_whenScheduleMapperMapToResourceEqualsScheduleResource_thenReturnTrue() {

		assertThat(ScheduleMapper.mapToResource(schedule).equals(scheduleResource)).isTrue();

	}

}
