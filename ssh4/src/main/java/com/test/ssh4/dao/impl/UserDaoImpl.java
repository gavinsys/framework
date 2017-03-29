package com.test.ssh4.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.ssh4.dao.UserDao;
import com.test.ssh4.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    
    /**
     * 使用@Autowired注解将sessionFactory注入到UserDaoImpl中
     */
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Serializable save(User user) {
        return sessionFactory.getCurrentSession().save(user);
    }
}