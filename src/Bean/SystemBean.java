package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import util.Debug;
import util.Pessoa;
import util.Usuario;


@ManagedBean(name="systemBean")
@SessionScoped
public class SystemBean {

	private Usuario user;
	private static Pessoa pessoa;
	
	public static Pessoa getPessoa() {
		return pessoa;
	}



	public static void setPessoa(Pessoa pessoa) {
		SystemBean.pessoa = pessoa;
	}
	private HttpSession sessao;
	
	public SystemBean(FacesContext context){
		
		sessao =(HttpSession) context.getExternalContext().getSession(false);
		this.setUser((Usuario) sessao.getAttribute("usuario") );
	   
	}
	
	
	
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public HttpSession getSessao() {
		return sessao;
	}
	public void setSessao(HttpSession sessao) {
		this.sessao = sessao;
	}
	
	
	//Encaminha para página de sessão expirada
	public void sessionExpired(){
		
		Debug.gerar("","","","A sessão do usuário expirou!");
		
	}
	
	
}
