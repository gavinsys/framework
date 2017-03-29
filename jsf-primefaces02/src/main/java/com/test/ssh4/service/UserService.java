package com.test.ssh4.service;

import java.io.Serializable;

import com.test.ssh4.model.User;

public interface UserService {
	
	/**
     * ±£¥Ê”√ªß
     * @param user
     * @return
     */
    Serializable save(User user); 
    
    User find(String username);
}
