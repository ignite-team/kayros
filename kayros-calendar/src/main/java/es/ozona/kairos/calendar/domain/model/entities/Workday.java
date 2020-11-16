package es.ozona.kairos.calendar.domain.model.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.valueobjects.BreakTime;
import es.ozona.kairos.calendar.domain.model.valueobjects.Day;
import es.ozona.kairos.calendar.domain.model.valueobjects.RestTime;
import es.ozona.kairos.calendar.domain.model.valueobjects.WorkTime;

@Entity
@Table(name = "shiftplan_workdays")
public class Workday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Day day;

	@Embedded
	private WorkTime workTime;

	@Embedded
	private BreakTime breakTime;

	@Embedded
	private RestTime restTime;

	public Workday() {

	}

	public Workday(Day day, WorkTime workTime, BreakTime breakTime, RestTime restTime) {
		super();
		this.day = day;
		this.workTime = workTime;
		this.breakTime = breakTime;
		this.restTime = restTime;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public WorkTime getWorkTime() {
		return workTime;
	}

	public void setWorkTime(WorkTime workTime) {
		this.workTime = workTime;
	}

	public BreakTime getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(BreakTime breakTime) {
		this.breakTime = breakTime;
	}

	public RestTime getRestTime() {
		return restTime;
	}

	public void setRestTime(RestTime restTime) {
		this.restTime = restTime;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { id, day, workTime, breakTime, restTime });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Workday)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
