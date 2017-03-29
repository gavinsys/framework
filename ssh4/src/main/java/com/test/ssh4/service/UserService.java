package com.test.ssh4.service;

import java.io.Serializable;

import com.test.ssh4.model.User;

public interface UserService {
	
    /**
     * 保存用户
     * @param user
     * @return
     */
    Serializable save(User user); 
}
