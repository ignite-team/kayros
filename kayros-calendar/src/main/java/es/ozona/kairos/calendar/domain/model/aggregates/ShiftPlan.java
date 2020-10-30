package es.ozona.kairos.calendar.domain.model.aggregates;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

import org.modelmapper.internal.util.Assert;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.commands.DeleteWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyShiftPlanPeriodCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.BreakTime;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.calendar.domain.model.valueobjects.Day;
import es.ozona.kairos.calendar.domain.model.valueobjects.Period;
import es.ozona.kairos.calendar.domain.model.valueobjects.RestTime;
import es.ozona.kairos.calendar.domain.model.valueobjects.ShiftPlanId;
import es.ozona.kairos.calendar.domain.model.valueobjects.WorkTime;
import es.ozona.kairos.shareddomain.model.events.AbstractEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarDeletedEventData;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanCreatedEventData;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanPeriodModifiedEvent;
import es.ozona.kairos.shareddomain.model.events.ShiftPlanPeriodModifiedEventData;

@Entity
@Table(name = "shiftplan")
public class ShiftPlan extends AbstractAggregateRoot<ShiftPlan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5591313042923593316L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private ShiftPlanId shiftPlanId;

	@Embedded
	private CalendarId calendarId;

	@Embedded
	private Period period;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "shiftplan_id")
	private Set<Workday> workdays = Collections.emptySet();

	public ShiftPlan() {

	}

	public ShiftPlan(CreateShiftPlanCommand createShiftPlanCommand) {
		//TODO: verificar que el periodo pertenece a año del calendario.
		//TODO: verificar que los shiftPlan no se solapan.
		this.shiftPlanId = new ShiftPlanId(createShiftPlanCommand.getShiftPlanId());
		this.calendarId = new CalendarId(createShiftPlanCommand.getCalendarId());

		this.period = new Period(createShiftPlanCommand.getStartDate(), createShiftPlanCommand.getEndDate());

		addDomainEvent(new ShiftPlanCreatedEvent(new ShiftPlanCreatedEventData(createShiftPlanCommand.getShiftPlanId(), createShiftPlanCommand.getCalendarId(),
				createShiftPlanCommand.getStartDate(), createShiftPlanCommand.getEndDate())));
	}

	public void modifyShifPlanPeriod(ModifyShiftPlanPeriodCommand modifyShiftPlanIntervalCommand) {

		this.period = new Period(modifyShiftPlanIntervalCommand.getStartDate(), modifyShiftPlanIntervalCommand.getEndDate());

		addDomainEvent(new ShiftPlanPeriodModifiedEvent(new ShiftPlanPeriodModifiedEventData(this.getShiftPlanId().getShiftPlanId(),
				this.getCalendarId().getCalendarId(), modifyShiftPlanIntervalCommand.getStartDate(), modifyShiftPlanIntervalCommand.getEndDate())));
	}

	public void delete(DeleteShiftPlanCommand deleteShiftPlanCommand) {
		// TODO: verify delete operation should be like this
		addDomainEvent(new CalendarDeletedEvent(new CalendarDeletedEventData(deleteShiftPlanCommand.getShiftPlanId())));
	}

	public Workday addWorkday(AddWorkdayCommand addWorkdayCommand) {

		Assert.isTrue(!workdays.stream().anyMatch(w -> w.getDay().getDay().equals(addWorkdayCommand.getDay())),
				String.format("Day %s already exists.", addWorkdayCommand.getDay().name()));

		final Workday workday = new Workday(new Day(addWorkdayCommand.getDay()),
				new WorkTime(addWorkdayCommand.getWorktimeEntry(), addWorkdayCommand.getWorktimeExit()),
				new BreakTime(addWorkdayCommand.getBreakTimeStart(), addWorkdayCommand.getBreakTimeEnd()), new RestTime(addWorkdayCommand.getRestTime()));

		this.workdays.add(workday);

		return workday;
	}

	public Workday modifyWorkday(ModifyWorkdayCommand modifyWorkdayCommand) {

		final Optional<Workday> workday = workdays.stream().filter(w -> w.getDay().getDay().equals(modifyWorkdayCommand.getDay())).findFirst();
		if (workday.isPresent()) {
			workday.get().setBreakTime(new BreakTime(modifyWorkdayCommand.getBreakTimeStart(), modifyWorkdayCommand.getBreakTimeEnd()));
			workday.get().setWorkTime(new WorkTime(modifyWorkdayCommand.getWorktimeEntry(), modifyWorkdayCommand.getWorktimeExit()));
			workday.get().setRestTime(new RestTime(modifyWorkdayCommand.getRestTime()));
		} else {
			throw new WorkdayNotFoundException(modifyWorkdayCommand.getDay());
		};

		return workday.get();
	}

	public void deleteWorkday(DeleteWorkdayCommand deleteWorkdayCommand) {

		final List<Workday> currectWorkdayList = workdays.stream().filter(w -> w.getDay().getDay().equals(deleteWorkdayCommand.getDay()))
				.collect(Collectors.toList());

		Assert.isTrue(currectWorkdayList.size() > 0, String.format("Workday with ID %s does not exist.", deleteWorkdayCommand.getDay().name()));

		workdays.remove(currectWorkdayList.get(0));

	}

	public Long getId() {
		return id;
	}

	public ShiftPlanId getShiftPlanId() {
		return shiftPlanId;
	}

	public CalendarId getCalendarId() {
		return calendarId;
	}

	public Period getPeriod() {
		return period;
	}

	public List<Workday> getWorkdays() {
		return Collections.unmodifiableList(workdays.stream().sorted(new Comparator<Workday>() {
			@Override
			public int compare(Workday o1, Workday o2) {

				if (o1.getDay().getDay().ordinal() > o2.getDay().getDay().ordinal()) {
					return 1;
				} else if (o1.getDay().getDay().ordinal() < o2.getDay().getDay().ordinal()) {
					return -1;
				} else {
					return 0;
				}
			}
		}).collect(Collectors.toList()));
	}

	private void addDomainEvent(AbstractEvent calendarEvent) {
		registerEvent(calendarEvent);
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftPlanId, calendarId, period, workdays });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlan)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
