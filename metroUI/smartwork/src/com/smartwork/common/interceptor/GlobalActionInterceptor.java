package com.smartwork.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.smartwork.common.model.Contacts;

public class GlobalActionInterceptor implements Interceptor {
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Contacts loginUser = controller.getSessionAttr("user");
		if (loginUser != null)
			inv.invoke();
		else
			controller.redirect("/login");
	}
}
