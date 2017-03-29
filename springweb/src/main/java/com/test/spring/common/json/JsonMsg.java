package com.test.spring.common.json;


/**
 * 用來與前臺傳輸的json對象
 * @author qi.lan
 *
 */
public class JsonMsg {

	public final static int success=1;
	
	public final static int fail=0;
	
	private String msg;
	
	private int status;
	
	private Object data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public JsonMsg(String msg, int status, Object data) {
		super();
		this.msg = msg;
		this.status = status;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonMsg(String msg, int status) {
		super();
		this.msg = msg;
		this.status = status;
	}
	
}
