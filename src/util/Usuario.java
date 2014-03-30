package util;






public class Usuario {

	public static final int PESSOA =1;
	public static final int EMPRESA =2;
	
	private long id;
	private int status;//status de confirmação de usuario '1'-->confirmado; '0'-->não-confirmado
	private String nome;
	private String senha;
	private String senha2;
	private Localizacao localizacao;
	private Contato contato;
	private byte[] img_perfil;
	
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	
	public byte[] getImg_perfil() {
		return img_perfil;
	}
	public void setImg_perfil(byte[] img_perfil) {
		this.img_perfil = img_perfil;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	private Finan finan;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	public Finan getFinan() {
		return finan;
	}
	public void setFinan(Finan finan) {
		this.finan = finan;
	}
	

}