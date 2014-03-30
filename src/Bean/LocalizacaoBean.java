package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import Modelo.LocalizacaoDAO;
import util.Cidade;
import util.Estado;

@ManagedBean(name="localizacaoBean")
public class LocalizacaoBean {

	private List<Estado> estados = new ArrayList<Estado>();
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	private String estado;
	private String cidade;
	
	public String getEstado() {
		
		Estado e = new Estado();
		
		e.setId(1);
		e.setEstado("Minas Gerais");
		
		estados.add(e);
		
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public List<Cidade> getCidades() {
		
		 
		cidades = new LocalizacaoDAO().getCidades(11);	
			
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	
	
	public void seleEstado(){
		
		System.out.println("Chegou aqui");
		 
		Cidade c = new Cidade();
		
		c.setCidade("Belo Vale");
		
		cidades.add(c);
		
	}
	
	
	
	
}
