
package es.ozona.kairos.calendar.interfaces.rest.dto;

import java.time.LocalTime;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A shiftplan resource")
public class WorkdayResource {

	@ApiModelProperty(value = "Day of the week from sunday to saturday using ordinal value", required = true, example = "1")
	private DayOfWeek day;

	@ApiModelProperty(value = "Entry time to work", required = true, example = "08:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)	
	private LocalTime workTimeEntry;

	@ApiModelProperty(value = "Time to leave work", required = true, example = "17:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)	
	private LocalTime workTimeExit;

	@ApiModelProperty(value = "Break time start", required = false, example = "14:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime breakTimeStart;

	@ApiModelProperty(value = "Break time end", required = false, example = "15:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime breakTimeEnd;

	@ApiModelProperty(value = "The time to rest", required = false, example = "00:30")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime restTime;

	public WorkdayResource() {

	}

	public WorkdayResource(String shiftplanId, DayOfWeek day, LocalTime workTimeEntry, LocalTime workTimeExit, LocalTime breakTimeStart, LocalTime breakTimeEnd,
			LocalTime restTime) {
		super();
		this.day = day;
		this.workTimeEntry = workTimeEntry;
		this.workTimeExit = workTimeExit;
		this.breakTimeStart = breakTimeStart;
		this.breakTimeEnd = breakTimeEnd;
		this.restTime = restTime;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public LocalTime getWorkTimeEntry() {
		return workTimeEntry;
	}

	public void setWorkTimeEntry(LocalTime worktimeEntry) {
		this.workTimeEntry = worktimeEntry;
	}

	public LocalTime getWorkTimeExit() {
		return workTimeExit;
	}

	public void setWorkTimeExit(LocalTime worktimeExit) {
		this.workTimeExit = worktimeExit;
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
