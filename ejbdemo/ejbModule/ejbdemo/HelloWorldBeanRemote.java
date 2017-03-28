package ejbdemo;

import javax.ejb.Remote;

@Remote
public interface HelloWorldBeanRemote {
	public String sayHello();
}
