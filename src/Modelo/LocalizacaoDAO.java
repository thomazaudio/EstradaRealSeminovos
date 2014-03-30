package Modelo;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Debug;
import util.Localizacao;
import util.Cidade;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocalizacaoDAO {
public void insert(Localizacao loc) {
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		sessao.save(loc);
		sessao.flush();
		sessao.close();
		
	}

public void insertOrUpdate(Localizacao loc){
	
	Session sessao = HibernateUtil.getSessaoV().openSession();
	sessao.saveOrUpdate(loc);
	sessao.flush();
	sessao.close();
}

public Localizacao getLocalizacao(long id){
	
	Session sessao =  HibernateUtil.getSessaoV().openSession();
	
	return (Localizacao) sessao.createCriteria(Localizacao.class).add(Restrictions.eq("id", id)).list().get(0);
	
	
}

	/**
	 * 
	 * @param user
	 */
	public void update(Localizacao loc) {
		
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		sessao.merge(loc);
		sessao.flush();
		sessao.close();
	}
	
	
	
	public String getNomeCidade(long cod){
		
		Connection con=null;
		Statement stm=null;
		ResultSet res=null;
		try{
			
			
		con = Banco.abreBanco();
		stm = con.createStatement();
		res	= stm.executeQuery("SELECT * FROM tb_cidades WHERE id="+cod);
			
			if(res.next())
			return res.getString("nome");
			else
			return "";	
			
			
		}catch(Exception e){
			
			Debug.gerar("Modelo", "LocalizacaoDAO","getNomeCidade", e.getMessage());
			
			return "";
		}
			
		
	}
	
	
		public String getNomeEstado(long cod){
		
		Connection con=null;
		Statement stm=null;
		ResultSet res=null;
		try{
			
			
		con = Banco.abreBanco();
		stm = con.createStatement();
		res	= stm.executeQuery("SELECT * FROM tb_cidades WHERE id="+cod);
			
			if(res.next())
			return res.getString("nome");
			else
			return "";	
			
			
		}catch(Exception e){
			
			Debug.gerar("Modelo", "LocalizacaoDAO","getNomeCidade", e.getMessage());
			
			return "";
		}
		
	}
	
	
	
	//Recupera todas as cidades a partir de um estado
	public ArrayList<Cidade> getCidades(int estado){
		
		Cidade cidade =null;
		ArrayList<Cidade> cidades = new ArrayList<Cidade>();
		
		try{
			
		Connection con = Banco.abreBanco();	
		Statement stm = con.createStatement();
		ResultSet res =  stm.executeQuery("SELECT * FROM tb_cidades WHERE estado="+estado);
		
		while(res.next()){
			
			cidade  = new Cidade();
			cidade.setCidade(res.getString("nome"));
			cidade.setId(res.getInt("id"));
		    
			cidades.add(cidade);
			
			
		}
			
			
		}catch(Exception e){
			
			Debug.gerar("Modelo","LocalizacaoDAO","getCidades", e.getMessage());
		}
		
		
		return cidades;
	}
	

	/**
	 * 
	 * @param user
	 */
	public void delete(Localizacao loc) {
		throw new UnsupportedOperationException();
	}
	
	
}
