package util;

import Modelo.VeiculoDAO;

public class Moto extends Veiculo {

	private String cilindradas;
	private String versao;
	
	
	//Recupera o modelo  do veiculo
	
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

}