package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.*;

public class UsuarioDAO {

	private Connection con;
	private Statement stm;
	/**
	 * 
	 * @param user
	 */
	public boolean insert(Usuario user) {
		
		try{
		Session sessao = HibernateUtil.getSessaoV().openSession();
		sessao.save(user);
		sessao.flush();
		sessao.close();
		return true;
		}catch(Exception e){
			
			Debug.gerar("Modelo","UsuarioDAO","insert", e.getMessage());
			return false;
		}
		
	}

	/**
	 * 
	 * @param user
	 */
	public void update(Usuario user) {
		
		
		

		try{
		Session sessao = HibernateUtil.getSessaoV().openSession();
		sessao.merge(user);
		
		sessao.flush();
		sessao.close();
	
		
		}catch(Exception e){
			
			Debug.gerar("Modelo","UsuarioDAO","update", e.getMessage());
			
		}
	}
	
	
	//Recupera o tipo de usuário
	public int getTipoUser(long id){
		
		try{
			
		  Connection con = Banco.abreBanco();
		  Statement stm   = con.createStatement();
		  ResultSet res = stm.executeQuery("SELECT * FROM usuario WHERE ID_USUARIO="+id);
		  
		  if(res.next())
		  {
		  
		  if(res.getString("TIPO_USUARIO").equals("PESSOA_FISICA"))
		  return Usuario.PESSOA;	
		  else return Usuario.EMPRESA;
		  }
		  else return 0;
			
		}catch(Exception e){
			
			Debug.gerar("", "UsuarioDAO", "getTipoUser", e.getMessage());
			return 0;
		}
	}
	

	/**
	 * 
	 * @param user
	 */
	public void delete(Usuario user) {
		throw new UnsupportedOperationException();
	}
	
	//Confirmação de usuário
	public boolean confirmaUsuario(long id_user){
		
		try{
		
		con =  Banco.abreBanco();
		stm =  con.createStatement();
		stm.executeUpdate("UPDATE  USUARIO set status=1 WHERE ID_USUARIO="+id_user);
		stm.close();
		
		return true;	
		}catch(Exception e){
			
			Debug.gerar("Modelo", "UsuarioDAO","confirmarUsuario", e.getMessage());
			return false;
		}
		
		
	}
	
	//Valida a exisitencia de uma chave já cadastrada no banco de dados
	public boolean existeChave(Class classe,String nome_chave ,String chave){
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		
		if( sessao.createCriteria(classe).add(Restrictions.eq(nome_chave, chave)).uniqueResult()==null )
		return false;
		else
		return true;
		
		
	}
	
	public Usuario getUser(long id_user){
		
		try{
		 
		Usuario user;	
			
		 Session sessao = HibernateUtil.getSessaoV().openSession();
		 user = (Usuario) sessao.createCriteria(Usuario.class).add(Restrictions.eq("id",id_user)).uniqueResult();
		
		 sessao.flush();
		 sessao.close();
		 return user;
		}catch(Exception e){
			Debug.gerar("Modelo","UsuarioDAO","getUser", e.getMessage());
			return null;
		}
	}
	
	
	
	
	//LOGIN
    public Object logar(String email, String senha) {
		
    	Contato con;
    	 
    	//Recupera o contato atraves do email
    	con =  new ContatoDAO().getContato(email);
    	
        //recupera  usuário ao qual  o email pertence 
    	try{
			
		Session sessao =  HibernateUtil.getSessaoV().openSession();	
		Criteria cri =  sessao.createCriteria(Usuario.class);
		cri.add(Restrictions.eq("contato.id",con.getId()));
		cri.add(Restrictions.eq("senha", senha));
		return(Object) cri.uniqueResult();
		
			
		}catch(Exception e){
			
			Debug.gerar("Modelo","PessoaDAO","logar", e.getMessage());
			return null;
		}
		
		
	}
	
}