package es.ozona.kairos.calendar.interfaces.rest.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import io.swagger.annotations.ApiModelProperty;

public class ModifyWorkdayResource {

	@ApiModelProperty(value = "Entry time to work", required = true, example = "08:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	@NotNull
	private LocalTime worktimeEntry;

	@ApiModelProperty(value = "Time to leave work", required = true, example = "17:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	@NotNull
	private LocalTime worktimeExit;

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

	public ModifyWorkdayResource() {

	}

	public ModifyWorkdayResource(LocalTime worktimeEntry, LocalTime worktimeExit, LocalTime breakTimeStart, LocalTime breakTimeEnd, LocalTime restTime) {
		this.worktimeEntry = worktimeEntry;
		this.worktimeExit = worktimeExit;
		this.breakTimeStart = breakTimeStart;
		this.breakTimeEnd = breakTimeEnd;
		this.restTime = restTime;
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

		return ObjectUtils.nullSafeHashCode(new Object[] { worktimeEntry, worktimeExit, breakTimeStart, breakTimeEnd, restTime });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof AddWorkdayResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
