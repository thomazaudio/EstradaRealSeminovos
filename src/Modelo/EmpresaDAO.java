package Modelo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.*;

public class EmpresaDAO extends UsuarioDAO {

	/**
	 * 
	 * @param cnpj
	 * @param senha
	 */
	public Empresa logar(String cnpj, String senha) {
		
		try{
			
			Session sessao =  HibernateUtil.getSessaoV().openSession();	
			Criteria cri =  sessao.createCriteria(Empresa.class);
			cri.add(Restrictions.eq("cnpj",cnpj));
			cri.add(Restrictions.eq("senha", senha));
			return(Empresa) cri.uniqueResult();
			
				
			}catch(Exception e){
				
				Debug.gerar("Modelo","EmpresaDAO","logar", e.getMessage());
				return null;
			}
			
	}

}