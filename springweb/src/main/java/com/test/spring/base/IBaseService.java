package com.test.spring.base;

import java.util.Collection;
import java.util.List;

import com.test.spring.common.Page;


public interface IBaseService<T> {
	List<T> getAll();
	T get(String id);
	void insert(T t);
	void insert(Collection<?> entities);
	void delete(String id);
	void delete(Collection<?> entities);
	void update(T t);
	void update(Collection<?> entities);
	List<T> getList(T t, Page page);
	
}
