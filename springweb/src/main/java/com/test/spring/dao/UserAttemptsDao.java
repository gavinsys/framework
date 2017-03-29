package com.test.spring.dao;

import com.test.spring.base.IBaseDao;
import com.test.spring.entity.UserAttempts;

public interface UserAttemptsDao extends IBaseDao<UserAttempts> {
	
	public UserAttempts findUserAttemptsByUserName(String username);
	
}
