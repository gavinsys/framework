package com.test.spring.base;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.test.spring.common.Page;
import com.test.spring.common.json.SimpleJsonFilter;
import com.test.spring.service.ResourceService;
import com.test.spring.service.RoleService;
import com.test.spring.service.UserService;

public abstract class BaseController implements IBaseController {
	
	private Logger logger=Logger.getLogger(BaseController.class.getName());
	
	@Autowired
	protected UserService userService;
	@Autowired
	protected RoleService roleService;
	@Autowired
	protected ResourceService resourceService;
	
	protected HttpServletRequest currentRequest;
	protected HttpServletResponse currentResponse;
	
	protected Page page = new Page();
	
	@Autowired
	private MessageSource messageSource;
	
	private static final CookieLocaleResolver COOKIELOCALERESOLVER=new CookieLocaleResolver();
	
	//================== 公共方法
	
	//这个注释会让 spring mvc每次接受请求的时候，先执行该方法
	@ModelAttribute
	public void setReqAndResp(HttpServletRequest request,HttpServletResponse response){
		this.currentRequest=request;
		this.currentResponse=response;
	}
	
	public String getMessage(String key,Object...args){
		return messageSource.getMessage(key, args, COOKIELOCALERESOLVER.resolveLocale(currentRequest));
	}
	
	
	
	public String index(){
		return null;
	}
	public Page getPage() {
		page=page==null?new Page():page;
		return page;
	}
	public void setPage(@RequestParam Page page) {
		this.page = page;
	}
	public String search(){
		return null;
	}
	public String show(){
		return null;
	}
	public String editNew(){
		return null;
	}
	public String create(){
		return null;
	}
	public String edit(){
		return null;
	}
	public String update(){
		return null;
	}
	public String destory(){
		return null;
	}
	
	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @param includesProperties
	 *            需要转换的属性
	 * @param excludesProperties
	 *            不需要转换的属性
	 */
	public void writeJsonByFilter(Object object,HttpServletRequest request,HttpServletResponse response, String[] includesProperties, String[] excludesProperties) {
		try {
			SimpleJsonFilter filter = new SimpleJsonFilter();// excludes优先于includes
			if (excludesProperties != null && excludesProperties.length > 0) {
				filter.getExcludes().addAll(Arrays.<String> asList(excludesProperties));
			}
			if (includesProperties != null && includesProperties.length > 0) {
				filter.getIncludes().addAll(Arrays.<String> asList(includesProperties));
			}
			logger.info("对象转JSON：要排除的属性[" + excludesProperties + "]要包含的属性[" + includesProperties + "]");
			String json;
			String User_Agent = request.getHeader("User-Agent");
			if (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1) {
				// 使用SerializerFeature.BrowserCompatible特性会把所有的中文都会序列化为\\uXXXX这种格式，字节数会多一些，但是能兼容IE6
				json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BrowserCompatible);
			} else {
				// 使用SerializerFeature.WriteDateUseDateFormat特性来序列化日期格式的类型为yyyy-MM-dd hh24:mi:ss
				// 使用SerializerFeature.DisableCircularReferenceDetect特性关闭引用检测和生成
				json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
			}
			logger.info("转换后的JSON字符串：" + json);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeJsonByFilter(Object object){
		writeJsonByFilter(object, currentRequest, currentResponse,null,null);
	}
			
}
