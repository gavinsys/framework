package com.easytonent.controller;

import java.util.Date;

import com.easytonent.common.model.User;
import com.easytonent.tools.ToolSecurity;
import com.easytonent.tools.ToolUUID;
import com.easytonent.validator.UserValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * UserController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class UserController extends Controller {
	
	/**
	 * 默认
	 */
	public void index(){
		Page<User> userPage = User.dao.paginate(getParaToInt(0, 1), 10);
		setAttr("userPage", userPage);
	}
	
	/**
	 * 保存
	 */
	@Before(UserValidator.class)
	public void save(){
		User user = this.getModel(User.class);
		user.setId(ToolUUID.uuid());
		user.setPass(ToolSecurity.MD5(getPara("user.pass")));
		user.setCreated(new Date());
		user.save();
		forwardAction("/platform/user");
	}
	
	/**
	 * 编辑
	 */
	public void edit(){
		User user = User.dao.findById(getPara());
		renderJson(user);
	}
	
	/**
	 * 修改
	 */
	@Before(UserValidator.class)
	public void update(){
		User user = this.getModel(User.class);
		user.setPass(ToolSecurity.MD5(getPara("user.pass")));
		user.update();
		forwardAction("/platform/user");
	}
	
	/**
	 * 删除
	 */
	public void delete(){
		User.dao.deleteById(getPara());
		forwardAction("/platform/user");
	}
}


