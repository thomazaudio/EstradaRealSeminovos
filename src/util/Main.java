package util;

import java.util.Calendar;




public class Main {

	
	public static void main(String[] args) {

		
		//new VerificadorDeIntegridade().verificaIntegridadeAnuncio();
		
		
	    //new AnuncioAdmDAO().confirmaPagamento(258);
		
		//VerificadorDeIntegridade.verificaIntegridadeAnuncio();
		
		
		
		//System.out.println(new Email().getComprovantePonto("Thomaz",Calendar.getInstance()));
	
		
		
		
		
		Calendar data_anuncio = Calendar.getInstance();
		
		System.out.println(Plano.getDataFimAnuncio(data_anuncio,1));
		
		
	}
	
	
	
}
