package com.easytonent.tools;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.easytonent.constant.ConstantError;


public class ToolSMS {
	private static final String TEMPLATE_SMS="【gogohome】尊敬的顧客，為了保證你的隱私，驗證碼為：{0}，在3分鐘內使用，請勿外洩。";
	private static final String MERID = "51244";
	private static final String KEY = "88888888";
	private static final String URL = "http://124.248.215.10:5083/smsApi/sendMessage";
	private static Logger logger = Logger.getLogger(ToolSMS.class.getName());
	
	public static String sendMessage(String area, String phone, Integer code) {
		try{
			Map<String,String> params = new TreeMap<String,String>();
			params.put("sequenceNumber", UUID.randomUUID().toString().replaceAll("-", ""));
			params.put("merId", MERID);
			params.put("callNumber", area+"-"+phone);
			params.put("callbackUrl", "");
			params.put("messageContent", TEMPLATE_SMS.replace("{0}", code.toString()));
			params.put("reserved", "");
			Map<String, String> map = signture(params);
			String data = map2Data(map);
			String responseText = ToolHttp.doPostQueryCmd(URL, data, null, null, "application/x-www-form-urlencoded");
			logger.info(responseText);
			if(!responseText.isEmpty()&&responseText.contains("status=00:Success")){
				return ConstantError.REPLY_CODE_0;
			}
			return ConstantError.REPLY_CODE_1_2;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	public static Map<String, String> signture(Map<String, String> treeMap){
		
		Iterator<?> treeiter = treeMap.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (treeiter.hasNext()) {
			Object key = treeiter.next();
			sb.append(key +"="+ treeMap.get(key) +"&");
		}
        
	    String accessKey = ToolSecurity.sha256(sb.append(KEY).toString());
	    treeMap.put("accessKey", accessKey);
	    return treeMap;
	}
	
	public static String map2Data(Map<String, String> map) throws UnsupportedEncodingException{
		StringBuffer b = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if(!ArrayUtils.contains(new String[] {""}, entry.getKey())){
				b.append(entry.getKey());
				b.append('=');
				if (entry.getValue() != null) {
					b.append(StringUtils.isBlank(entry.getValue())?"":new String(new Base64().encode(entry.getValue().getBytes("UTF-8"))));
				}
				b.append("&");
			}
		}
		return b.toString();
	}
	
}
