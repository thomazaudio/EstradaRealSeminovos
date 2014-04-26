package Bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import Modelo.ParceiroDAO;
import util.Parceiro;


@ManagedBean
public class ParceiroBean {

	
	ArrayList<Parceiro> parceiros;

	public ArrayList<Parceiro> getParceiros() {
		
		parceiros = new ParceiroDAO().getAllParceiros();
		
		return parceiros;
	}

	public void setParceiros(ArrayList<Parceiro> parceiros) {
		this.parceiros = parceiros;
	}
	
	
	
	
}
