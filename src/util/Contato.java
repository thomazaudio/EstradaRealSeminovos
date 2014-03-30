package util;

public class Contato {
 
	private long id; 
	private String nome;
	private String cel;
	private String tel1;
	private String tel2;
	private String email;
	private int confirmado;
	
	public int getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(int confirmado) {
		this.confirmado = confirmado;
	}
	private int comp;
	
	public int getComp() {
		return comp;
	}
	public void setComp(int comp) {
		this.comp = comp;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}