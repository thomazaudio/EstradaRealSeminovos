package Bean;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Modelo.PagamentoDAO;
import util.Debug;
import util.Pagamento;
import util.Transacao;
import util.Usuario;

@ManagedBean
@SessionScoped
public class AdicaoCreditoBean {


	private double valor;


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	//FORMA DE PAGAMENTO PARA ADIÇÃO DE CRÉDITO
	public void toEscPagamento(){

		Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		
		
		
		//Recupera o usuário na sessão
		Usuario user =  UsuarioBean.getUserSession();
		
		if(user==null)
		Debug.gerar("","AdicaoCreditoBean","toEscPagamento","A sessão do usuário expirou!");
			
		//Lança o pagamento no sistema
		Pagamento pg = new Pagamento();
		
		pg.setCodUser(user.getId());
		pg.setDescricao("Adicionar crédito na conta");
		pg.setData(Calendar.getInstance());
		pg.setIdVeiculo(0);
		pg.setTipo(Transacao.CREDITO);
		pg.setPrioridade(0);
		pg.setValor(valor);
		
		System.out.println("Valor do pagamento: "+valor);
		
		new PagamentoDAO().insert(pg);

				

		//Encaminha para a pagina de escolha de pagamento
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(new ContextoBean().getContextoInicial()+"/esc_pagamento.jsf?tipo_transacao=3&&cod_pagamento="+pg.getCod());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
