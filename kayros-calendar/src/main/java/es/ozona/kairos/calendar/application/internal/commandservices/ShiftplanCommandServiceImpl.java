package es.ozona.kairos.calendar.application.internal.commandservices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.application.internal.exception.ShiftplanNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftplanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteShiftplanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyShiftplanPeriodCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.calendar.domain.model.valueobjects.ShiftplanId;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftplanRepository;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftplanDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftplanDeletedEventData;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandServiceImpl;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ShiftplanCommandServiceImpl extends BaseCommandServiceImpl<Shiftplan, Long, ShiftplanRepository> implements ShiftplanCommandService {

	private static final Logger LOG = LoggerFactory.getLogger(ShiftplanCommandServiceImpl.class);

	// TODO: refactorizar y llevar al domain service
	@Autowired
	private CalendarRepository calendarRepository;

	@Override
	public Shiftplan createShiftplan(CreateShiftplanCommand createShiftplanCommand) throws CalendarNotFoundException {

		// Generate shiftplan ID
		if (createShiftplanCommand.getShiftplanId() == null) {
			createShiftplanCommand.setShiftplanId(repository.nextId());
		}

		// TODO: we should check we are modifying a shiftplan for the same year
		this.findByCalendarId(createShiftplanCommand.getCalendarId());

		final Shiftplan shiftplan = new Shiftplan(createShiftplanCommand);

		repository.save(shiftplan);

		return shiftplan;
	}

	@Override
	public Shiftplan modifyShiftplanPeriod(ModifyShiftplanPeriodCommand modifyShiftplanPeriodCommand) throws ShiftplanNotFoundException {

		final Shiftplan shiftplan = this.findByShiftplanId(modifyShiftplanPeriodCommand.getShiftplanId());

		shiftplan.modifyShifplanPeriod(modifyShiftplanPeriodCommand);
		repository.save(shiftplan);

		return shiftplan;

	}

	@Override
	public void deleteShiftplan(DeleteShiftplanCommand deleteShiftplanCommand) throws ShiftplanNotFoundException {
		final Shiftplan shiftplan = this.findByShiftplanId(deleteShiftplanCommand.getShiftplanId());

		shiftplan.delete(deleteShiftplanCommand);
		repository.delete(shiftplan);

		// publicamos el borrado
		publisher.publishEvent(new ShiftplanDeletedEvent(new ShiftplanDeletedEventData(deleteShiftplanCommand.getShiftplanId())));
	}

	@Override
	public void updateEvent(CalendarDeletedEvent calendarDeletedEvent) {
		final List<Shiftplan> shiftplans = repository.findByCalendarId(new CalendarId(calendarDeletedEvent.getCalendarDeleteEventData().getCalendarId()));

		if (shiftplans.isEmpty()) {
			if (LOG.isWarnEnabled())
				LOG.warn("Calendar with ID {} haven't got any shiftplan.", calendarDeletedEvent.getCalendarDeleteEventData().getCalendarId());
		} else {
			repository.deleteInBatch(shiftplans);
		}
	}

	@Override
	public Workday addWorkday(AddWorkdayCommand addWorkdayCommand) throws ShiftplanNotFoundException {
		final Shiftplan shiftplan = this.findByShiftplanId(addWorkdayCommand.getShiftplanId());

		final Workday workday = shiftplan.addWorkday(addWorkdayCommand);
		repository.save(shiftplan);

		return workday;
	}

	@Override
	public void deleteWorkday(DeleteWorkdayCommand deleteWorkdayCommand) throws ShiftplanNotFoundException {
		final Shiftplan shiftplan = this.findByShiftplanId(deleteWorkdayCommand.getShiftplanId());

		shiftplan.deleteWorkday(deleteWorkdayCommand);
		repository.save(shiftplan);
	}

	private Shiftplan findByShiftplanId(String shiftplanId) throws ShiftplanNotFoundException {
		final Shiftplan shiftplan = repository.findByShiftplanId(new ShiftplanId(shiftplanId));
		if (shiftplan == null) {
			throw new ShiftplanNotFoundException(String.format("Shiftplan with Id %s not found.", shiftplanId));
		}
		return shiftplan;
	}

	private Calendar findByCalendarId(String calendarId) throws CalendarNotFoundException {
		final List<Calendar> calendars = calendarRepository.findByCalendarId(new CalendarId(calendarId));
		if (calendars.size() != 1) {
			throw new CalendarNotFoundException(String.format("1 result was expected but %d was obtained for ID %s.", calendars.size(), calendars));
		}
		return calendars.get(0);
	}

	@Override
	public Workday modifyWorkday(ModifyWorkdayCommand modifyWorkdayCommand) {
		final Shiftplan shiftplan = repository.findByShiftplanId(new ShiftplanId(modifyWorkdayCommand.getShiftplanId()));
		if (shiftplan == null) {
			throw new ShiftplanNotFoundException(String.format("Shiftplan with Id %s not found.", modifyWorkdayCommand.getShiftplanId()));
		}
		
		final Workday workday = shiftplan.modifyWorkday(modifyWorkdayCommand);
		repository.save(shiftplan);
		
		return workday;
	}

}
