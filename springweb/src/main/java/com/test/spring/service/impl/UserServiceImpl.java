package com.test.spring.service.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.test.spring.base.BaseServiceImpl;
import com.test.spring.common.Page;
import com.test.spring.entity.User;
import com.test.spring.model.UserModel;
import com.test.spring.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	public User findUserByUserName(String username) {
		return userDao.findUserByUserName(username);
	}

	@Override
	public List<User> getAll() {
		return userDao.findEntityListByEntityName(User.class.getName());
	}

	@Override
	public User get(String id) {
		return userDao.get(User.class, id);
	}

	@Override
	public List<User> getList(User t,Page page) {
		StringBuffer sb = new StringBuffer("FROM  "+ User.class.getName()+" className");
		final LinkedList<Object> parameters = new LinkedList<Object>();
		sb.append(" WHERE 1=1 ");
		if(t!=null){
			if(!StringUtils.isEmpty(t.getUsername())){
				sb.append("AND className.username=?");
				parameters.add(t.getUsername());
			}
		}
		return userDao.findEntityListByHql(sb.toString(),page, parameters.toArray());
	}

	@Override
	public void insert(User t) {
		userDao.insert(t);
	}

	@Override
	public void insert(Collection<?> entities) {
		userDao.insert(entities);
	}
	
	@Override
	public void delete(String id) {
		User u = userDao.load(User.class, id);
		userDao.delete(u);
	}

	@Override
	public void delete(Collection<?> entities) {
		userDao.delete(entities);
	}
	
	@Override
	public void update(User t) {
		userDao.update(t);
	}
	
	@Override
	public void update(Collection<?> entities) {
		userDao.update(entities);
	}

	@Override
	public List<User> searchUser(UserModel userModel, Page page) {
		StringBuffer sb = new StringBuffer("FROM  "+ User.class.getName()+" className");
		final LinkedList<Object> parameters = new LinkedList<Object>();
		sb.append(" WHERE 1=1 ");
		if(userModel!=null){
			if(!StringUtils.isEmpty(userModel.getName())){
				sb.append("AND className.username=?");
				parameters.add(userModel.getName());
			}
		}
		return userDao.findEntityListByHql(sb.toString(),page, parameters.toArray());
	}
}
