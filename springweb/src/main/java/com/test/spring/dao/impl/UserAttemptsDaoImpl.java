package com.test.spring.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.spring.base.BaseDaoImpl;
import com.test.spring.dao.UserAttemptsDao;
import com.test.spring.entity.UserAttempts;

@Repository("userAttemptsDao")
public class UserAttemptsDaoImpl extends BaseDaoImpl<UserAttempts> implements UserAttemptsDao {

	public UserAttempts findUserAttemptsByUserName(String username) {
		String hql = "SELECT userAttempts FROM "+getGenericClassName()+" userAttempts WHERE userAttempts.username=?";
		List<UserAttempts> list = findEntityListByHql(hql, username);
		return list.size()>0?list.get(0):null;
	}
	
}
