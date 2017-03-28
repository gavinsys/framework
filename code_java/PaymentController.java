package com.taifung.payment.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.taifung.payment.common.CommonUtils;
import com.taifung.payment.common.FormPoster;
import com.taifung.payment.common.SystemConfig;
import com.taifung.payment.model.CallbackModel;
import com.taifung.payment.model.CustomerModel;
import com.taifung.payment.model.PaymentModel;
import com.taifung.payment.model.ResponseModel;

@Controller
public class PaymentController {
	private static Logger logger = Logger.getLogger(PaymentController.class.getName());
	private Map<String , String> orderMap = new HashMap<String, String>();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index_() {
		return "index";
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment() {
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/payment_1", method = RequestMethod.GET)
	public String payment_1() {
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/payment_2", method = RequestMethod.GET)
	public String payment_2() {
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String payment(CustomerModel customerModel, BindingResult errors, Model model) {
		customerModel.setOrderNumber(UUID.randomUUID().toString().replace("-", ""));
		model.addAttribute("customerModel", customerModel);
		return "payment_1";
	}
	
	@RequestMapping(value = "/payment_1", method = RequestMethod.POST)
	public String payment_1(PaymentModel paymentModel, BindingResult errors, Model model) {
		model.addAttribute("paymentModel", paymentModel);
		return "payment_2";
	}
	@RequestMapping(value = "/payment_2", method = RequestMethod.POST)
	public String payment_2(PaymentModel paymentModel, BindingResult errors, Model model) {
		try{
			logger.info("Submit Parameters: " + paymentModel.toString());
			/*if(paymentModel.getBankNumber().equals("WEIXIN")){
				Integer amount = (int) (Integer.valueOf(paymentModel.getOrderAmount())*100*CurrencyEnum.convert(paymentModel.getOrderCurrency()).getRate());
				
				String signature = "appid="+SystemConfig.get("appid")+"&body=支付测试&mch_id="+SystemConfig.get("mch_id")+"&"
						+ "nonce_str="+paymentModel.getOrderNumber()+"&"
						+ "notify_url="+SystemConfig.get("notify_url")+"&"
						+ "out_trade_no="+paymentModel.getOrderNumber()+"&product_id="+paymentModel.getOrderNumber()+"&spbill_create_ip="+SystemConfig.get("IP")+"&"
						+ "total_fee="+amount+"&trade_type=NATIVE&key="+SystemConfig.get("apikey");
				logger.info("Before Encode: "+ signature);
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				String signatured = md5.encodePassword(signature, null).toUpperCase();
				logger.info("After  Encode: "+ signatured);
				String dataStr = "<xml>"+
									"<appid>"+SystemConfig.get("appid")+"</appid>"+
									"<body>支付测试</body>"+
									"<mch_id>"+SystemConfig.get("mch_id")+"</mch_id>"+
									"<nonce_str>"+paymentModel.getOrderNumber()+"</nonce_str>"+
									"<notify_url>"+SystemConfig.get("notify_url")+"</notify_url>"+
									"<out_trade_no>"+paymentModel.getOrderNumber()+"</out_trade_no>"+
									"<product_id>"+paymentModel.getOrderNumber()+"</product_id>"+
									"<spbill_create_ip>"+SystemConfig.get("IP")+"</spbill_create_ip>"+
									"<total_fee>"+amount+"</total_fee>"+
									"<trade_type>NATIVE</trade_type>"+
									"<sign>"+signatured+"</sign>"+
								"</xml>";
				logger.info("Request Parameters: " + dataStr);
				String returnData = FormPoster.post(SystemConfig.get("url2"), dataStr, "UTF-8");
				logger.info("Response Parameters: "+ returnData);
				ResponseModel responseModel  = new ResponseModel();
				if(CommonUtils.readElement(returnData, "return_code").equals("SUCCESS")){
					if(CommonUtils.readElement(returnData, "result_code").equals("SUCCESS")){
						responseModel.setRespCode(CommonUtils.readElement(returnData, "return_msg"));
						model.addAttribute("code_url", CommonUtils.readElement(returnData, "code_url"));
						model.addAttribute("out_trade_no", paymentModel.getOrderNumber());
					}else{
						responseModel.setRespCode(CommonUtils.readElement(returnData, "err_code"));
						responseModel.setRespMsg(CommonUtils.readElement(returnData, "err_code_des"));
					}
				}else{
					responseModel.setRespCode(CommonUtils.readElement(returnData, "return_code"));
					responseModel.setRespMsg(CommonUtils.readElement(returnData, "return_msg"));
				}
				model.addAttribute("responseModel", responseModel);
			}else if(paymentModel.getBankNumber().equals("ALIPAY")){
				ResponseModel responseModel  = new ResponseModel();
				String amount = String.valueOf(Integer.valueOf(paymentModel.getOrderAmount())*CurrencyEnum.convert(paymentModel.getOrderCurrency()).getRate());
				//把请求参数打包成数组
				AlipayTradePrecreateResponse res = ToAlipayQrTradePay.qrPay(paymentModel.getOrderNumber(), amount, "支付测试");
				//缺少验证签名
				if(res.isSuccess()){
					responseModel.setRespCode("OK");
					model.addAttribute("code_url", res.getQrCode());
					model.addAttribute("out_trade_no", res.getOutTradeNo());
				}else{
					responseModel.setRespCode(res.getCode());
					responseModel.setRespMsg(res.getMsg());
				}
				model.addAttribute("responseModel", responseModel);
			}else{*/
				String data = CommonUtils.signature(paymentModel, new String[]{"class"});
				String signature = data+SystemConfig.get("key");
				logger.info("Before Encode: "+ signature);
				String signatured = new ShaPasswordEncoder(256).encodePassword(signature, null);
				logger.info("After  Encode: "+ signatured);
				String dataStr = data+"signature="+signatured;
				logger.info("Request Parameters: " + dataStr);
				String returnData = FormPoster.post(SystemConfig.get("url"), dataStr, "ASCII");
				logger.info("Response Parameters: "+ returnData);
				ResponseModel responseModel = new Gson().fromJson(returnData, ResponseModel.class);
				if("00".equals(responseModel.getRespCode())){
					if("qr_code".equals(responseModel.getFormData().get("type"))){
						if("00".equals(responseModel.getFormData().get("respCode"))){
							responseModel.setRespCode("OK");
							model.addAttribute("code_url", responseModel.getFormData().get("code_url"));
							model.addAttribute("out_trade_no", responseModel.getPaymentId());
						}else{
							responseModel.setRespCode(responseModel.getFormData().get("respCode"));
							responseModel.setRespMsg(responseModel.getFormData().get("respMsg"));
						}
					}else{
						logger.info("Poster Parameters: \n"+ FormPoster.post(responseModel.getUrl(), responseModel.getFormData()));
					}
				}
				model.addAttribute("responseModel", responseModel);
//			}
			
			return "payment_3";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value = "/notify")
	public void notify(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		try{
			
			ServletInputStream in = request.getInputStream();
			InputStreamReader reader = new InputStreamReader(in,"UTF-8");
			int c;
			StringBuffer sb = new StringBuffer();
			while((c = reader.read()) != -1){
				sb.append((char) c);
			}
			String pay_result = sb.toString();
			logger.info("Notify Parameters: " + pay_result);
			Map<String, String> map = CommonUtils.readElementMap(pay_result);
			String signature = CommonUtils.signature(map, SystemConfig.get("apikey"));
			logger.info("Before Encode: "+ signature);
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String signatured = md5.encodePassword(signature, null).toUpperCase();
			logger.info("After  Encode: "+ signatured);
			String return_code = "";
			String return_msg = "";
			if(signatured.equals(map.get("sign"))){
				if("SUCCESS".equals(map.get("result_code"))){
					return_code = "SUCCESS";
					return_msg = "OK";
				}else{
					return_code = "FAIL";
					return_msg = map.get("err_code")+":"+map.get("err_code_des");
				}
			}else{
				return_code = "SIGNFAIL";
				return_msg = "签名失败";
			}
			
			orderMap.put(map.get("out_trade_no"), return_code);
			
			String return_data = "<xml><return_code><![CDATA["+return_code+"]]></return_code><return_msg><![CDATA["+return_msg+"]]></return_msg></xml>";
			logger.info("Response Parameters: " + return_data);
			OutputStreamWriter os = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			os.write(return_data);
			
			in.close();
			os.flush();
			os.close();
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value = "/notify2")
	public void notify2(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		try{
			PrintWriter out = response.getWriter();
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			
			System.out.println("out_trade_no:"+out_trade_no);
			System.out.println("trade_no:"+trade_no);
			System.out.println("trade_status:"+trade_status);
			if("TRADE_SUCCESS".equals(trade_status)){
				System.out.println("success");
				orderMap.put(out_trade_no, "SUCCESS");
				out.println("success");	//请不要修改或删除
			}else{
				orderMap.put(out_trade_no, "FAIL");
				System.out.println("fail");
				out.println("fail");
			}
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

//			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				
//				System.out.println("verify:success");

				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				
				//判断是否在商户网站中已经做过了这次通知返回的处理
					//如果没有做过处理，那么执行商户的业务程序
					//如果有做过处理，那么不执行商户的业务程序
//				orderMap.put(out_trade_no, "SUCCESS");
//				out.println("success");	//请不要修改或删除

				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				//////////////////////////////////////////////////////////////////////////////////////////
//			}else{//验证失败
				
//				orderMap.put(out_trade_no, "FAIL");
//				System.out.println("verify:fail");
//				out.println("fail");
//			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value = "/orderListener")
	public @ResponseBody String orderListener(String out_trade_no, HttpSession session) {
		String result_code = (String)orderMap.get(out_trade_no);
		if(result_code!=null){
			logger.info(result_code);
			orderMap.remove(out_trade_no);
		}
		return result_code;
	}
	
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public void callback(CallbackModel callbackModel, BindingResult errors, Model model,HttpServletResponse response) {
		try{
			logger.info("Callback Parameters: " + callbackModel.toString());
			String accessKey = callbackModel.getAccessKey();
			String data = CommonUtils.signature(callbackModel, new String[]{"class", "accessKey"});
			String signature = data+SystemConfig.get("key");
			logger.info("Before Encode: "+ signature);
			String signatured = new ShaPasswordEncoder(256).encodePassword(signature, null);
			logger.info("After  Encode: "+ signatured);
			if(accessKey.equals(signatured)){
				orderMap.put(callbackModel.getPaymentId(), "SUCCESS");
				logger.info(callbackModel.getRespCode() +":"+ callbackModel.getRespMsg());
			}else{
				orderMap.put(callbackModel.getPaymentId(), "FAIL");
				logger.info("验签失败");
			}
			responseSuccess(response);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	private void responseSuccess(HttpServletResponse response){
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("OK");
			if(writer != null){
				writer.close();
			}
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}
	}
	
	@RequestMapping(value = "/frontend")
	public String frontend(CallbackModel callbackModel, BindingResult errors, Model model) {
		try{
			logger.info("Frontend Parameters: " + callbackModel.toString());
			
			model.addAttribute("callbackModel", callbackModel);
			return "payment_4";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
