package com.test.spring.dao;

import com.test.spring.base.IBaseDao;
import com.test.spring.entity.User;

public interface UserDao extends IBaseDao<User> {
	
	public User findUserByUserName(String username);
	
}
