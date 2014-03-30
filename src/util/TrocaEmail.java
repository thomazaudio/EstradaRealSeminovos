package util;

public class TrocaEmail {

	private long idUsuario;//Id do usuário que está solicitando a troca
	private long id;//id da troca
	private long idContato;//id do contato a ser alterado
	private String emailtroca;//email a ser atualizado no contato
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdContato() {
		return idContato;
	}
	public void setIdContato(long idContato) {
		this.idContato = idContato;
	}
	public String getEmailtroca() {
		return emailtroca;
	}
	public void setEmailtroca(String emailtroca) {
		this.emailtroca = emailtroca;
	}
	
	
}
