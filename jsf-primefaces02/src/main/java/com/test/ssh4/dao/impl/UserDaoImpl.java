package com.test.ssh4.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.ssh4.dao.UserDao;
import com.test.ssh4.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    
	/**
     * 使用@Autowired注解将sessionFactory注入到UserDaoImpl中
     */
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getCurrentSession() {  
        return sessionFactory.getCurrentSession();  
    }  
    
    @Override
    public Serializable save(User user) {
        return getCurrentSession().save(user);
    }

	@Override
	public User find(String username) {
		Query query = getCurrentSession().createQuery("FROM User u where u.name=?");
		query.setParameter(0, username);
		List<User> list = query.list();
		return list!=null&&list.size()>0?list.get(0):null;
	}
    
    
}