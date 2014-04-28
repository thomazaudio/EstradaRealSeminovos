package util;

import java.math.BigDecimal;










import org.w3c.dom.DocumentType;





import br.com.uol.pagseguro.domain.Document;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;

public class PagSeguro {

	PaymentRequest paymentRequest =new PaymentRequest(); 
	
	
	
	
	public void geraPagamento(){
		
		
		
		paymentRequest.addItem(  
				  "000-1", // Identifica��o em seu sistema  
				  "Notebook Prata", // Descri��o  
				  Integer.valueOf(1), // Quantidade  
				  new BigDecimal("1830.00"), // Valor unit�rio  
				  new Long(1501), // Peso unit�rio, em gramas  
				  new BigDecimal("0.00") // Valor unit�rio do frete  
				);  
		
		
		paymentRequest.setShippingAddress(  
			    "BRA", // Pa�s  
			    "SP", // UF  
			    "Sao Paulo", // Cidade  
			    "Jardim Paulistano", // Bairro  
			    "01452002", // CEP  
			    "Av. Brig. Faria Lima", // Logradouro  
			    "1384", // N�mero  
			    "1o andar" // Complemento  
			  );  
		
		paymentRequest.setShippingType(ShippingType.SEDEX); 
		
	    paymentRequest.setSender(  
	    	      "Jo�o Comprador", // Nome completo  
	    	      "comprador@uol.com.br", // email  
	    	      "11", // DDD  
	    	      "56273440", // Telefone  
	    	      br.com.uol.pagseguro.enums.DocumentType.CPF, // Tipo de documento  
	    	      "000.000.001-91" // N�mero do documento  
	    	    );  
	    
	    paymentRequest.setCurrency(Currency.BRL);
	    
	    
	 // Referenciando a transa��o do PagSeguro em seu sistema  
	    paymentRequest.setReference("REF1234-USER214-ORDER754851B");  
	      
	    // URL para onde o comprador ser� redirecionado (GET) ap�s o fluxo de pagamento  
	    paymentRequest.setRedirectURL("http://www.lojamodelo.com.br/thankyou");  
	      
	    // URL para onde ser�o enviadas notifica��es (POST) indicando altera��es no status da transa��o  
	    paymentRequest.setNotificationURL("http://www.lojamodelo.com.br/notifications"); 
	    
	    
	    
	    //Efetiva��o do pagamento
	    
	    try {  
	        
	        boolean onlyCheckoutCode = false;  
	        String response = paymentRequest.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);  
	        
	        System.out.println(response);  
	        
	      } catch (PagSeguroServiceException e) {  
	        
	          System.err.println(e.getMessage());  
	      }  
		
	}
	
	
	
	
	
}
