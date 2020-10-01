package es.ozona.kayros.webapp.outboundservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ozona.kayros.webapp.shareddomain.model.Timesheet;

@Service("externalTimesheetService")
public class ExternalTimesheetServiceImpl implements ExternalTimesheetService {

	@Autowired
	private TimesheetService timesheetService;

	@Override
	public Timesheet clock(String username) {

		Timesheet timesheet = timesheetService.clock(username);

		return timesheet;

	}

	/**
	 * Anti-corruption layer conversion
	 * 
	 * @param calendar
	 * @return Calendar to Schedule conversion
	 */
//	private CalendarId toCalendarId(Calendar calendar) {
//
//		return new CalendarId(calendar.getCalendarId());
//	}

}
