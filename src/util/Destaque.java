package util;

import java.io.IOException;
import java.util.Calendar;

import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import Modelo.ImgDAO;

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
	private byte[] imgBanner;
	
	
	
	//RECUPERA A IMAGEM DE CAPA NO BANNER(NO CASO DE DESTAQUE-BANNER)
	 public StreamedContent getcapaVeiculo() throws IOException {
	       
	        DefaultStreamedContent imgCapa;
	        
	        
	        try{
	        	
				imgCapa = new DefaultStreamedContent();
				imgCapa.setContentType("image/jpeg");
				imgCapa.setStream(new ImgDAO().getImgBanner(this.getId()).getImg());
	        	
	        }catch(Exception e){
	        	imgCapa = new DefaultStreamedContent();
	        	imgCapa.setStream(null);
	        	Debug.gerar("Bean","Destaque" , "Erro ao recuperar imagem do Banner",e.getMessage());
	        }
	        

	        
	        return imgCapa;
	    }
	
	public byte[] getImgBanner() {
		return imgBanner;
	}
	public void setImgBanner(byte[] img) {
		this.imgBanner = img;
	}
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
