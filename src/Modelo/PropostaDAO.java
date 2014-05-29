package Modelo;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Comunicacao;
import util.Contato;
import util.Proposta;
import util.Usuario;

public class PropostaDAO {
	
	
	public void insert(Proposta p){
		
		Session sessao  = HibernateUtil.getSessaoV().openSession();
		sessao.save(p);
		
		
		//Recupera o usuário recebedor da proposta
		Usuario user =  new UsuarioDAO().getUser(p.getIdUser());
		
		System.out.println("Id do usuário: "+user.getId());
		
		Email email = new Email();
		
		Contato destinatario = new ContatoDAO().getContato(user.getContato().getId());
		
		System.out.println("Remetente: "+p.getEmailRemetente());
		
		System.out.println("Nome: "+p.getNome());
		
		
		
		//Envia um email para o recebedor da proposta
		//email.enviaEmail(p.getEmailRemetente(),user.getNome(),Comunicacao.TEXTO_PROPOSTA_RECEBIDA(user.getNome()),Comunicacao.ASSUNTO_PROPOSTA_RECEBIDA);
		
		
		//Envia uma cópia da proposta para o solicitante
		email.enviaEmail(p.getEmailRemetente(),p.getNome(),Comunicacao.TEXTO_PROPOSTA_ENVIADA(p.getNome()),Comunicacao.ASSUNTO_PROPOSTA_ENVIADA);
		
		
		
		sessao.flush();
		sessao.close();
		
	}
	
	//PROPOSTAS DE UM USUÁRIO
	public ArrayList<Proposta> getPropostas(long idUser){
		
		Session sessao  = HibernateUtil.getSessaoV().openSession();
		
		return (ArrayList<Proposta>) sessao.createCriteria(Proposta.class).add(Restrictions.eq("idUser",idUser)).list();
		
		
	}
	
	//PROPOSTAS DE UM VEÍCULO
	public ArrayList<Proposta> getPropostasForVeiculo(long idVeiculo){
		
		
        Session sessao  = HibernateUtil.getSessaoV().openSession();
		
		return (ArrayList<Proposta>) sessao.createCriteria(Proposta.class).add(Restrictions.eq("idVeiculo",idVeiculo)).list();
	}
	
	
	

}
