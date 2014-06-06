package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;





import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.DefaultStreamedContent;

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
	private long codRevenda=0;
		
	
	
	public long getCodRevenda() {
		
		
	    codRevenda  = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod_revenda"));
		return codRevenda;
	}
	public void setCodRevenda(long codRevenda) {
		this.codRevenda = codRevenda;
	}
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
	
	
	
	//RESULTADO DA BUSCA POR CÓDIGO DA CONCESIONÁRIA
	 public List<Veiculo> getResultadoBuscaByUser() {
		
		 
		
	 
		//Classe de veículo
		String classe = null;
	    if(codTipo==1)
		classe =  "Moto";	
	    else if(codTipo==2)
		classe =  "Carro";	
	    
	    pag = new Paginacao(new VeiculoDAO().count(classe,this.recuperaRestricoesParaBusca()),this.getQuantPorPagina());
				
	    System.out.println("Index Inicial: "+pag.getIndexIni(this.getPage()));
				
				
				
				TesteTime tempo = new TesteTime();
				tempo.getTimeini();
				
				//SOMENTE PARA REVENDAS
				int tipo_venda=2;	
				
				System.out.println("Ordem: "+this.getOrdem());
				System.out.println("Tipo de ordem: "+this.getTipoOrdem());
				
			    //recupera os veiculos
				List<Veiculo> 	resultadoBusca =  new VeiculoDAO().getVeiculosBusca(codFabricante,codModelo, anoDe, anoAte, quilometragemDe, quilometragemAte, tipo_venda, precoDe, precoAte,classe,pag.getIndexIni(this.getPage()),this.getQuantPorPagina(),recuperaRestricoesParaBusca(),this.getOrdem(),this.getTipoOrdem(),this.getCodRevenda());
			
		
		if(resultadoBusca.size()==0)
		this.setTextoBusca("A busca n�o retornou nenhum resultado.");
		else
		this.setTextoBusca(String.format("Mostrando a p�gina '%d' de um total de '%d' p�gina(s).",this.getPage(),this.pag.getTotalPaginas()));
			
		
		System.out.println("Tempo Total para buscar: "+tempo.getTotalTime());
		
		return resultadoBusca;
	}
	
	
	//RESULTADO DA BUSCA GERAL
	public List<Veiculo> getResultadoBusca() {
		
		TesteTime t = new TesteTime();
		
		//Pagina
	 
		//Classe de veículo
		String classe = null;
	    if(codTipo==1)
		classe =  "Moto";	
	    else if(codTipo==2)
		classe =  "Carro";	
	    
	    System.out.println("Classe do veículo na busca: "+classe);
	    
	    pag = new Paginacao(new VeiculoDAO().count(classe,this.recuperaRestricoesParaBusca()),this.getQuantPorPagina());
				
	    System.out.println("Index Inicial: "+pag.getIndexIni(this.getPage()));
				
				
				
				TesteTime tempo = new TesteTime();
				tempo.getTimeini();
				
				int tipo_venda;
				
				if(revenda&&particular)
				tipo_venda=3;
				else if(revenda)
				tipo_venda=2;
				else if(particular)
				tipo_venda = 1;
				else 
				tipo_venda=3;	
				
				System.out.println("Ordem: "+this.getOrdem());
				System.out.println("Tipo de ordem: "+this.getTipoOrdem());
				
			    //recupera os veiculos
				List<Veiculo> 	resultadoBusca =  new VeiculoDAO().getVeiculosBusca(codFabricante,codModelo, anoDe, anoAte, quilometragemDe, quilometragemAte, tipo_venda, precoDe, precoAte,classe,pag.getIndexIni(this.getPage()),this.getQuantPorPagina(),recuperaRestricoesParaBusca(),this.getOrdem(),this.getTipoOrdem(),0);
			
		
		if(resultadoBusca.size()==0)
		this.setTextoBusca("A busca n�o retornou nenhum resultado.");
		else
		this.setTextoBusca(String.format("Mostrando a p�gina '%d' de um total de '%d' p�gina(s).",this.getPage(),this.pag.getTotalPaginas()));
			
		
		System.out.println("Tempo Total para buscar: "+tempo.getTotalTime());
		
		return resultadoBusca;
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
	
	//OP��ES DE FABRICANTE
	public List<SelectItem> getFabricantes() {
		
		//Carro
		if(this.getCodTipo()==2)
				{
					//Recupera todos os fabricantes  de carros cadastrados no banco de dados
					ArrayList<ItemVeiculo> itens =  new VeiculoDAO().getAllMarcasCarro();
					
					ItemVeiculo it;
					it = new ItemVeiculo();
					it.setCod(23);
					it.setNome("GM - Chevrolet");
					itens.remove(it);
					
					fabricantes.clear();
					
					//FABRICANTES PRINCIPAIS
					fabricantes.add(new SelectItem(23,"Chevrolet"));
					fabricantes.add(new SelectItem(21,"Fiat"));
					fabricantes.add(new SelectItem(22,"Ford"));
					fabricantes.add(new SelectItem(25,"Honda"));
					fabricantes.add(new SelectItem(41,"Mitsubish"));
					fabricantes.add(new SelectItem(44,"Peugeot"));
					fabricantes.add(new SelectItem(48,"Renault"));
					fabricantes.add(new SelectItem(56,"Toyota"));
					fabricantes.add(new SelectItem(59,"Volkswagem"));
					
				
					
					
					//FABRICANTES RESTANTES
					//for(int i=0;i<itens.size();i++)
					//fabricantes.add(new SelectItem(itens.get(i).getCod(),itens.get(i).getNome()));	
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
	
	//REALIZA A BUSCA DO VEÍCULO(RESETAR)
	public void buscar(){
		
		
		String pagina_busca="";
		
		//PÁGINA QUE VAI APARECER O RESULTADO DA BUSCA
		pagina_busca = "resultado_busca.jsf";
		
		//Classe de veículo
		String classe = null;
		
		if(codTipo==1)
		classe =  "Moto";
		
		else if(codTipo==2)
		classe =  "Carro";	
		
		//COMEÇANDO NA PÁGINA 1
		this.setPage(1);
		
		pag = new Paginacao(new VeiculoDAO().count(classe,this.recuperaRestricoesParaBusca()),this.getQuantPorPagina());
		
		
		
		
		
		this.setQuantPorPagina(10);
		
	    //recupera os veiculos
		//buscaTotal = new VeiculoDAO().getVeiculosBusca(codFabricante,codModelo, anoDe, anoAte, quilometragemDe, quilometragemAte, tipo_venda, precoDe, precoAte,classe);
		//resultadoBusca = buscaTotal;
		
		try {
			
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina_busca);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	      
	    //BUSCA RELACIONADA A UMA REVENDA ESPECÍFICA (RESETAR)
        public void buscarByUsuario(){
		
        	
      
		
    	System.out.println("O codigo da revenda é :"+codRevenda);
    	
		String pagina_busca="";
		
		//PÁGINA QUE VAI APARECER O RESULTADO DA BUSCA
		pagina_busca = new ContextoBean().getContextoInicial()+"/loja/index.jsf?cod_revenda="+this.getCodRevenda();
		
		//Classe de veículo
		String classe = null;
		
		if(codTipo==1)
		classe =  "Moto";
		
		else if(codTipo==2)
		classe =  "Carro";	
		
		//COMEÇANDO NA PÁGINA 1
		this.setPage(1);
		
		pag = new Paginacao(new VeiculoDAO().count(classe,this.recuperaRestricoesParaBusca()),this.getQuantPorPagina());
		
		this.setQuantPorPagina(10);
		
		try {
			
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina_busca);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
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
	
	
	
	//Recupera a imagem de capa do ve�culo
	
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
	
	
	//ITENS DE PAGINA��O
		private Paginacao pag;
		private ArrayList<Pagina> paginas;
		private int page=1;
		private int quantPorPagina=10;
		private long quantPaginas;
		private long totalRegistros;
		private int ordem=1;//Crescente
		private int tipoOrdem=3;//Destaque
		
		public int getOrdem() {
			return ordem;
		}
		public void setOrdem(int ordem) {
			this.ordem = ordem;
		}

        
		
		
		
		public int getTipoOrdem() {
			return tipoOrdem;
		}
		public void setTipoOrdem(int tipoOrdem) {
			this.tipoOrdem = tipoOrdem;
		}
		public long getTotalRegistros() {
			
			//Classe de ve�culo
			
			String classe = null;
			
			if(codTipo==1)
			classe =  "Moto";	
			else if(codTipo==2)
			classe =  "Carro";	
			
			totalRegistros =  new VeiculoDAO().count(classe,this.recuperaRestricoesParaBusca());
			
			return totalRegistros  ;
		}
		
		public void setTotalRegistros(long totalRegistros) {
			this.totalRegistros = totalRegistros;
		}
		
		public long getQuantPaginas() {
			return quantPaginas;
		}

		public void setQuantPaginas(long quantPaginas) {
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
		
		//Passa para a pr�xima pagina
		public void proximaPagina(){
			
			page++;
		}
		
		public ArrayList<Pagina> getPaginas() {
		
		    paginas =   new ArrayList<Pagina>();
		    
			//Configura a pagina��o
			pag = new Paginacao(this.getTotalRegistros(),this.getQuantPorPagina());
			
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

		//Altera��o da pagina
		public void alterarPage()
		{
	       

			String pag_string = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page");
		
			this.setPage(Integer.parseInt(pag_string));
			
			
		}
	
	
		
	//RECUPERA AS RETRIÇOES PARA BUSCA
	public ArrayList<Criterion>	recuperaRestricoesParaBusca(){
		
		ArrayList<Criterion> cri =  new ArrayList<Criterion>();
		
         		
		
		if(this.getCodFabricante()!=0)
		cri.add(Restrictions.eq("cod_fabricante",this.getCodFabricante()));
		//Eqs 
		if(this.getCodModelo()!=0)
		cri.add(Restrictions.eq("codModelo", this.getCodModelo()));
		
		//Ano
		if(this.getAnoAte()!=0)
		cri.add(Restrictions.le("anoModelo",this.getAnoAte()));	
		
		cri.add(Restrictions.ge("anoFabricacao",this.getAnoDe()));
		
		//Pre�o
		cri.add(Restrictions.ge("preco",this.getPrecoDe()));
		
		if(this.getPrecoAte()!=0)
		cri.add(Restrictions.le("preco",this.getPrecoAte()));
		
		

		int tipo_venda;
		
		if(revenda&&particular)
		tipo_venda=3;
		else if(revenda)
		tipo_venda=2;
		else if(particular)
		tipo_venda = 1;
		else 
		tipo_venda=3;
		
		if(tipo_venda!=3)
		cri.add(Restrictions.eq("tipoVenda", tipo_venda));	
		
		
		return cri;
		
	}
	
	
	    //RECUPERA AS RETRIÇOES PARA BUSCA RELACIONADA A UMA REVENDA ESPECÍFICA
		public ArrayList<Criterion>	recuperaRestricoesParaBuscaByUser(long cod_revenda){
			
			ArrayList<Criterion> cri =  new ArrayList<Criterion>();
			
			if(this.getCodFabricante()!=0)
			cri.add(Restrictions.eq("cod_fabricante",this.getCodFabricante()));
			//Eqs 
			if(this.getCodModelo()!=0)
			cri.add(Restrictions.eq("codModelo", this.getCodModelo()));
			
			//Ano
			if(this.getAnoAte()!=0)
			cri.add(Restrictions.le("anoModelo",this.getAnoAte()));	
			
			cri.add(Restrictions.ge("anoFabricacao",this.getAnoDe()));
			
			//Pre�o
			cri.add(Restrictions.ge("preco",this.getPrecoDe()));
			
			if(this.getPrecoAte()!=0)
			cri.add(Restrictions.le("preco",this.getPrecoAte()));
			
			

			int tipo_venda;
			
			if(revenda&&particular)
			tipo_venda=3;
			else if(revenda)
			tipo_venda=2;
			else if(particular)
			tipo_venda = 1;
			else 
			tipo_venda=3;
			
			if(tipo_venda!=3)
			cri.add(Restrictions.eq("tipoVenda", tipo_venda));	
			
			
			return cri;
			
		}
	
}
