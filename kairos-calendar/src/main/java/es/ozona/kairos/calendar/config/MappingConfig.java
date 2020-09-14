package es.ozona.kairos.calendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.aggregates.Shiftplan;
import es.ozona.kairos.calendar.domain.model.commands.AddCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateCalendarCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftplanCommand;
import es.ozona.kairos.calendar.domain.model.commands.ModifyShiftplanPeriodCommand;
import es.ozona.kairos.calendar.interfaces.rest.dto.AddCalendarHolidayResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.AddWorkdayResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.CalendarResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.CreateCalendarResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.CreateShiftplanResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ModifyShiftplanPeriodResource;
import es.ozona.kairos.calendar.interfaces.rest.dto.ShiftplanResource;
import es.ozona.micro.core.interfaces.rest.MCModelMapper;

@Configuration
public class MappingConfig {

	@Bean
	@Scope("singleton")
	public MCModelMapper getModelMapper() {
		final MCModelMapper modelMapper = new MCModelMapper();

		// Calendars
		modelMapper.createTypeMap(CreateCalendarCommand.class, CreateCalendarResource.class);

		// Holidays
		modelMapper.createTypeMap(AddCalendarHolidayCommand.class, AddCalendarHolidayResource.class);

		modelMapper.createTypeMap(AddWorkdayCommand.class, AddWorkdayResource.class);
		modelMapper.createTypeMap(Calendar.class, CalendarResource.class);

		modelMapper.createTypeMap(CreateShiftplanCommand.class, CreateShiftplanResource.class);
		modelMapper.createTypeMap(Shiftplan.class, ShiftplanResource.class).addMapping((src) -> src.getPeriod().getStartDate(), ShiftplanResource::setStartDate)
				.addMapping((src) -> src.getPeriod().getEndDate(), ShiftplanResource::setEndDate);

		modelMapper.createTypeMap(ModifyShiftplanPeriodCommand.class, ModifyShiftplanPeriodResource.class);

		// Domain Object -> User Resource
		return modelMapper;
	}

}
