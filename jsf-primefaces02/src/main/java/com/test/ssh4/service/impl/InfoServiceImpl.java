package com.test.ssh4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.ssh4.dao.InfoDao;
import com.test.ssh4.model.Info;
import com.test.ssh4.service.InfoService;

@Service("infoService")
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private InfoDao infoDao;

	@Override
	public List<Info> find() {
		return infoDao.find();
	}

	@Override
	public void save(Info info) {
		infoDao.save(info);
	}

	@Override
	public void update(Info info) {
		infoDao.update(info);
	}

	@Override
	public void delete(Info info) {
		infoDao.delete(info);
	}

	
}
