package com.easytonent.controller;

import com.easytonent.common.model.User;
import com.easytonent.tools.ToolSecurity;
import com.easytonent.validator.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

/**
 * IndexController
 */
public class IndexController extends Controller {
	
	public void index(){
		render("index.jsp");
	}
	@Clear
	public void log(){
		render("log4j.jsp");
	}
	@Clear
	public void logout(){
		getSession().removeAttribute("flag");
		render("login.jsp");
	}
	@Clear
	@Before(LoginValidator.class) // 配置方式与拦截器完全一样
	public void login() {
		User user = User.dao.findByNameAndPass(getPara("name"), ToolSecurity.MD5(getPara("pass")));
		if(user != null){
			getSession().setAttribute("flag", true);
			render("index.jsp");
		}else{
			setAttr("loginError", "用戶或密碼錯誤!");
			render("login.jsp");
		}
	}
}





