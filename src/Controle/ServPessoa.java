package Controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Modelo.ContatoDAO;
import Modelo.Email;
import Modelo.LocalizacaoDAO;
import Modelo.PessoaDAO;
import Modelo.UsuarioDAO;
import util.Contato;
import util.Localizacao;
import util.Pessoa;

/**
 * Servlet implementation class ServPessoa
 */
public class ServPessoa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final int CADASTRAR =1;
	public static final int LOGAR=2;  
	
	private StringBuffer result;//Resultado da solicita��o
	private int soli;//Solicita��o recebida
	private String page_pos;//Pagina a ser encaminhada apos o processamento da solicita��o
	private Pessoa pessoa;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServPessoa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    
		     //Controle da sess�o
		     HttpSession sessao = request.getSession();
		     
		     
		     //Recebe a solicita��o
		     soli =Integer.parseInt(request.getParameter("SOLI"));
		     
		     page_pos =  request.getParameter("page_pos");
		     
		     result = null;		     
		     //Cadastro de pessoa
		     if(soli==ServPessoa.CADASTRAR)
		     {
		      
		     pessoa = new Pessoa();
		     pessoa.setCpf(request.getParameter("cpf"));
		     pessoa.setNome(request.getParameter("nome"));	 
		     pessoa.setSenha(request.getParameter("senha"));
		     
		     Contato contato = new Contato();
		     contato.setEmail(request.getParameter("email"));
		     
		     //Contato incompleto
		     contato.setComp(0);
		     
		     pessoa.setContato(contato);
		     
		     //status de n�o-confirmado
		     pessoa.setStatus(0);
		     
		     //Tentativa de realiza��o de cadastro
		     result = cadastrarPessoa(pessoa);
		     request.setAttribute("result",result);
		     
		     
		     if(result!=null)
		     request.getRequestDispatcher("index.jsp?page=cadastro_pessoa_fisica.jsp").forward(request,response);
		     else
		     {
		   
		    	 
		     request.getRequestDispatcher("index.jsp?page=cadastro_sucesso.jsp?email="+contato.getEmail()).forward(request,response);	 
		     }
		     
		     }
		     
		     else if(soli==ServPessoa.LOGAR)
		     {
		    	 
		    	 String cpf = request.getParameter("cpf");
		    	 String senha = request.getParameter("senha");
		    	 
		    	 Pessoa pessoa = new PessoaDAO().logar(cpf, senha);
		    	 
		    	 sessao.setAttribute("usuario",pessoa);
		    	 
		    	 
		    	 
		     }
		
		
	}

	private StringBuffer cadastrarPessoa(Pessoa pessoa){
		
		 StringBuffer buffer2;
		
		 if(new UsuarioDAO().existeChave(Pessoa.class,"cpf",pessoa.getCpf()))
		 {
			 
		 buffer2 = new StringBuffer();	 
		 buffer2.append("<h3>O cpf("+pessoa.getCpf()+") j� est� cadastrado no sistema.</h3>");
		 buffer2.append("<p><a href=\"#\">Clique aqui</a> para realizar login.</p>");
		 
		  
		 return buffer2 ;
		 }
		 
		 else if(new UsuarioDAO().existeChave(Contato.class,"email",pessoa.getContato().getEmail()))
		 {
			 
			 buffer2 = new StringBuffer();	 
			 buffer2.append("<h3>O <strong>email("+pessoa.getContato().getEmail()+")</strong> j� est� cadastrado no sistema.</h3>");
			 buffer2.append("<p><a href=\"#\">Clique aqui</a> para logar.</p>");	 
			 return buffer2 ;
		 }
		 else
		 {
		 
	     //Salva uma localiza��o generica para o usu�rio
	     Localizacao loc =  new Localizacao();	
	     new LocalizacaoDAO().insert(loc);
	     
	     //Salva um contato generico para o usu�rio
	     new ContatoDAO().insert(pessoa.getContato());
	     
	     //Seta a localiza��o do usu�rio
	     pessoa.setLocalizacao(loc);
			 
		 if(new UsuarioDAO().insert(pessoa))
		 {
		 
		
		 	 
		 //Envia o email de confirma��o
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("<h3>Ol� <strong>"+pessoa.getNome()+"<strong>!<h3>");
		 buffer.append("<h4>Recebemos uma solicita��o de cadastro para o nosso site</h4>");
		 buffer.append("<h5>Clique no link abaixo para confirmar sua inscri��o no nosso sitema.</h5>");
		 buffer.append("<h5>Se voc� n�o realizou esta solicita��o, ignore esta mensagem.</h5>");
		 buffer.append("<h5><a href=\"http://localhost:8080/EstradaReal3/index.jsp?page=confirma_usuario.jsp&&id_usuario="+pessoa.getId() +"\">Confirmar Cadastro</a></h5>");
		     
		 //new Email(pessoa.getContato().getEmail(),pessoa.getNome(),buffer,"Confirma��o de Usu�rio").sendHtmlEmail();
		 	 
		 return null;
		 
		 }
		 else
		 {
		 buffer2 = new StringBuffer();	 
		 buffer2.append("<h3>O sistema encontra-se em manuten��o, tente novamente mais tarde.</h3>");	 
		 return buffer2;
		 } 
		 }
	}
	
}
