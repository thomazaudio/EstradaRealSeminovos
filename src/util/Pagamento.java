package util;

import java.util.Calendar;

import Modelo.PagamentoDAO;

public class Pagamento {
	
	
    
	
	//Método de pagamento(parametro= tipo_pagamento)
	public static final int PAGAMENTO_BOLETO=1;
	public static final int PAGAMENTO_CREDITO_CONTA=2;
	public static final int PAGAMENTO_PAG_SEGURO=3;
	
	//Processos
	//Validacao dos dados
	public static final int VALIDACAO_EM_ANALISE=0;
	public static final int VALIDACAO_OK=1;
	
	
	//Status Pagamento
	public static final int CONFIRMADO=1;
	public static final int AGUARDANDO_APROVACAO=2;
	public static final int NAO_APROVADO=3;
	
	private double valor;
	private long cod;
	private long codUser;
	private int tipo;
	private Calendar data;
	private String descricao;
	private int status;
	private long idVeiculo;
	private int prioridade;
	private int formaPagamento;
	
	
	public int getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public String getDataFormated(){
		
		if(this.getData()==null)
		this.setData(Calendar.getInstance());	
		
		return Util.getDataFormated(this.getData());
	}
	
	public long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public long getCod() {
		return cod;
	}
	public void setCod(long cod) {
		this.cod = cod;
	}
	public long getCodUser() {
		return codUser;
	}
	public void setCodUser(long codUser) {
		this.codUser = codUser;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	//MÉTODOS
	//REALIZA A APROVAÇÃO DO PAGAMENTO
	public void aprovar(){
		
		
		new Acao().executaAcaoPagamento(this);
		
	}
	
	
	
	
}
