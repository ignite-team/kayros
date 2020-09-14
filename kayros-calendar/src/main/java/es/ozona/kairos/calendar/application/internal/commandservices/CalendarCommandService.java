package es.ozona.kairos.calendar.application.internal.commandservices;

import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.commands.AddCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarDetailCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarYearCommand;
import es.ozona.kairos.calendar.domain.model.entities.Holiday;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandService;

public interface CalendarCommandService extends BaseCommandService<Calendar, Long, CalendarRepository> {

	Calendar createCalendar(CreateCalendarCommand createCalendarCommand);

	Calendar modifyCalendarDetail(ModifyCalendarDetailCommand modifyCalendarDetailCommand);

	Calendar modifyCalendarYear(ModifyCalendarYearCommand modifyCalendarYearCommand);

	void deleteCalendar(DeleteCalendarCommand deleteCalendarCommand);

	Holiday addCalendarHoliday(AddCalendarHolidayCommand addCalendarHolidayCommand);

	void deleteCalendarHoliday(DeleteCalendarHolidayCommand deleteCalendarHolidayCommand);

}
