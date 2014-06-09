package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import util.Usuario;
import Modelo.UsuarioDAO;


@ManagedBean
public class RevendaBean {
	
	
	private Usuario user;
	
	private long codRevenda;
	private String nomeRevenda;
	
	public String getNomeRevenda() {
		
		//Recupera o usuário
		long id_usuario  = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod_revenda"));
		
		System.out.println("ID: "+id_usuario);
		
		nomeRevenda =  new UsuarioDAO().getNomeUsuario(id_usuario);
		
		return nomeRevenda;
		
	}

	public void setNomeRevenda(String nomeRevenda) {
		this.nomeRevenda = nomeRevenda;
	}

	public long getCodRevenda() {
		
		//Recupera o usuário
		 codRevenda= Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod_revenda"));
		
		return codRevenda;
	}

	public void setCodRevenda(long codRevenda) {
		this.codRevenda = codRevenda;
	}

	public RevendaBean(){
		
		
	}

	public Usuario getUser() {
		
			
		user   = new UsuarioDAO().getUser(this.getCodRevenda());
		
		System.out.println("Nome do usuário: "+user.getNome());
		
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}
