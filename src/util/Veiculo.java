package util;


import java.util.*;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import Modelo.ImgDAO;
import Modelo.VeiculoDAO;

public abstract class Veiculo {


	private long id;
	private String nome;
	private String titulo="n/a";
    private String cor;
	private int anoFabricacao;
	private int anoModelo;
	private Collection<Item> itens;
	private double preco;
	private int preco_negociavel;
	private boolean troca;
	private String placa;
	private String quilometragem;
	private String combustivel;
	private String observacao;
	private ArrayList<String> acessorios;
	private String condicaoTroca;
	private int status;
	private int prioridade_anuncio;
	private String modelo;
	private int cod_fabricante;
	private byte[] img;
	private int fumante;
	private String sexo_condutor;
	private int cod_estado;
	private int cod_cidade;
	private String descricao;
	private Contato contato;
	private String estadoConservacao;
	private int tipoVenda;
	private int codModelo;
	private DefaultStreamedContent imgCapa;
	private String classColor;
	
	
	
	
	
	
	
	public String getClassColor() {
		return classColor;
	}
	public void setClassColor(String classColor) {
		this.classColor = classColor;
	}

	private int statusValidacao;
	public int getStatusValidacao() {
		return statusValidacao;
	}
	public void setStatusValidacao(int statusValidacao) {
		this.statusValidacao = statusValidacao;
	}
	
	
	
	
	public int getStatusPagamento() {
		return statusPagamento;
	}
	public void setStatusPagamento(int statusPagamento) {
		this.statusPagamento = statusPagamento;
	}



	
	public String getStatusAnuncio(){
		
		
		
		//Verifica se os dados já foram validados
		if(this.statusPagamento==Pagamento.CONFIRMADO)
		{
			
			if(this.statusValidacao==Pagamento.VALIDACAO_OK)
			return "Anúncio publicado";
			
			else 
			return "Analisando dados do anúncio";	
		}

		else
		return "Aguardando pagamento";	
		
	}

	private int statusPagamento;
	
	public void setImgCapa(DefaultStreamedContent imgCapa) {
		this.imgCapa = imgCapa;
	}
	public int getCodModelo() {
		return codModelo;
	}
	public void setCodModelo(int codModelo) {
		this.codModelo = codModelo;
	}
	public int getTipoVenda() {
		return tipoVenda;
	}
	public void setTipoVenda(int tipoVenda) {
		this.tipoVenda = tipoVenda;
	}
	public int getPreco_negociavel() {
		return preco_negociavel;
	}
	public void setPreco_negociavel(int preco_negociavel) {
		this.preco_negociavel = preco_negociavel;
	}
	public String getEstadoConservacao() {
		return estadoConservacao;
	}
	public void setEstadoConservacao(String estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}
	public int getCod_fabricante() {
		return cod_fabricante;
	}
	public void setCod_fabricante(int cod_fabicante) {
		this.cod_fabricante = cod_fabicante;
	}
	
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
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
	public Collection<Item> getItens() {
		return itens;
	}
	public void setItens(Collection<Item> itens) {
		this.itens = itens;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public boolean isTroca() {
		return troca;
	}
	public void setTroca(boolean troca) {
		this.troca = troca;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(String quilometragem) {
		this.quilometragem = quilometragem;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	public ArrayList<Item> getItensFiltered(){
		

		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
	    new_itens.add(last_itens.get(i));	
				
		
		return new_itens;
		
	}
	
	
	
	public ArrayList<String> getAcessorios() {
		
		
		ArrayList<Item> n_itens =new ArrayList<>(this.getItens());
		
		System.out.println("Itens: ");
		
		for(int i=0;i<n_itens.size();i++)
		System.out.println(n_itens.get(i).getItem());
		
		return acessorios;
	}
	public void setAcessorios(ArrayList<String> acessorios) {
		this.acessorios = acessorios;
	}
	public String getCondicaoTroca() {
		return condicaoTroca;
	}
	public void setCondicaoTroca(String condicaoTroca) {
		this.condicaoTroca = condicaoTroca;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPrioridade_anuncio() {
		return prioridade_anuncio;
	}
	public void setPrioridade_anuncio(int prioridade_anuncio) {
		this.prioridade_anuncio = prioridade_anuncio;
	}
	
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public int getFumante() {
		return fumante;
	}
	public void setFumante(int fumante) {
		this.fumante = fumante;
	}
	public String getSexo_condutor() {
		return sexo_condutor;
	}
	public void setSexo_condutor(String sexo_condutor) {
		this.sexo_condutor = sexo_condutor;
	}
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getModelo() {
		
		
		modelo =  new VeiculoDAO().getNomeModelo(this.getCodModelo());
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
	public ArrayList<Item> getItensConforto(){
		
		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
		{
			if(last_itens.get(i).getTipo()==Item.TIPO_CONFORTO)
			new_itens.add(last_itens.get(i));	
				
		}
		return new_itens;
		
		
	}
	
	
	
	public ArrayList<Item> getItensDesempenho(){
		
		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
		{
			if(last_itens.get(i).getTipo()==Item.TIPO_DESEMPENHO)
			new_itens.add(last_itens.get(i));	
				
		}
		return new_itens;
	}
	
	public ArrayList<Item> getItensDesigner(){
		
		
		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
		{
			if(last_itens.get(i).getTipo()==Item.TIPO_DESIGNER)
			new_itens.add(last_itens.get(i));	
				
		}
		return new_itens;
	}
	
	public ArrayList<Item> getItensSeguranca(){
		
		
		ArrayList<Item> last_itens = new ArrayList<Item>(this.itens);
		ArrayList<Item> new_itens = new ArrayList<Item>();
		
		for(int i=0;i<last_itens.size();i++)
		{
			if(last_itens.get(i).getTipo()==Item.TIPO_SEGURANCA)
			new_itens.add(last_itens.get(i));	
				
		}
		return new_itens;
	}
	
	public String getTitulo() {
		
		
VeiculoDAO v = new VeiculoDAO();
		
		
		if(v.getTipo(this.getId()).equals("CARRO"))
		{
			Carro c = (Carro)this;
			return v.getNomeFabricante(this.getCod_fabricante())+" "+v.getNomeModelo(this.getCodModelo())+" "+c.getVersao()
			+" "+c.getMotor();
		}
		
		else return "Titulo não definido para este tipo de veículo";
		
		
		
		
		
	}
	public void setTitulo(String titulo) {
		
		
	
		
		this.titulo = titulo;
	}
	
	

	
	//Recupera a imagem de capa do veículo
	
	public StreamedContent getImgCapa() {

		
		
		//System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterNames());
		
	    // long id_veiculo = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_veiculo"));
		
		
		try{
			
			
			imgCapa = new DefaultStreamedContent();
			imgCapa.setContentType("image/jpeg");
			imgCapa.setStream(new ImgDAO().getImgCapa(0).getImg());
			 
			
			System.out.println("Chegou aqui(imgCapa)");
		
			
			
		}
		catch(Exception e){

			Debug.gerar("util","Veiculo","getImgCapa", e.getMessage());
			return null;
		}
		
	
		return imgCapa;

	}



	
}
