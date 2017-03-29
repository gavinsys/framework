package com.test.spring.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.spring.base.BaseController;
import com.test.spring.model.Menus;
import com.test.spring.model.Trees;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController{

	@RequestMapping(value="/getMenus")  
    public @ResponseBody List<Menus> getMenus(String username, String grade) { 
		return resourceService.findMenusByUser(username, grade, null);
    }
	
	@RequestMapping(value="/getTrees")  
    public @ResponseBody List<Trees> getTrees(String username, String grade, String parentId) { 
		return resourceService.findTreesByUser(username, grade, parentId);
    }
	
}