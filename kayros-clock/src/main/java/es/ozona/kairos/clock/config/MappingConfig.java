package es.ozona.kairos.clock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import es.ozona.kairos.clock.domain.model.aggregates.Timesheet;
import es.ozona.kairos.clock.domain.model.entities.WorkingTimePeriod;
import es.ozona.kairos.clock.interfaces.rest.dto.TimesheetResource;
import es.ozona.kairos.clock.interfaces.rest.dto.WorkingTimePeriodResource;
import es.ozona.kairos.clock.interfaces.rest.dto.converters.TimesheetConverter;
import es.ozona.kairos.clock.interfaces.rest.dto.converters.WorkingTimePeriodConverter;
import es.ozona.micro.core.interfaces.rest.MCModelMapper;

@Configuration
public class MappingConfig {

	@Bean
	@Scope("singleton")
	public MCModelMapper getModelMapper() {
		final MCModelMapper modelMapper = new MCModelMapper();

		modelMapper.createTypeMap(Timesheet.class, TimesheetResource.class).setConverter(new TimesheetConverter());

		modelMapper.createTypeMap(WorkingTimePeriod.class, WorkingTimePeriodResource.class).setConverter(new WorkingTimePeriodConverter());

		return modelMapper;
	}

}
