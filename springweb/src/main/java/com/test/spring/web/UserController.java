package com.test.spring.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.spring.base.BaseController;
import com.test.spring.entity.User;
import com.test.spring.model.UserModel;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	public static final String BASE = "/moudles/user/";
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(){
		return BASE+"index";
	}	
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String search(UserModel userModel, Model model){
		userService.searchUser(userModel, page);
		model.addAttribute("page", page);
		return BASE+"index";
	}
	
	@RequestMapping(value="/show", method = RequestMethod.GET)
	public String show(String id, Model model){
		User user = userService.get(id);
		model.addAttribute("user", user);
		return BASE+"info";
	}
	
	@RequestMapping(value="/editNew", method = RequestMethod.GET)
	public String editNew(){
		return BASE+"insert";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public void create(@Valid UserModel user,Errors errors){
		
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
