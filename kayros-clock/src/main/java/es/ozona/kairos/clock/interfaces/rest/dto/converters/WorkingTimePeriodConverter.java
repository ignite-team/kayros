package es.ozona.kairos.clock.interfaces.rest.dto.converters;

import org.modelmapper.AbstractConverter;

import es.ozona.kairos.clock.domain.model.entities.WorkingTimePeriod;
import es.ozona.kairos.clock.interfaces.rest.dto.WorkingTimePeriodResource;

public class WorkingTimePeriodConverter extends AbstractConverter<WorkingTimePeriod, WorkingTimePeriodResource> {

	@Override
	protected WorkingTimePeriodResource convert(WorkingTimePeriod source) {

		final WorkingTimePeriodResource wtp = new WorkingTimePeriodResource();

		wtp.setStartTime(source.getStartTime().getStartTime());
		wtp.setGeneratedStartTime(source.getStartTime().isGenerated());
		wtp.setEditedStartTime(source.getStartTime().isEdited());
		wtp.setTelecommuting(source.getTelecommuting());
		wtp.setWorkplace(source.getWorkplace());

		if (source.getFinishTime().isPresent()) {
			
			wtp.setFinishTime(source.getFinishTime().get().getFinishTime());
			wtp.setGeneratedFinishTime(source.getFinishTime().get().isGenerated());
			wtp.setEditedFinishTime(source.getFinishTime().get().isEdited());
			
		}

		return wtp;

	}

}
