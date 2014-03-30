package util;

public class EmpresaUtil {
   
	
	//Recupera o texto que ser� enviado para o email dos usu�rio em caso de sucesso no cadastro
	public StringBuffer getBufferEmailCadSucesso(String nome,long id){
		
		 StringBuffer buffer_email = new StringBuffer();
		 buffer_email .append("<h3>Ol� <strong>"+nome+"<strong>!<h3>");
		 buffer_email .append("<h4>Recebemos uma solicita��o de cadastro para o nosso site</h4>");
		 buffer_email .append("<h5>Clique no link abaixo para confirmar sua inscri��o no nosso sitema.</h5>");
		 buffer_email .append("<h5>Se voc� n�o realizou esta solicita��o ignore esta mensagem.</h5>");
		 buffer_email .append("<h5><a href=\"http://localhost:8080/EstradaReal3/index.jsp?page=confirma_usuario.jsp&&id_usuario="+id+"\">Confirmar Cadastro</a></h5>");
	     return buffer_email;
	     
	  
	}
	
	
	//Recupera o texto no caso de email j� existente no sistema
	public StringBuffer getBufferEmailExist(String email){
		
		StringBuffer buffer;
		buffer = new StringBuffer();
		buffer.append("<h3>O email<strong>("+email+")</strong> j� est� cadastrado no sistema.</h3>");
		buffer.append("<p><strong><a href\"#\">Clique aqui</a></strong> para realizar login.</p>");
		return buffer;
		
	}
	
	
	//Recupera o texto em caso de cnpj j� existente no sistema
	public StringBuffer getBufferCnpjExist(String cnpj){
		
		StringBuffer buffer;
		buffer = new StringBuffer();
		buffer.append("<p class=\"alerta error\">O  cnpj<strong>("+cnpj+")</strong> j� est� cadastrado no sistema.</p>");
		buffer.append("<p><strong><a href\"#\">Clique aqui</a></strong> para realizar login.</p>");
		return buffer;
      	
	}
	
	public StringBuffer getBufferErroLogin(){
		
		StringBuffer buffer;
		buffer = new StringBuffer();
		buffer.append("<p class=\"alerta error\">Cnpj ou senha inv�lidos</p>.");
		return buffer;
	}
	
	
	
	
}
