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
				  "000-1", // Identificação em seu sistema  
				  "Notebook Prata", // Descrição  
				  Integer.valueOf(1), // Quantidade  
				  new BigDecimal("1830.00"), // Valor unitário  
				  new Long(1501), // Peso unitário, em gramas  
				  new BigDecimal("0.00") // Valor unitário do frete  
				);  
		
		
		paymentRequest.setShippingAddress(  
			    "BRA", // País  
			    "SP", // UF  
			    "Sao Paulo", // Cidade  
			    "Jardim Paulistano", // Bairro  
			    "01452002", // CEP  
			    "Av. Brig. Faria Lima", // Logradouro  
			    "1384", // Número  
			    "1o andar" // Complemento  
			  );  
		
		paymentRequest.setShippingType(ShippingType.SEDEX); 
		
	    paymentRequest.setSender(  
	    	      "João Comprador", // Nome completo  
	    	      "comprador@uol.com.br", // email  
	    	      "11", // DDD  
	    	      "56273440", // Telefone  
	    	      br.com.uol.pagseguro.enums.DocumentType.CPF, // Tipo de documento  
	    	      "000.000.001-91" // Número do documento  
	    	    );  
	    
	    paymentRequest.setCurrency(Currency.BRL);
	    
	    
	 // Referenciando a transação do PagSeguro em seu sistema  
	    paymentRequest.setReference("REF1234-USER214-ORDER754851B");  
	      
	    // URL para onde o comprador será redirecionado (GET) após o fluxo de pagamento  
	    paymentRequest.setRedirectURL("http://www.lojamodelo.com.br/thankyou");  
	      
	    // URL para onde serão enviadas notificações (POST) indicando alterações no status da transação  
	    paymentRequest.setNotificationURL("http://www.lojamodelo.com.br/notifications"); 
	    
	    
	    
	    //Efetivação do pagamento
	    
	    try {  
	        
	        boolean onlyCheckoutCode = false;  
	        String response = paymentRequest.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);  
	        
	        System.out.println(response);  
	        
	      } catch (PagSeguroServiceException e) {  
	        
	          System.err.println(e.getMessage());  
	      }  
		
	}
	
	
	
	
	
}
