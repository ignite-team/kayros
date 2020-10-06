package es.ozona.kayros.webapp.infrastructure.feingclients;

import org.springframework.stereotype.Service;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

@Service
public class TimesheetServiceFallback implements TimesheetService {

	@Override
	public TimesheetResource clock(String employeeId, String username) {
		return new TimesheetResource();
	}

	@Override
	public PageResult<TimesheetResource> search(String query, String sort, int page, int size) {

		return new PageResult<TimesheetResource>();
	}

}
