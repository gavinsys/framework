package com.easytonent.common.interceptor;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.easytonent.common.model.Ctoken;
import com.easytonent.common.model.Customer;
import com.easytonent.common.model.Driver;
import com.easytonent.common.model.Dtoken;
import com.easytonent.constant.ConstantActionKey;
import com.easytonent.constant.ConstantError;
import com.easytonent.tools.ToolResponse;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class GlobalAppInterceptor implements Interceptor {
	private static Logger logger = Logger.getLogger(GlobalAppInterceptor.class.getName());
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		String uuid = controller.getPara("uuid");
		String deviceId = controller.getPara("deviceId");
		String accessToken = controller.getPara("accessToken");
		logger.info("APP: "+inv.getController().getParaMap().toString());
		String actionKey = inv.getActionKey();
		if(Arrays.asList(ConstantActionKey.C_ACTION_KEY).contains(actionKey)){ // 客户不能访问司机的URl
			Customer customer = Customer.dao.findById(uuid);
			if(customer==null){
				controller.renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1));
				return;
			}
			Ctoken token = Ctoken.dao.findByUuidAndDeviceIdAndAccesstoken(uuid, deviceId, accessToken);
			if(token!=null){
				inv.invoke();
			} else {
				controller.renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1));
			}
		}else if(Arrays.asList(ConstantActionKey.D_ACTION_KEY).contains(actionKey)){ // 司机不能访问客户的URL
			Driver driver = Driver.dao.findById(uuid);
			if(driver==null){
				controller.renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1));
				return;
			}
			Dtoken token = Dtoken.dao.findByUuidAndDeviceIdAndAccesstoken(uuid, deviceId, accessToken);
			if(token!=null){
				inv.invoke();
			} else {
				controller.renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1));
			}
		}else{ // 否则返回非法请求
			controller.renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1));
		}
		
	}
}
