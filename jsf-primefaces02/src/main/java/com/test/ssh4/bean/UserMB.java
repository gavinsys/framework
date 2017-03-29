package com.test.ssh4.bean;

import java.util.Date;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.ssh4.model.User;
import com.test.ssh4.service.UserService;

@Named
@SessionScoped
public class UserMB{
	
	@Inject
	private UserService userService;

	public void saveUser() {
		// ApplicationContext ac = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		// UserService userService = (UserService) ac.getBean("userService");
		User user = new User();
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setName(this.username);
		user.setPwd(this.password);
		user.setCreatedatetime(new Date());
		userService.save(user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You've registered"));
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