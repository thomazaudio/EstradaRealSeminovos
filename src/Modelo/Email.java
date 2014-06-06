package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;

import javax.faces.context.FacesContext;

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
		        email.setHtmlMsg(getPadraoEmail(mensagem).toString());
			   
			 
			   
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
	
	
	//RECUPERA O HTML DO EMAIL NO FORMATO PADRÃO
	public StringBuffer getPadraoEmail(StringBuffer msg){
		
		//Os arquivos base se encontram no src
		
		String header = lerArquivo("header.html").toString();
		
		String rodape = lerArquivo("rodape.html").toString();
		
		StringBuffer pad= new StringBuffer();
		
		//Adiciona o header ao buffer
		pad.append(header);
		
		//Adiciona a mensagem(msg)
		pad.append(msg.toString());
		
		//Adiciona o rodape para fechar
		pad.append(rodape);
		
		
		return pad;
		
	}
	
	public StringBuffer lerArquivo(String nome){
		
		 StringBuffer buffer = new StringBuffer();
           		
         try {
			
        	
			
			FileReader  file = new FileReader(getClass().getResource("").getFile()+nome);
			
			BufferedReader b = new BufferedReader(file);
			
			while(b.ready())
			buffer.append(b.readLine());
			
			
			
			
			file.close();
			b.close();
			
			
			
		} catch (Exception  e) {
			
			Debug.gerar("Modelo","Email","lerArquivo",e.getMessage());
			
		}
     
     return buffer;
		
	}
	
	
}
