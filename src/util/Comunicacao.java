package util;

import Modelo.Email;

public class Comunicacao {

	
	 
	//Envia um email para confirma��o de usu�rio
	public void sendConfirmacaoUser(Usuario user){
		
		
		StringBuffer msg = getHtmlConfirmacaoUser(user);
		
		Email email =  new Email(user.getContato().getEmail(),user.getNome(),msg,"Confirma��o de usu�rio");
		email.sendHtmlEmail();
	}
	
	
	public StringBuffer getHtmlConfirmacaoUser(Usuario user){
		
		
		StringBuffer buffer  = new StringBuffer();
		
		buffer.append("<p>Ol� "+user.getNome()+".</p>");
		
		return buffer;
	}
	
}
