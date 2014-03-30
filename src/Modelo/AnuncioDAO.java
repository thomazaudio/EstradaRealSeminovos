package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	
	//Recupera a lista de anuncio de um usuário
	@SuppressWarnings("unchecked")
	public ArrayList<Anuncio> getAnunciosForUser(long id_usuario){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		return (ArrayList<Anuncio>)sessao.createCriteria(Anuncio.class).add(Restrictions.eq("idUsuario",id_usuario)).list();
		
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