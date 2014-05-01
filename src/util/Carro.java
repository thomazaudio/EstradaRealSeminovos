package util;

import java.util.ArrayList;
import java.util.Collection;

import Modelo.VeiculoDAO;

public class Carro extends Veiculo {

	private int quantPortas;
	private String motor;
	private int quantValvulas;
	private String versao;
	private String transmissao;
	private Collection<Item> itens;
	
	//Recupera o modelo  do veiculo
	
     public Collection<Item> getItens() {
		return itens;
	}




	public void setItens(Collection<Item> itens) {
		this.itens = itens;
	}




	public String getModelo() {
		
		
		return  new VeiculoDAO().getNomeModelo(this.getCodModelo());
		
	}
	
	
	
	
	public Carro(){
		
	}
	
	
	
	public Carro(int ano,String cor,String nome){
		
		this.setAnoFabricacao(ano);
		
		this.setCor(cor);
		this.setNome(nome);
	}
	
	public String getTransmissao() {
		return transmissao;
	}
	public void setTransmissao(String transmissao) {
		this.transmissao = transmissao;
	}
	public int getQuantPortas() {
		return quantPortas;
	}
	public void setQuantPortas(int quantPortas) {
		this.quantPortas = quantPortas;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public int getQuantValvulas() {
		return quantValvulas;
	}
	public void setQuantValvulas(int quantValvulas) {
		this.quantValvulas = quantValvulas;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	
	
public ArrayList<Item> getItensConforto(){
		
		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
		{
			if(last_itens.get(i).getTipo()==Item.TIPO_CONFORTO)
			new_itens.add(last_itens.get(i));	
				
		}
		return new_itens;
		
		
	}
	
	
	
	public ArrayList<Item> getItensDesempenho(){
		
		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
		{
			if(last_itens.get(i).getTipo()==Item.TIPO_DESEMPENHO)
			new_itens.add(last_itens.get(i));	
				
		}
		return new_itens;
	}
	
	public ArrayList<Item> getItensDesigner(){
		
		
		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
		{
			if(last_itens.get(i).getTipo()==Item.TIPO_DESIGNER)
			new_itens.add(last_itens.get(i));	
				
		}
		return new_itens;
	}
	
	public ArrayList<Item> getItensSeguranca(){
		
		
		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
		{
			if(last_itens.get(i).getTipo()==Item.TIPO_SEGURANCA)
			new_itens.add(last_itens.get(i));	
				
		}
		return new_itens;
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