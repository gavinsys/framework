package com.test.service.impl;

import com.test.service.IHello;

public class HelloImpl implements IHello {
	private String msg;
	public void setMsg(String msg){
		this.msg = msg;
	}
	public String sayHi() {
		return "Hello "+ msg;
	}

}
