package com.easytonent.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.easytonent.common.cache.Cache;
import com.easytonent.common.model.Ctoken;
import com.easytonent.common.model.Dtoken;
import com.jfinal.core.Controller;

public class AppBaseController extends Controller {
	
	protected Logger logger = Logger.getLogger(AppBaseController.class.getName());
	
	protected static final Integer FILE_SIZE = 5*1024*1024;
	protected static final String KEY = "88888888";
	protected static final String _URL = "https://gcm-http.googleapis.com/gcm/send";
	protected static final String _KEY = "Authorization";
	protected static final String C_VALUE = "key=AIzaSyD8ttSyzJIHi3RjGsUxGhzqOKsIT1aAsPE";
	protected static final String D_VALUE = "key=AIzaSyDjAbdsDQSp7rrgdEOorZTDP3AQePbNYvA";
	protected static Cache<String, String> cache = new Cache<String, String>();
	protected static final int seconds = 180;
	
	public String gcmCustomer(String customerId){
		String appIds = "";
		List<Ctoken> listToken = Ctoken.dao.findByUuid(customerId);
		for(Ctoken ctoken: listToken){
			appIds += "\""+ctoken.getAppId()+"\",";
		}
		if(!"".equals(appIds)){
			appIds = "["+appIds.substring(0, appIds.length()-1)+"]"; // 组装gcm报文
		}
		
		return appIds;
	}
	
	public String gcmDriver(String driverId){
		String appIds = "";
		List<Dtoken> listToken = Dtoken.dao.findByUuid(driverId);
		for(Dtoken dtoken: listToken){
			appIds += "\""+dtoken.getAppId()+"\",";
		}
		if(!"".equals(appIds)){
			appIds = "["+appIds.substring(0, appIds.length()-1)+"]"; // 组装gcm报文
		}
		
		return appIds;
	}
}
