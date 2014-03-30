package util;

import java.util.ArrayList;

import Modelo.AnuncioDAO;
import Modelo.VeiculoDAO;

public class VerificadorDeIntegridade {

	
	
	
	
	
	public static void verificaIntegridadeAnuncio(){
		
		
		
		
	    ArrayList<Veiculo> vs = 	new VeiculoDAO().getAllVeiculos();
	    
	    if(vs.size()==0)
	    System.out.println("Os dados estão integros(Anuncio/Veiculo)");	
	    
	    for(int i=0;i<vs.size();i++ )
	    {
	    	if(new AnuncioDAO().getAnuncio(vs.get(i).getId())==null)
	    	{
	    		
	    	System.out.println("Deletando "+vs.get(i).getTitulo()+"...");	
	    	new VeiculoDAO().delete(vs.get(i));	
	    	}
	    	
	    }
	    
	    
	    
		
		
	}
	
	
}
