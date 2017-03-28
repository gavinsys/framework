package com.easytonent.controller;

import java.util.Date;
import java.util.List;

import com.easytonent.common.interceptor.GlobalActionInterceptor;
import com.easytonent.common.interceptor.GlobalAppInterceptor;
import com.easytonent.common.model.Ctoken;
import com.easytonent.common.model.Customer;
import com.easytonent.common.model.Driver;
import com.easytonent.common.model.Dtoken;
import com.easytonent.common.model.Intention;
import com.easytonent.common.model.Location;
import com.easytonent.common.model.Orders;
import com.easytonent.constant.ConstantError;
import com.easytonent.enums.EnumOnOff;
import com.easytonent.enums.EnumState;
import com.easytonent.enums.EnumStatus;
import com.easytonent.tools.ToolGPS;
import com.easytonent.tools.ToolHttp;
import com.easytonent.tools.ToolResponse;
import com.easytonent.tools.ToolString;
import com.easytonent.tools.ToolUUID;
import com.easytonent.validator.AppValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.auth.AccessTokenBuilder;

@Before({ GlobalAppInterceptor.class, AppValidator.class })
@Clear(GlobalActionInterceptor.class) // 全局清除Action拦截器
public class AppCustomerController extends AppBaseController {
	
	@ActionKey("/platform/quit") // 客户注销
	public void quit() {
		try{
			String uuid = getPara("uuid"); // 客户唯一标示
			String deviceId = getPara("deviceId"); // 手机设备ID
			
			// 注销 清除会话凭证token
			Ctoken ctoken = Ctoken.dao.findByUuidAndDeviceId(uuid, deviceId);
			if(ctoken != null){
				ctoken.delete();
			}
			
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 订单详情
	@ActionKey("/platform/getCustomer")
	public void getCustomer() {
		try{
			String customerId = getPara("customerId");
			
			renderJson(ToolResponse.responseData2Json(ConstantError.REPLY_CODE_0, "customer", JsonKit.toJson(Customer.dao.findById(customerId))));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 客户登陆
	@ActionKey("/platform/into")
	@Clear(GlobalAppInterceptor.class)
	public void into() {
		try{
			String code = getPara("code"); // 4位验证码
			String phone = getPara("phone"); // 手机号
			String appId = getPara("appId"); // 应用ID
			String deviceId = getPara("deviceId"); // 设备ID
			if(cache==null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_4));
			}
			String _code = cache.get(phone);
			if (code.equals(_code)) { // 验证4位验证码
				Customer customer = Customer.dao.findByPhone(phone);
				if(customer == null){ // 返回该手机未加盟
					customer = new Customer();
					customer.setId(ToolUUID.uuid());
					customer.setPhone(phone);
					customer.setCreated(new Date());
					customer.save();
					Ctoken _token = Ctoken.dao.builderAccessToken(customer.getId(), deviceId, appId, AccessTokenBuilder.getAccessToken(getRequest()));
					renderJson(ToolResponse.responseTokenAndData2Json(ConstantError.REPLY_CODE_0, "token", JsonKit.toJson(_token), "customer", JsonKit.toJson(customer)));
				}else{
					Ctoken dtoken = Ctoken.dao.findByUuidAndDeviceId(customer.getId(), deviceId);
					if(dtoken!=null){
						dtoken.delete();
					}
					Ctoken _token = Ctoken.dao.builderAccessToken(customer.getId(), deviceId, appId, AccessTokenBuilder.getAccessToken(getRequest()));
					renderJson(ToolResponse.responseTokenAndData2Json(ConstantError.REPLY_CODE_0, "token", JsonKit.toJson(_token), "customer", JsonKit.toJson(customer)));
				}
			}else{
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_4));
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 客戶完善/修改资料
	@ActionKey("/platform/setting")
	public void setting() {
		try{
//			List<UploadFile> files = getFiles("upload", FILE_SIZE, ToolString.encoding);
//			String img = ToolUploadFile.filePathByParameterName(files, "img"); // 头像
			String name = getPara("name"); // 真实姓名
			Integer carAge = getParaToInt("carAge"); // 车龄
			String gears = getPara("gears"); // 拍档
			String phone = getPara("phone"); // 手机号
			String urgentContacts = getPara("urgentContacts"); // 紧急联系电话
			
			Customer customer = Customer.dao.findById(getPara("uuid"));
//			customer.setImg(img);
			customer.setName(name);
			customer.setPhone(phone);
			customer.setCarAge(carAge);
			customer.setGears(gears);
			customer.setUrgentContacts(urgentContacts);
			
			customer.update();
			
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 客戶叫司机
	@ActionKey("/platform/calldriver")
	public void call() {
		try{
			String time = getPara("time");
			String to = getPara("to");
			String from = getPara("from");
			String uuid = getPara("uuid");
			String fromlat = getPara("fromlat");
			String fromlng = getPara("fromlng");
			String tolat = getPara("tolat");
			String tolng = getPara("tolng");
			
			Orders _order = Orders.dao.findByCustomer(uuid, EnumStatus.WAITING.getCode(), EnumStatus.BEGIN.getCode(), EnumStatus.END.getCode());
			if(_order!=null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_5));
				return;
			}
			
			String orderNo = ToolString.returnOrderNo();
			
			Orders order = new Orders();
			order.setId(ToolUUID.uuid());
			order.setTime(time);
			order.setOrderNo(orderNo);
			order.setToloc(to);
			order.setFromloc(from);
			order.setFromlat(fromlat);
			order.setFromlng(fromlng);
			order.setTolat(tolat);
			order.setTolng(tolng);
			order.setCustomerId(uuid);
			order.setStatus(EnumStatus.ORDERED.getCode());
			order.setCreated(new Date());
			order.save(); // 生成订单
			
			List<Driver> list = Driver.dao.findAll(EnumOnOff.ON.getCode()); // 加载所以在线司机
			String appIds = "";
			for(Driver d: list){
				Location loc = Location.dao.findById(d.getId());
				if(loc==null){
					renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_7));
					return;
				}
				double distance = ToolGPS.getDistance(Double.valueOf(fromlat), Double.valueOf(fromlng), Double.valueOf(loc.getLat()), Double.valueOf(loc.getLng())); // loc null exception
				if(distance<30000){ // 3000米范围内司机
					List<Dtoken> listToken = Dtoken.dao.findByUuid(d.getId());
					for(Dtoken token: listToken){
						appIds += "\""+token.getAppId()+"\",";
					}
				}
			}
			
			if(!"".equals(appIds)){
				appIds = "["+appIds.substring(0, appIds.length()-1)+"]"; // 组装gcm报文
			}
			
			Customer customer = Customer.dao.findById(uuid);
			String data = "{\"data\": {\"type\" : \"order\", \"order\" : "+JsonKit.toJson(order)+", \"customer\" : "+JsonKit.toJson(customer)+" }, \"registration_ids\": "+ appIds +"}";
			logger.info(data);
			ToolHttp.doPostQueryCmd(_URL, data, _KEY, D_VALUE, "application/json");
			
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
			
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 客戶代叫司机
	@ActionKey("/platform/ask")
	public void ask() {
		try{
			String contact = getPara("contact");
			String time = getPara("time");
			String to = getPara("to");
			String from = getPara("from");
			String uuid = getPara("uuid");
			String fromlat = getPara("fromlat");
			String fromlng = getPara("fromlng");
			String tolat = getPara("tolat");
			String tolng = getPara("tolng");
			
			Orders _order = Orders.dao.findByCustomer(uuid, EnumStatus.WAITING.getCode(), EnumStatus.BEGIN.getCode(), EnumStatus.END.getCode());
			if(_order!=null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_5));
				return;
			}
			
			String orderNo = ToolString.returnOrderNo();
			
			Orders order = new Orders();
			order.setId(ToolUUID.uuid());
			order.setTime(time);
			order.setOrderNo(orderNo);
			order.setToloc(to);
			order.setFromloc(from);
			order.setFromlat(fromlat);
			order.setFromlng(fromlng);
			order.setTolat(tolat);
			order.setTolng(tolng);
			order.setContact(contact);
			order.setCustomerId(uuid);
			order.setStatus(EnumStatus.ORDERED.getCode());
			order.setCreated(new Date());
			order.save(); // 生成订单
			
			List<Driver> list = Driver.dao.findAll(EnumOnOff.ON.getCode()); // 加载所以在线司机
			
			String appIds = "";
			for(Driver d: list){
				Location loc = Location.dao.findById(d.getId());
				if(loc==null){
					renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_7));
					return;
				}
				double distance = ToolGPS.getDistance(Double.valueOf(fromlat), Double.valueOf(fromlng), Double.valueOf(loc.getLat()), Double.valueOf(loc.getLng()));
				if(distance<3000){ // 3000米范围内司机
					List<Dtoken> listToken = Dtoken.dao.findByUuid(d.getId());
					for(Dtoken dtoken: listToken){
						appIds += "\""+dtoken.getAppId()+"\",";
					}
				}
			}
			
			if(!"".equals(appIds)){
				appIds = "["+appIds.substring(0, appIds.length()-1)+"]"; // 组装gcm报文
			}
			
			Customer customer = Customer.dao.findById(order.getCustomerId());
			
			String data = "{\"data\": {\"type\" : \"order\", \"order\" : "+JsonKit.toJson(order)+", \"customer\" : "+JsonKit.toJson(customer)+" }, \"registration_ids\": "+ appIds +"}";
			logger.info(data);
			ToolHttp.doPostQueryCmd(_URL, data, _KEY, D_VALUE, "application/json");
			
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
			
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 客戶下单给司机
	@ActionKey("/platform/take")
	public void take() {
		try{
			String orderNo = getPara("orderNo");
			String driverId = getPara("driverId");
			
			Orders order = Orders.dao.findByOrderNo(orderNo);
			if(order == null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_6));
			}else if(order.getStatus().equals(EnumStatus.ORDERED.getCode())){
				Intention inten = Intention.dao.findByIdAndOrderNo(driverId, orderNo);
				Driver driver = Driver.dao.findById(driverId);
				if(inten!=null){
				order.setStatus(EnumStatus.WAITING.getCode());
				order.setState(EnumState.TAKED.getCode());
				order.setDriverId(driver.getId());
				order.setPrice(inten.getPrice());
				order.update();
				inten.delete(); // 清除意向
				}else{
					renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_9));
					return;
				}
				
