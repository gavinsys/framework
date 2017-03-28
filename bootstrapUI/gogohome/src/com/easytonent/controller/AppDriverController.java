package com.easytonent.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.easytonent.common.interceptor.GlobalActionInterceptor;
import com.easytonent.common.interceptor.GlobalAppInterceptor;
import com.easytonent.common.model.Ctoken;
import com.easytonent.common.model.Customer;
import com.easytonent.common.model.Driver;
import com.easytonent.common.model.Dtoken;
import com.easytonent.common.model.Intention;
import com.easytonent.common.model.Location;
import com.easytonent.common.model.Orders;
import com.easytonent.common.model.Routes;
import com.easytonent.constant.ConstantError;
import com.easytonent.enums.EnumOnOff;
import com.easytonent.enums.EnumPass;
import com.easytonent.enums.EnumState;
import com.easytonent.enums.EnumStatus;
import com.easytonent.tools.ToolGPS;
import com.easytonent.tools.ToolHttp;
import com.easytonent.tools.ToolRandoms;
import com.easytonent.tools.ToolResponse;
import com.easytonent.tools.ToolSMS;
import com.easytonent.tools.ToolSecurity;
import com.easytonent.tools.ToolUUID;
import com.easytonent.validator.AppValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.auth.AccessTokenBuilder;

@Before({ GlobalAppInterceptor.class, AppValidator.class })
@Clear(GlobalActionInterceptor.class) // 全局清除Action拦截器
public class AppDriverController extends AppBaseController {
	
