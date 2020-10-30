package es.ozona.kayros.webapp.vms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import es.ozona.kayros.webapp.domain.model.Calendar;
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
	private List<Workday> workdays;
	private List<Holiday> holidays;

	@Init
	public void init() {

	}

	@AfterCompose
	public void afterCompose() {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Clients.evalJavaScript("initCalendar(" + format.format(new Date()) + ")");

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
			List<Holiday> holidays = new ArrayList<Holiday>();

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

						holidays.add(holiday);

					}

				}

			}

			this.setShiftPlans(shiftPlans);
			this.setHolidays(holidays);

		}

	}

	public List<ShiftPlan> getShiftPlans() {
		return shiftPlans;
	}

	public void setShiftPlans(List<ShiftPlan> shiftPlans) {

		this.shiftPlans = shiftPlans;

		if (this.shiftPlans.size() > 0) {

			List<Workday> workDays = new ArrayList<Workday>();

			for (ShiftPlan shiftPlan : this.shiftPlans) {

				List<Workday> finalWorkdays = calendarService.searchShiftPlanWorkdaysByShitfPlanId(shiftPlan.getShiftPlanId().toUpperCase());

				if (finalWorkdays.size() > 0) {

					for (Workday workday : finalWorkdays) {

						workDays.add(workday);

					}

				}

			}

			this.setWorkdays(workDays);

		}

	}

	public List<Workday> getWorkdays() {
		return workdays;
	}

	public void setWorkdays(List<Workday> workdays) {
		this.workdays = workdays;
	}

	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

	@GlobalCommand
	@NotifyChange({ "schedules", "calendars", "shiftPlans", "workdays", "holidays" })
	public void updateCalendar(@BindingParam("employeeId") String employeeId) {

		this.setSchedules(employeeService.findSchedulesByEmployeeId(employeeId));

	}

}
