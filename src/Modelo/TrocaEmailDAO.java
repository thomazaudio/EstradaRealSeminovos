package Modelo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Contato;
import util.Debug;
import util.TrocaEmail;

public class TrocaEmailDAO {

	
	

	  //LANÇA UM PEDIDO DE TROCA DE EMAIL PARA O CONTATO
	  public void insert(TrocaEmail troca){
		  
		  
		     
			
			Session sessao =  HibernateUtil.getSessaoV().openSession();
			sessao.save(troca);
			sessao.flush();
			sessao.close();
			
			//Envia o email para confirmação
			StringBuffer html = new StringBuffer();
			html.append("<p><a href=\"http://localhost:8080/Auto/ChangeEmail?cod_troca="+troca.getId()+"\" />Clique aqui</a> para confirmar o email.</p>");
			
			Email e = new Email(troca.getEmailtroca(),"",html,"Estrada Real - Alteração de email");
			e.sendHtmlEmail();
			
		  
	  }
	  
	  
	 //EXCLUI UM PEDIDO DE TROCA
	  public void delete(TrocaEmail troca){
		  
			
			Session sessao =  HibernateUtil.getSessaoV().openSession();
			sessao.delete(troca);
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
				
				
				//Deleta o pedido de troca
				delete(troca);
				
				}catch(Exception e){
				
					Debug.gerar("Modelo","TrocaEmailDAO","trocaEmail", e.getMessage());
					
				
			 }
		  
		  
	  }
}
