package es.ozona.kayros.webapp.internal.outboundservice.acl;

import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.shareddomain.model.WorkingTimePeriodResource;

public class WorkTimePeriodMapper {
	private WorkTimePeriodMapper() {

	}

	public static WorkingTimePeriod map(WorkingTimePeriodResource resource) {
		final WorkingTimePeriod wtp = new WorkingTimePeriod();
		wtp.setStartTime(resource.getStartTime());
		wtp.setGeneratedStartTime(resource.getGeneratedStartTime());
		wtp.setEditedStartTime(resource.getEditedStartTime());
		wtp.setFinishTime(resource.getFinishTime());
		wtp.setGeneratedFinishTime(resource.getGeneratedFinishTime());
		wtp.setEditedFinishTime(resource.getEditedFinishTime());

		return wtp;

	}
}
