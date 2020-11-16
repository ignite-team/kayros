package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
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

	private static String pattern = "dd/MM/yyyy";
	private static SimpleDateFormat formater = new SimpleDateFormat(pattern);

	@BeforeEach
	public void init() throws java.text.ParseException {

		calendarId = "2b477572-bd4a-4c28-a504-64c9486492cc";
		scheduleId = "2b477572-bd4a-4c28-a504-64c9486492cc";
		startDate = formater.parse("01/01/1970");
		endDate = formater.parse("01/01/1970");
		startDateString = "01/01/1970";
		endDateString = "01/01/1970";

		scheduleResource = new ScheduleResource(calendarId, scheduleId, startDateString, endDateString);
		schedule = new Schedule(calendarId, scheduleId, startDate, endDate);

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
