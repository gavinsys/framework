package com.test.ssh4.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.ssh4.dao.UserDao;
import com.test.ssh4.model.User;
import com.test.ssh4.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public Serializable save(User user) {
		return userDao.save(user);
	}
}
