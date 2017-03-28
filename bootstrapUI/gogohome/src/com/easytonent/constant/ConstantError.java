package com.easytonent.constant;

public class ConstantError {
	public static final String CODE = "code" ;
	public static final String MESSAGE = "message" ;
	public static final String REPLY_CODE_1_ = "-1" ;
	public static final String REPLY_CODE_0 = "0" ;
	public static final String REPLY_CODE_1 = "1" ;
	public static final String REPLY_CODE_2 = "2" ;
	public static final String REPLY_CODE_3 = "3" ;
	
	public static final String REPLY_CODE_1_1 = "11" ;
	public static final String REPLY_CODE_1_2 = "12" ;
	public static final String REPLY_CODE_1_3 = "13" ;
	public static final String REPLY_CODE_1_4 = "14" ;
	public static final String REPLY_CODE_1_5 = "15" ;
	public static final String REPLY_CODE_1_6 = "16" ;
	public static final String REPLY_CODE_1_7 = "17" ;
	public static final String REPLY_CODE_1_8 = "18" ;
	public static final String REPLY_CODE_1_9 = "19" ;
	
	public static final String REPLY_CODE_2_1 = "21" ;
	public static final String REPLY_CODE_2_2 = "22" ;
	public static final String REPLY_CODE_2_3 = "23" ;
	public static final String REPLY_CODE_2_4 = "24" ;
	public static final String REPLY_CODE_2_5 = "25" ;
	public static final String REPLY_CODE_2_6 = "26" ;
	public static final String REPLY_CODE_2_7 = "27" ;
	
	public static final String REPLY_MSG_1_ = "系統異常" ; // 响应成功
	public static final String REPLY_MSG_0 = "響應成功" ; // 响应成功
	public static final String REPLY_MSG_1 = "非法請求" ; // 非法請求
	public static final String REPLY_MSG_2 = "報文錯誤" ; // 報文錯誤
	public static final String REPLY_MSG_3 = "禁止重複提交" ; // 報文錯誤
	
	public static final String REPLY_MSG_1_1 = "驗證簽名失敗" ; // 验证签名失败
	public static final String REPLY_MSG_1_2 = "短信發送失敗" ; // 短信发送失败
	public static final String REPLY_MSG_1_3 = "該號碼已經使用" ; // 号码已经使用
	public static final String REPLY_MSG_1_4 = "短信驗證碼不正確" ; // 短信验证码不正确
	public static final String REPLY_MSG_1_5 = "該號碼不存在" ; // 号码不存在
	public static final String REPLY_MSG_1_6 = "該司機不在線" ; // 司机不在线
	public static final String REPLY_MSG_1_7 = "該司機定位失敗" ; // 該司機定位失敗
	public static final String REPLY_MSG_1_8 = "該司機還未審核通過" ; // 該司機還未審核通過
	public static final String REPLY_MSG_1_9 = "該司機已經另外接單" ; // 該司機已經另外接單
	
	public static final String REPLY_MSG_2_1 = "該訂單還未開始行程" ; // 订单还未开始行程
	public static final String REPLY_MSG_2_2 = "該訂單不能投意向" ; // 订单不能投意向
	public static final String REPLY_MSG_2_3 = "該訂單不能取消" ; // 订单不能取消
	public static final String REPLY_MSG_2_4 = "該訂單不能下單" ; // 订单不能下单给司机
	public static final String REPLY_MSG_2_5 = "有未完成訂單" ; // 订单不能下单给司机
	public static final String REPLY_MSG_2_6 = "訂單不存在" ; // 訂單不存在
	public static final String REPLY_MSG_2_7 = "訂單已經投過意向" ; // 訂單不存在
	
	public static String replyMsg(String replyCode){
		int reply = Integer.valueOf(replyCode);
		switch (reply) {
			case -1:return REPLY_MSG_1_;
			case 0:return REPLY_MSG_0;
			case 1:return REPLY_MSG_1;
			case 2:return REPLY_MSG_2;
			case 3:return REPLY_MSG_3;
			case 11:return REPLY_MSG_1_1;
			case 12:return REPLY_MSG_1_2;
			case 13:return REPLY_MSG_1_3;
			case 14:return REPLY_MSG_1_4;
			case 15:return REPLY_MSG_1_5;
			case 16:return REPLY_MSG_1_6;
			case 17:return REPLY_MSG_1_7;
			case 18:return REPLY_MSG_1_8;
			case 19:return REPLY_MSG_1_9;
			case 21:return REPLY_MSG_2_1;
			case 22:return REPLY_MSG_2_2;
			case 23:return REPLY_MSG_2_3;
			case 24:return REPLY_MSG_2_4;
			case 25:return REPLY_MSG_2_5;
			case 26:return REPLY_MSG_2_6;
			case 27:return REPLY_MSG_2_7;
			default:
				break;
		}
		return REPLY_MSG_1_;	
	}
	
	/*public static final String REPLY_MSG_0 = "RESP_SUCCESS" ; // 响应成功
	
	public static final String REPLY_MSG_1_1 = "RESP_FAIL_SIGN" ; // 验证签名失败
	public static final String REPLY_MSG_1_2 = "RESP_FAIL_SMS" ; // 短信发送失败
	public static final String REPLY_MSG_1_3 = "RESP_FAIL_PHONE_EXIST" ; // 号码已经使用
	public static final String REPLY_MSG_1_4 = "RESP_FAIL_CODE" ; // 短信验证码不正确
	public static final String REPLY_MSG_1_5 = "RESP_FAIL_PHONE_NO_EXIST" ; // 号码不存在
	public static final String REPLY_MSG_1_6 = "RESP_FAIL_STATE_OFF" ; // 司机不在线
	
	public static final String REPLY_MSG_2_1 = "RESP_ORDER_NO_BEGIN" ; // 订单还未开始行程
	public static final String REPLY_MSG_2_2 = "RESP_ORDER_NO_ORDERED" ; // 订单不能投意向
	public static final String REPLY_MSG_2_3 = "RESP_ORDER_NO_CANCEL" ; // 订单不能取消
	public static final String REPLY_MSG_2_4 = "RESP_ORDER_NO_TAKED" ; // 订单不能下单给司机
*/}


