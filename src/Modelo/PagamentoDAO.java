package Modelo;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Pagamento;

public class PagamentoDAO {

	
	
	public void insert(Pagamento pagamento){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		sessao.save(pagamento);
		
		
	}
	
	
	//Aprova um pagamento
	public void aprovaPagamento(long codPagamento){
		
		Pagamento pag = getPagamento(codPagamento);
		pag.setStatus(Pagamento.CONFIRMADO);
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		sessao.update(pag);
		
	}
	
	//Recupera um pagamento
	public Pagamento getPagamento(long id){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		return (Pagamento) sessao.createCriteria(Pagamento.class).add(Restrictions.eq("cod",id)).uniqueResult();
		
	}
	
	
	
	//RECUPERA TODOS OS PAGAMENTOS DE UM USUÁRIO
	public ArrayList<Pagamento> getAllPagamentos(long idUser){
		
		Session sessao = HibernateUtil.getSessaoV().openSession();
		
		return (ArrayList<Pagamento>) sessao.createCriteria(Pagamento.class).add(Restrictions.eq("idUser",idUser)).list();
		
		
	}
	
}
