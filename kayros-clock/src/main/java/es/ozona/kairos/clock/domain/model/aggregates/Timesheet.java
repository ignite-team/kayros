package es.ozona.kairos.clock.domain.model.aggregates;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.util.ObjectUtils;

import es.ozona.kairos.clock.domain.model.commands.ClockTimesheetCommand;
import es.ozona.kairos.clock.domain.model.commands.CreateTimesheetCommand;
import es.ozona.kairos.clock.domain.model.entities.WorkingTimePeriod;
import es.ozona.kairos.clock.domain.model.valueobjects.EmployeeId;
import es.ozona.kairos.clock.domain.model.valueobjects.FinishTime;
import es.ozona.kairos.clock.domain.model.valueobjects.StartTime;
import es.ozona.kairos.clock.domain.model.valueobjects.TimesheetId;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetClockedInEvent;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetClockedInEventData;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetClockedOutEvent;
import es.ozona.kairos.clock.shareddomain.model.events.TimesheetClockedOutEventData;
import es.ozona.kairos.clock.shareddomain.model.events.UnregisteredTimesheetClockedInEvent;
import es.ozona.kairos.clock.shareddomain.model.events.UnregisteredTimesheetClockedInEventData;

/**
 * The {@code Timesheet} class represents the ordered record of a workday's records. At the structural level, it is the aggregate responsible for managing the
 * registry.
 * 
 * @author Xose
 * @since 1.0
 */
