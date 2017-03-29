package jsf2demo.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="index")
public class IndexController {
	public String sayHello(){
		return "hello JSF!";
	}
}
