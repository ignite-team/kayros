package es.ozona.kayros.webapp.infrastructure.feingclients;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.shareddomain.model.EmployeeResource;
import es.ozona.kayros.webapp.shareddomain.model.ScheduleResource;

@Service
public class EmployeeServiceFallback implements EmployeeService {

	@Override
	public EmployeeResource find(String id) {
		return null;
	}

	@Override
	public PageResult<EmployeeResource> search(String query, String sort, int page, int size) {
		// TODO Auto-generated method stub
		return new PageResult<EmployeeResource>();
	}

	@Override
	public EmployeeResource create(EmployeeResource employeeResource) {
		// Si no se ha podido crear el empleado devolvemos el empleados con los datos originales
		return employeeResource;
	}

	@Override
	public EmployeeResource modify(EmployeeResource modifyEmployeeCommandResource, String id) {
		// TODO Auto-generated method stub
		return modifyEmployeeCommandResource;
	}

	@Override
	public List<ScheduleResource> searchSchedules(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
