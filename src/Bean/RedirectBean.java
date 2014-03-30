package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="redirectBean")
@SessionScoped
public class RedirectBean {

	
	private String page;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	} 
	
	
	//Redirecionamento
	public void redirect(){
		
		if(page!=null)
		{
		
		 try{
		 FacesContext.getCurrentInstance().getExternalContext().redirect(page);
			}
		 catch(Exception e){
			 
		 }
		}
		
		
	}
	
}
