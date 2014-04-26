package util;

import java.util.Calendar;

public class Destaque {

	
	public static final int DESTAQUE_BANNER=1;
	public static final int DESTAQUE_PARTICULAR=2;
	public static final int DESTAQUE_REVENDA=3;
	public static final int DESTAQUE_INFERIOR=4;
	
	private long id;
	private int tipoDestaque;
	private long codVeiculo;
	private Calendar dataIni;
	private Calendar dataFim;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getTipoDestaque() {
		return tipoDestaque;
	}
	public void setTipoDestaque(int tipoDestaque) {
		this.tipoDestaque = tipoDestaque;
	}
	public long getCodVeiculo() {
		return codVeiculo;
	}
	public void setCodVeiculo(long codVeiculo) {
		this.codVeiculo = codVeiculo;
	}
	public Calendar getDataIni() {
		return dataIni;
	}
	public void setDataIni(Calendar dataIni) {
		this.dataIni = dataIni;
	}
	public Calendar getDataFim() {
		return dataFim;
	}
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
