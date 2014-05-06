package util;

import java.util.ArrayList;

import Modelo.AnuncioDAO;
import Modelo.VeiculoDAO;

public class VerificadorDeIntegridade {

	
	
	
	
	
	public static void verificaIntegridadeAnuncio(){
		
		
		
		
	  ArrayList<Anuncio> anuncios = new AnuncioDAO().getAllAnuncios();
	  
	  
	  ArrayList<Long> sem_int =  new ArrayList<Long>();
	  
	 
	  
	  for(int i=0;i<anuncios.size();i++)
	  {
		  
		  if(new VeiculoDAO().getVeiculo(anuncios.get(i).getVeiculo().getId(),Veiculo.class)==null)
		  sem_int.add(anuncios.get(i).getId());
		  
	  }
	    
	  if(sem_int.size()==0)
	  System.out.println("Os anúncios estão integros.");	
	  
	  else
	  {
		  
		  System.out.println("Anúncios sem integridade");
		  
		  System.out.println("{");
		  
		  for(int i=0;i<sem_int.size();i++)
		  System.out.println(sem_int.get(i)+" ");	  
		  
		  
		  System.out.println("}");
	  }
	    
		
		
	}
	
	
}
