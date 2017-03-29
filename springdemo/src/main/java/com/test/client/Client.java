package com.test.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.service.IHello;

public class Client {
	public static void main(String[] args) {
        @SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        IHello hello = applicationContext.getBean(IHello.class);
        String sayHi = hello.sayHi();
        System.out.println(sayHi);
	}
}
