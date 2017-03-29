package com.test.ssh4.controller;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.test.ssh4.model.User;
import com.test.ssh4.service.UserService;

@Named
@Scope("session")
public class MainBean {

	@Inject
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void saveUser() {
		// ApplicationContext ac = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		// UserService userService = (UserService) ac.getBean("userService");
		User user = new User();
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setName("zhangÈý");
		user.setPwd("123456");
		user.setCreatedatetime(new Date());
		userService.save(user);
	}

	
}