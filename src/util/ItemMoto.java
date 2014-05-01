package util;

public class ItemMoto {

	
	public static final int TIPO_CONFORTO = 1;
	public static final int TIPO_DESEMPENHO = 2;
	public static final int TIPO_DESIGNER = 3;
	public static final int TIPO_SEGURANCA = 4;
	
	private long id;
	private int tipo;
	private String item;
	
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
