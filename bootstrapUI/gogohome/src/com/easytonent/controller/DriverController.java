package com.easytonent.controller;

import org.apache.log4j.Logger;

import com.easytonent.common.model.Driver;
import com.easytonent.enums.EnumOnOff;
import com.easytonent.enums.EnumPass;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * UserController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class DriverController extends Controller {
	
	/**
	 * 默认
	 */
	public void index(){
		Page<Driver> driverPage = Driver.dao.paginate(getParaToInt(0, 1), 10);
		setAttr("driverPage", driverPage);
	}
	
	/**
	 * 查看
	 */
	public void view(){
		Driver driver = Driver.dao.findById(getPara());
		driver.setStatus(EnumOnOff.findByCode(driver.getState()));
		driver.setStatus(EnumPass.findByCode(driver.getStatus()));
		renderJson(driver);
	}
	
	/**
	 * 修改
	 */
//	@Before(DriverValidator.class)
	public void ispass(){
		Logger.getLogger("sdf").info("111111111111");
		Driver driver = Driver.dao.findById(getPara("id"));
		driver.setStatus(EnumPass.ISPASS.getCode());
		driver.update();
		forwardAction("/platform/driver");
	}
	
	public void nopass(){
		Driver driver = Driver.dao.findById(getPara("id"));
		driver.setStatus(EnumPass.NOPASS.getCode());
		driver.update();
		forwardAction("/platform/driver");
	}
}


