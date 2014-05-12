package util;

import java.util.Calendar;
import java.util.Date;

public class Plano {
	
	
	
	//Quantidade de dias que o destaque ficara no ar
	public static final int DIAS_MEGA =30;
	public static final int DIAS_ULTRA=20;
	
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
	
	
	//Recupera a data final de cada plano lançado no sistema
	public static Calendar getDataFim(int prioridade_anuncio){
		
		
		Calendar fim = Calendar.getInstance();
		
		
		
		
		
		switch(prioridade_anuncio){
	
		case PRIORIDADE_MEGA :fim.add(Calendar.DAY_OF_MONTH,DIAS_MEGA);
		break;
		
		case PRIORIDADE_ULTRA :fim.add(Calendar.DAY_OF_MONTH,DIAS_ULTRA);
		
		}
		
		return fim;
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
