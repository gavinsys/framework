package com.test.spring.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.spring.base.BaseController;
import com.test.spring.service.RoleService;

@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController{
	
	private static final String BASE = "/moudles/role/" ;
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	
	@Resource
	private RoleService roleService ;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(){
		return BASE+"index";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	@ResponseBody
	public String search(){
		logger.info("search role");
		
		//roleService.get
		
		return null;
	}
	
	@RequestMapping(value="/show", method = RequestMethod.GET)
	public String show(){
		return null;
	}
	
	@RequestMapping(value="/editNew", method = RequestMethod.GET)
	public String editNew(){
		return null;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String create(){
		return null;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(){
		return null;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String update(){
		return null;
	}
	
	@RequestMapping(value="/destory", method = RequestMethod.GET)
	public String destory(){
		return null;
	}
}
