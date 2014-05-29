package Modelo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.Contato;
import util.Debug;
import util.TrocaEmail;


public class ContatoDAO {

	
	public void insert(Contato contato){
		
		try{
		Session sessao = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		sessao.save(contato);
		tx.commit();
		sessao.flush();
		sessao.close();
		}catch(Exception e){
			
			Debug.gerar("Modelo","ContatoDAO","insert", e.getMessage());
		}
		
	}
	
	public void update(Contato contato){
		

		try{
		Session sessao = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		sessao.merge(contato);
		tx.commit();
		sessao.flush();
		sessao.close();
		}catch(Exception e){
			
			Debug.gerar("Modelo","ContatoDAO","update", e.getMessage());
		}
	}
	
	public void delete(Contato contato){
		
		
		System.out.println("Id do contato: "+contato.getId());

		try{
		Session sessao = HibernateUtil.getSessaoV().openSession();
		sessao.delete(contato);
		sessao.flush();
		sessao.close();
		}catch(Exception e){
			
			Debug.gerar("Modelo","ContatoDAO","delete", e.getMessage());
		}
	}
	
	
		public void saveOrUpdate(Contato c){
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		sessao.saveOrUpdate(c);
		sessao.flush();
		sessao.close();
		
		}
	//Recupera o  contato padr�o de determinado usu�rio
	
	public Contato getContato(long id){
		
		
		try{
			Session sessao = HibernateUtil.getSessaoV().openSession();
			
			
			return (Contato)sessao.createCriteria(Contato.class).add(Restrictions.eq("id",id)).uniqueResult();
			
			}catch(Exception e){
				
				Debug.gerar("Modelo","ContatoDAO","getContatos(long id)", e.getMessage());
				return null;
			}
	}
	
	
	
	//Confirma um contato 
	public void confirmaContato(long id){
		
		Contato con =  this.getContato(id);
		
		con.setConfirmado(1);
		
		this.update(con);
		
	}
	
	
	//Recupera o  contato informando o email
	public Contato getContato(String email){
			
			
			try{
				Session sessao = HibernateUtil.getSessaoV().openSession();
				
				
				return (Contato)sessao.createCriteria(Contato.class).add(Restrictions.eq("email",email)).uniqueResult();
				
				}catch(Exception e){
				
					Debug.gerar("Modelo","ContatoDAO","getContatos(String email)", e.getMessage());
					return null;
				
			 }
		}
	
	
	
	 
	
	
}
