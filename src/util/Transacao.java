package util;

public class Transacao {
	
	
	    //Transações possiveis de pagamento (parametro= tipo_transacao)
		public static final int DEBITO_CREDITO=1;//PAGAMENTO DE AÚNCIO UTILIZANDO DINHEIRO EM CAIXA
		public static  final int CREDITO_DEBITO=2;//PAGAMENTO DE ANÚNCIO UTILIZANDO BOLETO OU PAG-SEGURO
		public static final  int CREDITO=3;//ADIÇÃO DE CRÉDITO NA CONTA DO USUÁRIO(DEPÓSITO)
		public static final int ALT_PLANO=4;//ALTERAÇÃO DE PLANO
		public static final int REEMITE_PAGAMENTO=6;//REEMISSÃO DE PAGAMENTO PARA UM ANÚNCIO
		
		
		//TRANSAÇÕES EM FORMA DE TEXTO
		public static String getTextoTransacao(int tipo_transacao){
			
			String texto;
			
			switch(tipo_transacao){
			
			case Transacao.CREDITO: texto = "Adição de crédito";
			break;
			
			case Transacao.CREDITO_DEBITO: texto = "Pagamento de Anúncio";
			break;
			
			case Transacao.ALT_PLANO :texto="Alteração de plano";
			break;
			
		
			
			case Transacao.DEBITO_CREDITO: texto = "Pagamento de anúncio/Promoção de anúncio";
			break;
			
			case Transacao.REEMITE_PAGAMENTO: texto ="Reemissão de pagamento de anúncio";
			break;
			
			default: return texto = "n/a";
			
			}
			
			return texto;
		}

}
