package Controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Debug;
import util.Empresa;
import util.Pessoa;
import util.SystemEmpresa;
import util.TrocaEmail;
import util.Usuario;
import Modelo.TrocaEmailDAO;
import Modelo.UsuarioDAO;

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
		
		//recupera a solicitaçao de troca
		TrocaEmail troca = new TrocaEmailDAO().getTroca(id);
		
		
		if(troca==null)
		{
			Debug.gerar("","","","A troca de email de id: "+id+"Não pode ser efetuda.");
			
			
			response.sendRedirect(SystemEmpresa.DOMINIO);
			
		}
		
		System.out.println("Solicitação de troca de email: ");
		System.out.println("email a ser trocado: "+troca.getEmailtroca());
		System.out.println("Id do usuário: "+troca.getIdUsuario());
		System.out.println("Id do contato a ser alteado: "+troca.getIdContato());
		
	
			
		new TrocaEmailDAO().trocaEmail(id);
		
		
		response.sendRedirect("arearestritausuario/confirmado_sucesso.jsf");
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
