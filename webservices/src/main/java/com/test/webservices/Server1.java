package com.test.webservices;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Server1 {
	public static void main(String[] args) {
		HelloWorldImpl implementor = new HelloWorldImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(HelloWorld.class);
		svrFactory.setAddress("http://localhost:9000/helloWorld");
		svrFactory.setServiceBean(implementor);
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.create();
	}
}
