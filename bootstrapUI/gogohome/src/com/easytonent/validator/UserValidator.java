package com.easytonent.validator;

import com.easytonent.common.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * UserValidator.
 */
public class UserValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("user.name", "nameMsg", "請輸入名稱!");
		validateRequiredString("user.pass", "passMsg", "請輸入密碼!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(User.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/user/save"))
			controller.render("add.jsp");
		else if (actionKey.equals("/user/update"))
			controller.render("edit.jsp");
	}
}
