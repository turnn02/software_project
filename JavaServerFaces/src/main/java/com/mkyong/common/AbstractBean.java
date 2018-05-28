package com.mkyong.common;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

public abstract class AbstractBean {

	@ManagedProperty("#{sessionDataBean}")
	protected SessionDataBean sessionDataBean;

	public void setSessionDataBean(SessionDataBean sessionDataBean) {
		this.sessionDataBean = sessionDataBean;
	}

	public void sendWarnMessage(String summary, String detail) {
		sendMessage(summary, detail, FacesMessage.SEVERITY_WARN);
	}
	
	public void sendInfoMessage(String summary, String detail) {
		sendMessage(summary, detail, FacesMessage.SEVERITY_INFO);
	}
	
	private void sendMessage(String summary, String detail, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
