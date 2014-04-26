package Bean;

import java.io.IOException;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import Modelo.UsuarioDAO;
import util.Usuario;


@ManagedBean(name="loginBean")

public class LoginBean {

	private FacesContext faces;
	private String pagepos;
	
	public String getPagepos() {
		
		pagepos = faces.getExternalContext().getRequestParameterMap().get("page_pos");
		return pagepos;
	}


	public void setPagepos(String pagepos) {
		this.pagepos = pagepos;
	}


	public LoginBean(){
		
		faces =  FacesContext.getCurrentInstance();
	}
	
	
	public void logar() throws IOException{
		
		
		FacesMessage msg = new FacesMessage();
		
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("Aguarde...");
		FacesContext.getCurrentInstance().addMessage(null,msg);
		
		//Recupera os parametros externos
		Map<String,String> ats =  faces.getExternalContext().getRequestParameterMap();
		
		String email,senha,page_pos;
		 
		email =  ats.get("email");
		senha =  ats.get("senha");
		page_pos = ats.get("page_pos");
		
		if(page_pos.length()<2)
		page_pos = "arearestritausuario/lista_anuncios.jsf";	
	    
		//Tenta realizar login
		Usuario user = (Usuario) new UsuarioDAO().logar(email, senha);
		
		
		
		
		
		if(user!=null)
		{
			
			
		System.out.println("Id do contato: "+user.getContato().getId());	
			
		//Verifica se o contato do usu�rio esta confirmado
		if(user.getContato().getConfirmado()==0)
		faces.getExternalContext().redirect("pagina_confirma_email.jsp?email="+email);		
			
		//Joga o usu�rio na sess�o	
		HttpSession sessao = (HttpSession)faces.getExternalContext().getSession(false);
		sessao.setAttribute("usuario",user);
	
		try {
			
			System.out.println("Chego aqui, page_pos: "+page_pos);
			
			faces.getExternalContext().redirect(page_pos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  
	  
		
		}
		else
		{
		
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary("email e/ou senha errado(s), por favor tente novamente.") ;	
		FacesContext.getCurrentInstance().addMessage("msgs", msg);
			
		}
		
		
	}
	
	
	//SE O USU�RIO J� ESTIVER LOGADO ELE � ENCAMINHADO PARA PAG_POS
	public void verificaLogin(){
		
		
		
		//Recupera os parametros externos
		Map<String,String> ats =  faces.getExternalContext().getRequestParameterMap();
				
		String page_pos;
				 
		
		page_pos = ats.get("page_pos");
		
		
     try {
			
			System.out.println("Chego aqui, page_pos: "+page_pos);
			
			faces.getExternalContext().redirect(page_pos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
