package com.test.spring.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.test.spring.common.Page;

public interface IBaseDao<T> {

	/**
	 * ###################      获取             ######################
	 */
	
	T load(Class<?> clazz, Serializable id);
	
	T get(Class<?> clazz,Serializable id);
	
	Object getObject(Class<?> clazz,Serializable id);
	
	
	
	/**
	 * ###################      添加/更新             ######################
	 */
	
	void insert(Object entity);
	
	void insert(Collection<?> entities);
	
	void save(Object entity);
	
	void saveOrUpdate(Object entity);

	void update(Object entity);
	
	void update(Collection<?> entities);
	
	
	
	/**
	 * ###################      删除           ###################
	 */
	
	void delete(Object entity);
	
	void delete(Collection<?> entities);
	
	
	/**
	 * #################      统计          ###################
	 */
	
	int count(final String className);
	
	
	/**
	 * #################      查询          ###################
	 */

	List<T> findEntityListByHql(String hql,Object ...params);
	
	List<T> findEntityListByEntityName(final String className);

	List<T> findEntityListByHql(String hql, Page page, Object[] params);

	
}
