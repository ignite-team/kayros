package es.ozona.kayros.webapp.internal.outboundservice.acl;

import java.util.Date;

import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.shareddomain.model.WorkingTimePeriodResource;

public class WorkTimePeriodMapper {

	private WorkTimePeriodMapper() {

	}

	public static WorkingTimePeriod mapFromResource(WorkingTimePeriodResource resource) {

		final WorkingTimePeriod wtp = new WorkingTimePeriod();

		wtp.setStartTime(resource.getStartTime() == null ? null : Date.from(resource.getStartTime().toInstant()));
		wtp.setGeneratedStartTime(resource.getGeneratedStartTime());
		wtp.setEditedStartTime(resource.getEditedStartTime());
		wtp.setFinishTime(resource.getFinishTime() == null ? null : Date.from(resource.getFinishTime().toInstant()));
		wtp.setGeneratedFinishTime(resource.getGeneratedFinishTime());
		wtp.setEditedFinishTime(resource.getEditedFinishTime());
		wtp.setTelecommuting(resource.getTelecommuting());
		wtp.setWorkplace(resource.getWorkplace());

		return wtp;

	}

}
