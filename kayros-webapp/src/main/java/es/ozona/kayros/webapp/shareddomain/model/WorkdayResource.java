package es.ozona.kayros.webapp.shareddomain.model;

import org.springframework.util.ObjectUtils;

public class WorkdayResource {

	private int day;
	private String workTimeEntry;
	private String workTimeExit;
	private String breakTimeStart;
	private String breakTimeEnd;
	private String restTime;

	public WorkdayResource() {

	}

	public WorkdayResource(int day, String workTimeEntry, String workTimeExit, String breakTimeStart, String breakTimeEnd, String restTime) {

		super();
		this.day = day;
		this.workTimeEntry = workTimeEntry;
		this.workTimeExit = workTimeExit;
		this.breakTimeStart = breakTimeStart;
		this.breakTimeEnd = breakTimeEnd;
		this.restTime = restTime;

	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getWorkTimeEntry() {
		return workTimeEntry;
	}

	public void setWorkTimeEntry(String workTimeEntry) {
		this.workTimeEntry = workTimeEntry;
	}

	public String getWorkTimeExit() {
		return workTimeExit;
	}

	public void setWorkTimeExit(String workTimeExit) {
		this.workTimeExit = workTimeExit;
	}

	public String getBreakTimeStart() {
		return breakTimeStart;
	}

	public void setBreakTimeStart(String breakTimeStart) {
		this.breakTimeStart = breakTimeStart;
	}

	public String getBreakTimeEnd() {
		return breakTimeEnd;
	}

	public void setBreakTimeEnd(String breakTimeEnd) {
		this.breakTimeEnd = breakTimeEnd;
	}

	public String getRestTime() {
		return restTime;
	}

	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { day, workTimeEntry, workTimeExit, breakTimeStart, breakTimeEnd, restTime });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof WorkdayResource)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
