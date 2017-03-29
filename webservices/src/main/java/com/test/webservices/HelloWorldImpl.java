package com.test.webservices;

import javax.jws.WebService;

@WebService(endpointInterface = "com.test.webservices.HelloWorld", serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}

}
