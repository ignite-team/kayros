package es.ozona.kairos.shareddomain.model.events;

import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;

public class ShiftPlanWorkdayAddedEventData {
	private String shiftPlanId;
	private DayOfWeek day;
	private ZonedDateTime worktimeEntry;
	private ZonedDateTime worktimeExit;
	private ZonedDateTime breakTimeStart;
	private ZonedDateTime breakTimeEnd;
	private LocalTime restTime;

	public ShiftPlanWorkdayAddedEventData() {

	}

	public ShiftPlanWorkdayAddedEventData(String shiftPlanId, DayOfWeek day, ZonedDateTime worktimeEntry, ZonedDateTime worktimeExit,
			ZonedDateTime breakTimeStart, ZonedDateTime breakTimeEnd, LocalTime restTime) {
		super();
		this.shiftPlanId = shiftPlanId;
		this.day = day;
		this.worktimeEntry = worktimeEntry;
		this.worktimeExit = worktimeExit;
		this.breakTimeStart = breakTimeStart;
		this.breakTimeEnd = breakTimeEnd;
		this.restTime = restTime;
	}

	public String getShiftPlanId() {
		return shiftPlanId;
	}

	public void setShiftPlanId(String shiftPlanId) {
		this.shiftPlanId = shiftPlanId;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public ZonedDateTime getWorktimeEntry() {
		return worktimeEntry;
	}

	public void setWorktimeEntry(ZonedDateTime worktimeEntry) {
		this.worktimeEntry = worktimeEntry;
	}

	public ZonedDateTime getWorktimeExit() {
		return worktimeExit;
	}

	public void setWorktimeExit(ZonedDateTime worktimeExit) {
		this.worktimeExit = worktimeExit;
	}

	public ZonedDateTime getBreakTimeStart() {
		return breakTimeStart;
	}

	public void setBreakTimeStart(ZonedDateTime breakTimeStart) {
		this.breakTimeStart = breakTimeStart;
	}

	public ZonedDateTime getBreakTimeEnd() {
		return breakTimeEnd;
	}

	public void setBreakTimeEnd(ZonedDateTime breakTimeEnd) {
		this.breakTimeEnd = breakTimeEnd;
	}

	public LocalTime getRestTime() {
		return restTime;
	}

	public void setRestTime(LocalTime restTime) {
		this.restTime = restTime;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftPlanId, day, worktimeEntry, worktimeExit, breakTimeStart, breakTimeEnd, restTime });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanWorkdayAddedEventData)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
