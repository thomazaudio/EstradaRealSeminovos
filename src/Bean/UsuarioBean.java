package Bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import Modelo.ContatoDAO;
import Modelo.ImgDAO;
import Modelo.LocalizacaoDAO;
import Modelo.PerguntaDAO;
import Modelo.TrocaEmailDAO;
import Modelo.UsuarioDAO;
import util.Debug;
import util.Imagem;
import util.Pergunta;
import util.TrocaEmail;
import util.Usuario;


@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {

	private FacesContext faces;
	private Usuario user;
	private HttpSession sessao;
	private DefaultStreamedContent logo;
	public static long idUser;
	private String email;
	private String textoIniUsuario;
	private String linkEditPerfil;

	public String getLinkEditPerfil() {
		
	    int tipo = new UsuarioDAO().getTipoUser(user.getId());
		
	    if(tipo==Usuario.EMPRESA)
	    return "edit_perfil_empresa.xhtml";
	    
	    else
	    return "edit_perfil_pessoa.xhtml";	
	    
		
	}


	public void setLinkEditPerfil(String linkEditPerfil) {
		this.linkEditPerfil = linkEditPerfil;
	}


	public String getTextoIniUsuario() {
		

		if(this.getUser().getContato().getConfirmado()==0)
		textoIniUsuario = "O seu email '"+this.getUser().getContato().getEmail()+"' ainda não foi confirmado.";
		
		return textoIniUsuario;
	}


	public void setTextoIniUsuario(String textoIniUsuario) {
		
			
		this.textoIniUsuario = textoIniUsuario;
	}


	public String getEmail() {
		
		
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public static long getIdUser() {
		return idUser;
	}


	public static void setIdUser(long idUser) {
		UsuarioBean.idUser = idUser;
	}


	public void setLogo(DefaultStreamedContent logo) {
		this.logo = logo;
	}


	private String nome="Thomaz";

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public UsuarioBean(){

		faces = FacesContext.getCurrentInstance();
		sessao = (HttpSession) faces.getExternalContext().getSession(false);
		getLogo();
		
		this.setEmail(this.getUser().getContato().getEmail());
	}


	//RECUPERA O USUÁRIO EM SESSÃO
	public Usuario getUser() {
          
		try{
			
        user = (Usuario)sessao.getAttribute("usuario");
        
		}
		catch(Exception e){
			
		}
		
		if(user==null)
		{
		user =  new Usuario();
		user.setNome("visitante");
		}
		
        return user;
	}
    
	
	public void setUser(Usuario user) {
		this.user = user;
	}

	//SALVA ALTERAÇÕES GERAIS (CLASSE PRINCIPAL)
	public void salvar(){

		System.out.println("Nome: "+user.getNome());
		new UsuarioDAO().update(user);
		addMessage("Salvo com sucesso");

	}

	//SALVA ALTERAÇÕES DE CONTATO
	public void salvarContato(){

		System.out.println("Nome: "+user.getNome());
		new ContatoDAO().update(user.getContato());;
		addMessage("Salvo com sucesso");

	}
	
	public void salvarLocalizacao(){
		
		System.out.println("Cod loc: "+user.getLocalizacao().getCod_cidade());
		new LocalizacaoDAO().update(user.getLocalizacao());;
		addMessage("Salvo com sucesso");
	}


	//ADICIONA UMA MENSAGEM AO CONTEXTO
	public void addMessage(String summary) {  
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
		FacesContext.getCurrentInstance().addMessage(null, message);  
	} 


	//RECUPERA A LOGO DO USUÁRIO (IMAGEM DE PERFIL)
	public DefaultStreamedContent getLogo() {

		try{
			
			
			logo = new DefaultStreamedContent();
			logo.setContentType("image/jpeg");
			logo.setStream(new ImgDAO().getLogo(user.getId()).getImg());
			
		
			
			return logo;
		}
		catch(Exception e){

			Debug.gerar("Bean","UsuarioBean","getLogo", e.getMessage());
			return null;
		}

	}


	
    //SALVA ALTERAÇÕES NO EMAIL 
	public void salvarEmail(){


		//Recupera os atributos
		Map<String,String> ats = faces.getExternalContext().getRequestParameterMap();
		String email1;

		email1 = ats.get("email1");

		  
			
			//Lança um novo pedido de alteração
			TrocaEmail troca = new TrocaEmail();
			troca.setIdUsuario(user.getId());
			troca.setIdContato(user.getContato().getId());
			troca.setEmailtroca(email1);
			
			new TrocaEmailDAO().insert(troca);
			
			addMessage("Enviamos um email para '"+email1+"' com um link de atiavação.");
	}
	
	
	//Recupera as todo as as perguntas recebidas do Usuário
	public ArrayList<Pergunta> getPerguntasRecebidas(){
		
		
		
		ArrayList<Pergunta> perguntas = new PerguntaDAO().getPerguntasRecebidas(this.getUser().getId());
		
		System.out.println("Quanidade de perguntas: "+perguntas.size());
		
		return perguntas;
	}
	
	
	//Responde uma pergunta
	public void EnviaRespostaPergunta(AjaxBehaviorEvent event){
		
	FacesMessage msg =  new FacesMessage();	
	msg.setSeverity(FacesMessage.SEVERITY_INFO);
	msg.setSummary("A resposta foi enviada com sucesso!");
		
		
	Pergunta p;	
		
	long id =  Long.parseLong(faces.getExternalContext().getRequestParameterMap().get("id_pergunta")); 	
		
	String resposta = faces.getExternalContext().getRequestParameterMap().get("resposta");
	
	//Recupera a pergunta
	p =  new PerguntaDAO().getPergunta(id);
	
	//Altera o status
	p.setStatus(Pergunta.RESPONDIDA);
	
	p.setResposta(resposta);
	
	new PerguntaDAO().update(p);
	
	faces.addMessage(null,msg);
		
	}
	
	
	//Exclui uma pergunta
	public void ExcluirPergunta(AjaxBehaviorEvent event){
			
		FacesMessage msg =  new FacesMessage();	
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("A resposta foi excluida com sucesso!");
			
			
		Pergunta p;	
			
		long id =  Long.parseLong(faces.getExternalContext().getRequestParameterMap().get("id_pergunta")); 	
			
		
		 p =  new Pergunta();
		
		 p.setId(id);
		
		
		//Exclui a pergunta
		new PerguntaDAO().delete(p);
		
		
		
		faces.addMessage(null,msg);
			
		}
		
	
	
	//texto da quantidade de pergutnas a serem respondidas
	
	public String getTextoQuantPerguntas(){
		
		String texto = "";
		int count = 0;
		ArrayList<Pergunta> perguntas = new PerguntaDAO().getPerguntasRecebidas(this.getUser().getId());
		
		for(int i=0;i<perguntas.size();i++)
		{
			
			if(perguntas.get(i).getStatus()==Pergunta.NAO_RESPONDIDA)
			count++;	
			
		}
		
		if(count>0)
		texto = "("+count+" não respondidas)";
		
		return texto;
	}
	
	public void logOff(){
		
	HttpSession sessao = (HttpSession)	FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	sessao.invalidate();
	
    try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("../esc_login.jsf");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
}
