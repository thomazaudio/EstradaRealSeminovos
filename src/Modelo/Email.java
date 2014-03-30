package Modelo;

import org.apache.commons.mail.HtmlEmail;
import util.Debug;

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
	
	public void sendHtmlEmail() {
	       
		   try{
		   
		   HtmlEmail email = new HtmlEmail();
		   //Utilize o hostname do seu provedor de email
		   System.out.println("alterando hostname...");
		   email.setHostName("smtp.gmail.com");
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(587);
		   //Adicione os destinatários
		   email.addTo(this.getDestinatario(),this.getNome_destinatario());
		   //Configure omail.com seu email do qual enviará
		   email.setFrom("thomazrd@gmail.com", "Estrada Real");
		   //Adicione um assunto
		   email.setSubject(this.getAssunto());
		   //Adicione a mensagem do email
		 
	       
		   email.setHtmlMsg(this.getMensagem().toString());
		   
		 
		   
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("thomazrd", "magda...");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		   }catch(Exception e){
			   
			   Debug.gerar("Modelo","Email","sendHtmlEmail", e.getMessage());
		   }
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
	
	
	
}
