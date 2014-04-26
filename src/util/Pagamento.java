package util;

import java.util.Calendar;

public class Pagamento {

	public static final int  PAGAR_ANUNCIO=1;
	public static final int ADICIONAR_CREDITO =2;
	public static final int PAGAMENTO_BOLETO=3;
	public static final int DEBITAR_DO_SALDO=4;
	
	
	//Processos
	//Validacao dos dados
	public static final int VALIDACAO_EM_ANALISE=0;
	public static final int VALIDACAO_OK=1;
	
	
	//Pagamento
	public static final int CONFIRMADO=1;
	public static final int AGUARDANDO_APROVACAO=2;
	public static final int NAO_APROVADO=3;
	
	
	private long codUser;
	private int tipo;
	private Calendar data;
	private String descricao;
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	private double valor;
	private long cod;
	
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
	
	
}
