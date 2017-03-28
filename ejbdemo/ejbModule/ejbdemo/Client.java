package ejbdemo;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Client {
	public static void main(String[] args) throws Exception {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");// ��JNDI
																					// API֪������˭������������������JNDI
																					// ���ֵ������ռ�ġ�
		final Context context = new InitialContext(jndiProperties);
		// appName �� moduleName�ֱ�ʹ���ĸ�ʽ����
		// �����.ear����appName,��������moduleName(.jar,.war)
		final String appName = "";
		final String moduleName = "ejbdemo";
		final String distinctName = "";
		// ʵ������
		final String beanName = HelloWorldBean.class.getSimpleName();
		System.out.println(beanName);
		// �ӿ�����
		final String viewClassName = HelloWorldBeanRemote.class.getName();
		System.out.println(viewClassName);
		String jndi = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;
		System.out.println(jndi);
		HelloWorldBeanRemote db = (HelloWorldBeanRemote) context.lookup(jndi);
		System.out.println(db.sayHello());
	}
}
