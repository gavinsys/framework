package ejbdemo;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Client {
	public static void main(String[] args) throws Exception {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");// 让JNDI
																					// API知道是由谁来管理我们用来查找JNDI
																					// 名字的命名空间的。
		final Context context = new InitialContext(jndiProperties);
		// appName 和 moduleName分别就打包的格式而定
		// 如果是.ear就是appName,其它的是moduleName(.jar,.war)
		final String appName = "";
		final String moduleName = "ejbdemo";
		final String distinctName = "";
		// 实现类名
		final String beanName = HelloWorldBean.class.getSimpleName();
		System.out.println(beanName);
		// 接口类名
		final String viewClassName = HelloWorldBeanRemote.class.getName();
		System.out.println(viewClassName);
		String jndi = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;
		System.out.println(jndi);
		HelloWorldBeanRemote db = (HelloWorldBeanRemote) context.lookup(jndi);
		System.out.println(db.sayHello());
	}
}
