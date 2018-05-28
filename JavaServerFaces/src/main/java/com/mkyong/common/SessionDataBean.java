package com.mkyong.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mkyong.persistence.User;

@ManagedBean
@SessionScoped
public class SessionDataBean {

	private boolean isLoggin;
	
	private User user;

	public boolean isLoggin() {
		return isLoggin;
	}

	public void setLoggin(boolean isLoggin) {
		this.isLoggin = isLoggin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
