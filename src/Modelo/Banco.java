package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import util.Debug;

public class Banco {
  
	public static Connection con;
	
	
	public static Connection abreBanco(){
		
		if(con==null)
		{
		try{
			
		Class.forName("com.mysql.jdbc.Driver");
	    con =	DriverManager.getConnection("jdbc:mysql://localhost/estrada_real","root", "leghacy123");
		
		}catch(Exception e){
			Debug.gerar("Modelo", "Banco","abreBanco", e.getMessage());
		}
		}
			
		return con;	
		
	}
	
	
}
