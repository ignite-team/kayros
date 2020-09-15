package es.ozona.ignite.views.layouts;

import java.time.Duration;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TimeCheck {
	
	@JsonIgnore
	private int day;
	private int hours;
	private LocalTime startTime;
	private LocalTime endTime;
	private Duration duration;
	
	public TimeCheck(int day, int hours, LocalTime startTime, LocalTime endTime, Duration diration) {
		super();
		this.day = day;
		this.hours = hours;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = diration;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
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
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration diration) {
		this.duration = diration;
	}


}
