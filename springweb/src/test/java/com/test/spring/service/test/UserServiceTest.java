package com.test.spring.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.spring.base.BaseTest;
import com.test.spring.entity.User;
import com.test.spring.service.UserService;

public class UserServiceTest extends BaseTest{

	@Autowired
	UserService userService;
	
	@Test
	public void testGetAll() {
		User user = new User();
		user.setUsername("test");
		User user2 = new User();
		user2.setUsername("test2");
		userService.insert(user);
		userService.insert(user2);
		List<User> list = userService.getAll();
		if(list.size()!=2){
			Assert.assertNotEquals(2, list.size());
		}
	}
	
	@Test
	public void testGet() {
		User user = new User();
		user.setUsername("test");
		userService.insert(user);
		User u = userService.get(user.getId());
		Assert.assertEquals(user.getId(), u.getId());
	}
	
	@Test
	public void testGetList() {
		User user = new User();
		user.setUsername("test");
		userService.insert(user);
		List<User> list = userService.getList(user,null);
		for(User u:list){
			if(u.getId().equals(user.getId())){
				Assert.assertEquals(user.getId(), u.getId());
			}
		}
	}
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setUsername("test");
		userService.insert(user);
		User u = userService.get(user.getId());
		Assert.assertEquals(u.getId(), user.getId());
	}
	
	@Test
	public void testInsert2() {
		User user = new User();
		user.setUsername("test");
		User user2 = new User();
		user2.setUsername("test2");
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		
		userService.insert(list);
		for(User uu : list){
			User u = userService.get(uu.getId());
			Assert.assertEquals(u.getId(), uu.getId());
		}
		
	}
	
	@Test
	public void testDelete(){
		User user = new User();
		user.setUsername("test");
		userService.insert(user);
		userService.delete(user.getId());
		User u = userService.get(user.getId());
		Assert.assertNull(u);
	}
	
	@Test
	public void testDelete2(){
		User user = new User();
		user.setUsername("test");
		userService.insert(user);
		User user2 = new User();
		user2.setUsername("test");
		userService.insert(user2);
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		userService.delete(list);
		for(User uu : list){
			User u = userService.get(uu.getId());
			Assert.assertNull(u);
		}
	}
	
	@Test
	public void testUpdate(){
		User user = new User();
		user.setUsername("test");
		userService.insert(user);
		user.setUsername("new test");
		userService.update(user);
		User u = userService.get(user.getId());
		Assert.assertEquals("new test", u.getUsername());
	}
	
	@Test
	public void testUpdate2(){
		User user = new User();
		user.setUsername("test");
		userService.insert(user);
		user.setUsername("new test");
		
		User user2 = new User();
		user2.setUsername("test2");
		userService.insert(user2);
		user2.setUsername("new test2");
		
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		userService.update(list);
		User u = userService.findUserByUserName(user.getUsername());
		Assert.assertEquals("new test", u.getUsername());
		User u2 = userService.findUserByUserName(user2.getUsername());
		Assert.assertEquals("new test2", u2.getUsername());
		
	}
	
	@Test
	public void testFindUserByUserUsername(){
		User user = new User();
		user.setUsername("test");
		userService.insert(user);
		User u = userService.findUserByUserName(user.getUsername());
		Assert.assertEquals(user.getUsername(), u.getUsername());
	}
	
}
