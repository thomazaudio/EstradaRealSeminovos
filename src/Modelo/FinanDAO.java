package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.Debug;
import util.Finan;

public class FinanDAO {
	
	
	
	
	
	//DEBITAR UM VALOR DA CONTA
    public void debitar(long user, double valor){
		
		Finan f = getFinan(user);
		
		System.out.println("Valor a ser debitado: "+valor);
		
		
		
		if(f!=null)
		{
	   
	
	    try{
	    	
	    	Connection con = Banco.abreBanco();
	    	Statement stm = con.createStatement();
	    	stm.executeUpdate("UPDATE financeiro SET SALDO="+(f.getSaldo()-valor)+" WHERE ID_FINAN="+f.getId());
	    	
	    	stm.close();
	    }catch(Exception e){
	    	
	    	Debug.gerar("","FinanDAO", "debitar", e.getMessage());
	    	
	    }
	    
	    
		}
		
	
		
	}
	
	public void depositar(long user, double valor){
		
		Finan f = getFinan(user);
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		
		if(f==null)
		{
			
			System.out.println("novo");
			f =  new Finan();
			f.setIdUser(user);
			f.setSaldo(valor);
			sessao.save(f);
			tx.commit();
			
			sessao.flush();
			sessao.close();
		}
		else
		{
			
			
			 try{
			    	
			    	Connection con = Banco.abreBanco();
			    	Statement stm = con.createStatement();
			    	stm.executeUpdate("UPDATE financeiro SET SALDO="+(f.getSaldo()+valor)+" WHERE ID_USER="+user);
			    	
			    	stm.close();
			    	
			    }catch(Exception e){
			    	
			    	Debug.gerar("","FinanDAO", "depositar", e.getMessage());
			    	
			    }
			    
		
		
		}
		
		

		
		
	}
	
	
	public Finan getFinan(long user){
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		return (Finan)sessao.createCriteria(Finan.class).add(Restrictions.eq("idUser",user)).uniqueResult();
		
	}
	
	
	public void dexccpositar(double valor, long user){
		
		try{
			
			Connection con  = Banco.abreBanco();
			Statement stm =  con.createStatement();
			stm.executeUpdate("UPDATE financeiro SET saldo="+(getSaldo(user)+valor)+" WHERE ID_USER="+user);
			
			stm.close();
			
		}catch(Exception e){
			
			Debug.gerar("","FinanDAO", "depositar", e.getMessage());
			
		}
		
	}
	
	public double getSaldo(long user){
		
		
		double saldo= 0;
		
     try{
			
			Connection con  = Banco.abreBanco();
			Statement stm =  con.createStatement();
			ResultSet res = stm.executeQuery("SELECT saldo FROM financeiro WHERE ID_USER="+user);
			
			if(res.next())
			saldo = res.getDouble("saldo");
			
			res.close();
			stm.close();
			
			
		}catch(Exception e){
			
			Debug.gerar("", "FinanDAO","getSaldo", e.getMessage());
		}
     
     return saldo;
		
	}

}
