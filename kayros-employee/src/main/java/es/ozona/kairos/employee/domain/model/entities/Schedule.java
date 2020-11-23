package es.ozona.kairos.employee.domain.model.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.employee.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.employee.domain.model.valueobjects.ScheduleId;
import es.ozona.kairos.employee.domain.model.valueobjects.Validity;

@Entity
@Table(name = "schedules", indexes = { @Index(name = "schedule_validity_start_date_idx", columnList = "start_date"),
		@Index(name = "schedule_calendar_id_idx", columnList = "calendar_id"), @Index(name = "schedule_employee_id_idx", columnList = "employee_id"), })
public class Schedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private ScheduleId scheduleId;

	@Embedded
	private CalendarId calendarId;

	@Embedded
	private Validity validity;

	public Schedule() {

	}

	public Schedule(ScheduleId scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Schedule(ScheduleId scheduleId, CalendarId calendarId, Validity validity) {
		super();
		this.scheduleId = scheduleId;
		this.calendarId = calendarId;
		this.validity = validity;
	}

	public ScheduleId getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(ScheduleId scheduleId) {
		this.scheduleId = scheduleId;
	}

	public CalendarId getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(CalendarId calendarId) {
		this.calendarId = calendarId;
	}

	public Validity getValidity() {
		return validity;
	}

	public void setValidity(Validity validity) {
		this.validity = validity;
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(new Object[] { scheduleId /* , calendarId, validity */ });
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Schedule)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
