package com.test.ssh4.dao;

import java.io.Serializable;

import com.test.ssh4.model.User;

public interface UserDao {
	/**
     * �����û�
     * @param user
     * @return
     */
    Serializable save(User user); 
    
    User find(String username);
}
