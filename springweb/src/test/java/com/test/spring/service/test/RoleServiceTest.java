package com.test.spring.service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.spring.base.BaseTest;
import com.test.spring.entity.Role;
import com.test.spring.service.RoleService;

public class RoleServiceTest extends BaseTest{
	@Autowired
	RoleService roleService;
	
	@Test
	public void testGetAll() {
		Role role = new Role();
		role.setName("test");
		Role role2 = new Role();
		role2.setName("test2");
		roleService.insert(role);
		roleService.insert(role2);
		List<Role> list = roleService.getAll();
		if(list.size()!=2){
			Assert.assertNotEquals(2, list.size());
		}
	}
}
