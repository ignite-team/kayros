package es.ozona.kayros.webapp.internal.outboundservice.acl;

import es.ozona.kayros.webapp.domain.model.Workday;
import es.ozona.kayros.webapp.shareddomain.model.WorkdayResource;

public class WorkdayMapper {

	private WorkdayMapper() {

	}

	public static Workday mapFromResource(WorkdayResource resource) {

		final Workday workday = new Workday(resource.getDay(), resource.getWorkTimeEntry(), resource.getWorkTimeExit(), resource.getBreakTimeStart(),
				resource.getBreakTimeEnd(), resource.getRestTime());

		return workday;

	}

	public static WorkdayResource mapToResource(Workday resource) {

		final WorkdayResource workday = new WorkdayResource(resource.getDay(), resource.getWorkTimeEntry(), resource.getWorkTimeExit(),
				resource.getBreakTimeStart(), resource.getBreakTimeEnd(), resource.getRestTime());

		return workday;

	}

}
