package es.ozona.kayros.webapp.infrastructure.feingclients;

import java.util.List;

import es.ozona.data.inquire.model.paging.PageResult;
import es.ozona.kayros.webapp.shareddomain.model.CalendarResource;
import es.ozona.kayros.webapp.shareddomain.model.HolidayResource;
import es.ozona.kayros.webapp.shareddomain.model.ShiftPlanResource;
import es.ozona.kayros.webapp.shareddomain.model.WorkdayResource;

public class CalendarServiceFallback implements CalendarService {

	@Override
	public CalendarResource findCalendarById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HolidayResource> findAllHolidaysByCalendarId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<CalendarResource> searchCalendars(String query, String sort, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShiftPlanResource findShiftPlanById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkdayResource> findAllWorkdaysByShiftplanId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<ShiftPlanResource> searchShiftPlans(String query, String sort, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
