package es.ozona.kayros.webapp.infraestructure.feingclients;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

@Service
public class TimesheetServiceFallback implements TimesheetService {

	public TimesheetResource clock(@RequestParam String username) {
		return new TimesheetResource();
	}

	@Override
	public PageResult<TimesheetResource> search(String query, String sort, int page, int size) {

		return new PageResult<TimesheetResource>();
	}

}
