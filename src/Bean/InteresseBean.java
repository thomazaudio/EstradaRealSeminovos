package Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import Modelo.VeiculoDAO;
import util.Veiculo;


@ManagedBean
@SessionScoped
public class InteresseBean {
	
	public static int MAX=5;

	public ArrayList<Veiculo> veiculos =  new ArrayList<Veiculo>();
	private Map<Long, Boolean> selecionados = new HashMap<Long, Boolean>();
	
	public Map<Long, Boolean> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(Map<Long, Boolean> selecionados) {
		this.selecionados = selecionados;
	}


	private String texto;
	

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ArrayList<Veiculo> getVeiculos() {
		
		return veiculos;
	}

	public void setVeiculos(ArrayList<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	
	public void addVeiculo(){
		  
		  FacesMessage msg = new FacesMessage();
		
		  FacesContext context =  FacesContext.getCurrentInstance();
		 
		  String id = context.getExternalContext().getRequestParameterMap().get("idVeiculo");
		  
		  
		  Veiculo v = (Veiculo)new VeiculoDAO().getVeiculo(Integer.parseInt(id),Veiculo.class);
		  
		  if(!veiculos.contains(v) && veiculos.size()<=MAX)
		  veiculos.add(v);
		  
		  msg.setSummary("Veículo adicionado a lista.");
		  msg.setSeverity(FacesMessage.SEVERITY_INFO);
		  
		  context.addMessage(null,msg);


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
	
	
	//Recupera os veiculos selecionados
	 public ArrayList<Veiculo> getVeiculosSelecionados() {
			
		 ArrayList<Veiculo> veiculos_selecionados = new ArrayList<Veiculo>();
		 
		 
		 for(int i=0;i<veiculos.size();i++)
		 {
			 
			System.out.println("O id do veiculo é :"+veiculos.get(i).getId());
			
			System.out.println("Selecionados: ");
			System.out.println(selecionados);
			
			
			if(selecionados.get(veiculos.get(i).getId())!=null)
			{	
			boolean selected = selecionados.get(veiculos.get(i).getId());
			
		    if(selected)
			veiculos_selecionados.add(veiculos.get(i));
			}
		 }

		        
		        
		
		       
		        
	        	return veiculos_selecionados;
	        
	     
	    }
	
	
}
