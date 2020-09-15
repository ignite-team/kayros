package es.ozona.ignite.entities;

import java.time.LocalTime;

public class ContinuousWorkDay extends WorkDay {

	protected LocalTime startTime;
	protected LocalTime endTime;
	protected LocalTime restTime;

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalTime getRestTime() {
		return restTime;
	}

	public void setRestTime(LocalTime restTime) {
		this.restTime = restTime;
	}

}
