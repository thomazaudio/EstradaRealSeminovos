package Controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Modelo.ContatoDAO;
import Modelo.Email;
import Modelo.EmpresaDAO;
import Modelo.LocalizacaoDAO;
import Modelo.UsuarioDAO;
import util.Contato;
import util.Empresa;
import util.EmpresaUtil;
import util.Localizacao;
import util.SystemUtil;
import util.Usuario;

/**
 * Servlet implementation class ServEmpresa
 */
public class ServEmpresa extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final int CADASTRAR =1;
	public static final int LOGAR=2; 
	public static final int GET_LOGO=3; 
	public final String PAGE_DEFAULT="index.jsp?page=ini_usuario.jsp";
	
	private StringBuffer result;//Resultado da solicita��o
	private int soli;//Solicita��o recebida
	private String page_pos;//Pagina a ser encaminhada apos o processamento da solicita��o
	private Empresa empresa;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServEmpresa() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
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

		//Cadastro de nova empresa
		if(soli==CADASTRAR)
		{

			empresa = new Empresa();	 
			empresa.setNome(request.getParameter("nome"));
			empresa.setCnpj(request.getParameter("cnpj"));
			empresa.setSenha(request.getParameter("senha"));

			//Contato generico para empresa
			Contato contato = new Contato();
			contato.setEmail(request.getParameter("email"));

			//Contato incompleto
		    contato.setComp(0);
			
			empresa.setContato(contato);
          
			//Marca com usu�rio n�o confirmado
			empresa.setStatus(0);
			
			//Tentativa de realiza��o de cadastro
		    result = cadastrarEmpresa(empresa);
		    request.setAttribute("result",result);
		     
		     
		    //Encaminha para a p�gina novamente
		    if(result!=null)
		     request.getRequestDispatcher("index.jsp?page=cadastro_pessoa_juridica.jsp").forward(request,response);
		     
		    else
		    request.getRequestDispatcher("index.jsp?page=cadastro_sucesso.jsp?email="+contato.getEmail()).forward(request,response);	 
		    
		}//Fim da solicita��o de cadastro
		
		
		else if(soli==LOGAR)
		{
			
			 String cnpj = request.getParameter("cnpj");
	    	 String senha = request.getParameter("senha");
	    	 
	    	 //Tenta realizar login
	    	 Empresa emp  = new EmpresaDAO().logar(cnpj, senha);
	    	
	    	 
	    	 //Verifica se o login foi bem  ou mal sucedido
	    	 if(emp==null)
	    	 {	 
	         request.setAttribute("result",new EmpresaUtil().getBufferErroLogin());	 
	         request.getRequestDispatcher("login_empresa.jsp").forward(request,response);
	    	 }
	    	 
	    	 else 
	    	 {
	    	 //Joga o usu�rio na sess�o
	    	 sessao.setAttribute("usuario",emp);
	    	 
	    	 if(page_pos!=null)
	    	 //Encaminha para a p�gina solicitada anteriormente
	    	 request.getRequestDispatcher(page_pos).forward(request, response);	 
	    	 
	    	 else
	    	 //Encaminha para pagina default	 
	    	 request.getRequestDispatcher(PAGE_DEFAULT).forward(request, response);	
	    	 }
		}


	}


	private StringBuffer cadastrarEmpresa(Empresa empresa){

		//Valida��es

		//Valida CNPJ
        if(new UsuarioDAO().existeChave(Usuario.class,"cnpj",empresa.getCnpj()))
		return new EmpresaUtil().getBufferCnpjExist(empresa.getCnpj());


		//Valida EMAIL
		if(new UsuarioDAO().existeChave(Contato.class,"email",empresa.getContato().getEmail()))
		return new EmpresaUtil().getBufferEmailExist(empresa.getContato().getEmail());

		else
		{

			//Salva uma localiza��o generica para a empresa
			Localizacao loc =  new Localizacao();	
			new LocalizacaoDAO().insert(loc);

			//Salva um contato generico para a empresa
			new ContatoDAO().insert(empresa.getContato());

			//Seta a localiza��o da empresa
			empresa.setLocalizacao(loc);

			if(new EmpresaDAO().insert(empresa))
			{

				//Envia o email de confirma��o para o email cadastrado	 
			 	 
				 //Recupera o buffer de email
				 StringBuffer buffer_email = new EmpresaUtil().getBufferEmailCadSucesso(empresa.getNome(),empresa.getId());
				
				 //Realiza o envio de email de confirma
				 new Email(empresa.getContato().getEmail(),empresa.getNome(),buffer_email,"Confirma��o de Usu�rio").sendHtmlEmail();
				 
				 return null;	 
			}
			else
			return new SystemUtil().getHtmlErroSystem();

		}
	}

}
