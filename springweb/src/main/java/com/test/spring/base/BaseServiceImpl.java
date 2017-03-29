package com.test.spring.base;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.spring.common.Page;
import com.test.spring.dao.ResourceDao;
import com.test.spring.dao.RoleDao;
import com.test.spring.dao.UserDao;

public abstract class BaseServiceImpl<T> implements IBaseService<T>{
	
	@Autowired
	protected UserDao userDao;
	@Autowired
	protected RoleDao roleDao;
	@Autowired
	protected ResourceDao resourceDao;
	
	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<T> getList(T t,Page page) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insert(T t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insert(Collection<?> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Collection<?> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Collection<?> entities) {
		// TODO Auto-generated method stub
		
	}
	
}
