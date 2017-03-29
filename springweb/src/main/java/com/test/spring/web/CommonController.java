package com.test.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.spring.base.BaseController;

@Controller
public class CommonController extends BaseController {
	
	@RequestMapping(value = "/")
	public String entry() {
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logout() {
		return "redirect:/j_spring_security_logout";
	}
	
	@RequestMapping(value = "/main")
	public String index() {
		return "main";
	}
	
	@RequestMapping(value = "/forbidden", method=RequestMethod.GET)
	public String forbidden() {
		return "/commom/forbidden";
	}
	
}
