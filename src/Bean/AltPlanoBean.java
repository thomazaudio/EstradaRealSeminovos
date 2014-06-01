package Bean;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;

import Modelo.FinanDAO;
import Modelo.PagamentoDAO;
import Modelo.UsuarioDAO;
import util.BoletoUtil;
import util.Debug;
import util.Pagamento;
import util.Plano;
import util.Transacao;
import util.Usuario;
import util.UsuarioUtil;


@ManagedBean
@SessionScoped
public class AltPlanoBean {
	
	private long codVeiculo;
	private int prioridade_anunio;//Nova prioridade escolhida

	
	public long getCodVeiculo(){
		
		return codVeiculo;
	}
	
	private long codPagamento;
	
	
	public long getCodPagamento() {
		return codPagamento;
	}


	public void setCodPagamento(long codPagamento) {
		this.codPagamento = codPagamento;
	}


	//Inicio da alteração
	public void alteraPlano(){
		
	Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
	codVeiculo= Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod_veiculo"));
        
	
		
		//Encaminha pra pagina de escolha do plano a ser alterado
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt_plano.jsf");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
			
		}
		
		
		
	
	
	 //Aós escolha do plano, escolha do pagamento
	 public void escPagamento(){
		
		//Recebe a prioridade que � de acordo com o plano escolhido
		prioridade_anunio = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prioridade_anuncio"));
		
		String page_pos;
		

		//Lança um novo pagamento no sistema
		Pagamento pag = new Pagamento();
		pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
		pag.setValor(Plano.getPrecoPlano(prioridade_anunio));
		pag.setPrioridade(prioridade_anunio);
		pag.setTipo(Transacao.ALT_PLANO);//Altera��o de plano
		pag.setCodUser(UsuarioBean.getUserSession().getId());
		pag.setIdVeiculo(this.getCodVeiculo());
		pag.setDescricao(Transacao.getTextoTransacao(Transacao.ALT_PLANO));
	    pag.setData(Calendar.getInstance());
		
		new PagamentoDAO().insert(pag);
    	
		
		if(prioridade_anunio==Plano.PRIORIDADE_GRATIS)
		{
		pag.aprovar();	
		page_pos="alt_sucesso.jsf";
		}

		
		else
		page_pos=new ContextoBean().getContextoInicial()+"/esc_pagamento.jsf?tipo_transacao=4&&cod_pagamento="+pag.getCod();	
			
		
		//Encaminha para a pagina de  escolha de pagamento
	    try {
	    	
			FacesContext.getCurrentInstance().getExternalContext().redirect(page_pos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	    
	
	      

}
