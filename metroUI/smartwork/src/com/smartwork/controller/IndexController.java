package com.smartwork.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.smartwork.common.interceptor.GlobalActionInterceptor;
import com.smartwork.common.model.Contacts;
import com.smartwork.common.model.Daily;
import com.smartwork.enums.EnumJobs;
import com.smartwork.tools.ToolExcel;
import com.smartwork.tools.ToolMD5;
import com.smartwork.tools.ToolUtils;
import com.smartwork.validator.ContactsValidator;
import com.smartwork.validator.DailyValidator;
import com.smartwork.validator.JoinValidator;
import com.smartwork.validator.LoginValidator;

public class IndexController extends Controller{
	
	@Clear(GlobalActionInterceptor.class)
	public void index(){
		render("login.jsp");
	}
	
	@ActionKey("logout")
	@Clear(GlobalActionInterceptor.class)
	public void logout(){
		Contacts contacts = (Contacts)getSessionAttr("user");
		if(contacts != null){
			getSession().removeAttribute("user");
		}
		render("login.jsp");
	}
	
	@ActionKey("login")
	@Before(LoginValidator.class)
	@Clear(GlobalActionInterceptor.class)
	public void login(){
		Contacts contacts = Contacts.dao.login(getPara("phone"), ToolMD5.MD5(getPara("pwd")));
		if(contacts == null){
			setAttr("loginError", "用户或密码错误!");
			render("login.jsp");
		}else{
			setSessionAttr("user", contacts);
			redirect("/list");
		}
	}
	
	@ActionKey("list")
	public void list(){
		Contacts contacts = (Contacts)getSessionAttr("user");
		Page<Daily> page = null;
		if(contacts.getJobs()==EnumJobs.MEMBER.getCode()){
			page = Daily.dao.findByContactsPage(getParaToInt(0, 1), 150, contacts.getId());
			if(page.getList().size()==0){
				page = Daily.dao.findByContactsPage(1, 150, contacts.getId());
			}
		}else if(contacts.getJobs()==EnumJobs.AD.getCode()){
			page = Daily.dao.findAll(getParaToInt(0, 1), 150);
			if(page.getList().size()==0){
				page = Daily.dao.findAll(1, 150);
			}
		}else{
			page = Daily.dao.findByJobsPage(getParaToInt(0, 1), 150, contacts.getDept()+"", contacts.getJobs());
			if(page.getList().size()==0){
				page = Daily.dao.findByJobsPage(1, 150, contacts.getDept()+"", contacts.getJobs());
			}
		}
		
		
		setAttr("dailyPage", page);
		render("list.jsp");
	}
	
	@ActionKey("contacts")
	public void contacts(){
		setAttr("contactsPage", Contacts.dao.paginate(getParaToInt(0, 1), 50));
		render("contacts.jsp");
	}
	
	@ActionKey("join")
	@Before(JoinValidator.class)
	@Clear(GlobalActionInterceptor.class)
	public void join(){
		Contacts _contacts = Contacts.dao.checkPhone(getPara("contacts.phone"));
		Contacts contacts = getModel(Contacts.class);
		if(_contacts==null){
			
			contacts.setId(ToolUtils.getUuidByJdk(true));
			contacts.setPwd(ToolMD5.MD5(getPara("contacts.pwd")));
			contacts.setCreated(new Date());
			contacts.save();
			render("login.jsp");
		}else{
			setAttr("contacts", contacts);
			setAttr("phoneMsg", "该手机已经使用");
			render("join.jsp");
		}
		
	}
	
	@ActionKey("daily")
	@Before(DailyValidator.class)
	public void daily(){
		Contacts contacts = (Contacts)getSessionAttr("user");
		Daily daily = getModel(Daily.class, "");
		daily.setId(ToolUtils.getUuidByJdk(true));
		daily.setBegin(new Date());
		daily.setContactsId(contacts.getId());
		daily.setCreated(new Date());
		daily.save();
		redirect("/list");
	}
	
	@ActionKey("updateDaily")
	@Before(DailyValidator.class)
	public void updateDaily(){
		Daily daily = Daily.dao.findById(getPara("did"));
		daily.setTitle(getPara("title"));
		daily.update();
		redirect("/list");
	}
	
	public void del(){
		List<Daily> list = Daily.dao.findByContacts(getPara());
		if(list==null||list.size()==0){
			Contacts.dao.deleteById(getPara());
		}
		redirect("/contacts", true);
	}
	
	public void delete(){
		Daily.dao.deleteById(getPara("id"));
		redirect("/list/"+getPara("pageNumber"), true);
	}
	
	public void edit(){
		Contacts contacts = Contacts.dao.findById(getPara());
		setAttr("contacts", contacts);
	}
	
	@Before(ContactsValidator.class)
	public void update(){
		Contacts contacts = getModel(Contacts.class);
		contacts.setPwd(ToolMD5.MD5(getPara("contacts.pwd")));
		contacts.update();
		redirect("/contacts", true);
	}
	
	public void finished(){
		Daily daily = Daily.dao.findById(getPara("id"));
		daily.setEnd(new Date());
		daily.update();
		redirect("/list/"+getPara("pageNumber"), true);
	}
	
	@ActionKey("export")
	public void export() throws Exception{
		Contacts contacts = (Contacts)getSessionAttr("user");
		List<Daily> list = null;
		if(contacts.getJobs()==EnumJobs.MEMBER.getCode()){
			list = Daily.dao.findByContacts(contacts.getId());
		}else if(contacts.getJobs()==EnumJobs.AD.getCode()){
			list = Daily.dao.findAll();
		}else{
			list = Daily.dao.findByJobs(contacts.getDept()+"", contacts.getJobs());
		}
		ToolExcel.toExcel(list, contacts.getNameen());
		renderFile(contacts.getNameen()+"_template.xlsx");
	}
	
	@ActionKey("download")
	public void download() throws Exception{
		ToolExcel.toExcel2(Contacts.dao.findAll());
		renderFile("_contacts.xlsx");
	}
}
