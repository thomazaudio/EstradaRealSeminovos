package Bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import Modelo.VeiculoDAO;
import util.Veiculo;


@ManagedBean
@SessionScoped
public class InteresseBean {

	public ArrayList<Veiculo> veiculos =  new ArrayList<Veiculo>();
	
	private String texto;
	

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<Veiculo> getVeiculos() {
		
		ArrayList<Veiculo> n_v =  new ArrayList<Veiculo>();
		
		for(int i =veiculos.size()-1;i>=0;i--)
		n_v.add(veiculos.get(i));	
		
		return n_v;
	}

	public void setVeiculos(ArrayList<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	
	public void addVeiculo(){
		  
		 
		
		  FacesContext context =  FacesContext.getCurrentInstance();
		 
		  String id = context.getExternalContext().getRequestParameterMap().get("idVeiculo");
		  
		  
		  if(veiculos.size()==5)
		  veiculos.set(4,(Veiculo)new VeiculoDAO().getVeiculo(Integer.parseInt(id),Veiculo.class));  
			  
		  else
		  veiculos.add((Veiculo)new VeiculoDAO().getVeiculo(Integer.parseInt(id),Veiculo.class));


	}
	
	
	public void removeVeiculo(AjaxBehaviorEvent event){
		
		  FacesContext context =  FacesContext.getCurrentInstance();
		  long id =Long.parseLong( context.getExternalContext().getRequestParameterMap().get("idVeiculo"));
		  
		  int i=0;
		  
		  while(i<veiculos.size() && veiculos.get(i).getId()!=id)
		  i++;
		  
		  
		  if(veiculos.get(i).getId()==id)
		  veiculos.remove(i);	  
	}
	
	
	
	
}
