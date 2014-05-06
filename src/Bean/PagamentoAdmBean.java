package Bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import Modelo.PagamentoDAO;
import util.Pagamento;


@ManagedBean
public class PagamentoAdmBean {
	
	
	
	private ArrayList<Pagamento> pagamentos;

	public ArrayList<Pagamento> getPagamentos() {
		
		
		pagamentos = new PagamentoDAO().getAllPagamentos();
		
		return pagamentos;
	}

	public void setPagamentos(ArrayList<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
	public void aprovar(AjaxBehaviorEvent event){
		
		long id_pg =  Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_pg"));
		
		Pagamento pg =  new PagamentoDAO().getPagamento(id_pg);
		
		pg.aprovar();
		
		
	}

}
