package Modelo;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.Debug;
import util.Pagamento;

public class PagamentoDAO {

	
	
	public void insert(Pagamento pagamento){
		
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		sessao.save(pagamento);
		tx.commit();
		sessao.flush();
		sessao.close();
		
		
	}
	
	
	public void update(Pagamento pagamento){
		

		Session sessao = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		sessao.merge(pagamento);
		tx.commit();
		sessao.flush();sessao.close();
	}
	
	
	public void aprovaPagamento(long codPagamento){
		
		try{
			Connection con  = Banco.abreBanco();
			Statement stm =  con.createStatement();
			stm.executeUpdate("UPDATE pagamento set STATUS="+Pagamento.CONFIRMADO+" WHERE COD_PAGAMENTO="+codPagamento);
			stm.close();
			
		}catch(Exception e){
			
			Debug.gerar("", "PagamentoDAO", "aprovaPagamento", e.getMessage());
		}
		
	}
	
	//Recupera um pagamento
	public Pagamento getPagamento(long id){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		return (Pagamento) sessao.createCriteria(Pagamento.class).add(Restrictions.eq("cod",id)).uniqueResult();
		
	}
	
	
	//Recupera a ultima transação relacionada a um veículo
	public Pagamento getLastPagamentVeiculo(long id_veiculo){
		
		Pagamento pg = null;
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		ArrayList<Pagamento> pgs = (ArrayList<Pagamento>) sessao.createCriteria(Pagamento.class).add(Restrictions.eq("idVeiculo",id_veiculo)).list();
		
		if(pgs.size()>0)
		pg = pgs.get(0);	
		
		return pg;
	}
	
	
	//RECUPERA TODOS OS PAGAMENTOS 
	public ArrayList<Pagamento> getAllPagamentos(){
			
			Session sessao = HibernateUtil.getSessaoV().openSession();
			
			return (ArrayList<Pagamento>) sessao.createCriteria(Pagamento.class).list();
			
			
}
	
	
	//RECUPERA TODOS OS PAGAMENTOS DE UM USU�RIO
	public ArrayList<Pagamento> getAllPagamentos(long idUser){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		return (ArrayList<Pagamento>) sessao.createCriteria(Pagamento.class).add(Restrictions.eq("codUser",idUser)).list();
		
		
	}
	
}
