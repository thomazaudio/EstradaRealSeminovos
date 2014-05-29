package Modelo;

import java.util.Calendar;

import org.apache.commons.mail.HtmlEmail;

import util.Debug;
import util.SystemEmpresa;

public class Email {
	
	
	

	private String destinatario;
	private String nome_destinatario;
    private StringBuffer mensagem;
	private String assunto;
	
	public Email(String destinatario,String nome_destinatario,StringBuffer mensagem,String assunto){
		
		this.setDestinatario(destinatario);
		this.setNome_destinatario(nome_destinatario);
		this.setMensagem(mensagem);
		this.setAssunto(assunto);
		
	}
	
	public Email(){
		
		
	}
	
	
		 
	       
		  
	
	
	
	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public StringBuffer getMensagem() {
		return mensagem;
	}

	public void setMensagem(StringBuffer mensagem) {
		this.mensagem = mensagem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getNome_destinatario() {
		return nome_destinatario;
	}

	public void setNome_destinatario(String nome_destinatario) {
		this.nome_destinatario = nome_destinatario;
	}
	
	
	
	public void enviaEmail(String destinatario,String nome_destinatario,StringBuffer mensagem,String assunto){
		
		
		  try{
			   
			   HtmlEmail email = new HtmlEmail(); 
			  
			   email.setHostName("mail.estradarealseminovos.com.br");
			 
			   
			   email.setSmtpPort(587);
			 
			   
			   email.addTo(destinatario,nome_destinatario);
			
			   
			   email.setFrom(SystemEmpresa.EMAIL,SystemEmpresa.NOME_EMPRESA);
			  
			   
			   email.setSubject(assunto);
			  
			   
			   
			   //Conteudo da mensagem de confirma��o de ponto
		        email.setHtmlMsg(mensagem.toString());
			   
			 
			   
			   //AUTENTICA��ES
			   System.out.println("autenticando...");
			  // email.setSSL(true);
			   email.setAuthentication("sistema@estradarealseminovos.com.br","leghacy");
			   System.out.println("enviando...");
			   email.send();
			   System.out.println("Email enviado!");
			   }catch(Exception e){
				   
				   Debug.gerar("Modelo","Email","sendHtmlEmail", e.getMessage());
			   }
		
	}
	
	
}
