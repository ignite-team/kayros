package es.ozona.kairos.calendar.application.internal.commandservices;

import es.ozona.kairos.calendar.application.internal.exception.ShiftplanNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftplanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteShiftplanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyShiftplanPeriodCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftplanRepository;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandService;

public interface ShiftplanCommandService extends BaseCommandService<Shiftplan, Long, ShiftplanRepository> {

	Shiftplan createShiftplan(CreateShiftplanCommand createShiftplanCommand);

	Shiftplan modifyShiftplanPeriod(ModifyShiftplanPeriodCommand modifyShiftplanPeriodCommand);

	void deleteShiftplan(DeleteShiftplanCommand deleteShiftplanCommand) throws ShiftplanNotFoundException;

	Workday addWorkday(AddWorkdayCommand addWorkdayCommand) throws ShiftplanNotFoundException;

	void deleteWorkday(DeleteWorkdayCommand deleteWorkdayCommand) throws ShiftplanNotFoundException;

	void updateEvent(CalendarDeletedEvent calendarDeletedEvent);

	Workday modifyWorkday(ModifyWorkdayCommand modifyWorkdayCommand);
}
