package Bean;

import java.util.ArrayList;
import java.util.Collections;

import javax.faces.bean.ManagedBean;

import Modelo.DestaqueDAO;
import Modelo.VeiculoDAO;
import util.Destaque;
import util.InfoDestaque;
import util.Veiculo;


@ManagedBean
public class DestaqueBean {
   
	private ArrayList<InfoDestaque> destaqueBanner=  new ArrayList<InfoDestaque>();
	private ArrayList<Veiculo> destaqueInferior =  new ArrayList<Veiculo>();
	
	private ArrayList<Veiculo> destaqueParticular;
	private ArrayList<Veiculo> destaqueRevenda;
	
	
	public ArrayList<Veiculo> getDestaqueInferior() {
		
		
		Veiculo info = null;
		
		long tempo  =  System.currentTimeMillis();
		
	    VeiculoDAO   dao  =  new VeiculoDAO();
		
		ArrayList<Destaque> destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_INFERIOR);
		
		
		
		for(int i=0;(i<destaques.size()&& i<10);i++)
		{	
			
		 info = (Veiculo) dao.getVeiculo(destaques.get(i).getCodVeiculo(),Veiculo.class);	
		 
		 if(info!=null)
		 destaqueInferior.add(info);
		 
			
		}
		
		
		
		return destaqueInferior;
	}
	public void setDestaqueInferior(ArrayList<Veiculo> destaqueInferior) {
		this.destaqueInferior = destaqueInferior;
	}
	
	public ArrayList<InfoDestaque> getDestaqueBanner() {
		
		InfoDestaque info = null;
		
		long tempo  =  System.currentTimeMillis();
		
		DestaqueDAO dao  =  new DestaqueDAO();
		
		ArrayList<Destaque> destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_BANNER);
		
		for(int i=0;i<destaques.size();i++)
		{	
			
		 info = dao.getInforDestaque(destaques.get(i).getCodVeiculo(),destaques.get(i).getId());	
		 
		 if(info!=null)
		 destaqueBanner.add(info);
		 
			
		}
		
		
		//Embaralha
		Collections.shuffle(destaqueBanner);
		
		return destaqueBanner;
	}
	public void setDestaqueBanner(ArrayList<InfoDestaque> destaqueBanner) {
		this.destaqueBanner = destaqueBanner;
	}
	public ArrayList<Veiculo> getDestaqueParticular() {
		return destaqueParticular;
	}
	public void setDestaqueParticular(ArrayList<Veiculo> destaqueParticular) {
		this.destaqueParticular = destaqueParticular;
	}
	public ArrayList<Veiculo> getDestaqueRevenda() {
		return destaqueRevenda;
	}
	public void setDestaqueRevenda(ArrayList<Veiculo> destaqueRevenda) {
		this.destaqueRevenda = destaqueRevenda;
	}
	
	
	
}
