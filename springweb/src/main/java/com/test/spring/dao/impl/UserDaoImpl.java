package com.test.spring.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.spring.base.BaseDaoImpl;
import com.test.spring.dao.UserDao;
import com.test.spring.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User findUserByUserName(String username) {
		String hql = "SELECT user FROM "+getGenericClassName()+" user WHERE user.username=?";
		List<User> list = findEntityListByHql(hql, username);
		return list.size()>0?list.get(0):null;
	}
	
}
