package es.ozona.kairos.calendar.domain.model.commands;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;

public class DeleteWorkdayCommand {
	private DayOfWeek day;
	private String shiftplanId;

	public DeleteWorkdayCommand() {

	}

	public DeleteWorkdayCommand(String shiftplanId, DayOfWeek day) {
		super();
		this.day = day;
		this.shiftplanId = shiftplanId;
	}	
	
	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public String getShiftplanId() {
		return shiftplanId;
	}

	public void setShiftplanId(String shiftplanId) {
		this.shiftplanId = shiftplanId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftplanId, day });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof DeleteCalendarHolidayCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
