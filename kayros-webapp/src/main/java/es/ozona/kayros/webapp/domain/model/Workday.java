package es.ozona.kayros.webapp.domain.model;

import org.springframework.util.ObjectUtils;

public class Workday {

	private int day;
	private String worktimeEntry;
	private String worktimeExit;
	private String breaktimeStart;
	private String breaktimeEnd;
	private String restTime;

	public Workday() {

	}

	public Workday(int day, String worktimeEntry, String worktimeExit, String breaktimeStart, String breaktimeEnd, String restTime) {

		super();
		this.day = day;
		this.worktimeEntry = worktimeEntry;
		this.worktimeExit = worktimeExit;
		this.breaktimeStart = breaktimeStart;
		this.breaktimeEnd = breaktimeEnd;
		this.restTime = restTime;

	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getWorkTimeEntry() {
		return worktimeEntry;
	}

	public void setWorkTimeEntry(String workTimeEntry) {
		this.worktimeEntry = workTimeEntry;
	}

	public String getWorkTimeExit() {
		return worktimeExit;
	}

	public void setWorkTimeExit(String workTimeExit) {
		this.worktimeExit = workTimeExit;
	}

	public String getBreakTimeStart() {
		return breaktimeStart;
	}

	public void setBreakTimeStart(String breakTimeStart) {
		this.breaktimeStart = breakTimeStart;
	}

	public String getBreakTimeEnd() {
		return breaktimeEnd;
	}

	public void setBreakTimeEnd(String breakTimeEnd) {
		this.breaktimeEnd = breakTimeEnd;
	}

	public String getRestTime() {
		return restTime;
	}

	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { day, worktimeEntry, worktimeExit, breaktimeStart, breaktimeEnd, restTime });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Workday)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}
}
