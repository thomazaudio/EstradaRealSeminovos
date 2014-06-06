package Bean;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.ContatoDAO;
import Modelo.LocalizacaoDAO;
import Modelo.UsuarioDAO;
import util.Comunicacao;
import util.Contato;
import util.Empresa;
import util.Localizacao;
import util.Pessoa;
import util.Usuario;
import util.Validacao;


@ManagedBean(name="cadastroBean")

public class CadastroBean {

	
	private Usuario usuarioSessionCad; 
	private String pagePosSession;
	
     public String getPagePosSession() {
    	 
    	 HttpSession sessao =((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)); 
    	 
     	pagePosSession =(String) sessao.getAttribute("pagePosSession");
    	 
		return pagePosSession;
	}


	public void setPagePosSession(String pagePosSession) {
		
		HttpSession sessao =((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)); 
		sessao.setAttribute("pagePosSession",pagePosSession);
		this.pagePosSession = pagePosSession;
		this.pagePosSession = pagePosSession;
	}


	public Usuario getUsuarioSessionCad() {
    	 
    	
    	HttpSession sessao =((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)); 
    	 
    	usuarioSessionCad =(Usuario) sessao.getAttribute("usuarioSessionCad");
		return usuarioSessionCad;
	}


	public void setUsuarioSessionCad(Usuario usuarioSessionCad) {
		
		HttpSession sessao =((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)); 
		sessao.setAttribute("usuarioSessionCad",usuarioSessionCad);
		this.usuarioSessionCad = usuarioSessionCad;
	}

	
	
	//Cadastro de usu�rio
	public void cad() throws IOException{
	 
	
		
	 FacesMessage msg =  new FacesMessage(null,"");
	 msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	 
	 //Recupera os parametros do contexto
	 Map<String,String> ats =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
	 //Salva um contato gen�rico
	 Contato c = new Contato();
	 c.setTel1(ats.get("tel"));
	 c.setTel2("");
	 new ContatoDAO().insert(c);
	 
	 
	 
	 //JOGA A P�GINA DE OrIGEM NA SESS�O
	 this.setPagePosSession(ats.get("page_pos2"));
	 
	 
	 //verifica o tipo de usu�rio(Verifica��o se � cnpj ou cpf)
	 String doc = ats.get("doc");
	
	 //Retira pontos e tra�os
	 doc = doc.replace(".","");
	 doc = doc.replace("-","");
	 doc = doc.replace("/","");
	 
	 
	 
	 
	 
	 switch(Validacao.getTipoDoc(doc)){
	  
	 //PESSOA FISICA 
	 case Validacao.IS_CPF :
	 
	 //Verifica se o documento est� cadastrado no sistema
	 if(new UsuarioDAO().existeChave(Usuario.class,"cpf",doc))	
	 {
		 msg.setSummary("O cpf'"+doc+"' j� est� em uso no sistema. Por favor, insira outro cpf ou cnpj.");
		 FacesContext.getCurrentInstance().addMessage(null,msg);	 
	 }
	 
	 else 
	 {
	 Pessoa pessoa = new Pessoa();	 
     pessoa.setContato(c);
     pessoa.setCpf(doc);
     
     this.setUsuarioSessionCad(pessoa);
     
     
     //redireciona para pr�xima etapa de cadastro
     FacesContext.getCurrentInstance().getExternalContext().redirect("cad_step2.jsf");
	 }
	 
     break;
	 
	 case Validacao.IS_CNPJ:
	 //Verifica se o documento est� cadastrado no sistema
	 if(new UsuarioDAO().existeChave(Usuario.class,"cnpj",doc))	
	 {
		msg.setSummary("O cnpj'"+doc+"' j� est� em uso no sistema. Por favor, insira outro cpf ou cnpj.");
		FacesContext.getCurrentInstance().addMessage(null,msg);	 
	 }	 
	 else
	 {
		 Empresa emp = new Empresa();	 
	     emp.setContato(c);
	     emp.setCnpj(doc);
	     
	     this.setUsuarioSessionCad(emp);	
	     
	     //redireciona para pr�xima etapa de cadastro
	     FacesContext.getCurrentInstance().getExternalContext().redirect("cad_step2_empresa.jsf");
	 }
	 break;
	 
	 default: 
	
	 msg.setSummary("Por favor, insira um cpf ou um cnpj v�lido.");
	 FacesContext.getCurrentInstance().addMessage(null,msg);
	 break;	 
	 
	 } 
		
	}
	
	
	//Cadastro de usu�rio em segunda etapa
	public void cadstep2() throws IOException{
		
		
		
		 FacesMessage msg =  new FacesMessage(null,"");
		 msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		
		//Recupera os parametros do contexto
		Map<String,String> ats =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		
		
		
		//Recupera o cadastro em Sessao
		Usuario user = this.getUsuarioSessionCad();
		
		//Salva uma localizacao gen�rica para o usu�rio
		Localizacao loc = new Localizacao();
		loc.setCod_cidade(Integer.parseInt(ats.get("cidade")));
		new LocalizacaoDAO().insert(loc);
		user.setLocalizacao(loc);
		
		
		//Completa os valores
		user.setNome(ats.get("nome"));
		user.getContato().setEmail(ats.get("email"));
		user.setSenha(ats.get("senha1"));
		
		
		//Valida��o dos dados
	    if( new UsuarioDAO().existeChave(Contato.class,"email",user.getContato().getEmail()))
	    {
	    	msg.setSummary("O email '"+user.getContato().getEmail()+"' j� est� em uso no sistema. Por favor, insira outro email ou fa�a login.");
	       FacesContext.getCurrentInstance().addMessage(null,msg);	
	    }
	    else
	    {
		
		//Atualiza o contato
		new ContatoDAO().update(user.getContato());
		
		
		
		//Salva o usuario no banco de dados
		new UsuarioDAO().insert(user);
	
		//Envia o email de confirma��o
		new Comunicacao().sendConfirmacaoUser(this.getUsuarioSessionCad());
		
		//Loga o usu�rio
		HttpSession sessao =((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)); 
		sessao.setAttribute("usuario",new UsuarioDAO().logar(user.getContato().getEmail(),user.getSenha()));
		
		//Redireciona pra a pagina de anúncios
		FacesContext.getCurrentInstance().getExternalContext().redirect("arearestritausuario/lista_anuncios.jsf");
	
	    }
	    
	    }
	
	public void red(){
		
		try{
			
		FacesMessage m = new FacesMessage("");	
		FacesContext.getCurrentInstance().addMessage(null,m);	
		
	      
		FacesContext.getCurrentInstance().wait(2000);
		FacesContext.getCurrentInstance().getExternalContext().redirect("../close_func.html");
		}
		catch(Exception e){
			
		}
	}
	
	//VALIDA��ES
	
	
}
