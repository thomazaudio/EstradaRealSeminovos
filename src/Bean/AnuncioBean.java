package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;






import Modelo.AnuncioDAO;
import util.Anuncio;
import util.Ordenacao;
import util.Pagina;
import util.Paginacao;






import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name="anuncioBean")
@ViewScoped
public class AnuncioBean {

	private long idUser;
    private String link;
	private FacesContext faces;
	
	
	private String tesste;
	
	
	
	public void editarAnuncio(AjaxBehaviorEvent event){
		
		
	}
	
	
	private String teste="g";
	
	public void testar(){
		
		String pag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page");
		
		teste = "Thomaz"+pag;
		
		
		System.out.println("Teste: "+teste);
		
	}
	public String getTeste(){
		
		return teste;
	}
	
	public void setTeste(String teste){
		
		this.teste = teste;
	}
	
	
	public Paginacao getPag() {
		
		
	   
		return pag;
	}


	public void setPag(Paginacao pag) {
		this.pag = pag;
	}

	private List<Anuncio> anuncios;
	
	
	public String getLink() {
		
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}
	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}


	public List<Anuncio> getAnuncios() {
		
		
		
	  anuncios = pag.getPaginaElements(getPage());
	  
	  
	  //Verifica o tipo de ordenação
	  
	  switch(tipoOrdenacao){
	  
	  case Ordenacao.ORD_DESTAQUE:
	  
		  ordenaPorDestaque(anuncios);
		  
	  break;
	  
	  case Ordenacao.ORD_ANO:
		  
		ordenaPorAno(anuncios);
	  
	  break;
	  
	  case Ordenacao.ORD_VALOR:
	  
	  break;
	  
	  }
	  
	 
	  
	  return anuncios;
		
	}


	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}


	
	
	public AnuncioBean(){
		
		faces = FacesContext.getCurrentInstance();
		idUser = new SystemBean(faces).getUser().getId();
	}
	
	public void novoAnuncio(){
		
		//verifica se existe usuário em sessão
		if(new UsuarioBean().getUser()==null)
			{
			try {
				faces.getExternalContext().redirect("anunciar2.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		  }
		 else
		 {
			 
			 try 
			 {
				 
				faces.getExternalContext().redirect("esc_login.jsf");
					
			 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			 
		 }
	}
	

	
	
	public void deletar(Anuncio an){
		
	    System.out.println(an.getDataIniFormated());	
		
	}
	
	//ITENS DE PAGINAÇÃO
	private Paginacao pag;
	private ArrayList<Pagina> paginas;
	private int page=1;
	private int quantPorPagina=10;
	private long quantPaginas;
	private int tipoOrdenacao;//Destaque, valor
	private int ordem;//Crescente decrescente
	
	public int getTipoOrdenacao() {
		return tipoOrdenacao;
	}
	public void setTipoOrdenacao(int tipoOrdenacao) {
		this.tipoOrdenacao = tipoOrdenacao;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
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
	
	//Passa para a próxima pagina
	public void proximaPagina(){
		
		page++;
	}
	
	public ArrayList<Pagina> getPaginas() {
	
	    paginas =   new ArrayList<Pagina>();
	    //Recupera todos os anúncios do usuário
		ArrayList itens = new AnuncioDAO().getAnunciosForUser(idUser);
		
		
		//Configura a paginação
		pag = new Paginacao(itens,this.getQuantPorPagina());
		
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
		
		
		
		System.out.println("Pagina2: "+this.getPage());
		
	}
	
	
	//Ordenação
	 // Para ordenar por destaque 
    private static void ordenaPorDestaque(List<Anuncio> lista) {  
        Collections.sort(lista, new Comparator<Anuncio>() {  
            @Override  
            public int compare(Anuncio o1, Anuncio o2) {  
                
            	Integer prioridade_1 = o1.getVeiculo().getPrioridade_anuncio();
            	Integer prioridade_2 = o2.getVeiculo().getPrioridade_anuncio();
            	
            	return prioridade_1.compareTo(prioridade_2);
            	
            	
            }
	
           
     });  
    }  
    
    
    // Para ordenar por preco
    private static void ordenaPorAno(List<Anuncio> lista) {  
        Collections.sort(lista, new Comparator<Anuncio>() {  
            @Override  
            public int compare(Anuncio o1, Anuncio o2) {  
                
            	
            	Integer  ano_1 = o1.getVeiculo().getAnoModelo();
            	Integer  ano_2 = o2.getVeiculo().getAnoModelo();
            	
            	return ano_1.compareTo(ano_2);
            	
            	
            }
	
           
     });  
    } 
    
    
    //Coloca o array em ordem decrescente
    public ArrayList<Anuncio> getDecrescente(ArrayList<Anuncio> ans){
    	
    	ArrayList<Anuncio> n = new ArrayList<Anuncio>();
    	
    	for(int i= (ans.size() -1);i>=0;i--)
    	n.add(ans.get(i));
    	
    	
    	return n;
    	
    	
    }
	
	
}
