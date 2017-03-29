package com.test.spring.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.spring.base.BaseServiceImpl;
import com.test.spring.entity.Role;
import com.test.spring.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Override
	public List<Role> getAll() {
		return roleDao.findEntityListByEntityName(Role.class.getName());
	}

	@Override
	public Role get(String id) {
		return roleDao.get(Role.class, id);
	}

	@Override
	public void insert(Role t) {
		roleDao.insert(t);
	}

	@Override
	public void insert(Collection<?> entities) {
		roleDao.insert(entities);
	}

	@Override
	public void delete(String id) {
		roleDao.delete(roleDao.load(Role.class, id));
	}

	@Override
	public void delete(Collection<?> entities) {
		roleDao.delete(entities);
	}

	@Override
	public void update(Role t) {
		roleDao.update(t);
	}

	@Override
	public void update(Collection<?> entities) {
		roleDao.update(entities);
	}

}
