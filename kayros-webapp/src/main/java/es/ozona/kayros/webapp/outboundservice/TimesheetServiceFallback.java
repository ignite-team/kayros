package es.ozona.kayros.webapp.outboundservice;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import es.ozona.kayros.webapp.shareddomain.model.Timesheet;

@Service
public class TimesheetServiceFallback implements TimesheetService {

	public Timesheet clock(@RequestParam String username) {
		return new Timesheet();
	}

}
