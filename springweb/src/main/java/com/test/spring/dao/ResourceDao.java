package com.test.spring.dao;

import java.util.List;

import com.test.spring.base.IBaseDao;
import com.test.spring.entity.Resource;
import com.test.spring.model.Menus;
import com.test.spring.model.Trees;

public interface ResourceDao extends IBaseDao<Resource> {

	List<Menus> findMenusByUser(String username, String grade, String parentId);

	List<Trees> findTreesByUser(String username, String grade, String parentId);
	
}
