package Modelo;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.Comunicacao;
import util.Contato;
import util.Proposta;
import util.Usuario;

public class PropostaDAO {
	
	
	public void insert(Proposta p){
		
		Session sessao  = HibernateUtil.getSessaoV().openSession();
		Transaction tx = sessao.beginTransaction(); 
		sessao.save(p);
		tx.commit();
		
		
		//Recupera o usu�rio recebedor da proposta
		Usuario user =  new UsuarioDAO().getUser(p.getIdUser());
		
		System.out.println("Id do usu�rio: "+user.getId());
		
		Email email = new Email();
		
		Contato destinatario = new ContatoDAO().getContato(user.getContato().getId());
		
		System.out.println("Remetente: "+p.getEmailRemetente());
		
		System.out.println("Nome: "+p.getNome());
		
		
		
		//Envia um email para o recebedor da proposta
		email.enviaEmail(destinatario.getEmail(),user.getNome(),Comunicacao.TEXTO_PROPOSTA_RECEBIDA(p),Comunicacao.ASSUNTO_PROPOSTA_RECEBIDA);
		
		
		//Envia uma c�pia da proposta para o solicitante
		email.enviaEmail(p.getEmailRemetente(),p.getNome(),Comunicacao.TEXTO_PROPOSTA_ENVIADA(p),Comunicacao.ASSUNTO_PROPOSTA_ENVIADA);
		
		
		
		sessao.flush();
		sessao.close();
		
	}
	
	//PROPOSTAS DE UM USU�RIO
	public ArrayList<Proposta> getPropostas(long idUser){
		
		Session sessao  = HibernateUtil.getSessaoV().openSession();
		
		return (ArrayList<Proposta>) sessao.createCriteria(Proposta.class).add(Restrictions.eq("idUser",idUser)).list();
		
		
	}
	
	//PROPOSTAS DE UM VE�CULO
	public ArrayList<Proposta> getPropostasForVeiculo(long idVeiculo){
		
		
        Session sessao  = HibernateUtil.getSessaoV().openSession();
		
		return (ArrayList<Proposta>) sessao.createCriteria(Proposta.class).add(Restrictions.eq("idVeiculo",idVeiculo)).list();
	}
	
	
	

}
