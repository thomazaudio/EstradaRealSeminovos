package util;

public class Localizacao {

	private long id;
	private int cod_estado;
	private int cod_cidade;
	
	public int getCod_estado() {
		return cod_estado;
	}
	public void setCod_estado(int cod_estado) {
		this.cod_estado = cod_estado;
	}
	public int getCod_cidade() {
		return cod_cidade;
	}
	public void setCod_cidade(int cod_cidade) {
		this.cod_cidade = cod_cidade;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}