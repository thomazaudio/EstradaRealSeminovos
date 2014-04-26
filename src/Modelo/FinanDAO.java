package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Debug;
import util.Finan;

public class FinanDAO {
	
	
	
	
	public void depositar(long user, double valor){
		
		Finan f = getFinan(user);
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		
		if(f==null)
		{
			
			System.out.println("novo");
			f =  new Finan();
			f.setIdUser(user);
			f.setSaldo(valor);
			sessao.save(f);
		}
		else
		{
		f.setSaldo(getSaldo(user)+valor);
		sessao.update(f);
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
