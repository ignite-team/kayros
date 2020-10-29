package es.ozona.kairos.calendar.domain.model.commands;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;

public class DeleteWorkdayCommand {
	private DayOfWeek day;
	private String shiftPlanId;

	public DeleteWorkdayCommand() {

	}

	public DeleteWorkdayCommand(String shiftPlanId, DayOfWeek day) {
		super();
		this.day = day;
		this.shiftPlanId = shiftPlanId;
	}	
	
	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public String getShiftPlanId() {
		return shiftPlanId;
	}

	public void setShiftPlanId(String shiftPlanId) {
		this.shiftPlanId = shiftPlanId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftPlanId, day });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof DeleteCalendarHolidayCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
