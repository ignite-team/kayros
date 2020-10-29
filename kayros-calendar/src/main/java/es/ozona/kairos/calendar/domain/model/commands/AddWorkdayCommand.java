
package es.ozona.kairos.calendar.domain.model.commands;

import java.time.LocalTime;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;

public class AddWorkdayCommand {
	private String shiftPlanId;
	private DayOfWeek day;
	private LocalTime worktimeEntry;
	private LocalTime worktimeExit;
	private LocalTime breakTimeStart;
	private LocalTime breakTimeEnd;
	private LocalTime restTime;

	public AddWorkdayCommand() {

	}

	public AddWorkdayCommand(String shiftPlanId, DayOfWeek day, LocalTime worktimeEntry, LocalTime worktimeExit, LocalTime breakTimeStart,
			LocalTime breakTimeEnd, LocalTime restTime) {
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

	public LocalTime getWorktimeEntry() {
		return worktimeEntry;
	}

	public void setWorktimeEntry(LocalTime worktimeEntry) {
		this.worktimeEntry = worktimeEntry;
	}

	public LocalTime getWorktimeExit() {
		return worktimeExit;
	}

	public void setWorktimeExit(LocalTime worktimeExit) {
		this.worktimeExit = worktimeExit;
	}

	public LocalTime getBreakTimeStart() {
		return breakTimeStart;
	}

	public void setBreakTimeStart(LocalTime breakTimeStart) {
		this.breakTimeStart = breakTimeStart;
	}

	public LocalTime getBreakTimeEnd() {
		return breakTimeEnd;
	}

	public void setBreakTimeEnd(LocalTime breakTimeEnd) {
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

		if (obj == null || !(obj instanceof AddWorkdayCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
