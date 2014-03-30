package Bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Empresa;
import util.Pessoa;
import util.TrocaEmail;
import util.Usuario;
import Modelo.TrocaEmailDAO;

/**
 * Servlet implementation class ChangeEmail
 */
public class ChangeEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession sessao = request.getSession();
		
		//Recebe o id da troca
		long id = Long.parseLong(request.getParameter("cod_troca"));
		
		//recupera a solicitação de troca
		TrocaEmail troca = new TrocaEmailDAO().getTroca(id);
		
		//Verifica se existe um usuário em sessão
		Usuario user = (Usuario) sessao.getAttribute("usuario");
		
		if(user==null)
		response.sendRedirect("esc_login.jsf?page_pos=ChangeEmail?cod_troca="+id);	
		else
		{
		
		
		if(user.getId()==troca.getIdUsuario())
		{
			
		new TrocaEmailDAO().trocaEmail(id);
		
		if(user.getClass()==Pessoa.class)
		response.sendRedirect("arearestritausuario/painel_pessoa.jsf?page=email_alterado");
		
		else if(user.getClass()==Empresa.class)
		response.sendRedirect("arearestritausuario/painel_empresa.jsf?page=email_alterado");	
		
		}
		else 
		response.sendRedirect("index.jsp?page=erro_autorizacao.jsp");		
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
