package es.ozona.kairos.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import es.ozona.kairos.employee.domain.model.aggregates.Employee;
import es.ozona.kairos.employee.domain.model.entities.Schedule;
import es.ozona.kairos.employee.interfaces.rest.dto.EmployeeResource;
import es.ozona.kairos.employee.interfaces.rest.dto.ScheduleResource;
import es.ozona.micro.core.interfaces.rest.MCModelMapper;

@Configuration
public class MappingConfig {

	@Bean
	@Scope("singleton")
	public MCModelMapper getModelMapper() {

		final MCModelMapper modelMapper = new MCModelMapper();

		modelMapper.createTypeMap(Employee.class, EmployeeResource.class)
			.addMapping(s -> s.getAccount().getUsername(), EmployeeResource::setUsername)
			.addMapping(s -> s.getAccount().getFirstname(), EmployeeResource::setFirstname)
			.addMapping(s -> s.getAccount().getLastname(), EmployeeResource::setLastname)
			.addMapping(s -> s.getAccount().getEmail(), EmployeeResource::setEmail)
			.addMapping(s -> s.getTelecommuting(), EmployeeResource::setTelecommuting)
			.addMapping(s -> s.getWorkplace(), EmployeeResource::setWorkplace);

		modelMapper.createTypeMap(Schedule.class, ScheduleResource.class)
			.addMapping(s -> s.getValidity().getStartDate(), ScheduleResource::setStartDate)
			.addMapping(s -> s.getValidity().getEndDate(), ScheduleResource::setEndDate);

		return modelMapper;

	}

}
