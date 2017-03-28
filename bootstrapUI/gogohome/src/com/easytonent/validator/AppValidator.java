package com.easytonent.validator;

import org.apache.log4j.Logger;

import com.easytonent.constant.ConstantError;
import com.easytonent.controller.AppBaseController;
import com.easytonent.tools.ToolResponse;
import com.easytonent.tools.ToolString;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class AppValidator extends Validator {
	protected Logger logger = Logger.getLogger(AppBaseController.class.getName());
	@Override
	protected void validate(Controller c) {
		logger.info("Validate: "+c.getParaMap().toString());
		if (getActionKey().equals("/platform/getCode")) {
			validateRequiredString("sign", "", "");
			validateRequiredString("type", "", "");
			validateRequiredString("area", "", "");
			validateRegex("phone", "\\b((1[3,5,7,8,9]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b", "", "");
		}
		if (getActionKey().equals("/platform/join")||getActionKey().equals("/platform/register")) {
			validateRequiredString("code", "", "");
			validateRegex("phone", "\\b((1[3,5,7,8,9]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b", "", "");
		}
		if (getActionKey().equals("/platform/location")) {
			validateRegex("lat", "^.{1,50}$", "", "");
			validateRegex("lng", "^.{1,50}$", "", "");
		}
		if (getActionKey().equals("/platform/uploadLoc")) {
			validateRegex("locs", "^.{1,500}$", "", "");
			validateRequiredString("orderNo", "", "");
		}
		if (getActionKey().equals("/platform/get")) {
			validateDouble("price", 1, 1000, "", "");
			validateRegex("time", "^(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})$", "", "");
			validateRequiredString("orderNo", "", "");
		}
		if (getActionKey().equals("/platform/enter") || getActionKey().equals("/platform/into")) {
			validateRequiredString("code", "", "");
			validateRegex("phone", "\\b((1[3,5,7,8,9]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b", "", "");
			validateRequiredString("appId", "", "");
			validateRequiredString("deviceId", "", "");
		}
		if (getActionKey().equals("/platform/update")) {
//			validateRequiredString("img", "", "");
			validateRequiredString("name", "", "");
			validateRegex("idNumber", ToolString.regExp_idCard, "", "");
//			validateRequiredString("dateLicense", "", "");
//			validateRequiredString("imgLicense", "", "");
//			validateRequiredString("imgIdNumber", "", "");
		}
		if (getActionKey().equals("/platform/action") || getActionKey().equals("/platform/finish") 
				 || getActionKey().equals("/platform/getList") || getActionKey().equals("/platform/getOrder") 
				|| getActionKey().equals("/platform/close")	|| getActionKey().equals("/platform/over") 
				|| getActionKey().equals("/platform/remove")|| getActionKey().equals("/platform/cancel")) {
			validateRequiredString("orderNo", "", "");
		}
		if (getActionKey().equals("/platform/queryLoc")) {
			validateRequiredString("uuid", "", "");
			validateRequiredString("deviceId", "", "");
			validateRequiredString("accessToken", "", "");
			validateRequiredString("orderNo", "", "");
		}
		if (getActionKey().equals("/platform/getLists")||getActionKey().equals("/platform/getOrders")) {
			validateInteger("pageNumber", 1, 200, "", "");
			validateInteger("pageSize", 1, 200, "", "");
		}
		if (getActionKey().equals("/platform/setting")) {
//			validateRequiredString("img", "", "");
			validateRegex("name", "^.{1,50}$", "", "");
			validateRegex("phone", "\\b((1[3,5,7,8,9]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b", "", "");
			validateInteger("carAge", 1, 100, "", "");
			validateRegex("gears", "^.{1,50}$", "", "");
			validateRegex("urgentContacts", "\\b((1[3,5,7,8,9]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b", "", "");
		}
		if (getActionKey().equals("/platform/calldriver")) {
			validateRegex("time", "(([0-1][0-9])|2[0-3]):[0-5][0-9]", "", "");
			validateRegex("from", "^.{1,200}$", "", "");
			validateRegex("to", "^.{1,200}$", "", "");
			validateRegex("fromlat", "^.{1,200}$", "", "");
			validateRegex("fromlng", "^.{1,200}$", "", "");
			validateRegex("tolat", "^.{1,200}$", "", "");
			validateRegex("tolng", "^.{1,200}$", "", "");
		}
		if (getActionKey().equals("/platform/ask")) {
			validateRegex("time", "(([0-1][0-9])|2[0-3]):[0-5][0-9]", "", "");
			validateRegex("from", "^.{1,200}$", "", "");
			validateRegex("to", "^.{1,200}$", "", "");
			validateRegex("fromlat", "^.{1,200}$", "", "");
			validateRegex("fromlng", "^.{1,200}$", "", "");
			validateRegex("tolat", "^.{1,200}$", "", "");
			validateRegex("tolng", "^.{1,200}$", "", "");
			validateRegex("contact", "\\b((1[3,5,7,8,9]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b", "", "");
		}
		if (getActionKey().equals("/platform/take")) {
			validateRequiredString("orderNo", "", "");
			validateRequiredString("driverId", "", "");
		}
		if (getActionKey().equals("/platform/getDriver")) {
			validateRequiredString("driverId", "", "");
		}
		if (getActionKey().equals("/platform/getCustomer")) {
			validateRequiredString("customerId", "", "");
		}
	}

	@Override
	protected void handleError(Controller c) {
		c.renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2));
	}

}
