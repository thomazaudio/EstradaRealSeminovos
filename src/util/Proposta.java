package util;

public class Proposta {
	
	
	private long id;
	private long idUser;
	private long idVeiculo;
	private String emailRemetente;
	private String telefoneRemetente;
	private String  msg;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	public String getEmailRemetente() {
		return emailRemetente;
	}
	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}
	public String getTelefoneRemetente() {
		return telefoneRemetente;
	}
	public void setTelefoneRemetente(String telefoneRemetente) {
		this.telefoneRemetente = telefoneRemetente;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	

}
