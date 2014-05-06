package util;

import java.util.Calendar;

import Modelo.PagamentoDAO;

public class Pagamento {

	//Tipos possiveis de pagamento (Transações)
	public static final int DEBITO_CREDITO=1;//PAGAMENTO DE AÚNCIO UTILIZANDO DINHEIRO EM CAIXA
	public static  final int CREDITO_DEBITO=2;//PAGAMENTO DE ANÚNCIO UTILIZANDO BOLETO OU PAG-SEGURO
	public static final  int CREDITO=3;//ADIÇÃO DE CRÉDITO NA CONTA DO USUÁRIO(DEPÓSITO)
	
	//Método de pagamento
	public static int PAGAMENTO_BOLETO=1;
	
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
		
		
		if(this.getStatus()!=Pagamento.CONFIRMADO){
		
		//MARCA O PAGAMENTO COMO APROVADO
		new PagamentoDAO().aprovaPagamento(this.getCod());
		
		
		//VERIFICAÇÃO DO TIPO 
		switch(this.getTipo()){
		
		case CREDITO:
			
		new Acao().executAcaoCredito(this.getCodUser(),this.getValor());
		
		break;
		
		case DEBITO_CREDITO: new Acao().executaAcaoDebitoCredito(this.getCodUser(),this.getIdVeiculo(),this.getValor());
		break;
		
		}
		
	  }
		
	}
	
	//TRANSAÇÕES EM FORMA DE TEXTO
	public static String getTextoTransacao(int tipo_transacao){
		
		String texto;
		
		switch(tipo_transacao){
		
		case CREDITO: texto = "Adição de crédito";
		break;
		
		case CREDITO_DEBITO: texto = "";
		break;
		
		case DEBITO_CREDITO: texto = "";
		
		default: return texto = "";
		
		}
		
		return texto;
	}
	
	
}
