package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.List;

import es.ozona.kayros.webapp.domain.model.Calendar;
import es.ozona.kayros.webapp.domain.model.Holiday;
import es.ozona.kayros.webapp.domain.model.ShiftPlan;
import es.ozona.kayros.webapp.domain.model.Workday;

public interface ExternalCalendarService {

	List<Calendar> searchCalendars();

	Calendar searchCalendarByCalendarId(String calendarId);

	List<Holiday> searchCalendarHolidaysByCalendarId(String calendarId);

	ShiftPlan searchShiftPlanByShiftPlanId(String shiftPlanId);

	List<Workday> searchShiftPlanWorkdaysByShitfPlanId(String shiftPlanId);

	List<ShiftPlan> searchShiftPlans();

	List<ShiftPlan> searchShiftPlansByCalendarId(String id);
}
