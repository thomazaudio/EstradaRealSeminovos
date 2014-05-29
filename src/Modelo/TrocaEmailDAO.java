package Modelo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.Comunicacao;
import util.Contato;
import util.Debug;
import util.TrocaEmail;

public class TrocaEmailDAO {

	
	

	  //LAN�A UM PEDIDO DE TROCA DE EMAIL PARA O CONTATO
	  public void insert(TrocaEmail troca){
		  
		  
		     
			
			Session sessao =  HibernateUtil.getSessaoV().openSession();
			Transaction tx = sessao.beginTransaction(); 
			sessao.save(troca);
		    tx.commit();
			sessao.flush();
			sessao.close();
			
			//Envia o email para confirmação
			new Comunicacao().sendMensagemTrocaEmail(troca);
				
			
			
		  
	  }
	  
	  
	 //EXCLUI UM PEDIDO DE TROCA
	  public void delete(TrocaEmail troca){
		  
			
			Session sessao =  HibernateUtil.getSessaoV().openSession();
			Transaction tx = sessao.beginTransaction(); 
			sessao.delete(troca);
			tx.commit();
			sessao.flush();
			sessao.close();
		  
	  }
	  
	  //RECUPERA UM PEDIDO DE TROCA
	  public TrocaEmail getTroca(long id){
		  
		  TrocaEmail troca;
		  Session sessao =  HibernateUtil.getSessaoV().openSession();
		  Criteria c = sessao.createCriteria(TrocaEmail.class);
		  c.add(Restrictions.eq("id",id));
		  troca = (TrocaEmail)  c.uniqueResult();
		  
		  sessao.flush();
		  sessao.close();
		  
		  return troca;
		  
	  }
	
	
	
	 //TROCA DE EMAIL EM UM CONTATO
	  public void trocaEmail(long id_troca){
		  
		    TrocaEmail troca;//troca de email
		    Contato con;//Contato a ser alterado

			try{
				
				Session sessao = HibernateUtil.getSessaoV().openSession();
				
				//Rescupera a troca
				troca = (TrocaEmail)sessao.createCriteria(TrocaEmail.class).add(Restrictions.eq("id",id_troca)).uniqueResult();
				
				//Recupera o contato a ser alterado
				con = new ContatoDAO().getContato(troca.getIdContato());
				
				//Seta o email no contato
				con.setEmail(troca.getEmailtroca());
				
				
				//Atualiza o contato
				new ContatoDAO().update(con);
				
				//Marca o contato com confirmado
				new ContatoDAO().confirmaContato(troca.getIdContato());
				
				
				//Deleta o pedido de troca
				delete(troca);
				
				}catch(Exception e){
				
					Debug.gerar("Modelo","TrocaEmailDAO","trocaEmail", e.getMessage());
					
				
			 }
		  
		  
	  }
}
