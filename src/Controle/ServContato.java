package Controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.ContatoDAO;
import util.Contato;

/**
 * Servlet implementation class ServContato
 */
public class ServContato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public  final int EDIT_CONTATO =2;
	public  final int CAD_CONTATO_VEICULO=3;
	public  final int CAD_CONTATO_USER=4;
	public  final int DELETE_CONTATO=5;
	public  final int CONFIRMA_CONTATO=6;
	public final int ENVIA_PROPOSTA = 7;
	private int soli;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServContato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Solicitação
		soli = Integer.parseInt(request.getParameter("SOLI"));
		
		if(soli==this.CONFIRMA_CONTATO)
		{
			
			long id = Long.parseLong(request.getParameter("id"));
			
			new ContatoDAO().confirmaContato(id);
			
			response.getWriter().write("Contato confirmado com sucesso.");
			
			
		}
		
		else if(soli==this.ENVIA_PROPOSTA){
			
			response.sendRedirect("proposta_sucesso.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Integer.parseInt(request.getParameter("id"))
		
		//Solicitação
		soli = Integer.parseInt(request.getParameter("SOLI"));
		
		
		//ADICIONAR UM CONTATO PARA UM VEICULO
		if(soli==this.CAD_CONTATO_VEICULO)
		{
		
			    Contato c = new Contato();
				
				//Recebe os dados do contato
			    // ID
				c.setId(Long.parseLong(request.getParameter("id_contato_veiculo")));	
				
				System.out.println("Id do contato: "+c.getId());
				
				//NOME
				c.setNome(request.getParameter("nome"));
			
				
				//CEL
				c.setCel(request.getParameter("cel"));
				
				//TEL-1
				c.setTel1(request.getParameter("tel1"));
				
				//TEL-2
				c.setTel2(request.getParameter("tel2"));
				
				//E-MAIL
				c.setEmail(request.getParameter("email"));
				
				c.setComp(1);
				
				new ContatoDAO().update(c);
				response.setContentType("text/html");
				response.getWriter().write("<script> parent.jQuery.fancybox.close(); </script>");
			
		}
		
		
		//EDTAR UM CONTATO
		else if(soli==this.EDIT_CONTATO)
		{
		
	    Contato c = new Contato();
			
		//Recebe os dados do contato
	    // ID
		c.setId(Long.parseLong(request.getParameter("id")));	
		
		//NOME
		c.setNome(request.getParameter("nome"));
		
		System.out.println("Nome: "+c.getNome());
		
		//CEL
		c.setCel(request.getParameter("cel"));
		
		//TEL-1
		c.setTel1(request.getParameter("tel1"));
		
		//TEL-2
		c.setTel2(request.getParameter("tel2"));
		
		//E-MAIL
		c.setEmail(request.getParameter("email"));
		
		
		System.out.println("Email: "+c.getEmail());
		
		c.setComp(1);
		
		new ContatoDAO().update(c);
		
		response.setContentType("text/html");
		response.getWriter().write("<script> parent.jQuery.fancybox.close(); </script>");
		
			
		}
		
		else if(soli==this.DELETE_CONTATO)
		{
			
		//Recebe a pagina a ser atualizada posteriormente
		String page_pos= request.getParameter("page_pos");
		
		Contato c = new Contato();
				
		//Recebe os dados do contato
		// ID
		c.setId(Long.parseLong(request.getParameter("id")));
		
		
		new ContatoDAO().delete(c);
		
		
		System.out.println("Pagina: "+page_pos);
		
	    //Ao final atualiza a pagina
		response.sendRedirect(page_pos);
			
		}
		
	}

}
