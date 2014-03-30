package Modelo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	
	  private static SessionFactory factory;
	  
	  
	  public static SessionFactory getSessaoV(){
		  
		  if(factory==null)
		  factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		  
		  return factory;  
		  
		  
		  
	  }
	  
	  public static SessionFactory reOpen(){
		  
         factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		  
		  return factory;  
		  
	  }
	 
}
