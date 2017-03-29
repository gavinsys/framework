package com.test.spring.service;

import java.util.List;

import com.test.spring.base.IBaseService;
import com.test.spring.entity.Resource;
import com.test.spring.model.Menus;
import com.test.spring.model.Trees;

public interface ResourceService extends IBaseService<Resource>{

	List<Menus> findMenusByUser(String username, String grade, String parentId);

	List<Trees> findTreesByUser(String username, String grade, String parentId);

}
