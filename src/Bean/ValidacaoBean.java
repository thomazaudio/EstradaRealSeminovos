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


		//Valida��o de regex
		if(this.validaRegexEmail(email1))
		{
		
		//Verifica se o email j� esta cadastrado
		if(new UsuarioDAO().existeChave(Contato.class,"email",email1)){

			fma = new FacesMessage("O email '"+email1+"' j� est� em uso no sistema. Por favor, insira outro email.");
			fma.setDetail("----");
			throw new ValidatorException(fma);	  

		}
		

	   }

		else
		{
			
			fma = new FacesMessage("Por favor, insira um email v�lido");
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

			//Verifica se o emails s�o diferentes
			if(!email1.equals(email2))
			{
				fma = new FacesMessage("Os emails n�o conferem..");
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
		//Verifica se as senhas s�o iguais
		else if(senha1.equals(senha2)!=true)
		{

			fma = new FacesMessage("As senhas n�o conferem.");
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

			msg.setSummary("Por favor, insira um cpf ou um cnpj v�lido.");
			throw new ValidatorException(msg);


		}

       }
	
	
	//VALIDA��O DE N�MERO
	public void validaNumero(FacesContext ctx, UIComponent component, Object value)
		throws ValidatorException{
			
			String numero = value.toString();
			numero = numero.replaceAll("-","");
			
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Por favor, entre com um n�mero v�lido");
			
			try{
				Integer.parseInt(numero);
			}
			catch(Exception e){
			
				throw new ValidatorException(msg);
				
			}
			
		}
	    
	


	//Valida��o de cpf em tempo real
	public void validacpftemp() 
			throws ValidatorException
			{

		FacesMessage fma = new FacesMessage();
		fma.setSeverity(FacesMessage.SEVERITY_ERROR);

		Map<String,String> ats = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		String doc = ats.get("doc");

		 //Retira pontos e tra�os
		 doc = doc.replace(".","");
		 doc = doc.replace("-","");
		 doc = doc.replace("/","");

		if(doc.length()>=11)

		{

			//Verifica se o cpf ou cnpj s�o validos   
			if(validaCpfOrCnpj(doc)) 
			{	 

				//Verifica se j� esxiste um usu�rio com mesmo cpf
				if(new UsuarioDAO().existeChave(Usuario.class,"cpf",doc)){

					fma = new FacesMessage("O cpf'"+doc+"' j� est� em uso no sistema. Por favor, insira outro cpf ou cnpj.");
					fma.setDetail("----");
					FacesContext.getCurrentInstance().addMessage(null,fma); 

				}

				//Verifica se j� esxiste um usu�rio com mesmo 
				else if(new UsuarioDAO().existeChave(Usuario.class,"cnpj",doc)){

					fma = new FacesMessage("O Cnpj'"+doc+"' j� est� em uso no sistema. Por favor, insira outro cpf ou cnpj.");
					fma.setDetail("----");
					FacesContext.getCurrentInstance().addMessage(null,fma); 

				}

			}
			else
			{

				fma = new FacesMessage("O documento que voc� digitou n�o � v�lido, por favor entre com um docmuneto v�lido.");
				fma.setDetail("----");
				FacesContext.getCurrentInstance().addMessage(null,fma); 

			}

		}

			}


	//Valida��o de email em tempo real
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

				fma = new FacesMessage("O email '"+email+"' j� est� em uso no sistema. Por favor, insira outro email.");
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
