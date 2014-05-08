package util;

public class Plano {
	
	//Nomes
	public static final String NOME_ANUNCIO_ULTRA="DESTAQUE ULTRA + HOME + BANNER";
	public static final String NOME_ANUNCIO_MEGA="DESTAQUE MEGA + HOME";
	public static final String NOME_ANUNCIO_GRATIS="GRATUITO";
	
	//Prioridades
	public static final int PRIORIDADE_ULTRA=1;
	public static final int PRIORIDADE_MEGA=2;
	public static final int PRIORIDADE_GRATIS=5;
	
	
	//Preços
	public static final double PRECO_MEGA=30;
	public static final double PRECO_ULTRA=50;
	
	
	//Recupera o preco de acordo com o tipo de plano
	public static double getPrecoPlano(int tipo){
		
		switch(tipo){
		case PRIORIDADE_MEGA: return PRECO_MEGA;
		
		case PRIORIDADE_ULTRA :return PRECO_ULTRA;
		
		default :
		Debug.gerar("","Plano","getPrecoPlano","Preço não definido para o plano: "+tipo);
		return 0;
		
		
		}
	}
	
	
	//Recupera o nome do plano de acordo com a prioridade
	public static String getPlano(int prioridade){
		
		switch(prioridade){
		
		
		case PRIORIDADE_ULTRA :return NOME_ANUNCIO_ULTRA;
		
		case PRIORIDADE_MEGA: return NOME_ANUNCIO_MEGA;
	
		
		case PRIORIDADE_GRATIS: return NOME_ANUNCIO_GRATIS;
		
		
		default :return "";
		
		
		}
		
	}
	

}
