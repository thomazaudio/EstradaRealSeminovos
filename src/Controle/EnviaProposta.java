package Controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.PropostaDAO;
import util.Proposta;


/**
 * Servlet implementation class EnviaPergunta
 */
public class EnviaProposta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviaProposta() {
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
		
		
		System.out.println("Chegou aqui, EnviaProposta");

		
		    Proposta proposta=null;
		
		
		
	  
			proposta =  new Proposta();
			
			//Recupera os dados a partir do request
			proposta.setIdVeiculo(Long.parseLong(request.getParameter("id_veiculo")));
			proposta.setEmailRemetente(request.getParameter("email"));
			proposta.setIdUser(Long.parseLong(request.getParameter("id_usuario")));
			proposta.setMsg(request.getParameter("msg"));
			proposta.setTelefoneRemetente(request.getParameter("telefone"));
			proposta.setNome(request.getParameter("nome"));
			
			
	        //Lan�a a proposta
		    new PropostaDAO().insert(proposta);
		    
		   response.sendRedirect("proposta_sucesso.jsp");
	}

}
