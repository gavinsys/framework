package com.easytonent.tools;

import org.apache.log4j.Logger;

import com.easytonent.constant.ConstantError;

public class ToolResponse {
	private static Logger logger = Logger.getLogger(ToolResponse.class.getName());
	public static String responseCode2Json(String code){
		StringBuffer sb = new StringBuffer("{\"code\" : \"").append(code).append("\", \"message\" : \"").append(ConstantError.replyMsg(code)).append("\"}");
		logger.info("Response Json: "+ sb.toString());
		return sb.toString();
	}
	public static String responseData2Json(String code, String key, String json){
		String data = "{\""+key+"\" : " + json +"}";
		StringBuffer sb = new StringBuffer("{\"code\" : \"").append(code).append("\", \"message\" : \"").append(ConstantError.replyMsg(code)).append("\"").append(", \"data\" : ").append(data).append("}");;
		logger.info("Response Json: "+ sb.toString());
		return sb.toString();
	}
	public static String responseTokenAndData2Json(String code, String key, String json, String _key, String _json){
		String data = "{\""+key+"\" : " + json +", \""+ _key +"\" : "+ _json +"}";
		StringBuffer sb = new StringBuffer("{\"code\" : \"").append(code).append("\", \"message\" : \"").append(ConstantError.replyMsg(code)).append("\"").append(", \"data\" : ").append(data).append("}");;
		logger.info("Response Json: "+ sb.toString());
		return sb.toString();
	}
}
