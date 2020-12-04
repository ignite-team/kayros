package es.ozona.kayros.webapp.vms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import com.google.gson.Gson;

import es.ozona.kayros.webapp.domain.model.Calendar;
import es.ozona.kayros.webapp.domain.model.CalendarEvent;
import es.ozona.kayros.webapp.domain.model.Holiday;
import es.ozona.kayros.webapp.domain.model.Schedule;
import es.ozona.kayros.webapp.domain.model.ShiftPlan;
import es.ozona.kayros.webapp.domain.model.Workday;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalCalendarService;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalEmployeeService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CalendarViewModel {

	@WireVariable("externalEmployeeService")
	protected ExternalEmployeeService employeeService;

	@WireVariable("externalCalendarService")
	protected ExternalCalendarService calendarService;

	private List<Schedule> schedules;
	private List<Calendar> calendars;
	private List<ShiftPlan> shiftPlans;
	private List<CalendarEvent> events;

	private java.util.Calendar monthBefore;
	private java.util.Calendar monthAfter;
	private java.util.Calendar actualMonth;

	private String fullShiftText;
	private String holidayText;

	private final String WORKDAYCLASS = "workdayClass";
	private final String HOLIDAYCLASS = "holidayClass";
	private final String HOLIDAYDISPLAY = "background";

	private final String FORMAT = "yyyy-MM-dd";

	private final SimpleDateFormat FORMATER = new SimpleDateFormat(FORMAT);

	@Init
	public void init() {

		fullShiftText = Labels.getLabel("general.fullShift");

		holidayText = Labels.getLabel("general.holiday");

		this.events = new ArrayList<CalendarEvent>();

		monthBefore = java.util.Calendar.getInstance();
		monthBefore.set(java.util.Calendar.MONTH, monthBefore.get(java.util.Calendar.MONTH) - 1);
		monthBefore.set(java.util.Calendar.DAY_OF_MONTH, 1);
		monthBefore.set(java.util.Calendar.HOUR_OF_DAY, 0);
		monthBefore.set(java.util.Calendar.MINUTE, 0);
		monthBefore.set(java.util.Calendar.SECOND, 0);
		monthBefore.set(java.util.Calendar.MILLISECOND, 0);

		monthAfter = java.util.Calendar.getInstance();
		monthAfter.set(java.util.Calendar.MONTH, monthAfter.get(java.util.Calendar.MONTH) + 2);
		monthAfter.set(java.util.Calendar.DAY_OF_MONTH, 1);
		monthBefore.set(java.util.Calendar.HOUR_OF_DAY, 0);
		monthBefore.set(java.util.Calendar.MINUTE, 0);
		monthBefore.set(java.util.Calendar.SECOND, 0);
		monthBefore.set(java.util.Calendar.MILLISECOND, 0);

		actualMonth = java.util.Calendar.getInstance();
		monthAfter.set(java.util.Calendar.DAY_OF_MONTH, 1);
		monthBefore.set(java.util.Calendar.HOUR_OF_DAY, 0);
		monthBefore.set(java.util.Calendar.MINUTE, 0);
		monthBefore.set(java.util.Calendar.SECOND, 0);
		monthBefore.set(java.util.Calendar.MILLISECOND, 0);

	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {

		this.schedules = schedules;

		if (this.schedules.size() > 0) {

			List<Calendar> finalCalendars = new ArrayList<Calendar>();

			for (Schedule schedule : this.schedules) {

				Calendar calendar = calendarService.searchCalendarByCalendarId(schedule.getCalendarId());

				if (calendar != null) {

					finalCalendars.add(calendar);

				}

			}

			this.setCalendars(finalCalendars);

		}

	}

	public List<Calendar> getCalendars() {
		return calendars;
	}

	public void setCalendars(List<Calendar> calendars) {

		this.calendars = calendars;

		if (this.calendars.size() > 0) {

			List<ShiftPlan> tempShiftPlans = new ArrayList<ShiftPlan>();

			for (Calendar calendar : this.calendars) {

				List<ShiftPlan> finalShiftPlans = calendarService.searchShiftPlansByCalendarId(calendar.getCalendarId());
				List<Holiday> finalHolidays = calendarService.searchCalendarHolidaysByCalendarId(calendar.getCalendarId());

				if (finalShiftPlans.size() > 0) {

					for (ShiftPlan shiftPlan : finalShiftPlans) {

						tempShiftPlans.add(shiftPlan);

					}

				}

				if (finalHolidays.size() > 0) {

					for (Holiday holiday : finalHolidays) {

						try {

							Date date = FORMATER.parse(holiday.getHoliday());

							if (date.getTime() >= monthBefore.getTime().getTime() && date.getTime() < monthAfter.getTime().getTime()) {

								CalendarEvent calendarEvent = new CalendarEvent(null, null, true, holiday.getHoliday(), null, holidayText, null, null, new String[] { HOLIDAYCLASS }, null, HOLIDAYDISPLAY);

								this.events.add(calendarEvent);

							}

						} catch (ParseException e) {

							e.printStackTrace();

						}

					}

				}

			}

			this.setShiftPlans(tempShiftPlans);

		}

	}

	public List<ShiftPlan> getShiftPlans() {
		return shiftPlans;
	}

	public void setShiftPlans(List<ShiftPlan> shiftPlans) {

		this.shiftPlans = shiftPlans;

		if (this.shiftPlans.size() > 0) {

			for (ShiftPlan shiftPlan : this.shiftPlans) {

				List<Workday> finalWorkdays = calendarService.searchShiftPlanWorkdaysByShitfPlanId(shiftPlan.getShiftPlanId());

				if (finalWorkdays.size() > 0) {

					java.util.Calendar start = java.util.Calendar.getInstance();
					start.setTime(shiftPlan.getStartDate());
					java.util.Calendar end = java.util.Calendar.getInstance();
					end.setTime(shiftPlan.getEndDate());

					if (actualMonth.getTime().getTime() >= start.getTime().getTime() && actualMonth.getTime().getTime() <= end.getTime().getTime()) {

						for (Date date = start.getTime(); start.getTime().getTime() <= end.getTime().getTime(); start.add(java.util.Calendar.DATE,
								1), date = start.getTime()) {

							if (date.getTime() >= monthBefore.getTime().getTime() && date.getTime() < monthAfter.getTime().getTime()) {

								java.util.Calendar cal = java.util.Calendar.getInstance();
								cal.setTime(date);

								int x = cal.get(java.util.Calendar.DAY_OF_WEEK);

								if (x != 1 && x != 7) {

									x -= 2;

									String day = FORMATER.format(date);

									String startHour = finalWorkdays.get(x).getWorkTimeEntry();
									String endHour = finalWorkdays.get(x).getWorkTimeExit();

									CalendarEvent event = new CalendarEvent(null, null, false, day + " " + startHour, day + " " + endHour, fullShiftText, "test", null, new String[] { WORKDAYCLASS }, null, null);

									this.events.add(event);

								}

							}

						}

					}

				}

			}

		}

	}

	public List<CalendarEvent> getEvents() {
		return events;
	}

	public void setEvents(List<CalendarEvent> events) {
		this.events = events;
	}

	@GlobalCommand
	@NotifyChange({ "schedules", "calendars", "shiftPlans", "events" })
	public void updateCalendar(@BindingParam("employeeId") String employeeId, @BindingParam("language") String language) {

		this.setSchedules(employeeService.findSchedulesByEmployeeId(employeeId));

		String json = new Gson().toJson(this.events);

		Clients.evalJavaScript("initCalendar('" + FORMATER.format(new Date()) + "', '" + FORMATER.format(monthBefore.getTime()) + "', '" + FORMATER.format(monthAfter.getTime()) + "', '" + language + "', " + json + ");");

	}

}
