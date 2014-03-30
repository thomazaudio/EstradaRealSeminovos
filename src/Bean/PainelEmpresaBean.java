package Bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import util.Usuario;



@ManagedBean(name="painelempresa")
public class PainelEmpresaBean {

	private String page;
	private  FacesContext context = FacesContext.getCurrentInstance();
	private HttpSession sessao  =(HttpSession)context.getExternalContext().getSession(false);
	private Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
	
	
	
	
	public String getPage() {
	
		page = paramMap.get("page");
		if(page==null)
		page="index";	
				
		page+=".xhtml";
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}

	
   public Usuario getUser(){
	   
	   
	    return (Usuario) sessao.getAttribute("usuario");
	   
   }
	
	
	
}
