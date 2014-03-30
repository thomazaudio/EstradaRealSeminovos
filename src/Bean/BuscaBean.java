package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;

import Modelo.AnuncioDAO;
import Modelo.ImgDAO;
import Modelo.LocalizacaoDAO;
import Modelo.VeiculoDAO;
import util.Cidade;
import util.Debug;
import util.ItemVeiculo;
import util.Pagina;
import util.Paginacao;
import util.TesteTime;
import util.Veiculo;


@ManagedBean(name="buscaBean")
@SessionScoped
public class BuscaBean {

	private int codTipo=2;
	private int codFabricante;
	private int codModelo;
	private int codCidade;
	private int anoDe;
	private int anoAte;
	private double precoDe;
	private double precoAte;
	private int quilometragemDe;
	private int quilometragemAte;
	private boolean particular;
	private boolean revenda;
	private long idVeiculo =0;
	private String textoBusca;
	public String getTextoBusca() {
		return textoBusca;
	}
	public void setTextoBusca(String textoBusca) {
		this.textoBusca = textoBusca;
	}
	public long getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}


	private DefaultStreamedContent imgCapa;
	
	private List<SelectItem> fabricantes = new ArrayList<SelectItem>();
	private List<SelectItem> modelos =new ArrayList<SelectItem>();
	
	
	
	private List<Veiculo> resultadoBusca =  new ArrayList<Veiculo>();
	
	private List<Veiculo> buscaTotal =  new ArrayList<Veiculo>();
	
	
	public List<Veiculo> getBuscaTotal() {
		return buscaTotal;
	}
	public void setBuscaTotal(List<Veiculo> buscaTotal) {
		this.buscaTotal = buscaTotal;
	}


	ArrayList<SelectItem> cidades =  new ArrayList<SelectItem>();
	
	public ArrayList<SelectItem> getCidades() {
		
		ArrayList<Cidade> c = new LocalizacaoDAO().getCidades(11);
		
		for(int i=0;i<c.size();i++)
		cidades.add(new SelectItem(c.get(i).getId(),c.get(i).getCidade()));	
		return cidades;
	}
	public void setCidades(ArrayList<SelectItem> cidades) {
		
		
		
		this.cidades = cidades;
	}
	public int getCodCidade() {
		return codCidade;
	}
	public void setCodCidade(int codCidade) {
		this.codCidade = codCidade;
	}
	
	public List<Veiculo> getResultadoBusca() {
		
		resultadoBusca= pag.getPaginaElements(getPage());
		
		if(resultadoBusca.size()==0)
		this.setTextoBusca("A busca não retornou nenhum resultado.");
		else
		this.setTextoBusca(String.format("Mostrando a página '%d' de um total de '%d' página(s).",this.getPage(),this.pag.getTotalPaginas()));
			
		
		return resultadoBusca;
	}
	public void setResultadoBusca(List<Veiculo> resultadoBusca) {
		this.resultadoBusca = resultadoBusca;
	}
	
	
	public int getCodTipo() {
		return codTipo;
	}
	public void setCodTipo(int codTipo) {
		this.codTipo = codTipo;
	}
	
	public void alteraTipoVeiculo(ValueChangeEvent e){
		
		int tipo = Integer.parseInt(e.getNewValue().toString());
		
		fabricantes =  new ArrayList<SelectItem>(); 
		
		//Carro
		if(tipo==2)
		{
			//Recupera todos os fabricantes  de carros cadastrados no banco de dados
			ArrayList<ItemVeiculo> itens =  new VeiculoDAO().getAllMarcasCarro();
			
			for(int i=0;i<itens.size();i++)
			fabricantes.add(new SelectItem(itens.get(i).getCod(),itens.get(i).getNome()));	
		}
	}
	
	public List<SelectItem> getFabricantes() {
		
		//Carro
		if(this.getCodTipo()==2)
				{
					//Recupera todos os fabricantes  de carros cadastrados no banco de dados
					ArrayList<ItemVeiculo> itens =  new VeiculoDAO().getAllMarcasCarro();
					
					for(int i=0;i<itens.size();i++)
					fabricantes.add(new SelectItem(itens.get(i).getCod(),itens.get(i).getNome()));	
				}
		
		return fabricantes;
	}
	public void setFabricantes(List<SelectItem> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	
	public void alteraModelo(ValueChangeEvent e){
		
		
		
		this.setCodModelo(Integer.parseInt(e.getNewValue().toString()));
	}
	
	public void alteraFab(ValueChangeEvent e){
		
		
		int cod = Integer.parseInt(e.getNewValue().toString());
		
		
		
		
		modelos =  new ArrayList<SelectItem>();
		
		ArrayList<ItemVeiculo> its =   new VeiculoDAO().getAllModelos(cod);
		
		
		for(int i=0;i<its.size();i++)
		System.out.println(its.get(i).getCod());	
		
		for(int i=0;i<its.size();i++)
		modelos.add(new SelectItem(its.get(i).getCod(),its.get(i).getNome()));	
		
		
	}
	
	//REALIZA A BUSCA DO VEÍCULO
	public void buscar(){
		
		
		this.setPage(1);
		
		TesteTime t = new TesteTime();
		
		int tipo_venda;
		
		if(revenda&&particular)
		tipo_venda=3;
		else if(revenda)
		tipo_venda=2;
		else if(particular)
		tipo_venda = 1;
		else 
		tipo_venda=3;	
		
	    //recupera os veiculos
		buscaTotal = new VeiculoDAO().getVeiculosBusca(codFabricante,codModelo, anoDe, anoAte, quilometragemDe, quilometragemAte, tipo_venda, precoDe, precoAte);
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("resultado_busca.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Tempo total para busca: "+t.getTotalTime());
		
	}
	
	public List<SelectItem> getModelos() {
	
	    return modelos;
		
	}
	public void setModelos(List<SelectItem> modelos) {
		this.modelos = modelos;
	}
	
	public int getCodFabricante() {
		return codFabricante;
	}
	public void setCodFabricante(int codFabricante) {
		this.codFabricante = codFabricante;
	}
	public int getCodModelo() {
		return codModelo;
	}
	public void setCodModelo(int codModelo) {
		this.codModelo = codModelo;
	}
	public int getAnoDe() {
		return anoDe;
	}
	public void setAnoDe(int anoDe) {
		this.anoDe = anoDe;
	}
	public int getAnoAte() {
		return anoAte;
	}
	public void setAnoAte(int anoAte) {
		this.anoAte = anoAte;
	}
	public double getPrecoDe() {
		return precoDe;
	}
	public void setPrecoDe(double precoDe) {
		this.precoDe = precoDe;
	}
	public double getPrecoAte() {
		return precoAte;
	}
	public void setPrecoAte(double precoAte) {
		this.precoAte = precoAte;
	}
	public int getQuilometragemDe() {
		return quilometragemDe;
	}
	public void setQuilometragemDe(int quilometragemDe) {
		this.quilometragemDe = quilometragemDe;
	}
	public int getQuilometragemAte() {
		return quilometragemAte;
	}
	public void setQuilometragemAte(int quilometragemAte) {
		this.quilometragemAte = quilometragemAte;
	}
	public boolean isParticular() {
		return particular;
	}
	public void setParticular(boolean particular) {
		this.particular = particular;
	}
	public boolean isRevenda() {
		return revenda;
	}
	public void setRevenda(boolean revenda) {
		this.revenda = revenda;
	}
	
	
	
	//Recupera a imagem de capa do veículo
	
			public DefaultStreamedContent getImgCapa() {

				
				 FacesContext context = FacesContext.getCurrentInstance();
			     
			     String imageID = context.getExternalContext().getRequestParameterMap().get("idVeiculo");
			     
			   
				
				try{
					
					
					imgCapa = new DefaultStreamedContent();
					imgCapa.setContentType("image/jpeg");
					imgCapa.setStream(new ImgDAO().getImgCapa(0).getImg());
					 
					
					
				
					
					
				}
				catch(Exception e){

					Debug.gerar("util","Veiculo","getImgCapa", e.getMessage());
					return null;
				}
				
			
				return imgCapa;

			}
	
	
	//ITENS DE PAGINAÇÃO
		private Paginacao pag;
		private ArrayList<Pagina> paginas;
		private int page=1;
		private int quantPorPagina=2;
		private int quantPaginas;
		
		public int getQuantPaginas() {
			return quantPaginas;
		}

		public void setQuantPaginas(int quantPaginas) {
			this.quantPaginas = quantPaginas;
		}

		public int getQuantPorPagina() {
			return quantPorPagina;
		}

		public void setQuantPorPagina(int quantPorPagina) {
			page =1;
			this.quantPorPagina = quantPorPagina;
		}

		public void altPage(ValueChangeEvent e){
			
			System.out.println("Pag: "+e.getNewValue().toString());
			
		}
		
		//Pagina anterior
	    public void antPagina(){
			
			page--;
		}
		
		//Passa para a próxima pagina
		public void proximaPagina(){
			
			page++;
		}
		
		public ArrayList<Pagina> getPaginas() {
		
		    paginas =   new ArrayList<Pagina>();
		    
		    
		   
		    //Resultado da busca 
		    ArrayList res = (ArrayList<Veiculo>) buscaTotal;
			
			//Configura a paginação
			pag = new Paginacao(res,this.getQuantPorPagina());
			
			this.setQuantPaginas(pag.getTotalPaginas());
			
			Pagina p;
			
			for(int i=1;i<=pag.getTotalPaginas();i++)
			{
				
				p = new Pagina();
				p.setPagina(i);
				if(this.getPage()==i)
				p.setAtual(true);	
				
				paginas.add(p);
			}
			
		    paginas = pag.refactorPaginacao(paginas,this.getPage());
			
			return paginas;
		}

		public void setPaginas(ArrayList<Pagina> paginas) {
			this.paginas = paginas;
		}

		
		
		
		public int getPage() {
			
			if(page<1)
			page++;	
				
			
			if(page>pag.getTotalPaginas())
			page--;	
			
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		//Alteração da pagina
		public void alterarPage()
		{
	       

			String pag_string = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page");
		
			this.setPage(Integer.parseInt(pag_string));
			
			
		}
	
	
	
}
