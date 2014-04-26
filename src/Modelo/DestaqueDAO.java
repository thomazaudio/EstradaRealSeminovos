package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Debug;
import util.Destaque;
import util.InfoDestaque;

public class DestaqueDAO {

	
	public void insert(Destaque d){
		
			Session sessao = HibernateUtil.getSessaoV().openSession();
			sessao.saveOrUpdate(d);
			sessao.flush();
			sessao.close();	
		
	}
	
	
	//COLOCA TODOS OS VEÍCULOS COMO DESTAQUE INFERIOR
	public void setAllDestaqueInferior(Calendar dataFim)
	{
		
		Destaque d;
		
		try{
			
			Connection con = Banco.abreBanco();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("Select ID_VEICULO from veiculo");
			
			while(res.next()){
				
				d =  new Destaque();
				
				d.setCodVeiculo(res.getLong("ID_VEICULO"));
				d.setDataIni(Calendar.getInstance());
				d.setDataFim(dataFim);
				d.setTipoDestaque(Destaque.DESTAQUE_INFERIOR);
				
				insert(d);
				
				
				
			}
			
			
		}catch(Exception e){
			
			
			Debug.gerar("", "DestaqueDAO","setAllDestaqueInferior", e.getMessage());
			
		}
		
	}
	
	//RECUPERA OS DESTAQUES A PARTIR DO TIPO
	public ArrayList<Destaque> getDestaques(int tipo){
		
		ArrayList<Destaque> destaques;
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		Criteria c =  sessao.createCriteria(Destaque.class);
		
		c.add(Restrictions.eq("tipoDestaque", tipo));
		
		destaques = (ArrayList<Destaque>) c.list();
		
		return destaques;
		
	}
	
	//RECUPERA AS INFORMAÇÕES DE CADA DESTAQUE
	public InfoDestaque getInforDestaque(long codVeiculo){
		
		InfoDestaque info = new InfoDestaque();
		
		info.setCodVeiculo(codVeiculo);
		
		Connection con=null;
		Statement stm=null;
		ResultSet res=null;
		try{
			
			con = Banco.abreBanco();
			stm =  con.createStatement();
			res =  stm.executeQuery("SELECT * FROM veiculo WHERE ID_VEICULO="+codVeiculo);
			
			if(res.next())
			{
				
				info.setTitulo(res.getString("TITULO"));
				info.setPreco(res.getDouble("PRECO"));
				info.setAnoFabricacao(res.getInt("ANO_FABRICACAO"));
				info.setAnoModelo(res.getInt("ANO_MODELO"));
				
			}
			
			
		}catch(Exception e){
			
			Debug.gerar("", "DestaqueDAO","getInfodestaque", e.getMessage());
		}
			
		
		
		return info;
		
	}
	
	
}
