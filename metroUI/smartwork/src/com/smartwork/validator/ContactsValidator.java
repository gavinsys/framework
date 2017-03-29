package com.smartwork.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.smartwork.common.model.Contacts;

public class ContactsValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("contacts.dept", "deptMsg", "部门不能为空");
		validateRequiredString("contacts.jobs", "jobsMsg", "职位不能为空");
		validateRequiredString("contacts.namecn", "namecnMsg", "中文名不能为空");
		validateRequiredString("contacts.nameen", "nameenMsg", "英文名不能为空");
		validateRequiredString("contacts.pwd", "pwdMsg", "密码不能为空");
		validateRegex("contacts.phone", "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", "phoneMsg", "手机格式错误");
		validateEmail("contacts.email", "emailMsg", "邮件格式错误");
	}

	@Override
	protected void handleError(Controller c) {
		c.keepModel(Contacts.class);
		c.render("edit.jsp");
	}

}
