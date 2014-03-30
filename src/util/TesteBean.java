package util;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="testeBean")
public class TesteBean {

	private String nome="Thomaz Reis Damasceno";

	public String getNome() {
		return "Thomaz";
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
