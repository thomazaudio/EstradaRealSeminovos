package Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean
 public class ContextoBean {

	private String contextoInicial;

	public String getContextoInicial() {
		
		contextoInicial = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
		
	
		return contextoInicial;
	}

	public void setContextoInicial(String contextoInicial) {
		this.contextoInicial = contextoInicial;
	}
	
}
