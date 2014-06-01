package Modelo;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.Anuncio;
import util.Debug;
import util.Item;

public class ItemDAO {

	
	
	 
	public boolean insert(Item it ) {
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		sessao.save(it);
		tx.commit();
		sessao.flush();
		sessao.close();
		return true;
	}
	
	
	//RECUPERA TODOS OS ACESSÓRIOS DE CARRO
	
	public Collection<Item> getAllItensCarro(){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		tx.commit();
		
		Criteria cri = sessao.createCriteria(Item.class);
		
		cri.add(Restrictions.eq("tipoVeiculo",1));
		
		
		
		return cri.list();
		
		 
	   
	}
	
	//RECUPERA TODOS OS ACESSÓRIOS DE MOTO
	
	public Collection<Item> getAllItensMoto(){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		tx.commit();
	
       Criteria cri = sessao.createCriteria(Item.class);
		
		cri.add(Restrictions.eq("tipoVeiculo",2));
		
		
		
		return cri.list();
		
		 
	   
	}
	
	//Recupera os itens atrves de um vetor de ids
	
	public Collection<Item> getItens(long[] ids){
		
		
		Collection<Item> itens = new HashSet<Item>();
		Item item=null;
		
		
		try{
		
		Connection con =  Banco.abreBanco();	
		Statement stm = con.createStatement();
		ResultSet res;
		for(int i=0;i<ids.length;i++)
		{
			
		res = stm.executeQuery("SELECT * FROM item WHERE ID_ITEM="+ids[i]);
		
		if(res.next())
		{
			item = new Item();
			item.setId(ids[i]);
			item.setItem(res.getString("ITEM"));
			itens.add(item);
			
		}
		
		res.close();
		}
			
		}catch(Exception e){
			
			Debug.gerar("Modelo","ItemDAO","getItens[int ids[]]", e.getMessage());
		}
		
		return itens;
		
	}
	
	
	
	/**
	 * 
	 * @param anuncio
	 */
	public boolean update(Anuncio anuncio) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param anuncio
	 */
	public void delete(Anuncio anuncio) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_veiculo
	 * @param classe_veiculo
	 */
	public Anuncio getAnuncio(int id_veiculo, Class classe_veiculo) {
		throw new UnsupportedOperationException();
	}

}
