package Modelo;


import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import util.Pergunta;

public class PerguntaDAO {

	
	//INSERE UMA NOVA PROPOSTA
	public void insert(Pergunta pergunta){
		
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		sessao.save(pergunta);
		sessao.flush();
		sessao.close();
		
		
	}
	
	
		public void delete(Pergunta pergunta){
		
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		sessao.delete(pergunta);
		sessao.flush();
		sessao.close();
		
		
	}
	
	//RECUPERA UMA PERGUNTA
	public Pergunta getPergunta(long id){
		
		
	Session sessao =  HibernateUtil.getSessaoV().openSession();
		
		return (Pergunta)  sessao.createCriteria(Pergunta.class).add(Restrictions.eq("id",id)).uniqueResult();
	}
	
	
	//ATUALIZA UMA PERGUNTA
	public void update(Pergunta pergunta){
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		sessao.merge(pergunta);
		sessao.flush();
		sessao.close();
		
		
	}
	
	//RECUPERA TODAS AS PERGUNTAS DE UM ANÚNCIO
	public ArrayList<Pergunta> getPerguntasAnuncio(long id_anuncio){
		
		Session sessao =  HibernateUtil.getSessaoV().openSession();
		
		return (ArrayList<Pergunta>) sessao.createCriteria(Pergunta.class).add(Restrictions.eq("id_anuncio",id_anuncio)).list();
		
	}
	
	//RECUPERA TODAS AS PERGUNTAS RECEBIDAS DE UM USUÁRIO
	public ArrayList<Pergunta> getPerguntasRecebidas(long id_usuario){
		
	Session sessao =  HibernateUtil.getSessaoV().openSession();
		
	
	return (ArrayList<Pergunta>) sessao.createCriteria(Pergunta.class).add(Restrictions.eq("id_destinatario",id_usuario)).list();
	
	}
	
}
