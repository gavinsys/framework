package com.easytonent.common.interceptor;

import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class GlobalActionInterceptor implements Interceptor {
	private static Logger logger = Logger.getLogger(GlobalActionInterceptor.class.getName());
	public void intercept(Invocation inv) {
		logger.info("Action: "+inv.getController().getParaMap().toString());
		Controller controller = inv.getController();
		Boolean loginUser = controller.getSessionAttr("flag");
		if (loginUser != null && loginUser == true)
			inv.invoke();
		else
			controller.redirect("/platform/login");
	}
}
