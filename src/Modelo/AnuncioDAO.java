package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;


import org.hibernate.criterion.Restrictions;

import util.*;

public class AnuncioDAO {

	
	
	/**
	 * 
	 * @param anuncio
	 */
	public boolean insert(Anuncio anuncio) {
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		sessao.evict(Anuncio.class);
		sessao.save(anuncio);
		sessao.flush();
		sessao.close();
		return true;
	}

	/**
	 * 
	 * @param anuncio
	 */
	public boolean update(Anuncio anuncio) {
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
	
		sessao.merge(anuncio);
		sessao.flush();
		sessao.close();
		return true;
	}
	
	public void delet(ArrayList<Long> sem_int){
		
		try{
			
			Connection con = Banco.abreBanco();
			Statement stm = con.createStatement();
			
			for(int i=0;i<sem_int.size();i++)
			stm.executeUpdate("DELETE FROM  anuncio WHERE ID_ANUNCIO="+sem_int.get(i));
			
			
		}catch(Exception e){
			
			Debug.gerar("","AnuncioDAO","delet(long id_anuncio)",e.getMessage());
			
		}
		
	}
	
	
	//Recupera a lista de anuncio de um usuário
	@SuppressWarnings("unchecked")
	public ArrayList<Anuncio> getAnunciosForUser(long id_usuario){
		
		ArrayList<Anuncio> anuncios;
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
	   anuncios = (ArrayList<Anuncio>)sessao.createCriteria(Anuncio.class).add(Restrictions.eq("idUsuario",id_usuario)).list();
		
		   
	   
	   
	   
	   
	   return anuncios;
	}
	
public ArrayList<Anuncio> getAllAnuncios(){
		
		ArrayList<Anuncio> anuncios;
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
	   anuncios = (ArrayList<Anuncio>)sessao.createCriteria(Anuncio.class).list();
		
		   
	   
	   
	   
	   
	   return anuncios;
	}
	
	
	//Atualiza a quantidade de visualizações
	public void incrementVi(long id_anuncio){
		
		int quant_vi=0;
		
		try{
		
		Connection con = Banco.abreBanco();
		Statement stm = con.createStatement();
		ResultSet res = stm.executeQuery("SELECT * FROM anuncio WHERE ID_ANUNCIO="+id_anuncio);
		
		if(res.next())
		{
			
		quant_vi = res.getInt("QUANT_VISUALIZACOES"); 	
		quant_vi++;
		stm.executeUpdate("UPDATE anuncio SET QUANT_VISUALIZACOES="+quant_vi+" WHERE id_anuncio="+id_anuncio);
		}
			
		}catch(Exception e){
			
			Debug.gerar("Modelo", "AnuncioDAO","incrementVi", e.getMessage());
		}
		
	}

	/**
	 * 
	 * @param anuncio
	 */
	public void delete(Anuncio anuncio) {
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		sessao.delete(anuncio);
		sessao.flush();
		sessao.close(); 
		
		//Delete o veiculo relacionado
		new VeiculoDAO().delete(anuncio.getVeiculo());
		
		
		
	}

	/**
	 * 
	 * @param id_veiculo
	 * @param classe_veiculo
	 */
	public Anuncio getAnuncio(long id_veiculo) {
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		return (Anuncio)sessao.createCriteria(Anuncio.class).add(Restrictions.eq("veiculo.id",id_veiculo)).uniqueResult();
		
	}
	
	

}