package com.test.ssh4.dao;

import java.util.List;

import com.test.ssh4.model.Info;

public interface InfoDao {
	List<Info> find();
	void save(Info info);
	void update(Info info);
	void delete(Info info);
}
