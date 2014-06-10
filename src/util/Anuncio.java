package util;


import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Anuncio {
	
	public static final int ANUNCIO_ATIVO=1;
	public static final int ANUNCIO_VENCIDO=2;
	
	private long id;
	private long idUsuario;
	private Calendar dataIni;
	private String status;
	private int ativo;
	private Contato contato;
	private Veiculo veiculo;
	private int quantVisualizacoes;
	private Localizacao localizacao;
	private Set<Pergunta> perguntas = new HashSet<Pergunta>(0);
	private String dataIniFormated="n/a";
	
	
	public String getDataIniFormated() {
		
		Calendar c = this.getDataIni();
		
	    dataIniFormated = String.format("%02d/%02d/%d - %02d:%02d",c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH)+1,c.get(Calendar.YEAR),c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE));
		
		return dataIniFormated;
	}
	
	
	public String getDataFimFormated(){
		
		
		
		
        Calendar c = Plano.getDataFimAnuncio(this.getDataIni(),this.getVeiculo().getPrioridade_anuncio());
		
	    return String.format("%02d/%02d/%d",c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(Calendar.YEAR),c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE));
		
		
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
	
	
	public String getTextoStatusAnuncio(){
		
		
		  if(isVencido())
		  return "Anúncio Vencido";	  
		
		 if(this.getVeiculo().getStatusPagamento()==Pagamento.CONFIRMADO)
			{
				
				if(this.getVeiculo().getStatusValidacao()==Pagamento.VALIDACAO_OK)
				return "ANÚNCIO PUBLICADO!";
				
				else 
				return "ANALISANDO DADOS DO ANÚNCIO";	
			}

			else
			return "AGUARDANDO PAGAMENTO";	
			
		}
	
	  public boolean isVencido(){
		  
		Calendar fim =   Plano.getDataFimAnuncio(this.getDataIni(),this.getVeiculo().getPrioridade_anuncio());
		
		if(Calendar.getInstance().after(fim))
			return true;
		else return false;
		  
	  }

}