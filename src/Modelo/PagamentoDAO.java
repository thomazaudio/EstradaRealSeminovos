package Modelo;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Debug;
import util.Pagamento;

public class PagamentoDAO {

	
	
	public void insert(Pagamento pagamento){
		
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		sessao.save(pagamento);
		
		
	}
	
	
	//Aprova um pagamento
	public void aprovaPagamento(long codPagamento){
		
		try{
			Connection con  = Banco.abreBanco();
			Statement stm =  con.createStatement();
			stm.executeUpdate("UPDATE pagamento set STATUS="+Pagamento.CONFIRMADO+" WHERE COD_PAGAMENTO="+codPagamento);
			
		}catch(Exception e){
			
			Debug.gerar("", "PagamentoDAO", "aprovaPagamento", e.getMessage());
		}
		
	}
	
	//Recupera um pagamento
	public Pagamento getPagamento(long id){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		return (Pagamento) sessao.createCriteria(Pagamento.class).add(Restrictions.eq("cod",id)).uniqueResult();
		
	}
	
	
	
	//RECUPERA TODOS OS PAGAMENTOS 
	public ArrayList<Pagamento> getAllPagamentos(){
			
			Session sessao = HibernateUtil.getSessaoV().openSession();
			
			return (ArrayList<Pagamento>) sessao.createCriteria(Pagamento.class).list();
			
			
}
	
	
	//RECUPERA TODOS OS PAGAMENTOS DE UM USUÁRIO
	public ArrayList<Pagamento> getAllPagamentos(long idUser){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		return (ArrayList<Pagamento>) sessao.createCriteria(Pagamento.class).add(Restrictions.eq("codUser",idUser)).list();
		
		
	}
	
}
