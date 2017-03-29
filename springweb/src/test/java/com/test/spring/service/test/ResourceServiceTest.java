package com.test.spring.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.spring.base.BaseTest;
import com.test.spring.model.Menus;
import com.test.spring.model.Trees;
import com.test.spring.service.ResourceService;

public class ResourceServiceTest extends BaseTest{
	@Autowired
	ResourceService resourceService;
	
	@Test
	public void testGetMenus() {
		List<Trees> list = resourceService.findTreesByUser("admin", "2", "1");
		for(Trees re: list){
			System.out.println(re.getText());
		}
		
//		List<Menus> list = resourceService.findMenusByUser("admin", "1", "");
//		for(Menus re: list){
//			System.out.println(re.getName());
//		}
		
	}
}
