package Bean;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import util.Contato;
import util.Usuario;
import util.Validacao;
import Modelo.UsuarioDAO;

@ManagedBean(name="validacaoBean")
public class ValidacaoBean {

	//Email 1
	public void validaEmail1(FacesContext ctx, UIComponent component, Object value) 
			throws ValidatorException
			{

		FacesMessage fma = new FacesMessage();
		fma.setSeverity(FacesMessage.SEVERITY_ERROR);

		Map<String,String> ats = ctx.getExternalContext().getRequestParameterMap();

		String email1 = ats.get("email1");


		//Validação de regex
		if(this.validaRegexEmail(email1))
		{
		
		//Verifica se o email já esta cadastrado
		if(new UsuarioDAO().existeChave(Contato.class,"email",email1)){

			fma = new FacesMessage("O email '"+email1+"' já está em uso no sistema. Por favor, insira outro email.");
			fma.setDetail("----");
			throw new ValidatorException(fma);	  

		}
		

	   }

		else
		{
			
			fma = new FacesMessage("Por favor, insira um email válido");
			fma.setDetail("----");
			throw new ValidatorException(fma);	  
			
		}
	}
	
	//Email 2
	public void validaEmail2(FacesContext ctx, UIComponent component, Object value) 
				throws ValidatorException
				{

			FacesMessage fma = new FacesMessage();
			fma.setSeverity(FacesMessage.SEVERITY_ERROR);

			Map<String,String> ats = ctx.getExternalContext().getRequestParameterMap();


			String email1 = ats.get("email1");
			String email2 = ats.get("email2");

			//Verifica se o emails são diferentes
			if(!email1.equals(email2))
			{
				fma = new FacesMessage("Os emails não conferem..");
				fma.setDetail("----");
				throw new ValidatorException(fma);	  
			}

		}

	//SENHA
	public void validaSenha(FacesContext ctx, UIComponent component, Object value) 
				throws ValidatorException
				{

			FacesMessage fma = new FacesMessage();
			fma.setSeverity(FacesMessage.SEVERITY_ERROR);

			Map<String,String> ats = ctx.getExternalContext().getRequestParameterMap();
            String senha1 = ats.get("senha1");
		

			//Verifica se a senha possue o minino de caracteres
			if(senha1.length()<5)
			{

				fma = new FacesMessage("A senha dever conter pelo menos 5 caracteres.");
				fma.setDetail("----");
				throw new ValidatorException(fma);	

			}



		}
	
	
	//SENHA 2
	public void validaSenha2(FacesContext ctx, UIComponent component, Object value) 
			throws ValidatorException
			{


		FacesMessage fma = new FacesMessage();
		fma.setSeverity(FacesMessage.SEVERITY_ERROR);
		Map<String,String> ats = ctx.getExternalContext().getRequestParameterMap();

		String senha1 = ats.get("senha1");
		String senha2 = ats.get("senha2");

		//Verifica se a senha possue o minino de caracteres
		if(senha1.length()<5)
		{



		}
		//Verifica se as senhas são iguais
		else if(senha1.equals(senha2)!=true)
		{

			fma = new FacesMessage("As senhas não conferem.");
			fma.setDetail("----");
			throw new ValidatorException(fma);	
		}

	}
	
	
	//CPF OU CNPJ
	public void validaCpf_Cnpj(FacesContext ctx, UIComponent component, Object value) 
			throws ValidatorException
			{
		
		System.out.println("Chegou no valida");

        FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);

		//Recupera os parametros do contexto
		Map<String,String> ats =  ctx.getExternalContext().getRequestParameterMap();

		
		String doc = ats.get("doc");

		switch(Validacao.getTipoDoc(doc)){

		case Validacao.IS_CPF :



			break;

		case Validacao.IS_CNPJ:



			break;

		default: 

			msg.setSummary("Por favor, insira um cpf ou um cnpj válido.");
			throw new ValidatorException(msg);


		}

       }
	
	
	//VALIDAÇÃO DE NÚMERO
	public void validaNumero(FacesContext ctx, UIComponent component, Object value)
		throws ValidatorException{
			
			String numero = value.toString();
			numero = numero.replaceAll("-","");
			
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Por favor, entre com um número válido");
			
			try{
				Integer.parseInt(numero);
			}
			catch(Exception e){
			
				throw new ValidatorException(msg);
				
			}
			
		}
	    
	


	//Validação de cpf em tempo real
	public void validacpftemp() 
			throws ValidatorException
			{

		FacesMessage fma = new FacesMessage();
		fma.setSeverity(FacesMessage.SEVERITY_ERROR);

		Map<String,String> ats = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		String doc = ats.get("doc");

		 //Retira pontos e traços
		 doc = doc.replace(".","");
		 doc = doc.replace("-","");
		 doc = doc.replace("/","");

		if(doc.length()>=11)

		{

			//Verifica se o cpf ou cnpj são validos   
			if(validaCpfOrCnpj(doc)) 
			{	 

				//Verifica se já esxiste um usuário com mesmo cpf
				if(new UsuarioDAO().existeChave(Usuario.class,"cpf",doc)){

					fma = new FacesMessage("O cpf'"+doc+"' já está em uso no sistema. Por favor, insira outro cpf ou cnpj.");
					fma.setDetail("----");
					FacesContext.getCurrentInstance().addMessage(null,fma); 

				}

				//Verifica se já esxiste um usuário com mesmo 
				else if(new UsuarioDAO().existeChave(Usuario.class,"cnpj",doc)){

					fma = new FacesMessage("O Cnpj'"+doc+"' já está em uso no sistema. Por favor, insira outro cpf ou cnpj.");
					fma.setDetail("----");
					FacesContext.getCurrentInstance().addMessage(null,fma); 

				}

			}
			else
			{

				fma = new FacesMessage("O documento que você digitou não é válido, por favor entre com um docmuneto válido.");
				fma.setDetail("----");
				FacesContext.getCurrentInstance().addMessage(null,fma); 

			}

		}

			}


	//Validação de email em tempo real
	public void validaemailtemp() 
			throws ValidatorException
			{

		FacesMessage fma = new FacesMessage();
		fma.setSeverity(FacesMessage.SEVERITY_ERROR);

		Map<String,String> ats = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		String email = ats.get("email");



		if(email.contains("@") && email.contains("."))

		{



			if(new UsuarioDAO().existeChave(Contato.class,"email",email)){

				fma = new FacesMessage("O email '"+email+"' já está em uso no sistema. Por favor, insira outro email.");
				fma.setDetail("----");
				FacesContext.getCurrentInstance().addMessage(null,fma); 



			}


		}

			}

	
	public boolean validaRegexEmail(String email){
	
		return true;
		
		
	}

	public boolean validaCpfOrCnpj(String doc){



		switch(Validacao.getTipoDoc(doc)){


		//PESSOA FISICA 
		case Validacao.IS_CPF :
			return true;


		case Validacao.IS_CNPJ:
			return true;


		default: 
			return false;


		} 
	}

}
