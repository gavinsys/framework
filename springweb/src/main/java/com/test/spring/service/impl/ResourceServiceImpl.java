package com.test.spring.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.spring.base.BaseServiceImpl;
import com.test.spring.entity.Resource;
import com.test.spring.model.Menus;
import com.test.spring.model.Trees;
import com.test.spring.service.ResourceService;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

	@Override
	public List<Menus> findMenusByUser(String username, String grade, String parentId) {
		return resourceDao.findMenusByUser(username, grade, parentId);
	}
	
	@Override
	public List<Trees> findTreesByUser(String username, String grade, String parentId) {
		return resourceDao.findTreesByUser(username, grade, parentId);
	}
	
	@Override
	public List<Resource> getAll() {
		return resourceDao.findEntityListByEntityName(Resource.class.getName());
	}

	@Override
	public Resource get(String id) {
		return resourceDao.get(Resource.class, id);
	}

	@Override
	public void insert(Resource t) {
		resourceDao.insert(t);
	}

	@Override
	public void insert(Collection<?> entities) {
		resourceDao.insert(entities);
	}

	@Override
	public void delete(String id) {
		resourceDao.delete(resourceDao.load(Resource.class, id));
	}

	@Override
	public void delete(Collection<?> entities) {
		resourceDao.delete(entities);
	}

	@Override
	public void update(Resource t) {
		resourceDao.update(t);
	}

	@Override
	public void update(Collection<?> entities) {
		resourceDao.update(entities);
	}

}
