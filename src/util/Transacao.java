package util;

public class Transacao {
	
	
	    //Transa��es possiveis de pagamento (parametro= tipo_transacao)
		public static final int DEBITO_CREDITO=1;//PAGAMENTO DE A�NCIO UTILIZANDO DINHEIRO EM CAIXA
		public static  final int CREDITO_DEBITO=2;//PAGAMENTO DE AN�NCIO UTILIZANDO BOLETO OU PAG-SEGURO
		public static final  int CREDITO=3;//ADI��O DE CR�DITO NA CONTA DO USU�RIO(DEP�SITO)
		public static final int ALT_PLANO=4;//ALTERA��O DE PLANO
		public static final int REEMITE_PAGAMENTO=6;//REEMISS�O DE PAGAMENTO PARA UM AN�NCIO
		
		
		//TRANSA��ES EM FORMA DE TEXTO
		public static String getTextoTransacao(int tipo_transacao){
			
			String texto;
			
			switch(tipo_transacao){
			
			case Transacao.CREDITO: texto = "Adi��o de cr�dito";
			break;
			
			case Transacao.CREDITO_DEBITO: texto = "Pagamento de An�ncio";
			break;
			
			case Transacao.ALT_PLANO :texto="Altera��o de plano";
			break;
			
		
			
			case Transacao.DEBITO_CREDITO: texto = "Pagamento de an�ncio/Promo��o de an�ncio";
			break;
			
			case Transacao.REEMITE_PAGAMENTO: texto ="Reemiss�o de pagamento de an�ncio";
			break;
			
			default: return texto = "n/a";
			
			}
			
			return texto;
		}

}
