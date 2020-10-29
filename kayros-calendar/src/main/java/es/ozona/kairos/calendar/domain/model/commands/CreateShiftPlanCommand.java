package es.ozona.kairos.calendar.domain.model.commands;

import java.time.LocalDate;

import org.springframework.util.ObjectUtils;

public class CreateShiftPlanCommand {
	private String shiftPlanId;
	private String calendarId;
	private LocalDate startDate;
	private LocalDate endDate;

	public CreateShiftPlanCommand() {

	}

	public CreateShiftPlanCommand(String shiftPlanId, String calendarId, LocalDate startDate, LocalDate endDate) {
		this.shiftPlanId = shiftPlanId;
		this.calendarId = calendarId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getShiftPlanId() {
		return shiftPlanId;
	}

	public void setShiftPlanId(String shiftPlanId) {
		this.shiftPlanId = shiftPlanId;
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

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftPlanId, calendarId, startDate, endDate });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CreateShiftPlanCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
