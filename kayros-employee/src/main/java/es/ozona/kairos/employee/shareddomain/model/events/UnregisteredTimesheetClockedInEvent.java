package es.ozona.kairos.employee.shareddomain.model.events;

public class UnregisteredTimesheetClockedInEvent {

	private UnregisteredTimesheetClockedInEventData unregisteredTimesheetClockInEventData;
	
	public UnregisteredTimesheetClockedInEvent() {

	}

	public UnregisteredTimesheetClockedInEvent(UnregisteredTimesheetClockedInEventData unregisteredTimesheetClockInEventData) {
		this.unregisteredTimesheetClockInEventData = unregisteredTimesheetClockInEventData;
	}

	public UnregisteredTimesheetClockedInEventData getUnregisteredTimesheetClockInEventData() {
		return unregisteredTimesheetClockInEventData;
	}

	public void setUnregisteredTimesheetClockInEventData(UnregisteredTimesheetClockedInEventData unregisteredTimesheetClockInEventData) {
		this.unregisteredTimesheetClockInEventData = unregisteredTimesheetClockInEventData;
	}

}
