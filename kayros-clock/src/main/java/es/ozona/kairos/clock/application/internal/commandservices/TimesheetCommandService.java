package es.ozona.kairos.clock.application.internal.commandservices;

import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.domain.model.commands.ClockTimesheetCommand;
import es.ozona.kairos.clock.domain.model.commands.CreateTimesheetCommand;
import es.ozona.kairos.clock.infrastructure.repositories.TimesheetRepository;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandService;

public interface TimesheetCommandService extends BaseCommandService<Timesheet, Long, TimesheetRepository> {

	public Timesheet clock(ClockTimesheetCommand clockTimesheetCommand);

	public Timesheet create(CreateTimesheetCommand createTimesheetCommand);
	
}
