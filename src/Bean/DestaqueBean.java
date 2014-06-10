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
	
	public static final int MIN  =5;
	
	public static final int MAX_DESTAQUE_INFERIOR=20;
	public static final int MAX_DESTAQUE_BANNER=10;
   
	private ArrayList<InfoDestaque> destaqueBanner=  new ArrayList<InfoDestaque>();
	private ArrayList<InfoDestaque> destaqueInferior1 =  new ArrayList<InfoDestaque>();
	private ArrayList<Veiculo> destaqueParticular;
	private ArrayList<Veiculo> destaqueRevenda;
	
	private ArrayList<InfoDestaque> destaqueInferior2 =  new ArrayList<InfoDestaque>();
	
	private ArrayList<InfoDestaque> destaqueInferior3 =  new ArrayList<InfoDestaque>();
	
	
	public ArrayList<InfoDestaque> getDestaqueInferior3() {
		
		

		//div 3, div 2
		InfoDestaque info = null;
		long max=0;
		int ini = 0;
		int div=1;
		long total =  new DestaqueDAO().count(Destaque.DESTAQUE_INFERIOR);
		
		if((total/3)>=5)
			div=3;
		else
		return new ArrayList<InfoDestaque>();	
		
        max =  total/div;
		
		ini  =(int)(total%div) + (int)max + ((int)max+1) ;
		
		System.out.println("Ini no destaque 3: "+ini);
		
		
        DestaqueDAO dao  =  new DestaqueDAO();
		
		ArrayList<Destaque> destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_INFERIOR,ini,(int)max);
		
		
		
		for(int i=0;(i<destaques.size()&& i<=MAX_DESTAQUE_INFERIOR);i++)
		{	
			
		 info =  dao.getInforDestaque(destaques.get(i).getCodVeiculo(),destaques.get(i).getId());	
		 
		 if(info!=null)
		 destaqueInferior3.add(info);
		 
			
		}
		
		
		System.out.println("Qauntidade de veículos no destaque 3: "+destaqueInferior3.size());
		
		return destaqueInferior3;
	}
	public void setDestaqueInferior3(ArrayList<InfoDestaque> destaqueInferior3) {
		
		//div 3
		
		this.destaqueInferior3 = destaqueInferior3;
	}
	public ArrayList<InfoDestaque> getDestaqueInferior2() {
		
		//div 3, div 2
		InfoDestaque info = null;
		long max=0;
		int ini = 0;
		int div=1;
		long total =  new DestaqueDAO().count(Destaque.DESTAQUE_INFERIOR);
		
		if((total/3)>=5)
			div=3;
		else if((total/2)>=5)
			div=2;
		else
		return new ArrayList<InfoDestaque>();
		
		max =  total/div;
		
		ini  =(int)(total%div) + (int)max ;
		
		System.out.println("Posição inicial do segundo destaque: "+ini);
		System.out.println("Maximo de registros do segundo destaque: "+max);
		
		
        DestaqueDAO dao  =  new DestaqueDAO();
		
		ArrayList<Destaque> destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_INFERIOR,ini,(int)max);
		
		
		
		for(int i=0;(i<destaques.size()&& i<=MAX_DESTAQUE_INFERIOR);i++)
		{	
			
		 info =  dao.getInforDestaque(destaques.get(i).getCodVeiculo(),destaques.get(i).getId());	
		 
		 if(info!=null)
		 destaqueInferior2.add(info);
		 
			
		}
		
		
		
		System.out.println("Qauntidade de veículos no destaque 2: "+destaqueInferior2.size());
		
		return destaqueInferior2;
		
		
		
		
		
	}
	public void setDestaqueInferior2(ArrayList<InfoDestaque> destaqueInferior2) {
		
		this.destaqueInferior2 = destaqueInferior2;
	}
	public ArrayList<InfoDestaque> getDestaqueInferior1() {
		
		
		//div 3, div 2, div 1
		InfoDestaque info = null;
		long max=0;
		int ini = 0;
		int div =1;
		
		long total =  new DestaqueDAO().count(Destaque.DESTAQUE_INFERIOR);
		
		if((total/3)>=5)
			div=3;
		else if((total/2)>=5)
			div=2;
		
		max =  total/div;
		
		max = max + (total%div);
		
		System.out.println("Resto: "+(total%div));
	
		
	
	
		
		DestaqueDAO dao  =  new DestaqueDAO();
		
		ArrayList<Destaque> destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_INFERIOR,ini,(int)max);
		
		
		
		for(int i=0;(i<destaques.size()&& i<=MAX_DESTAQUE_INFERIOR);i++)
		{	
			
		 info =  dao.getInforDestaque(destaques.get(i).getCodVeiculo(),destaques.get(i).getId());	
		 
		 if(info!=null)
		 destaqueInferior1.add(info);
		 
			
		}
		
		System.out.println("Qauntidade de veículos no destaque 1: "+destaqueInferior1.size());
		
		return destaqueInferior1;
	}
	public void setDestaqueInferior1(ArrayList<InfoDestaque> destaqueInferior) {
		this.destaqueInferior1 = destaqueInferior;
	}
	
	public ArrayList<InfoDestaque> getDestaqueBanner() {
		
		InfoDestaque info = null;
		
		long tempo  =  System.currentTimeMillis();
		
		DestaqueDAO dao  =  new DestaqueDAO();
		
		ArrayList<Destaque> destaques = new DestaqueDAO().getDestaques(Destaque.DESTAQUE_BANNER,0,0);
		
		Collections.shuffle(destaques);
		
		for(int i=0;(i<destaques.size() && i<=MAX_DESTAQUE_BANNER);i++)
		{	
			
		 info = dao.getInforDestaque(destaques.get(i).getCodVeiculo(),destaques.get(i).getId());	
		 
		 if(info!=null)
		 destaqueBanner.add(info);
		 
			
		}
		
		
		//Embaralha
		//Collections.shuffle(destaqueBanner);
		
		
		
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
