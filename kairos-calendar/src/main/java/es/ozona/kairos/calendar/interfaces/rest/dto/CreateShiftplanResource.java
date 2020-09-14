package es.ozona.kairos.calendar.interfaces.rest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A create shiftplan resource")
public class CreateShiftplanResource {
	@Length(min = 16, max = 36)
	@ApiModelProperty(value = "The shiftplan ID, must be unique", required = false, allowEmptyValue = true, example = "7CAC32FF-11E0-4B72-AF0F-5A674E1EDDBD")
	private String shiftplanId;

	@NotEmpty
	@Length(min = 16, max = 36)
	@ApiModelProperty(value = "The calendar ID of shifplan onwer", required = true, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String calendarId;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@ApiModelProperty(value = "The start date", required = true, example = "2020/01/01")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate startDate;

	@ApiModelProperty(value = "The end date", required = false, example = "2020/12/31")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate endDate;

	public CreateShiftplanResource() {

	}

	public CreateShiftplanResource(String shiftplanId, String calendarId, LocalDate startDate, LocalDate endDate) {
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

		if (obj == null || !(obj instanceof CreateShiftplanResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
