package com.test.spring.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.test.spring.dao.UserDao;
import com.test.spring.entity.User;

@Component
public class ProcessJob {
	
	@Autowired
	private UserDao userDao;
	
	@Scheduled(cron = "0 0 0/1 * * ?") 
	public void disLockAccount(){
		User user = userDao.findUserByUserName("admin");
		System.out.println(user.getUsername());
	}
}
