package es.ozona.kairos.calendar.application.internal.commandservices;

import es.ozona.kairos.calendar.application.internal.exception.ShiftPlanNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.ShiftPlan;
import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyShiftPlanPeriodCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftPlanRepository;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandService;

public interface ShiftPlanCommandService extends BaseCommandService<ShiftPlan, Long, ShiftPlanRepository> {

	ShiftPlan createShiftPlan(CreateShiftPlanCommand createShiftPlanCommand);

	ShiftPlan modifyShiftPlanPeriod(ModifyShiftPlanPeriodCommand modifyShiftPlanPeriodCommand);

	void deleteShiftPlan(DeleteShiftPlanCommand deleteShiftPlanCommand) throws ShiftPlanNotFoundException;

	Workday addWorkday(AddWorkdayCommand addWorkdayCommand) throws ShiftPlanNotFoundException;

	void deleteWorkday(DeleteWorkdayCommand deleteWorkdayCommand) throws ShiftPlanNotFoundException;

	void updateEvent(CalendarDeletedEvent calendarDeletedEvent);

	Workday modifyWorkday(ModifyWorkdayCommand modifyWorkdayCommand);
}