	@ActionKey("/platform/getCode") // 获取验证码
	@Clear(GlobalAppInterceptor.class) // 清除App拦截器
	public void getCode() {
		try{
			String area = getPara("area"); // 区域 86,853
			String phone = getPara("phone"); // 手机号
			String type = getPara("type"); // 加密类型 MD5，SHA-256
			String sign = getPara("sign"); // 加密后的值
			
			String _sign = ToolSecurity.sign(type, phone+KEY); // 加密报文
			
			if (sign.equals(_sign)) { // 验证签名
				int code = ToolRandoms.number(999, 10000); // 生成4位验证码
				String resp = ToolSMS.sendMessage(area, phone, code); // 发送SMS
				if(resp.equals(ConstantError.REPLY_CODE_0)){
			        cache.put(phone, code+"", seconds, TimeUnit.SECONDS); // 缓存验证码  180s
					renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
				}else{
					renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_2)); // 返回验证码短信发送失败
				}
			}else{
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_1)); // 返回验证签名失败
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	@ActionKey("/platform/destory") // 司机注销
	public void destory() {
		try{
			String uuid = getPara("uuid"); // 司机唯一标示
			String deviceId = getPara("deviceId"); // 手机设备ID
			
			// 注销 清除会话凭证token
			Dtoken dtoken = Dtoken.dao.findByUuidAndDeviceId(uuid, deviceId);
			if(dtoken != null){
				dtoken.delete();
			}
			
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	@ActionKey("/platform/getDriver") // 获取单个司机信息
	public void getDriver() {
		try{
			String uuid = getPara("uuid"); // 司机uuid
			
			renderJson(ToolResponse.responseData2Json(ConstantError.REPLY_CODE_0, "driver", JsonKit.toJson(Driver.dao.findById(uuid))));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	@ActionKey("/platform/on") // 司机出车
	public void on() {
		try{
			Driver driver = Driver.dao.findById(getPara("uuid"));
			if(!EnumPass.ISPASS.getCode().equals(driver.getStatus())){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_8));
				return;
			}
			driver.setState(EnumOnOff.ON.getCode());
			driver.update();
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	@ActionKey("/platform/off") // 司机收车
	public void off() {
		try{
			Driver driver = Driver.dao.findById(getPara("uuid"));
			driver.setState(EnumOnOff.OFF.getCode());
			driver.update();
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 司机登陆
	@ActionKey("/platform/enter")
	@Clear(GlobalAppInterceptor.class)
	public void enter() {
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
				Driver driver = Driver.dao.findByPhone(phone);
				if(driver == null){ // 返回该手机未加盟
					driver = new Driver();
					driver.setId(ToolUUID.uuid());
					driver.setPhone(phone);
					driver.setStatus(EnumPass.NEW.getCode());
					driver.setState(EnumOnOff.OFF.getCode());
					driver.setCreated(new Date());
					driver.save();
					Dtoken _token = Dtoken.dao.builderAccessToken(driver.getId(), deviceId, appId, AccessTokenBuilder.getAccessToken(getRequest()));
					renderJson(ToolResponse.responseTokenAndData2Json(ConstantError.REPLY_CODE_0, "token", JsonKit.toJson(_token), "driver", JsonKit.toJson(driver))); // 加盟成功
				}else{
					Dtoken dtoken = Dtoken.dao.findByUuidAndDeviceId(driver.getId(), deviceId);
					if(dtoken!=null){
						dtoken.delete();
					}
					Dtoken _token = Dtoken.dao.builderAccessToken(driver.getId(), deviceId, appId, AccessTokenBuilder.getAccessToken(getRequest()));
					renderJson(ToolResponse.responseTokenAndData2Json(ConstantError.REPLY_CODE_0, "token", JsonKit.toJson(_token), "driver", JsonKit.toJson(driver))); // 加盟成功
				}
			}else{
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_4));
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 司机完善/修改资料
	@ActionKey("/platform/update")
	public void update() {
		try {
//			List<UploadFile> files = getFiles("upload", FILE_SIZE, ToolString.encoding);

			String name = getPara("name"); // 真实姓名
			String phone = getPara("phone"); // 手机号
			String idNumber = getPara("idNumber"); // 身份证号
//			String img = ToolUploadFile.filePathByParameterName(files, "img"); // 头像
//			String imgLicense = ToolUploadFile.filePathByParameterName(files, "imgLicense"); // 驾驶证照
//			String imgIdNumber = ToolUploadFile.filePathByParameterName(files, "imgIdNumber"); // 手持身份证照
			//Date dateLicense = ToolDateFormat.string2Date(getPara("dateLicense"), ToolDateFormat.pattern_1); // 领证日期

			Driver driver = Driver.dao.findById(getPara("uuid"));
//			driver.setImg(img);
			driver.setName(name);
			driver.setPhone(phone);
			driver.setIdNumber(idNumber);
//			driver.setImgLicense(imgLicense);
//			driver.setImgIdNumber(imgIdNumber);

			driver.update();
			
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
		} catch (Exception e) {
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}

	}

	// 查询订单地图
	@ActionKey("/platform/queryLoc")
	@Clear(GlobalAppInterceptor.class) // 清除App拦截器
	public void queryLoc() {
		try{
			String orderNo = getPara("orderNo");
			String uuid = getPara("uuid");
			String deviceId = getPara("deviceId");
			String accessToken = getPara("accessToken");
			
			Ctoken token = Ctoken.dao.findByUuidAndDeviceIdAndAccesstoken(uuid, deviceId, accessToken);
			if(token==null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1));
				return;
			}
			
			List<Routes> list = Routes.dao.findByOrderNo(orderNo);
			
			renderJson(ToolResponse.responseData2Json(ConstantError.REPLY_CODE_0, "routes", JsonKit.toJson(list)));
		} catch (Exception e) {
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 画地图
	@ActionKey("/platform/uploadLoc")
	public void uploadLoc() {
		try{
			String loc = getPara("locs");
			String orderNo = getPara("orderNo");
			Orders order = Orders.dao.findByOrderNo(orderNo);
			if(order==null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_6));
			}else if(order.getState().equals(EnumState.BEGIN.getCode())){ // 司机在线才记录位置
				Routes routes = new Routes();
				routes.setId(ToolUUID.uuid());
				routes.setOrderNo(orderNo);
				routes.setLoc(loc);
				routes.setCreated(new Date());
				routes.save();
				
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
			}else{
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_1));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 实时定位
	@ActionKey("/platform/synclocation")
	public void synclocation() {
		try{
			String driverId = getPara("driverId");
			Location loc = Location.dao.findById(driverId);
			
			renderJson(ToolResponse.responseData2Json(ConstantError.REPLY_CODE_0, "location", JsonKit.toJson(loc)));
		} catch (Exception e) {
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 实时定位
	@ActionKey("/platform/location")
	public void location() {
		try{
			String lat = getPara("lat");
			String lng = getPara("lng");
			String uuid = getPara("uuid");
			Driver driver = Driver.dao.findById(uuid);
			if(driver!=null&&driver.getState().equals(EnumOnOff.ON.getCode())){ // 司机在线才记录位置
				Location loc = Location.dao.findById(uuid);
				if(loc == null){
					loc = new Location();
					loc.setDriverId(uuid);
					loc.setLat(lat);
					loc.setLng(lng);
					loc.save();
				}else{
					loc.setLat(lat);
					loc.setLng(lng);
					loc.update();
				}
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
			}else{
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_6));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 我有意向
	@ActionKey("/platform/get")
	public void get() {
		try{
			String orderNo = getPara("orderNo");
			BigDecimal price = new BigDecimal(getPara("price"));
			String time = getPara("time");
			String uuid = getPara("uuid");
			Orders order = Orders.dao.findByOrderNo(orderNo);
			if(order == null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_6));
			}else if(order.getStatus().equals(EnumStatus.ORDERED.getCode())){
				Intention inten = Intention.dao.findByIdAndOrderNo(uuid, orderNo);
				if(inten!=null){
					renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_7));
					return;
				}
				Intention intention = new Intention();
				intention.setId(uuid);// just one
				intention.setOrderNo(orderNo);
				intention.setFromloc(order.getFromloc());
				intention.setToloc(order.getToloc());
				intention.setPrice(price);
				intention.setTime(time);
				intention.setState(EnumState.QP.getCode());
				Location loc = Location.dao.findById(uuid); // loc null exception
				if(loc==null){
					renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_7));
					return;
				}
				double distance = ToolGPS.getDistance(Double.valueOf(order.getFromlat()), Double.valueOf(order.getFromlng()), Double.valueOf(loc.getLat()), Double.valueOf(loc.getLng())); // loc null exception
				intention.setDistance(distance);
				intention.save();
				
				String appIds = this.gcmCustomer(order.getCustomerId());
				
				Driver driver = Driver.dao.findById(uuid);
				Location location = Location.dao.findById(uuid);
				String data = "{\"data\":{\"type\":\"intention\", \"intention\":"+JsonKit.toJson(intention)+", \"driver\":"+JsonKit.toJson(driver)+", \"location\":"+ JsonKit.toJson(location) +" }, \"registration_ids\":"+ appIds +"}";
				logger.info(data);
				ToolHttp.doPostQueryCmd(_URL, data, _KEY, C_VALUE, "application/json");
				
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
			}else{
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_2));
			}
			
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 接到客人
	@ActionKey("/platform/action")
	public void action() {
		try{
			String orderNo = getPara("orderNo");
			
			Orders order = Orders.dao.findByOrderNo(orderNo);
			order.setStatus(EnumStatus.BEGIN.getCode());
			order.setState(EnumState.BEGIN.getCode());
			order.update();
			
			String appIds = this.gcmCustomer(order.getCustomerId());
			
			Driver driver = Driver.dao.findById(order.getDriverId());
			String data = "{\"data\": {\"type\" : \"begin\", \"order\" : "+JsonKit.toJson(order)+", \"driver\" : "+JsonKit.toJson(driver)+" }, \"registration_ids\": "+ appIds +"}";
			logger.info(data);
			ToolHttp.doPostQueryCmd(_URL, data, _KEY, C_VALUE, "application/json");
			
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 送客到家
	@ActionKey("/platform/finish")
	public void finish() {
		try{
			String orderNo = getPara("orderNo");
			
			Orders order = Orders.dao.findByOrderNo(orderNo);
			order.setStatus(EnumStatus.END.getCode());
			order.setState(EnumState.END.getCode());
			order.update();
	
			String appIds = this.gcmCustomer(order.getCustomerId());
			
			Driver driver = Driver.dao.findById(order.getDriverId());
			String data = "{\"data\": {\"type\" : \"end\", \"order\" : "+JsonKit.toJson(order)+", \"driver\" : "+JsonKit.toJson(driver)+" }, \"registration_ids\": "+ appIds +"}";
			logger.info(data);
			ToolHttp.doPostQueryCmd(_URL, data, _KEY, C_VALUE, "application/json");
			
			renderJson(ToolResponse.responseData2Json(ConstantError.REPLY_CODE_0, "order", JsonKit.toJson(order)));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 订单列表
	@ActionKey("/platform/getLists")
	public void getLists() {
		try{
			String uuid = getPara("uuid");
			int pageSize = getParaToInt("pageSize");
			int pageNumber = getParaToInt("pageNumber");
			
			renderJson(ToolResponse.responseData2Json(ConstantError.REPLY_CODE_0, "orders", JsonKit.toJson(Orders.dao.paginateByDriver(pageNumber, pageSize, uuid))));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}

	// 订单详情
	@ActionKey("/platform/getList")
	public void getList() {
		try{
			String orderNo = getPara("orderNo");
			Orders order = Orders.dao.findByOrderNo(orderNo);
			Customer customer = Customer.dao.findById(order.getCustomerId());
			renderJson(ToolResponse.responseTokenAndData2Json(ConstantError.REPLY_CODE_0, "customer", JsonKit.toJson(customer), "order", JsonKit.toJson(order)));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 完成订单
	@ActionKey("/platform/over")
	public void over() {
		try{
			String orderNo = getPara("orderNo");
			
			Orders order = Orders.dao.findByOrderNo(orderNo);
			order.setState(EnumState.CLOSED.getCode());
			order.update();
			
			String appIds = this.gcmCustomer(order.getCustomerId());
			
			Driver driver = Driver.dao.findById(order.getDriverId());
			String data = "{\"data\": {\"type\" : \"over\", \"order\" : "+JsonKit.toJson(order)+", \"driver\" : "+JsonKit.toJson(driver)+" }, \"registration_ids\": "+ appIds +"}";
			logger.info(data);
			ToolHttp.doPostQueryCmd(_URL, data, _KEY, C_VALUE, "application/json");
			
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_0));
		}catch(Exception e){
			logger.error(e.getMessage(), e); // log4j 记录日志
			renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_1_)); // 返回 -1：系统异常
		}
	}
	
	// 取消订单
	@ActionKey("/platform/remove")
	public void remove() {
		try{
			String orderNo = getPara("orderNo");
			Orders order = Orders.dao.findByOrderNo(orderNo);
			if(order==null){
				renderJson(ToolResponse.responseCode2Json(ConstantError.REPLY_CODE_2_6));
			}else if(order.getState().equals(EnumState.QP.getCode())){
				order.setState(EnumState.CANCEL.getCode());
				order.update();
				
				String appIds = this.gcmCustomer(order.getCustomerId());
				
				Driver driver = Driver.dao.findById(order.getDriverId());
				String data = "{\"data\": {\"type\" : \"remove\", \"order\" : "+JsonKit.toJson(order)+", \"driver\" : "+JsonKit.toJson(driver)+" }, \"registration_ids\": "+ appIds +"}";
				logger.info(data);
				ToolHttp.doPostQueryCmd(_URL, data, _KEY, C_VALUE, "application/json");
				
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
