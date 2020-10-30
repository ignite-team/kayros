package es.ozona.kairos.calendar.application.internal.commandservices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.application.internal.exception.ShiftPlanNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.aggregates.ShiftPlan;
import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyShiftPlanPeriodCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.calendar.domain.model.valueobjects.ShiftPlanId;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;
import es.ozona.kairos.calendar.infrastructure.repositories.ShiftPlanRepository;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanDeletedEventData;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandServiceImpl;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ShiftPlanCommandServiceImpl extends BaseCommandServiceImpl<ShiftPlan, Long, ShiftPlanRepository> implements ShiftPlanCommandService {

	private static final Logger LOG = LoggerFactory.getLogger(ShiftPlanCommandServiceImpl.class);

	// TODO: refactorizar y llevar al domain service
	@Autowired
	private CalendarRepository calendarRepository;

	@Override
	public ShiftPlan createShiftPlan(CreateShiftPlanCommand createShiftPlanCommand) throws CalendarNotFoundException {

		// Generate shiftPlan ID
		if (createShiftPlanCommand.getShiftPlanId() == null) {
			createShiftPlanCommand.setShiftPlanId(repository.nextId());
		}

		// TODO: we should check we are modifying a shiftPlan for the same year
		this.findByCalendarId(createShiftPlanCommand.getCalendarId());

		final ShiftPlan shiftPlan = new ShiftPlan(createShiftPlanCommand);

		repository.save(shiftPlan);

		return shiftPlan;
	}

	@Override
	public ShiftPlan modifyShiftPlanPeriod(ModifyShiftPlanPeriodCommand modifyShiftPlanPeriodCommand) throws ShiftPlanNotFoundException {

		final ShiftPlan shiftPlan = this.findByShiftPlanId(modifyShiftPlanPeriodCommand.getShiftPlanId());

		shiftPlan.modifyShifPlanPeriod(modifyShiftPlanPeriodCommand);
		repository.save(shiftPlan);

		return shiftPlan;

	}

	@Override
	public void deleteShiftPlan(DeleteShiftPlanCommand deleteShiftPlanCommand) throws ShiftPlanNotFoundException {
		final ShiftPlan shiftPlan = this.findByShiftPlanId(deleteShiftPlanCommand.getShiftPlanId());

		shiftPlan.delete(deleteShiftPlanCommand);
		repository.delete(shiftPlan);

		// publicamos el borrado
		publisher.publishEvent(new ShiftPlanDeletedEvent(new ShiftPlanDeletedEventData(deleteShiftPlanCommand.getShiftPlanId())));
	}

	@Override
	public void updateEvent(CalendarDeletedEvent calendarDeletedEvent) {
		final List<ShiftPlan> shiftPlans = repository.findByCalendarId(new CalendarId(calendarDeletedEvent.getCalendarDeleteEventData().getCalendarId()));

		if (shiftPlans.isEmpty()) {
			if (LOG.isWarnEnabled())
				LOG.warn("Calendar with ID {} haven't got any shiftPlan.", calendarDeletedEvent.getCalendarDeleteEventData().getCalendarId());
		} else {
			repository.deleteInBatch(shiftPlans);
		}
	}

	@Override
	public Workday addWorkday(AddWorkdayCommand addWorkdayCommand) throws ShiftPlanNotFoundException {
		final ShiftPlan shiftPlan = this.findByShiftPlanId(addWorkdayCommand.getShiftPlanId());

		final Workday workday = shiftPlan.addWorkday(addWorkdayCommand);
		repository.save(shiftPlan);

		return workday;
	}

	@Override
	public void deleteWorkday(DeleteWorkdayCommand deleteWorkdayCommand) throws ShiftPlanNotFoundException {
		final ShiftPlan shiftPlan = this.findByShiftPlanId(deleteWorkdayCommand.getShiftPlanId());

		shiftPlan.deleteWorkday(deleteWorkdayCommand);
		repository.save(shiftPlan);
	}

	private ShiftPlan findByShiftPlanId(String shiftPlanId) throws ShiftPlanNotFoundException {
		final ShiftPlan shiftPlan = repository.findByShiftPlanId(new ShiftPlanId(shiftPlanId));
		if (shiftPlan == null) {
			throw new ShiftPlanNotFoundException(String.format("ShiftPlan with Id %s not found.", shiftPlanId));
		}
		return shiftPlan;
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
		final ShiftPlan shiftPlan = repository.findByShiftPlanId(new ShiftPlanId(modifyWorkdayCommand.getShiftPlanId()));
		if (shiftPlan == null) {
			throw new ShiftPlanNotFoundException(String.format("ShiftPlan with Id %s not found.", modifyWorkdayCommand.getShiftPlanId()));
		}

		final Workday workday = shiftPlan.modifyWorkday(modifyWorkdayCommand);
		repository.save(shiftPlan);

		return workday;
	}

}
