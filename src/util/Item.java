package util;

public class Item {

	public static final int TIPO_CONFORTO = 1;
	public static final int TIPO_DESEMPENHO = 2;
	public static final int TIPO_DESIGNER = 3;
	public static final int TIPO_SEGURANCA = 4;
	
	private long id;
	private int tipo;
	private String item;
	private int tipoVeiculo;
	
	public int getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(int tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	
}
