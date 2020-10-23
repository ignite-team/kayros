package es.ozona.kayros.webapp.vms;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

public class ErrorViewModel {

	protected String code;
	protected String message;

	@Init
	public void init() {

		code = null;
		message = null;

		if (Executions.getCurrent() != null) {

			if (Executions.getCurrent().getAttribute("javax.servlet.error.status_code") != null) {

				code = Executions.getCurrent().getAttribute("javax.servlet.error.status_code").toString();

			}

			if (Executions.getCurrent().getAttribute("error") != null) {

				message = (String) Executions.getCurrent().getAttribute("error");

			}

		}

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
