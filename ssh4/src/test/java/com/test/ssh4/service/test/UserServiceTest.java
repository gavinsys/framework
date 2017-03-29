package com.test.ssh4.service.test;

import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.ssh4.model.User;
import com.test.ssh4.service.UserService;

public class UserServiceTest {
	private static UserService userService;
	
	@BeforeClass
	public static void setUp(){
		System.err.println("setUp...");
	}
	/**
	 * 这个before方法在所有的测试方法之前执行，并且只执行一次 所有做Junit单元测试时一些初始化工作可以在这个方法里面进行
	 * 比如在before方法里面初始化ApplicationContext和userService
	 */
	@SuppressWarnings("resource")
	@Before
	public void before() {
		System.out.println("before...");
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "spring.xml", "spring-hibernate.xml" });
		userService = (UserService) ac.getBean("userService");
	}

	@Test
	public void testSaveMethod() {
		User user = new User();
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setName("孤傲苍狼");
		user.setPwd("123");
		user.setCreatedatetime(new Date());
		userService.save(user);
	}
	
	@After
	public void after(){
		System.out.println("after...");
	}
	@AfterClass
	public static void tearDown(){
		System.err.println("tearDown...");
	}
}
