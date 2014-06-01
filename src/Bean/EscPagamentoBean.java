package Bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
public class EscPagamentoBean {

	private FacesContext contexto;
	
	//PARAMETRO NECESSÁRIO PARA ESCOLHA DA FORMA DE PAGAMENTO
	private long codPagamento;
	
	//TIPO DE TRANSAÇÃO
	private int tipo_transacao;
	
	public int getTipo_transacao() {
		
		tipo_transacao = Integer.parseInt(getParametros().get("tipo_transacao"));
		
		
		return tipo_transacao;
	}


	public void setTipo_transacao(int tipo_transacao) {
		this.tipo_transacao = tipo_transacao;
	}


	public long getCodPagamento() {
		
		//RECUPERA APARTIR DO REQUEST
		codPagamento = Long.parseLong(getParametros().get("cod_pagamento"));
		
		System.out.println("Código do pagamento recebido: "+codPagamento);
		return codPagamento;
	}


	public void setCodPagamento(long codPagamento) {
		this.codPagamento = codPagamento;
	}


	public EscPagamentoBean(){
		
		
		//Configura o contexto
		contexto = FacesContext.getCurrentInstance();
		
	}
	
	
    private Map<String,String> getParametros(){
		
		Map<String,String> params = contexto.getExternalContext().getRequestParameterMap();
		
		return params;
	}
	
	
}


