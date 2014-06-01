package util;

import java.util.ArrayList;
import java.util.Collection;

import Modelo.VeiculoDAO;

public class Moto extends Veiculo {

	private String cilindradas;
	private String versao;
	private Collection<Item> itens;
	
	
	
	
	
	
	

	public Collection<Item> getItens() {
		return itens;
	}

	public void setItens(Collection<Item> itens) {
		this.itens = itens;
	}

	public String getModelo() {
		
		
		return  new VeiculoDAO().getNomeModeloMoto(this.getCodModelo());
		
	}
    
	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(String cilindradas) {
		this.cilindradas = cilindradas;
	}
	
	//iTENS orGANIZADOS PARA BUSCA
    public ArrayList<Item> getItensFiltered(){
		

		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
	    new_itens.add(last_itens.get(i));	
				
		
		return new_itens;
		
	}

}