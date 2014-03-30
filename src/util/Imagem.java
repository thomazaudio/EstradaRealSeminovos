package util;

import java.io.InputStream;

public class Imagem {

	private long id;
	private InputStream img;
	private long id_veiculo;
	private int capa =0;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public InputStream getImg() {
		return img;
	}
	public void setImg(InputStream img) {
		this.img = img;
	}
	public long getId_veiculo() {
		return id_veiculo;
	}
	public void setId_veiculo(long id_veiculo) {
		this.id_veiculo = id_veiculo;
	}
	public int isCapa() {
		return capa;
	}
	public void setCapa(int capa) {
		this.capa = capa;
	}
	

}