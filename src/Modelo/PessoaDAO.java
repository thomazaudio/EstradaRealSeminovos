package Modelo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.*;

public class PessoaDAO extends UsuarioDAO {

	/**
	 * 
	 * @param cpf
	 * @param senha
	 */
	public Pessoa logar(String cpf, String senha) {
		
		try{
			
		Session sessao =  HibernateUtil.getSessaoV().openSession();	
		Criteria cri =  sessao.createCriteria(Pessoa.class);
		cri.add(Restrictions.eq("cpf",cpf));
		cri.add(Restrictions.eq("senha", senha));
		return(Pessoa) cri.uniqueResult();
		
			
		}catch(Exception e){
			
			Debug.gerar("Modelo","PessoaDAO","logar", e.getMessage());
			return null;
		}
		
		
	}
	
	
	

}