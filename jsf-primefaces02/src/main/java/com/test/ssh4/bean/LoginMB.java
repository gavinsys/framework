package com.test.ssh4.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.ssh4.model.User;
import com.test.ssh4.service.UserService;

@Named
@SessionScoped
public class LoginMB{
	
	@Inject
	private UserService userService;

	public String login() {
		
		User user = userService.find(username);
		if(this.password.equals(user.getPwd())){
			return "index";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong Password!"));
			return "login";
		}
	}
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
	
}