package es.ozona.kayros.webapp.entities;

import java.time.LocalTime;

public class SplittedWorkDay extends ContinuousWorkDay {
	protected LocalTime startBreakTime;
	protected LocalTime endBreakTime;
	
	public LocalTime getStartBreakTime() {
		return startBreakTime;
	}
	public void setStartBreakTime(LocalTime localTime) {
		this.startBreakTime = localTime;
	}
	public LocalTime getEndBreakTime() {
		return endBreakTime;
	}
	public void setEndBreakTime(LocalTime endBreakTime) {
		this.endBreakTime = endBreakTime;
	}	
}
