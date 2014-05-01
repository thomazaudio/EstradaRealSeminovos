package util;

import Modelo.VeiculoDAO;

public class Carro extends Veiculo {

	private int quantPortas;
	private String motor;
	private int quantValvulas;
	private String versao;
	private String transmissao;
	
	//Recupera o modelo  do veiculo
	
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
	

}