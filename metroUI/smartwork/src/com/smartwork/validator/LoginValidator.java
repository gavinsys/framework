package com.smartwork.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("phone", "phoneMsg", "手机不能为空");
		validateRequiredString("pwd", "pwdMsg", "密码不能为空");
	}

	@Override
	protected void handleError(Controller c) {
		c.keepPara("phone");
		c.render("login.jsp");
	}

}
