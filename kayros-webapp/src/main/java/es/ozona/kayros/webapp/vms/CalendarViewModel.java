package es.ozona.kayros.webapp.vms;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.util.Clients;

public class CalendarViewModel {

	@Init
	public void init() {

	}

	@AfterCompose
	public void afterCompose() {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Clients.evalJavaScript("initCalendar(" + format.format(new Date()) + ")");

	}

}
