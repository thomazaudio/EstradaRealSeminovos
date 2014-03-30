package Controle;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.PerguntaDAO;
import util.Pergunta;
import util.Usuario;

/**
 * Servlet implementation class EnviaPergunta
 */
public class EnviaPergunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviaPergunta() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession sessao = request.getSession();
		
		Pergunta pergunta=null;
		Usuario user = null;
		
		
	    //Tenta recuperar os dados da pergunta atraves da sessao ou do request
		pergunta = (Pergunta) sessao.getAttribute("pergunta");
		
		request.getParameter("");
		
		if(pergunta==null)
		{
			pergunta =  new Pergunta();
			
			//Recupera os dados a partir do request
			pergunta.setId_anuncio(Long.parseLong(request.getParameter("id_anuncio")));
			//pergunta.setId_remetente(Long.parseLong(request.getParameter("id_remetente")));
			pergunta.setId_destinatario(Long.parseLong(request.getParameter("id_destinatario")));
			pergunta.setPergunta(request.getParameter("pergunta"));
			pergunta.setResposta("");
			pergunta.setDataPergunta(Calendar.getInstance());
			pergunta.setStatus(Pergunta.NAO_RESPONDIDA);
			
		}
		
		
		//Verifica se o usuário está em sessão
		user = (Usuario) sessao.getAttribute("usuario");
		
		//Se estiver em sessão realiza o envio da pergunta
		if(user!=null)
		{
			
		pergunta.setId_remetente(user.getId());	
		
		//Envia a pergunta
		new PerguntaDAO().insert(pergunta);	
		
		sessao.removeAttribute("pergunta");
			
		request.getRequestDispatcher("pergunta_sucesso.jsp").forward(request, response);
			
			
		}
		
		//Se nao estiver em sessao, joga os dados da pergunta em sessao e seguida redireciona para paagina de login
		else{
		 
		 sessao.setAttribute("pergunta",pergunta);	
			
		 request.getRequestDispatcher("esc_login_simples.jsf?page_pos=EnviaPergunta").forward(request,response);
			
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		HttpSession sessao = request.getSession();
		
		Pergunta pergunta=null;
		Usuario user = null;
		
		
	    //Tenta recuperar os dados da pergunta atraves da sessao ou do request
		pergunta = (Pergunta) sessao.getAttribute("pergunta");
		
		request.getParameter("");
		
		if(pergunta==null)
		{
			pergunta =  new Pergunta();
			
			//Recupera os dados a partir do request
			pergunta.setId_anuncio(Long.parseLong(request.getParameter("id_anuncio")));
			//pergunta.setId_remetente(Long.parseLong(request.getParameter("id_remetente")));
			pergunta.setId_veiculo(Long.parseLong(request.getParameter("id_veiculo")));
			pergunta.setId_destinatario(Long.parseLong(request.getParameter("id_destinatario")));
			pergunta.setPergunta(request.getParameter("pergunta"));
			pergunta.setResposta("");
			pergunta.setDataPergunta(Calendar.getInstance());
			pergunta.setStatus(Pergunta.NAO_RESPONDIDA);
			
		}
		
		
		//Verifica se o usuário está em sessão
		user = (Usuario) sessao.getAttribute("usuario");
		
		//Se estiver em sessão realiza o envio da pergunta
		if(user!=null)
		{
			
		pergunta.setId_remetente(user.getId());	
			
		//Envia a pergunta
		new PerguntaDAO().insert(pergunta);		
		sessao.removeAttribute("pergunta");
		request.getRequestDispatcher("pergunta_sucesso.jsp").forward(request, response);
			
			
		}
		
		//Se nao estiver em sessao, joga os dados da pergunta em sessao e seguida redireciona para paagina de login
		else{
		 
		 sessao.setAttribute("pergunta",pergunta);	
			
		 request.getRequestDispatcher("esc_login_simples.jsf?page_pos=EnviaPergunta").forward(request,response);
			
		}
	
		
		
		
	}

}