@Entity
@Table(name = "timesheets")
public class Timesheet extends AbstractAggregateRoot<Timesheet> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private TimesheetId timesheetId;

	@Embedded
	private EmployeeId employeeId;
	
	private String username;

	@NotNull
	@Column(name = "date", nullable = false)
	private LocalDate date;

	@Embedded
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "tiemsheet_id")
	private Set<WorkingTimePeriod> workingTimePeriods = new HashSet<WorkingTimePeriod>(0);

	/**
	 * Default constructor.
	 */
	public Timesheet() {

	}
	
	public Timesheet(CreateTimesheetCommand createTimesheetCommand, boolean unregistered) {
		this(createTimesheetCommand);
		registerEvent(new UnregisteredTimesheetClockedInEvent(new UnregisteredTimesheetClockedInEventData(createTimesheetCommand.getEmployeeId(), createTimesheetCommand.getUsername())));
	}

	/**
	 * Create an instance of the class {@code Timesheet} with the given arguments
	 * 
	 * @param timesheetCommand
	 */
	public Timesheet(CreateTimesheetCommand createTimesheetCommand) {
		this.timesheetId = new TimesheetId(createTimesheetCommand.getTimesheetId());
		this.employeeId = new EmployeeId(createTimesheetCommand.getEmployeeId());
		this.username = createTimesheetCommand.getUsername();
		this.date = createTimesheetCommand.getDate();

		final ClockTimesheetCommand clockTimesheetCommand = new ClockTimesheetCommand(createTimesheetCommand.getEmployeeId(), createTimesheetCommand.getUsername());

		clockIn(clockTimesheetCommand);
	}

	/**
	 * Sets relational Id
	 * 
	 * @return the relational Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets relational Id
	 * 
	 * @param id the relational Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the timesheetId
	 * 
	 * @return the {@ TimesheetId}
	 */
	public TimesheetId getTimesheetId() {
		return timesheetId;
	}

	/**
	 * Sets a timesheetId
	 * 
	 * @param timesheetId the {@code ThimesheetId}
	 */
	public void setTimesheetId(TimesheetId timesheetId) {
		this.timesheetId = timesheetId;
	}

	/**
	 * Gets the employeeId
	 * 
	 * @return the {@code EmployeeId}
	 */
	public EmployeeId getEmployeeId() {
		return employeeId;
	}

	/**
	 * Sets an emploeeId
	 * 
	 * @param employeeId the {@code EmployeeId}
	 */
	public void setEmployeeId(EmployeeId employeeId) {
		this.employeeId = employeeId;
	}	
	
	/**
	 * Gets the username
	 * 
	 * @return the {@code Timesheet}
	 */	
	public String getUsername() {
		return username;
	}

	/**
	 * Sets an username
	 * 
	 * @param username the {@code Timesheet}
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the day of {@code Timesheet}.
	 * 
	 * @return the day.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets a day of {@code Timesheet}
	 * 
	 * @param date a day
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Gets working time periods
	 * 
	 * @return a unmodificable list of {@code WorkingTimePeriod) 's
	 */
	public List<WorkingTimePeriod> getWorkingTimePeriod() {

		return Collections.unmodifiableList(workingTimePeriods.stream().sorted(new WorkingTimePeriodComparator()).collect(Collectors.toList()));

	}

	public void clock(ClockTimesheetCommand clockTimesheetCommand) {
		final Optional<WorkingTimePeriod> workingTimePeriod = workingTimePeriods.stream().max(new WorkingTimePeriodComparator());

		if (workingTimePeriod.isPresent()) {
			final WorkingTimePeriod wtpValue = workingTimePeriod.get();
 
			if (wtpValue.getFinishTime().isEmpty()) {
				clockOut(clockTimesheetCommand, workingTimePeriod.get());

			} else {
				clockIn(clockTimesheetCommand);

			}
		} else {
			clockIn(clockTimesheetCommand);
		}
	}

	protected void clockIn(ClockTimesheetCommand clockTimesheetCommand) {
		final WorkingTimePeriod newWorkingTimePeriod = new WorkingTimePeriod(new StartTime(ZonedDateTime.now(), Boolean.FALSE, Boolean.FALSE));
		this.workingTimePeriods.add(newWorkingTimePeriod);
		
		registerEvent(new TimesheetClockedInEvent(new TimesheetClockedInEventData(employeeId.getEmployeeId(), timesheetId.getTimesheetId(), date,
				newWorkingTimePeriod.getStartTime().getStartTime(), newWorkingTimePeriod.getStartTime().isGenerated(),
				newWorkingTimePeriod.getStartTime().isGenerated(),
				newWorkingTimePeriod.getFinishTime().isPresent() ? newWorkingTimePeriod.getFinishTime().get().getFinishTime() : null,
				newWorkingTimePeriod.getFinishTime().isPresent() ? newWorkingTimePeriod.getFinishTime().get().isGenerated() : null,
				newWorkingTimePeriod.getFinishTime().isPresent() ? newWorkingTimePeriod.getFinishTime().get().isEdited() : null)));
	}

	protected void clockOut(ClockTimesheetCommand clockTimesheetCommand, WorkingTimePeriod workingTimePeriod) {
		final FinishTime finishTime = new FinishTime(clockTimesheetCommand.getClockTime(), Boolean.FALSE, Boolean.FALSE);
		workingTimePeriod.setFinishTime(finishTime);

		registerEvent(new TimesheetClockedOutEvent(new TimesheetClockedOutEventData(employeeId.getEmployeeId(), timesheetId.getTimesheetId(), date,
				workingTimePeriod.getStartTime().getStartTime(), workingTimePeriod.getStartTime().isGenerated(), workingTimePeriod.getStartTime().isGenerated(),
				workingTimePeriod.getFinishTime().isPresent() ? workingTimePeriod.getFinishTime().get().getFinishTime() : null,
				workingTimePeriod.getFinishTime().isPresent() ? workingTimePeriod.getFinishTime().get().isGenerated() : null,
				workingTimePeriod.getFinishTime().isPresent() ? workingTimePeriod.getFinishTime().get().isEdited() : null)));
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { id, timesheetId, employeeId, workingTimePeriods });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Timesheet)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

	public static class WorkingTimePeriodComparator implements Comparator<WorkingTimePeriod> {
		@Override
		public int compare(WorkingTimePeriod o1, WorkingTimePeriod o2) {

			if (o1.getStartTime().getStartTime().isAfter(o2.getStartTime().getStartTime())) {
				return 1;
			} else if (o1.getStartTime().getStartTime().isBefore(o2.getStartTime().getStartTime())) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}
