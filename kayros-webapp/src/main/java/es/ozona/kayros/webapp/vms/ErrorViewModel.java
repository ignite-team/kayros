package es.ozona.kayros.webapp.vms;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

public class ErrorViewModel {

	protected String code;
	protected String message;


	@Init
	public void init() {
		code = Executions.getCurrent().getAttribute("javax.servlet.error.status_code").toString();
		message = (String)Executions.getCurrent().getAttribute("error");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return code + " - " + message;
	}
	
}
