package com.test.spring.web;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.test.spring.base.BaseController;
import com.test.spring.common.VerifyCodeUtils;

@Controller
@RequestMapping(value = "/init")
public class InitController extends BaseController {
	
	@RequestMapping(value = "/north", method=RequestMethod.GET)
	public String north() {
		return "/layout/north";
	}
	
	@RequestMapping(value = "/center", method=RequestMethod.GET)
	public String center() {
		return "/layout/center";
	}
	
	@RequestMapping(value = "/south", method=RequestMethod.GET)
	public String south() {
		return "/layout/south";
	}
	
	@RequestMapping(value = "/west", method=RequestMethod.GET)
	public String west() {
		return "/layout/west";
	}
	
	@RequestMapping(value = "/portal", method=RequestMethod.GET)
	public String portal() {
		return "/layout/portal";
	}
	
	@RequestMapping(value = "/subtree", method=RequestMethod.GET)
	public String subtree(String subtree_id, String subtree_index, Model model) {
		System.out.println(subtree_id+" "+subtree_index);
		model.addAttribute("subtree_id", subtree_id);
		model.addAttribute("subtree_index", subtree_index);
		return "/layout/subtree";
	}
	
	@RequestMapping(value = "/global", method = { RequestMethod.GET })
	public String global(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "langType", defaultValue = "zh") String langType) {
		if (langType.equals("zh")) {
			Locale locale = new Locale("zh", "CN");
			(new CookieLocaleResolver()).setLocale(request, response, locale);
		} else if (langType.equals("en")) {
			Locale locale = new Locale("en", "US");
			(new CookieLocaleResolver()).setLocale(request, response, locale);
		} else {
			(new CookieLocaleResolver()).setLocale(request, response, LocaleContextHolder.getLocale());
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/authImage", method=RequestMethod.GET)
	public void authImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session  
        HttpSession session = request.getSession(true);  
        session.setAttribute("rand", verifyCode.toLowerCase());  
        //生成图片  
        int w = 200, h = 80;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}
}
