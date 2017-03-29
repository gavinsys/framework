package com.smartwork.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.smartwork.common.model.Daily;

public class DailyValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("title", "titleMsg", "部门不能为空");
	}

	@Override
	protected void handleError(Controller c) {
		c.keepModel(Daily.class, "");
		c.redirect("/list");
	}

}