				String appIds = gcmDriver(order.getDriverId());
				
				Customer customer = Customer.dao.findById(order.getCustomerId());
				
				String data = "{\"data\": {\"type\" : \"selected\", \"order\" : "+JsonKit.toJson(order)+", \"customer\" : "+JsonKit.toJson(customer)+",  \"driver\" : "+JsonKit.toJson(driver)+"}, \"registration_ids\": "+ appIds +"}";
				logger.info(data);
				System.out.println(data);
				ToolHttp.doPostQueryCmd(_URL, data, _KEY, D_VALUE, "application/json");
				
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
				
			}else{
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_4));
			}
			
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 订单列表
	@ActionKey("/platform/getOrders")
	public void getOrders() {
		try{
			String uuid = getPara("uuid");
			int pageSize = getParaToInt("pageSize");
			int pageNumber = getParaToInt("pageNumber");
			
			renderJson(ToolResponse.responseData2Json(ConstantError.REPLY_CODE_0, "orders", JsonKit.toJson(Orders.dao.paginateByCusomter(pageNumber, pageSize, uuid))));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 订单详情
	@ActionKey("/platform/getOrder")
	@Before(AppValidator.class)
	public void getOrder() {
		try{
			String orderNo = getPara("orderNo");
			Orders order = Orders.dao.findByOrderNo(orderNo);
			Driver driver = Driver.dao.findById(order.getDriverId());
			renderJson(ToolResponse.responseTokenAndData2Json(ConstantError.REPLY_CODE_0, "driver", JsonKit.toJson(driver), "order", JsonKit.toJson(order)));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 完成订单
	@ActionKey("/platform/close")
	public void close() {
		try{
			String orderNo = getPara("orderNo");
			
			Orders order = Orders.dao.findByOrderNo(orderNo);
			if(order == null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_6));
				return;
			} 
			order.setState(EnumState.CLOSED.getCode());
			order.setStatus(EnumStatus.CLOSED.getCode());
			order.update();
			
			String appIds = gcmDriver(order.getDriverId());
			
			Customer customer = Customer.dao.findById(order.getCustomerId());
			String data = "{\"data\": {\"type\" : \"close\", \"order\" : "+JsonKit.toJson(order)+", \"customer\" : "+JsonKit.toJson(customer)+" }, \"registration_ids\": "+ appIds +"}";
			logger.info(data);
			ToolHttp.doPostQueryCmd(_URL, data, _KEY, D_VALUE, "application/json");

			renderJson(ToolResponse.responseData2Json(ConstantError.REPLY_CODE_0, "order", JsonKit.toJson(order)));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 取消订单
	@ActionKey("/platform/cancel")
	public void cancel() {
		try{
			String orderNo = getPara("orderNo");
			Orders order = Orders.dao.findByOrderNo(orderNo);
			if(order == null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_6));
			}else if(order.getStatus().equals(EnumStatus.ORDERED.getCode())){
				order.setStatus(EnumStatus.CANCEL.getCode());
				order.update();

				String appIds = gcmDriver(order.getDriverId());
				
				Customer customer = Customer.dao.findById(order.getCustomerId());
				String data = "{\"data\": {\"type\" : \"cancel\", \"order\" : "+JsonKit.toJson(order)+", \"customer\" : "+JsonKit.toJson(customer)+" }, \"registration_ids\": "+ appIds +"}";
				logger.info(data);
				ToolHttp.doPostQueryCmd(_URL, data, _KEY, D_VALUE, "application/json");
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
			}else{
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_3));
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
}
