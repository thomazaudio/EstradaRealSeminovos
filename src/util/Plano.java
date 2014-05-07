package util;

public class Plano {
	
	//Nomes
	public static final String NOME_ANUNCIO_MEGA="Mega";
	public static final String NOME_ANUNCIO_GRATIS="Gratuito";
	
	//Prioridades
	public static final int PRIORIDADE_MEGA=1;
	public static final int PRIORIDADE_GRATIS=5;
	
	
	//Preços
	public static final double PRECO_MEGA=30;
	
	
	//Recupera o preco de acordo com o tipo de plano
	public static double getPrecoPlano(int tipo){
		
		switch(tipo){
		case PRIORIDADE_MEGA: return PRECO_MEGA;
		
		default : return 0;
		
		}
	}
	
	
	//Recupera o nome do plano de acordo com a prioridade
	public static String getPlano(int prioridade){
		
		switch(prioridade){
		
		case PRIORIDADE_MEGA: return NOME_ANUNCIO_MEGA;
		
		
		case PRIORIDADE_GRATIS: return NOME_ANUNCIO_GRATIS;
		
		
		default :return "";
		
		
		}
		
	}
	

}
