package util;

public class PlanoAnuncio {

	
	public static final int GRATUITO =1;
	
	public static String getPlanoString(int tipo){
		
		switch(tipo){
		case GRATUITO:return "Gratuito";
		
		default: return "";
		
		}
	}
	
	
	
	
	
	
}
