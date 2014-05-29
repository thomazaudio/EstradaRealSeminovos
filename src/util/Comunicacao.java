package util;

import Modelo.DestaqueDAO;
import Modelo.Email;
import Modelo.UsuarioDAO;

public class Comunicacao {
	
	//-----ASSUNTO----
	//PROPOSTA
	public static final String ASSUNTO_PROPOSTA_RECEBIDA="Nova Proposta Recebida";//Assunto do email enviado ao anunciante em caso de nova proposta
	public static final String ASSUNTO_PROPOSTA_ENVIADA="C�pia da Proposta";//Assunto do email que ser� enviado a quem est� fazendo a proposta(C�pia da Proposta)
   
	
	//--CCONFIRMACAO DE USU�RIO
	public static final String ASSUNTO_CONFIRMACAO_USUARIO="Confirma��o de usu�rio";
	
	
	//--RELEMBRAR SENHA
	public static final String ASSUNTO_RELEMBRA_SENHA="Senha de acesso ao portal";
	
	
	//--ALTERAR EMAIL
	public static final String ASSUNTO_TROCA_EMAIL="Alteração de email";
	
	
	//-----Textos--
	//Recebimento de nova proposta
	public static final StringBuffer TEXTO_PROPOSTA_RECEBIDA(String nome){
		
		StringBuffer texto =  new StringBuffer();
		
		texto.append("<h4>OlÁ "+nome+"!</h4>");
		texto.append("<p>Voc� acaba de receber um proposta referente a um ve�culo cadastrado no nosso site.</p>");
		
    	return texto;
    }
	
	//Proposta enviada(cópia da proposta)
	public static final StringBuffer TEXTO_PROPOSTA_ENVIADA(String nome){
		
		StringBuffer texto =  new StringBuffer();
		
		texto.append("<h4>Ol� "+nome+"!</h4>");
		texto.append("<p>Obrigado por utilizar nosso sitema!.</p>");
		
    	return texto;
    }
	
	//Relembrar senha de usuário
	public static final StringBuffer TEXTO_RELEMBRAR_SENHA(String senha){
		
		StringBuffer texto =  new StringBuffer();
		
		texto.append("Sua senha de acesso: "+senha);
		
		return texto;
		
	}
	
	
	//MENSAGEM DE CONFIRMAÇÃO DE USUÁROP EM CASO DE CADASTRO
		public StringBuffer getHtmlConfirmacaoUser(Usuario user){
			
			
			StringBuffer buffer  = new StringBuffer();
			
			buffer.append("<p>Olá "+user.getNome()+".</p>");
			
			String link_confirmacao = SystemEmpresa.DOMINIO+"/Auto/confirma_usuario.jsp?id_usuario="+user.getId();
			
			buffer.append("<a href=\""+link_confirmacao+"\" >Clique aqui para confirmar o email</a>");
			buffer.append("<p>Caso o link acima não funcione digite este endereço no navegador: "+link_confirmacao+"</p>");
			
			return buffer;
			
		}
		
		
	//MENSAGEM DE TROCA DE EMAIL
		public static final StringBuffer getTextoTrocaEmail(TrocaEmail troca){
			
			StringBuffer texto  = new StringBuffer();
			
			texto.append("<h3>Olá!</h3>");
			texto.append(String.format("<p>Clique no link para confirmar seu email: <a href=\"%s\" >Confirmar Email</a>",SystemEmpresa.DOMINIO+"ChangeEmail?cod_troca="+troca.getId()));
			texto.append("<p>Caso não tenha efetuado o pedido dessa requisição favor desconsiderar essa mensagem.</p>");
			
			return texto;
		}
		
	///////FIM DAS MENSAGEM ESTATICAS	
	
	
		
	////////-------ENVIOS----	
	 
	//Envia um email para confirma��o de usu�rio
	public void sendConfirmacaoUser(Usuario user){
		
		
		StringBuffer msg = getHtmlConfirmacaoUser(user);
		
		Email email = new Email();
	
		email.enviaEmail(user.getContato().getEmail(),user.getNome(),msg,ASSUNTO_CONFIRMACAO_USUARIO);
	}
	
	
	
	
	
	//ENVIA UMA MENSAGEM DE DESTAQUE DESATIVADO PARA O USUÁRIO DONO DO DESTAQUE
	public void sendMensagemDestaqueDesativado(long id_destaque){
		 
		//Recupera o destaque
		Destaque d =  new DestaqueDAO().getDestaque(id_destaque);
		
		//Recupera o anúncio (d.COD_VEICULO)
		
		//Recuperar o usuário e contato
		
		//Envia a mensagem
		
		System.out.println("Enviando mensagem de destaque desativado");
		
	}
	
	//ENVIA UMA MENSAGEM DIZENDO QUE O ANÚNCIO FOI DESATIVADO
	public void sendMensagemAnuncioDesativado(long id_veiculo){
		
		//Recupera o veiculo completo
		
	}
	
	//ENVIA A MENSAGEM PARA RELEMBRAR A SENHA REAQLACIONADA AO USUÁRIO
	public void sendMensagemRelembrarSenha(String email, String senha){
		
		Email envio =  new Email();
		envio.enviaEmail(email,"---",TEXTO_RELEMBRAR_SENHA(senha),ASSUNTO_RELEMBRA_SENHA);
		
		
	}
	
	//PEDIDO DE TROCA DE EMAIL
	public void sendMensagemTrocaEmail(TrocaEmail troca){
		
		Email envio =  new Email();
		envio.enviaEmail(troca.getEmailtroca(),"",getTextoTrocaEmail(troca),ASSUNTO_TROCA_EMAIL);
		
	}
	
	
	
}
