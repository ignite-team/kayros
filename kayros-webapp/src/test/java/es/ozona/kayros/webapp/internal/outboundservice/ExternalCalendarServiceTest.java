package es.ozona.kayros.webapp.internal.outboundservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.domain.model.Calendar;
import es.ozona.kayros.webapp.domain.model.ShiftPlan;
import es.ozona.kayros.webapp.infrastructure.feingclients.CalendarService;
import es.ozona.kayros.webapp.shareddomain.model.CalendarResource;
import es.ozona.kayros.webapp.shareddomain.model.HolidayResource;
import es.ozona.kayros.webapp.shareddomain.model.ShiftPlanResource;
import es.ozona.kayros.webapp.shareddomain.model.WorkdayResource;

@SpringBootTest
public class ExternalCalendarServiceTest {

	private String calendarId;
	private String title;
	private String description;
	private int year;
	private boolean markedAsDefault;

	private String shiftPlanId;
	private Date startDate;
	private Date endDate;
	private String startDateString;
	private String endDateString;

	private int day;
	private String worktimeEntry;
	private String worktimeExit;
	private String breaktimeStart;
	private String breaktimeEnd;
	private String restTime;

	private String holidayString;

	private CalendarResource calendarResource;

	private ShiftPlanResource shiftPlanResource;

	private WorkdayResource workdayResource;

	private HolidayResource holidayResource;

	private Calendar calendar;
	private ShiftPlan shiftPlan;

	private List<CalendarResource> calendarResourceList;
	private List<ShiftPlanResource> shiftPlanResourceList;

	private List<WorkdayResource> workdayResourceList;
	private List<HolidayResource> holidayResourceList;
	private List<WorkdayResource> emptyWorkdayResourceList;
	private List<HolidayResource> emptyHolidayResourceList;

	private PageResult<CalendarResource> calendarPageResult;
	private PageResult<CalendarResource> emptyCalendarPageResult;

	private PageResult<ShiftPlanResource> shiftPlanPageResult;
	private PageResult<ShiftPlanResource> emptyShiftPlanPageResult;

	@Autowired
	ExternalCalendarService externalCalendarService;

	@MockBean
	CalendarService calendarService;

	@BeforeEach
	public void init() {

		calendarId = "2B477572-BD4A-4C28-A504-64C9486492CC";
		title = "title";
		description = "description";
		year = 2020;
		markedAsDefault = false;

		shiftPlanId = "2B477572-BD4A-4C28-A504-64C948649211";
		startDate = new Date(-3600000);
		endDate = new Date(-3600000);
		startDateString = "1970/01/01";
		endDateString = "1970/01/01";

		day = 0;
		worktimeEntry = "worktimeEntry";
		worktimeExit = "worktimeExit";
		breaktimeStart = "breaktimeStart";
		breaktimeEnd = "breaktimeEnd";
		restTime = "restTime";

		holidayString = "holiday";

		calendarResource = new CalendarResource(calendarId, title, description, year, markedAsDefault);

		shiftPlanResource = new ShiftPlanResource(shiftPlanId, calendarId, startDateString, endDateString);

		workdayResource = new WorkdayResource(day, worktimeEntry, worktimeExit, breaktimeStart, breaktimeEnd, restTime);

		holidayResource = new HolidayResource(holidayString);

		calendar = new Calendar(calendarId, title, description, year, markedAsDefault);
		shiftPlan = new ShiftPlan(shiftPlanId, calendarId, startDate, endDate);

		calendarResourceList = new ArrayList<CalendarResource>();
		shiftPlanResourceList = new ArrayList<ShiftPlanResource>();
		workdayResourceList = new ArrayList<WorkdayResource>();
		holidayResourceList = new ArrayList<HolidayResource>();

		emptyWorkdayResourceList = new ArrayList<WorkdayResource>();
		emptyHolidayResourceList = new ArrayList<HolidayResource>();

		calendarResourceList.add(calendarResource);
		shiftPlanResourceList.add(shiftPlanResource);
		workdayResourceList.add(workdayResource);
		holidayResourceList.add(holidayResource);

		calendarPageResult = new PageResult<CalendarResource>();
		emptyCalendarPageResult = new PageResult<CalendarResource>();

		shiftPlanPageResult = new PageResult<ShiftPlanResource>();
		emptyShiftPlanPageResult = new PageResult<ShiftPlanResource>();

		calendarPageResult.setItems(calendarResourceList);
		shiftPlanPageResult.setItems(shiftPlanResourceList);

	}

