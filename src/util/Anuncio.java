package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Anuncio {
	

	//Processos
	//Validacao dos dados
	public static final int VALIDACAO_EM_ANALISE=0;
	public static final int VALIDACAO_OK=1;
    
	
	private long id;
	private long idUsuario;
	private Calendar dataIni;
	private String status;
	private int ativo;
	private Contato contato;
	private Calendar dataFim;
	private Veiculo veiculo;
	private int quantVisualizacoes;
	private Localizacao localizacao;
	private Set<Pergunta> perguntas = new HashSet<Pergunta>(0);
	private String dataIniFormated="n/a";
	
	
	public String getDataIniFormated() {
		
		Calendar c = this.getDataIni();
		
	    dataIniFormated = String.format("%d/%d/%d",c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH)+1,c.get(Calendar.YEAR));
		
		return dataIniFormated;
	}
	public void setDataIniFormated(String dataFormated) {
		this.dataIniFormated = dataFormated;
	}
	
	public Set<Pergunta> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(Set<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Calendar getDataIni() {
		return dataIni;
	}
	public void setDataIni(Calendar dataIni) {
		this.dataIni = dataIni;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public Calendar getDataFim() {
		return dataFim;
	}
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public int getQuantVisualizacoes() {
		return quantVisualizacoes;
	}
	public void setQuantVisualizacoes(int quantVisualizacoes) {
		this.quantVisualizacoes = quantVisualizacoes;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

}