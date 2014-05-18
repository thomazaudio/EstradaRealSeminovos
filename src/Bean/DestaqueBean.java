package Bean;

import java.util.ArrayList;
import java.util.Collections;
import javax.faces.bean.ManagedBean;
import Modelo.DestaqueDAO;
import util.Destaque;
import util.InfoDestaque;
import util.Veiculo;


@ManagedBean
public class DestaqueBean {
	
	
	public static final int MAX_DESTAQUE_INFERIOR=10;
	public static final int MAX_DESTAQUE_BANNER=10;
   
	private ArrayList<InfoDestaque> destaqueBanner=  new ArrayList<InfoDestaque>();
	private ArrayList<InfoDestaque> destaqueInferior =  new ArrayList<InfoDestaque>();
	
	private ArrayList<Veiculo> destaqueParticular;
	private ArrayList<Veiculo> destaqueRevenda;
	
	
	public ArrayList<InfoDestaque> getDestaqueInferior() {
		
		
		InfoDestaque info = null;
		
	
		
		DestaqueDAO dao  =  new DestaqueDAO();
		
		ArrayList<Destaque> destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_INFERIOR);
		
		//Embaralha
		Collections.shuffle(destaques);
		
		for(int i=0;(i<destaques.size()&& i<=MAX_DESTAQUE_INFERIOR);i++)
		{	
			
		 info =  dao.getInforDestaque(destaques.get(i).getCodVeiculo(),destaques.get(i).getId());	
		 
		 if(info!=null)
		 destaqueInferior.add(info);
		 
			
		}
		
		
		
		return destaqueInferior;
	}
	public void setDestaqueInferior(ArrayList<InfoDestaque> destaqueInferior) {
		this.destaqueInferior = destaqueInferior;
	}
	
	public ArrayList<InfoDestaque> getDestaqueBanner() {
		
		InfoDestaque info = null;
		
		long tempo  =  System.currentTimeMillis();
		
		DestaqueDAO dao  =  new DestaqueDAO();
		
		ArrayList<Destaque> destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_BANNER);
		
		for(int i=0;(i<destaques.size() && i<=MAX_DESTAQUE_BANNER);i++)
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
