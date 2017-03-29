package com.test.ssh4.dao;

import java.io.Serializable;

import com.test.ssh4.model.User;

public interface UserDao {
	/**
     * 保存用户
     * @param user
     * @return
     */
    Serializable save(User user); 
}