	@Test
	public void givenExistingCalendars_whenCallSearchCalendars_thenReturnCalendars() {

		Mockito.when(calendarService.searchCalendars(null, "+year", 1, 1000)).thenReturn(calendarPageResult);
		assertThat(externalCalendarService.searchCalendars().size() > 0).isTrue();

	}

	@Test
	public void givenNoCalendars_whenCallSearchCalendars_thenReturnNoCalendars() {

		Mockito.when(calendarService.searchCalendars(null, "+year", 1, 1000)).thenReturn(emptyCalendarPageResult);
		assertThat(externalCalendarService.searchCalendars().size() == 0).isTrue();

	}

	@Test
	public void givenExistingShiftPlans_whenCallSearchShiftPlans_thenReturnShiftPlans() {

		Mockito.when(calendarService.searchShiftPlans(null, "+startDate", 1, 1000)).thenReturn(shiftPlanPageResult);
		assertThat(externalCalendarService.searchShiftPlans().size() > 0).isTrue();

	}

	@Test
	public void givenNoShiftPlans_whenCallSearchShiftPlans_thenReturnNoShiftPlans() {

		Mockito.when(calendarService.searchShiftPlans(null, "+startDate", 1, 1000)).thenReturn(emptyShiftPlanPageResult);
		assertThat(externalCalendarService.searchShiftPlans().size() == 0).isTrue();

	}

	@Test
	public void givenExistingHolidays_whenCallSearchCalendarHolidaysByCalendarId_thenReturnHolidays() {

		Mockito.when(calendarService.findAllHolidaysByCalendarId(calendarId)).thenReturn(holidayResourceList);
		assertThat(externalCalendarService.searchCalendarHolidaysByCalendarId(calendarId).size() > 0).isTrue();

	}

	@Test
	public void givenNoHolidays_whenCallSearchCalendarHolidaysByCalendarId_thenReturnNoHolidays() {

		Mockito.when(calendarService.findAllHolidaysByCalendarId(calendarId)).thenReturn(emptyHolidayResourceList);
		assertThat(externalCalendarService.searchCalendarHolidaysByCalendarId(calendarId).size() == 0).isTrue();

	}

	@Test
	public void givenExistingWorkdays_whenCallSearchShiftPlanWorkdaysByShitfPlanId_thenReturnWorkdays() {

		Mockito.when(calendarService.findAllWorkdaysByShiftplanId(shiftPlanId)).thenReturn(workdayResourceList);
		assertThat(externalCalendarService.searchShiftPlanWorkdaysByShitfPlanId(shiftPlanId).size() > 0).isTrue();

	}

	@Test
	public void givenNoWorkdays_whenCallSearchShiftPlanWorkdaysByShitfPlanId_thenReturnNoWorkdays() {

		Mockito.when(calendarService.findAllWorkdaysByShiftplanId(shiftPlanId)).thenReturn(emptyWorkdayResourceList);
		assertThat(externalCalendarService.searchShiftPlanWorkdaysByShitfPlanId(shiftPlanId).size() == 0).isTrue();

	}

	@Test
	public void givenExistingCalendar_whenCallSearchCalendarByCalendarId_thenReturnCalendar() {

		Mockito.when(calendarService.findCalendarById(calendarId)).thenReturn(calendarResource);
		assertThat(externalCalendarService.searchCalendarByCalendarId(calendarId)).isEqualTo(calendar);

	}

	@Test
	public void givenNoCalendar_whenCallSearchCalendarByCalendarId_thenReturnNull() {

		Mockito.when(calendarService.findCalendarById(calendarId)).thenReturn(null);
		assertThat(externalCalendarService.searchCalendarByCalendarId(calendarId)).isNull();

	}

	@Test
	public void givenExistingShiftPlan_whenCallSearchShiftPlanByShiftPlanId_thenReturnShiftPlans() {

		Mockito.when(calendarService.findShiftPlanById(shiftPlanId)).thenReturn(shiftPlanResource);
		assertThat(externalCalendarService.searchShiftPlanByShiftPlanId(shiftPlanId)).isEqualTo(shiftPlan);

	}

	@Test
	public void givenNoShiftPlan_whenCallSearchShiftPlanByShiftPlanId_thenReturnNull() {

		Mockito.when(calendarService.findShiftPlanById(shiftPlanId)).thenReturn(null);
		assertThat(externalCalendarService.searchShiftPlanByShiftPlanId(shiftPlanId)).isNull();

	}

}
