package es.ozona.kairos.calendar.domain.model.aggregates;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.util.ObjectUtils;

import com.sun.istack.NotNull;

import es.ozona.kairos.calendar.domain.model.commands.AddCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarDetailCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyCalendarYearCommand;
import es.ozona.kairos.calendar.domain.model.entities.Holiday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.shareddomain.model.events.AbstractEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEventData;
import es.ozona.kairos.shareddomain.model.events.CalendarHolidayAddedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarHolidayAddedEventData;
import es.ozona.kairos.shareddomain.model.events.CalendarHolidayDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarHolidayDeletedEventData;
import es.ozona.kairos.shareddomain.model.events.CalendarYearModifiedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarYearModifiedEventData;

@Entity
@Table(name = "calendars")
public class Calendar extends AbstractAggregateRoot<Calendar> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private CalendarId calendarId; // aggregate ID

	@NotNull
	private String title;

	private String description;
	
	@NotNull
	private Boolean markedAsDefault;

	@NotNull
	@Min(value = 1975)
	@Max(value = 2099)
	private Integer year;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "calendar_id")
	private Set<Holiday> holidays = new HashSet<Holiday>(0);

	public Calendar() {

	}

	public Calendar(CreateCalendarCommand createCalendarCommand) {
		super();
		this.calendarId = new CalendarId(createCalendarCommand.getCalendarId());
		this.title = createCalendarCommand.getTitle();
		this.description = createCalendarCommand.getDescription();
		this.year = createCalendarCommand.getYear();
		// TODO: solo se puede existir un calendario por defecto.
		this.markedAsDefault = createCalendarCommand.getMarkedAsDefault();

		addDomainEvent(new CalendarCreatedEvent(new CalendarCreatedEventData(this.calendarId.getCalendarId(), year)));
	}

	public void modifyDetail(ModifyCalendarDetailCommand modifyCalendarDetailCommand) {
		this.title = modifyCalendarDetailCommand.getTitle();
		this.description = modifyCalendarDetailCommand.getDescription();
	}

	public void modifyYear(ModifyCalendarYearCommand modifyYearCommand) {
		this.year = modifyYearCommand.getYear();

		final CalendarYearModifiedEventData eventData = new CalendarYearModifiedEventData(modifyYearCommand.getCalendarId(), modifyYearCommand.getYear());

		addDomainEvent(new CalendarYearModifiedEvent(eventData));
	}

	public void delete(DeleteCalendarCommand deleteCalendarCommand) {
		// TODO: here delete logic.
	}

	public Holiday addCalendarHoliday(AddCalendarHolidayCommand addCalendarHolidayCommand) {
		final Holiday holiday = new Holiday(addCalendarHolidayCommand.getHoliday());
		holidays.add(holiday);

		addDomainEvent(new CalendarHolidayAddedEvent(
				new CalendarHolidayAddedEventData(addCalendarHolidayCommand.getCalendarId(), addCalendarHolidayCommand.getHoliday())));

		return holiday;
	}

	public void deleteCalendarHoliday(DeleteCalendarHolidayCommand deleteCalendarHolidayCommand) {
		final Holiday holiday = new Holiday(deleteCalendarHolidayCommand.getDate());
		this.holidays.remove(holiday);

		addDomainEvent(new CalendarHolidayDeletedEvent(
				new CalendarHolidayDeletedEventData(deleteCalendarHolidayCommand.getCalendarId(), deleteCalendarHolidayCommand.getDate())));
	}

	public Long getId() {
		return id;
	}

	public CalendarId getCalendarId() {
		return calendarId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Integer getYear() {
		return year;
	}	

	public Boolean getMarkedAsDefault() {
		return markedAsDefault;
	}

	public void setMarkedAsDefault(Boolean markedAsDefault) {
		this.markedAsDefault = markedAsDefault;
	}

	public List<Holiday> getHolidays() {
		return Collections.unmodifiableList(holidays.stream().sorted().collect(Collectors.toList()));
	}

	private void addDomainEvent(AbstractEvent calendarEvent) {
		registerEvent(calendarEvent);
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, year, holidays });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Calendar)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
