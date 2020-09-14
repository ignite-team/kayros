package es.ozona.kairos.calendar.interfaces.rest.dto;

import java.time.LocalDate;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import es.ozona.kairos.calendar.domain.model.commands.CreateShiftplanCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A shiftplan resource")
public class ShiftplanResource {
	@ApiModelProperty(value = "The shiftplan ID, must be unique", required = false, allowEmptyValue = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD")
	private String shiftplanId;

	@ApiModelProperty(value = "The calendar ID of shifplan onwer", required = true, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String calendarId;

	@ApiModelProperty(value = "The start date", required = true, example = "2020/01/01")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate startDate;

	@ApiModelProperty(value = "The end date", required = false, example = "2020/12/31")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate endDate;

	public ShiftplanResource() {

	}

	public ShiftplanResource(String shiftplanId, String calendarId, LocalDate startDate, LocalDate endDate) {
		this.shiftplanId = shiftplanId;
		this.calendarId = calendarId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getShiftplanId() {
		return shiftplanId;
	}

	public void setShiftplanId(String shiftplanId) {
		this.shiftplanId = shiftplanId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftplanId, calendarId, startDate, endDate });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CreateShiftplanCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
