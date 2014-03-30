package util;

import Modelo.Email;

public class Comunicacao {

	
	 
	//Envia um email para confirmação de usuário
	public void sendConfirmacaoUser(Usuario user){
		
		
		StringBuffer msg = getHtmlConfirmacaoUser(user);
		
		Email email =  new Email(user.getContato().getEmail(),user.getNome(),msg,"Confirmação de usuário");
		email.sendHtmlEmail();
	}
	
	
	public StringBuffer getHtmlConfirmacaoUser(Usuario user){
		
		
		StringBuffer buffer  = new StringBuffer();
		
		buffer.append("<p>Olá "+user.getNome()+".</p>");
		
		return buffer;
	}
	
}
