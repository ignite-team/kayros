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
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

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

	private final static String fullFormat = "yyyy/MM/dd HH:mm";
	private final static String format = "yyyy/MM/dd";
	private final static String format2 = "yyyy/MM/dd";

	private final static SimpleDateFormat formaterFull = new SimpleDateFormat(fullFormat);
	private final static SimpleDateFormat formater = new SimpleDateFormat(format);
	private final static SimpleDateFormat formater2 = new SimpleDateFormat(format2);

	@Init
	public void init() {

		this.events = new ArrayList<CalendarEvent>();

		monthBefore = java.util.Calendar.getInstance();
		monthBefore.set(java.util.Calendar.MONTH, monthBefore.get(java.util.Calendar.MONTH) - 1);
		monthBefore.set(java.util.Calendar.DAY_OF_MONTH, 1);

		monthAfter = java.util.Calendar.getInstance();
		monthAfter.set(java.util.Calendar.MONTH, monthAfter.get(java.util.Calendar.MONTH) + 2);
		monthAfter.set(java.util.Calendar.DAY_OF_MONTH, 1);

	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {

		this.schedules = schedules;

		if (this.schedules.size() > 0) {

			List<Calendar> finalCalendars = new ArrayList<Calendar>();

			for (Schedule schedule : this.schedules) {

				Calendar calendar = calendarService.searchCalendarByCalendarId(schedule.getCalendarId().toUpperCase());

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

			List<ShiftPlan> shiftPlans = new ArrayList<ShiftPlan>();

			for (Calendar calendar : this.calendars) {

				List<ShiftPlan> finalShiftPlans = calendarService.searchShiftPlansByCalendarId(calendar.getCalendarId().toUpperCase());
				List<Holiday> finalHolidays = calendarService.searchCalendarHolidaysByCalendarId(calendar.getCalendarId().toUpperCase());

				if (finalShiftPlans.size() > 0) {

					for (ShiftPlan shiftPlan : finalShiftPlans) {

						shiftPlans.add(shiftPlan);

					}

				}

				if (finalHolidays.size() > 0) {

					for (Holiday holiday : finalHolidays) {

						try {

							Date date = formater2.parse(holiday.getHoliday());

							if (date.getTime() >= monthBefore.getTime().getTime() && date.getTime() < monthAfter.getTime().getTime()) {

								CalendarEvent calendarEvent = new CalendarEvent(null, "holidays", true, date, null, "Holiday", null, null);

								this.events.add(calendarEvent);

							}

						} catch (ParseException e) {

							e.printStackTrace();

						}

					}

				}

			}

			this.setShiftPlans(shiftPlans);

		}

	}

	public List<ShiftPlan> getShiftPlans() {
		return shiftPlans;
	}

	public void setShiftPlans(List<ShiftPlan> shiftPlans) {

		this.shiftPlans = shiftPlans;

		if (this.shiftPlans.size() > 0) {

			for (ShiftPlan shiftPlan : this.shiftPlans) {

				List<Workday> finalWorkdays = calendarService.searchShiftPlanWorkdaysByShitfPlanId(shiftPlan.getShiftPlanId().toUpperCase());

				if (finalWorkdays.size() > 0) {

					java.util.Calendar start = java.util.Calendar.getInstance();
					start.setTime(shiftPlan.getStartDate());
					java.util.Calendar end = java.util.Calendar.getInstance();
					end.setTime(shiftPlan.getEndDate());

					for (Date date = start.getTime(); start.before(end); start.add(java.util.Calendar.DATE, 1), date = start.getTime()) {

						if (date.getTime() >= monthBefore.getTime().getTime() && date.getTime() < monthAfter.getTime().getTime()) {

							java.util.Calendar cal = java.util.Calendar.getInstance();
							cal.setTime(date);

							int x = cal.get(java.util.Calendar.DAY_OF_WEEK);

							if (x != 1 && x != 7) {

								x -= 2;

								try {

									String day = formater.format(date);

									if (finalWorkdays.get(x).getBreakTimeStart() != null) {

										String morningStartHour = finalWorkdays.get(x).getWorkTimeEntry();
										String morningEndHour = finalWorkdays.get(x).getBreakTimeStart();

										String afternoonStartHour = finalWorkdays.get(x).getBreakTimeEnd();
										String afternoonEndHour = finalWorkdays.get(x).getWorkTimeExit();

										Date morningEventStartDate = formaterFull.parse(day + " " + morningStartHour);
										Date morningEventEndDate = formaterFull.parse(day + " " + morningEndHour);

										Date afternoonEventStartDate = formaterFull.parse(day + " " + afternoonStartHour);
										Date afternoonEventEndDate = formaterFull.parse(day + " " + afternoonEndHour);

										CalendarEvent morningEvent = new CalendarEvent(null, "workdays", false, morningEventStartDate, morningEventEndDate,
												"Workday", null, null);
										CalendarEvent afternoonEvent = new CalendarEvent(null, "workdays", false, afternoonEventStartDate,
												afternoonEventEndDate, "Workday", null, null);

										this.events.add(morningEvent);
										this.events.add(afternoonEvent);

									} else {

										String startHour = finalWorkdays.get(x).getWorkTimeEntry();
										String endHour = finalWorkdays.get(x).getWorkTimeExit();

										Date eventStartDate = formaterFull.parse(day + " " + startHour);
										Date eventEndDate = formaterFull.parse(day + " " + endHour);

										CalendarEvent event = new CalendarEvent(null, "workdays", false, eventStartDate, eventEndDate, "Workday", null, null);

										this.events.add(event);

									}

								} catch (ParseException e) {

									e.printStackTrace();

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
	public void updateCalendar(@BindingParam("employeeId") String employeeId) {

		this.setSchedules(employeeService.findSchedulesByEmployeeId(employeeId));

		System.err.println(this.events.size());

		// Clients.evalJavaScript("initCalendar(" + (new Date()) + ")");

	}

}
