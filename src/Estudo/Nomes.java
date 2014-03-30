package Estudo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;







@ManagedBean(name="nomes")
@SessionScoped
public class Nomes {

	private List<String> nomes= new ArrayList<String>();
			
	private String nome="Thomaz";
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public void adicionar(){
		
		nomes.add(nome);
		
		System.out.println("Nome: "+nome);
	}
	
	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(ArrayList<String> nomes) {
		this.nomes = nomes;
	}
	
	
}
