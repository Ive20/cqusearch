package cqu.reptile;

import java.sql.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cqu.hib.*;

public class Repdatadao {
	public boolean inurl(String url,String key)
	{
		Configuration config = new Configuration();  
        config.configure();  
             // 如果hibernate.cfg.xml中没有配置的某些属性，你可以通过config.setProperty(propertyName, value);来配置.  
        //如config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");  
        @SuppressWarnings("deprecation")
		SessionFactory factory = config.buildSessionFactory();  
        Session session = factory.openSession();  
                Transaction tran = session.beginTransaction();  
                session.close();
        		Url u=new Url();
        		u.setUrl(url);
        		u.setKeyword(key);
        session.save(u);  
               //session.persist(user);  
        tran.commit();  
		return false;	
	}

}