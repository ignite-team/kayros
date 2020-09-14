package es.ozona.kairos.calendar.application.internal.commandservices;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.commands.AddCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarDetailCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarYearCommand;
import es.ozona.kairos.calendar.domain.model.entities.Holiday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEventData;
import es.ozona.micro.core.application.internal.commandservices.BaseCommandServiceImpl;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class CalendarCommandServiceImpl extends BaseCommandServiceImpl<Calendar, Long, CalendarRepository> implements CalendarCommandService {

	private static final Logger LOG = LoggerFactory.getLogger(CalendarCommandServiceImpl.class);

	@Autowired
	private CalendarRepository repository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public Calendar createCalendar(@Valid CreateCalendarCommand createCalendarCommand) {

		// Generate calendar ID
		if (createCalendarCommand.getCalendarId() == null) {
			createCalendarCommand.setCalendarId(generateCalendarId());
		}

		Calendar calendar = new Calendar(createCalendarCommand);

		calendar = repository.save(calendar);

		return calendar;
	}

	@Override
	public Calendar modifyCalendarDetail(@Valid ModifyCalendarDetailCommand modifyCalendarDetailCommand) throws CalendarNotFoundException {
		Calendar calendar = this.findByCalendarId(modifyCalendarDetailCommand.getCalendarId());
		calendar.modifyDetail(modifyCalendarDetailCommand);
		return calendar;
	}

	@Override
	public Calendar modifyCalendarYear(@Valid ModifyCalendarYearCommand modifyCalendarYearCommand) throws CalendarNotFoundException {
		Calendar calendar = this.findByCalendarId(modifyCalendarYearCommand.getCalendarId());
		calendar.modifyYear(modifyCalendarYearCommand);
		return calendar;
	}

	@Override
	public void deleteCalendar(DeleteCalendarCommand deleteCalendarCommand) throws CalendarNotFoundException {
		final Calendar calendar = this.findByCalendarId(deleteCalendarCommand.getCalendarId());

		// delegamos la logica de borrado en el agregado.
		calendar.delete(deleteCalendarCommand);
		repository.delete(calendar);

		// publicamos el borrado
		publisher.publishEvent(new CalendarDeletedEvent(new CalendarDeletedEventData(deleteCalendarCommand.getCalendarId())));
	}

	@Override
	public Holiday addCalendarHoliday(AddCalendarHolidayCommand addCalendarHolidayCommand) throws CalendarNotFoundException {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Find calendar with ID {}.", addCalendarHolidayCommand.getCalendarId());
		}

		final Calendar calendar = this.findByCalendarId(addCalendarHolidayCommand.getCalendarId());

		if (LOG.isDebugEnabled()) {
			LOG.debug("Calendar with ID {} Found.", addCalendarHolidayCommand.getCalendarId());
		}

		final Holiday holiday = calendar.addCalendarHoliday(addCalendarHolidayCommand);

		repository.save(calendar);

		return holiday;

	}
	
	@Override
	public void deleteCalendarHoliday(DeleteCalendarHolidayCommand deleteCalendarHolidayCommand) throws CalendarNotFoundException {

		final Calendar calendar = this.findByCalendarId(deleteCalendarHolidayCommand.getCalendarId());

		calendar.deleteCalendarHoliday(deleteCalendarHolidayCommand);

		repository.save(calendar);

	}

	private Calendar findByCalendarId(String calendarId) throws CalendarNotFoundException {
		final List<Calendar> calendars = repository.findByCalendarId(new CalendarId(calendarId));
		if (calendars.size() != 1) {
			throw new CalendarNotFoundException(String.format("1 result was expected but %d was obtained for ID %s.", calendars.size(), calendarId));
		}
		return calendars.get(0);
	}

	private String generateCalendarId() {
		return repository.nextId();
	}

}
