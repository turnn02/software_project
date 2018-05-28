package com.mkyong.common;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.mkyong.persistence.DB;
import com.mkyong.persistence.User;
 
@ManagedBean
public class LoginBean {
	
	@ManagedProperty("#{sessionDataBean}")
	private SessionDataBean sessionDataBean;
	
	
    private String username;
     
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String logout() {
    	sessionDataBean.setLoggin(false);
    	sessionDataBean.setUser(null);
        return "login?faces-redirect=true";
    }
   
    public String login() {
        FacesMessage message = null;
         
        User user = DB.isSuccessfulLogin(username, password);
        
        if(user != null) {
        	sessionDataBean.setLoggin(true);
        	sessionDataBean.setUser(user);
            return "home?faces-redirect=true";
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        
    }

	public void setSessionDataBean(SessionDataBean sessionDataBean) {
		this.sessionDataBean = sessionDataBean;
	}
    
    
}