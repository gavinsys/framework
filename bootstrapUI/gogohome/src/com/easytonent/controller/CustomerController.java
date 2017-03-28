package com.easytonent.controller;

import com.easytonent.common.model.Customer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * UserController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class CustomerController extends Controller {
	
	/**
	 * 默认
	 */
	public void index(){
		Page<Customer> customerPage = Customer.dao.paginate(getParaToInt(0, 1), 10);
		setAttr("customerPage", customerPage);
	}
	
}


