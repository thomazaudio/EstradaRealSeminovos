package util;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import Modelo.ImgDAO;

public class InfoDestaque {

	private long idDestaque;
	public long getIdDestaque() {
		return idDestaque;
	}
	
	
	public void setIdDestaque(long idDestaque) {
		this.idDestaque = idDestaque;
	}


	private long codVeiculo;
	private int anoFabricacao;
	private int anoModelo;
	private double preco;
	private String titulo;
	
	public long getCodVeiculo() {
		return codVeiculo;
	}
	public void setCodVeiculo(long codVeiculo) {
		this.codVeiculo = codVeiculo;
	}
	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public int getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
    
	
	
	
}
