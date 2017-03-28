package test.hibernatedemo;

import java.util.Date;
import java.util.EnumSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

public class Client {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();   
        cfg.configure();//读取配置文件    
          
     // 最新用法
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
        
		// Session session = factory.openSession();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		User user = new User();
		user.setCreatedBy("admin");
		user.setCreatedDate(new Date());
		user.setUsername("张三");
		session.save(user);
		session.getTransaction().commit();
		// session.close();
	}
}
