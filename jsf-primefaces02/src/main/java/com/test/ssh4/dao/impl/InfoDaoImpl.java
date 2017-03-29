package com.test.ssh4.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.ssh4.dao.InfoDao;
import com.test.ssh4.model.Info;

@Repository
public class InfoDaoImpl implements InfoDao {
    
	/**
     * 使用@Autowired注解将sessionFactory注入到UserDaoImpl中
     */
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getCurrentSession() {  
        return sessionFactory.getCurrentSession();  
    }  
    
	@Override
	public List<Info> find() {
		return getCurrentSession().createQuery("FROM Info").list();
	}

	@Override
	public void save(Info info) {
		getCurrentSession().save(info);
	}

	@Override
	public void update(Info info) {
		getCurrentSession().update(info);
	}

	@Override
	public void delete(Info info) {
		getCurrentSession().delete(info);
	}
    
    
}