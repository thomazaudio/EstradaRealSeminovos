package Bean;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import util.Veiculo;
import Modelo.AnuncioAdmDAO;
import Modelo.VeiculoDAO;

@ManagedBean
public class AnuncioAdmBean {
	
	private ArrayList<Veiculo> veiculos;
	
	
	
	
	public ArrayList<Veiculo> getVeiculos(){
		
		veiculos =  new VeiculoDAO().getAllVeiculos();
		
		return veiculos;
	}
	
	
	
	
	public void confirmarPagamento(javax.faces.event.AjaxBehaviorEvent event){
		
		//Recupera o id do veiculo
		long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		
		new  AnuncioAdmDAO().confirmaPagamento(id);
		
	}
	
    public void confereDados(javax.faces.event.AjaxBehaviorEvent event){
		
		//Recupera o id do veiculo
		long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		
		new  AnuncioAdmDAO().confereDados(id);
		
	}
	

}
