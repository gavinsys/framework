package com.test.spring.service;

import java.util.List;

import com.test.spring.base.IBaseService;
import com.test.spring.common.Page;
import com.test.spring.entity.User;
import com.test.spring.model.UserModel;

public interface UserService extends IBaseService<User>{
	
	public User findUserByUserName(String username);
	
	public List<User> searchUser(UserModel userModel, Page page);
}
