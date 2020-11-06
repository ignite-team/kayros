package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import es.ozona.kayros.webapp.domain.model.Calendar;
import es.ozona.kayros.webapp.domain.model.Holiday;
import es.ozona.kayros.webapp.domain.model.ShiftPlan;
import es.ozona.kayros.webapp.domain.model.Workday;
import es.ozona.kayros.webapp.infrastructure.feingclients.CalendarService;
import es.ozona.kayros.webapp.internal.outboundservice.acl.CalendarMapper;
import es.ozona.kayros.webapp.internal.outboundservice.acl.HolidayMapper;
import es.ozona.kayros.webapp.internal.outboundservice.acl.ShiftPlanMapper;
import es.ozona.kayros.webapp.internal.outboundservice.acl.WorkdayMapper;
import es.ozona.kayros.webapp.shareddomain.model.CalendarResource;
import es.ozona.kayros.webapp.shareddomain.model.HolidayResource;
import es.ozona.kayros.webapp.shareddomain.model.ShiftPlanResource;
import es.ozona.kayros.webapp.shareddomain.model.WorkdayResource;

@Service("externalCalendarService")
public class ExternalCalendarServiceImpl implements ExternalCalendarService {

	@Autowired
	private CalendarService calendarService;

	@Override
	public List<Calendar> searchCalendars() {

		final List<CalendarResource> calendars = calendarService.searchCalendars(null, "+year", 1, 1000).getItems();
		return CollectionUtils.isEmpty(calendars) ? new ArrayList<Calendar>()
				: calendars.stream().map(c -> CalendarMapper.mapFromResource(c)).collect(Collectors.toList());
	}

	@Override
	public Calendar searchCalendarByCalendarId(String calendarId) {

		final CalendarResource calendar = calendarService.findCalendarById(calendarId);

		if (calendar != null) {

			Calendar finalCalendar = CalendarMapper.mapFromResource(calendar);
			return finalCalendar;

		} else {

			return null;

		}

	}

	@Override
	public List<Holiday> searchCalendarHolidaysByCalendarId(String calendarId) {

		final List<HolidayResource> holidays = calendarService.findAllHolidaysByCalendarId(calendarId);

		return CollectionUtils.isEmpty(holidays) ? new ArrayList<Holiday>()
				: holidays.stream().map(h -> HolidayMapper.mapFromResource(h)).collect(Collectors.toList());

	}

	@Override
	public ShiftPlan searchShiftPlanByShiftPlanId(String shiftPlanId) {

		final ShiftPlanResource shiftPlan = calendarService.findShiftPlanById(shiftPlanId);

		if (shiftPlan != null) {

			ShiftPlan finalShiftPlan = ShiftPlanMapper.mapFromResource(shiftPlan);
			return finalShiftPlan;

		} else {

			return null;

		}

	}

	@Override
	public List<Workday> searchShiftPlanWorkdaysByShitfPlanId(String shiftPlanId) {

		final List<WorkdayResource> workdays = calendarService.findAllWorkdaysByShiftplanId(shiftPlanId);

		return CollectionUtils.isEmpty(workdays) ? new ArrayList<Workday>()
				: workdays.stream().map(w -> WorkdayMapper.mapFromResource(w)).collect(Collectors.toList());

	}

	@Override
	public List<ShiftPlan> searchShiftPlans() {

		final List<ShiftPlanResource> shiftPlans = calendarService.searchShiftPlans(null, "+startDate", 1, 1000).getItems();
		return CollectionUtils.isEmpty(shiftPlans) ? new ArrayList<ShiftPlan>()
				: shiftPlans.stream().map(s -> ShiftPlanMapper.mapFromResource(s)).collect(Collectors.toList());
	}

	@Override
	public List<ShiftPlan> searchShiftPlansByCalendarId(String id) {

		final List<ShiftPlanResource> shiftPlans = calendarService.searchShiftPlans(String.format("calendarId:%s*", id), "+startDate", 1, 1000).getItems();
		return CollectionUtils.isEmpty(shiftPlans) ? new ArrayList<ShiftPlan>()
				: shiftPlans.stream().map(s -> ShiftPlanMapper.mapFromResource(s)).collect(Collectors.toList());

	}

}
