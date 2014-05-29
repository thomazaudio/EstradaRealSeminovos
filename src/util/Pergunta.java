package util;

import java.util.Calendar;

public class Pergunta {

	//Constantes
	public static final int NAO_RESPONDIDA=0;
    public static final int RESPONDIDA =1;
	
    
    
    private long id;
	private long id_remetente;
	private long id_destinatario;
	private long id_anuncio;
	private long id_veiculo;
	private String pergunta;
	private String resposta;
	private Calendar dataPergunta;
	private Calendar dataResposta;
	private int status;
	
	
	
	public long getId_veiculo() {
		return id_veiculo;
	}
	public void setId_veiculo(long id_veiculo) {
		this.id_veiculo = id_veiculo;
	}


	
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_remetente() {
		return id_remetente;
	}
	public void setId_remetente(long id_rementente) {
		this.id_remetente = id_rementente;
	}
	public long getId_destinatario() {
		return id_destinatario;
	}
	public void setId_destinatario(long id_destinatario) {
		this.id_destinatario = id_destinatario;
	}
	public long getId_anuncio() {
		return id_anuncio;
	}
	public void setId_anuncio(long id_anuncio) {
		this.id_anuncio = id_anuncio;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public Calendar getDataPergunta() {
		return dataPergunta;
	}
	public void setDataPergunta(Calendar dataPergunta) {
		this.dataPergunta = dataPergunta;
	}
	public Calendar getDataResposta() {
		return dataResposta;
	}
	public void setDataResposta(Calendar dataResposta) {
		this.dataResposta = dataResposta;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNAO_RESPONDIDA() {
		return NAO_RESPONDIDA;
	}
	public int getRESPONDIDA() {
		return RESPONDIDA;
	}
	
	
	public String getDataPerguntaFormated(){
	    
		 Calendar c = this.getDataPergunta();
		
	    String  dataIniFormated = String.format("%2d/%2d/%d -  %2d:%2d",c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH)+1,c.get(Calendar.YEAR),c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE));
		
		return dataIniFormated;
	}
	 
	
}
