package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalendarEventTest {

	private String id;
	private String groupId;
	private boolean allDay;
	private String start;
	private String end;
	private String title;
	private String description;
	private String url;
	private String[] classNames;
	private String color;
	private String display;

	private CalendarEvent emptyCalendarEvent;
	private CalendarEvent calendarEvent;

	@BeforeEach
	public void init() {

		id = "id";
		groupId = "groupId";
		allDay = false;
		start = "start";
		end = "end";
		title = "title";
		description = "description";
		url = "url";
		classNames = new String[0];
		color = "color";
		display = "display";

		emptyCalendarEvent = new CalendarEvent();
		calendarEvent = new CalendarEvent(id, groupId, allDay, start, end, title, description, url, classNames, color, display);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetId_thenReturnId() {

		assertThat(calendarEvent.getId()).isEqualTo(id);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetGroupId_thenReturnGroupId() {

		assertThat(calendarEvent.getGroupId()).isEqualTo(groupId);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetAllDay_thenReturnAllDay() {

		assertThat(calendarEvent.getAllDay()).isEqualTo(allDay);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetStart_thenReturnStart() {

		assertThat(calendarEvent.getStart()).isEqualTo(start);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetEnd_thenReturnEnd() {

		assertThat(calendarEvent.getEnd()).isEqualTo(end);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetTitle_thenReturnTitle() {

		assertThat(calendarEvent.getTitle()).isEqualTo(title);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetDescription_thenReturnDescription() {

		assertThat(calendarEvent.getDescription()).isEqualTo(description);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetUrl_thenReturnUrl() {

		assertThat(calendarEvent.getUrl()).isEqualTo(url);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetClassNames_thenReturnClassNames() {

		assertThat(calendarEvent.getClassNames()).isEqualTo(classNames);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetColor_thenReturnColor() {

		assertThat(calendarEvent.getColor()).isEqualTo(color);

	}

	@Test
	public void givenCalendarEvent_whenCalendarEventGetDisplay_thenReturnDisplay() {

		assertThat(calendarEvent.getDisplay()).isEqualTo(display);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetCalendarEventIdAndGetCalendarEvent_thenReturnCalendarEventId() {

		emptyCalendarEvent.setId(id);
		assertThat(emptyCalendarEvent.getId()).isEqualTo(id);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetGroupIdAndGetGroupId_thenReturnGroupId() {

		emptyCalendarEvent.setGroupId(groupId);
		assertThat(emptyCalendarEvent.getGroupId()).isEqualTo(groupId);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetAllDayAndGetAllDay_thenReturnAllDay() {

		emptyCalendarEvent.setAllDay(allDay);
		assertThat(emptyCalendarEvent.getAllDay()).isEqualTo(allDay);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetStartAndGetStart_thenReturnStart() {

		emptyCalendarEvent.setStart(start);
		assertThat(emptyCalendarEvent.getStart()).isEqualTo(start);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetEndAndGetEnd_thenReturnEnd() {

		emptyCalendarEvent.setEnd(end);
		assertThat(emptyCalendarEvent.getEnd()).isEqualTo(end);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetTitleAndGetTitle_thenReturnTitle() {

		emptyCalendarEvent.setTitle(title);
		assertThat(emptyCalendarEvent.getTitle()).isEqualTo(title);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetDescriptionAndGetDescription_thenReturnDescription() {

		emptyCalendarEvent.setDescription(description);
		assertThat(emptyCalendarEvent.getDescription()).isEqualTo(description);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetUrlAndGetUrl_thenReturnUrl() {

		emptyCalendarEvent.setUrl(url);
		assertThat(emptyCalendarEvent.getUrl()).isEqualTo(url);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetClassNamesAndGetClassNames_thenReturnClassNames() {

		emptyCalendarEvent.setClassNames(classNames);
		assertThat(emptyCalendarEvent.getClassNames()).isEqualTo(classNames);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetColorAndGetColor_thenReturnColor() {

		emptyCalendarEvent.setColor(color);
		assertThat(emptyCalendarEvent.getColor()).isEqualTo(color);

	}

	@Test
	public void givenEmptyCalendarEvent_whenEmptyCalendarEventSetDisplayAndGetDisplay_thenReturnDisplay() {

		emptyCalendarEvent.setDisplay(display);
		assertThat(emptyCalendarEvent.getDisplay()).isEqualTo(display);

	}

	@Test
	public void givenCalendarEvent_whenEqualsWithEmptyCalendarEvent_thenReturnFalse() {

		assertThat(calendarEvent.equals(emptyCalendarEvent)).isFalse();

	}

	@Test
	public void givenCalendarEvent_whenEqualsWithNull_thenReturnFalse() {

		assertThat(calendarEvent.equals(null)).isFalse();

	}

	@Test
	public void givenCalendarEvent_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(calendarEvent.equals(id)).isFalse();

	}

	@Test
	public void givenCalendarEvent_whenEqualsWithCalendarEvent_thenReturnTrue() {

		assertThat(calendarEvent.equals(calendarEvent)).isTrue();

	}

}
