package es.ozona.kairos.employee.shareddomain.model.events;

public class UnregisteredTimesheetClockedInEvent {

	private UnregisteredTimesheetClockedInEventData UnregisteredTimesheetClockInEventData;
	
	public UnregisteredTimesheetClockedInEvent() {
		
	}

	public UnregisteredTimesheetClockedInEvent(UnregisteredTimesheetClockedInEventData UnregisteredTimesheetClockInEventData) {
		this.UnregisteredTimesheetClockInEventData = UnregisteredTimesheetClockInEventData;
	}

	public UnregisteredTimesheetClockedInEventData getUnregisteredTimesheetClockInEventData() {
		return UnregisteredTimesheetClockInEventData;
	}

	public void setUnregisteredTimesheetClockInEventData(UnregisteredTimesheetClockedInEventData UnregisteredTimesheetClockInEventData) {
		this.UnregisteredTimesheetClockInEventData = UnregisteredTimesheetClockInEventData;
	}

}
