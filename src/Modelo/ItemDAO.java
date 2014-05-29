package Modelo;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.Anuncio;
import util.Debug;
import util.Item;

public class ItemDAO {

	
	
	 
	public boolean insert(Item it ) {
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		sessao.save(it);
		sessao.flush();
		sessao.close();
		return true;
	}
	
	public Collection<Item> getAllItens(){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		tx.commit();
		return sessao.createCriteria(Item.class).list();
		
		 
	   
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
	
	//Recupera os itens atrves de um vetor de ids(MOTOS)
	
		public Collection<Item> getItensMoto(long[] ids){
			
			
			Collection<Item> itens = new HashSet<Item>();
			Item item=null;
			
			
			try{
			
			Connection con =  Banco.abreBanco();	
			Statement stm = con.createStatement();
			ResultSet res;
			for(int i=0;i<ids.length;i++)
			{
				
			res = stm.executeQuery("SELECT * FROM item_moto WHERE ID_ITEM="+ids[i]);
			
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
