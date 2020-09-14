package es.ozona.kairos.calendar.interfaces.rest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class AddCalendarHolidayResource {

	@ApiModelProperty(value = "holiday date", required = true, example = "01/01/2020")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	private LocalDate holiday;

	public AddCalendarHolidayResource() {

	}

	public AddCalendarHolidayResource(LocalDate holiday) {
		this.holiday = holiday;
	}

	public LocalDate getHoliday() {
		return holiday;
	}

	public void setHoliday(LocalDate holiday) {
		this.holiday = holiday;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(holiday);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof AddCalendarHolidayResource)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
