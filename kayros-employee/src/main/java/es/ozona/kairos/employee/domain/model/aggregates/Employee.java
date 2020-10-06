package es.ozona.kairos.employee.domain.model.aggregates;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.util.ObjectUtils;

import com.sun.istack.NotNull;

import es.ozona.kairos.employee.domain.model.commands.AssignScheduleCommand;
import es.ozona.kairos.employee.domain.model.commands.CreateEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.ModifyEmployeeCommand;
import es.ozona.kairos.employee.domain.model.commands.UnassignScheduleCommand;
import es.ozona.kairos.employee.domain.model.entities.Schedule;
import es.ozona.kairos.employee.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.employee.domain.model.valueobjects.EmployeeId;
import es.ozona.kairos.employee.domain.model.valueobjects.ScheduleId;
import es.ozona.kairos.employee.domain.model.valueobjects.UserAccount;
import es.ozona.kairos.employee.domain.model.valueobjects.Validity;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeCreatedEvent;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeCreatedEventData;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeModifiedEvent;
import es.ozona.kairos.employee.shareddomain.model.events.EmployeeModifiedEventData;

@Entity
@Table(name = "employees", indexes = { @Index(name = "employee_user_name_idx", columnList = "username") })
public class Employee extends AbstractAggregateRoot<Employee> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private EmployeeId employeeId;

	@Embedded
	private UserAccount account;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "employee_id")
	private Set<Schedule> schedules = new HashSet<Schedule>(0);

	@NotNull
	private Boolean telecommuting;

	public Employee() {

	}

	public Employee(CreateEmployeeCommand createEmployeeCommand) {

		super();

		this.employeeId = new EmployeeId(createEmployeeCommand.getEmployeeId());
		this.account = new UserAccount(createEmployeeCommand.getUsername(), createEmployeeCommand.getEmail(), createEmployeeCommand.getFirstname(), createEmployeeCommand.getLastname());

		this.telecommuting = createEmployeeCommand.getTelecommuting();

		this.registerEvent(new EmployeeCreatedEvent(new EmployeeCreatedEventData(createEmployeeCommand.getEmployeeId(), createEmployeeCommand.getUsername(), createEmployeeCommand.getEmail(), createEmployeeCommand.getFirstname(), createEmployeeCommand.getLastname(), createEmployeeCommand.getTelecommuting())));

	}

	public void modify(ModifyEmployeeCommand modifyEmployeeCommand) {

		this.account = new UserAccount(modifyEmployeeCommand.getUsername(), modifyEmployeeCommand.getEmail(), modifyEmployeeCommand.getFirstname(), modifyEmployeeCommand.getLastname());

		this.telecommuting = modifyEmployeeCommand.getTelecommuting();

		this.registerEvent(new EmployeeModifiedEvent(new EmployeeModifiedEventData(modifyEmployeeCommand.getEmployeeId(), modifyEmployeeCommand.getUsername(), modifyEmployeeCommand.getEmail(), modifyEmployeeCommand.getFirstname(), modifyEmployeeCommand.getLastname(), modifyEmployeeCommand.getTelecommuting())));

	}

	public Schedule assignSchedule(AssignScheduleCommand assignScheduleCommand) {

		final Schedule schedule = new Schedule(new ScheduleId(assignScheduleCommand.getScheduleId()), new CalendarId(assignScheduleCommand.getCalendarId()), new Validity(assignScheduleCommand.getStartDate(), assignScheduleCommand.getEndDate()));

		this.schedules.add(schedule);

		return schedule;

	}

	public void unassignSchedule(UnassignScheduleCommand unassignScheduleCommand) {

		// TODO: cuando el numero de calendarios programados sea muy alto, el rendimiento puede bajar.
		// valorar cambir el modelo, para mejorar el rendimiento.
		this.schedules.remove(new Schedule(new ScheduleId(unassignScheduleCommand.getEmployeeId())));

	}

	public EmployeeId getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(EmployeeId employeeId) {
		this.employeeId = employeeId;
	}

	public UserAccount getAccount() {
		return account;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

	public List<Schedule> getSchedules() {
		return Collections.unmodifiableList(schedules.stream().sorted(new ScheduleComparator()).collect(Collectors.toList()));
	}
	
	public Boolean getTelecommuting() {

		return telecommuting;
	
	}

	public void setTelecommuting(Boolean telecommuting) {

		this.telecommuting = telecommuting;

	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(employeeId);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		return ObjectUtils.nullSafeEquals(this.employeeId, ((Employee) obj).getEmployeeId());
	}

	public static class ScheduleComparator implements Comparator<Schedule> {
		@Override
		public int compare(Schedule o1, Schedule o2) {

			if (o1.getValidity().getStartDate().isAfter(o2.getValidity().getStartDate())) {
				return 1;
			} else if (o1.getValidity().getStartDate().isBefore(o2.getValidity().getStartDate())) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}
