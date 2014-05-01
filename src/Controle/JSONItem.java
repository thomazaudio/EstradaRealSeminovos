package Controle;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ItemVeiculo;
import Modelo.VeiculoDAO;

import com.google.gson.Gson;



/**
 * Servlet implementation class JSONItem
 */
public class JSONItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final int GET_ALL_MODELOS = 1;//Carro
	private final int CLOSE_FRAME = 2;
	private final int GET_ALL_MODELOS_MOTO = 3;//Moto
	private int soli;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
		
		
		soli = Integer.parseInt(request.getParameter("soli"));
		
		if(soli==this.GET_ALL_MODELOS)
		{
		
	
			
		//recupera o codigo do fabricante
		int cod_fab = Integer.parseInt(request.getParameter("fabricante"));	
			
			
		Map<Integer,String> dados = new LinkedHashMap<Integer, String>();
		
		
		ArrayList<ItemVeiculo> itens = new VeiculoDAO().getAllModelos(cod_fab);
		
		for(int i=0;i<itens.size();i++)
		dados.put(itens.get(i).getCod(),itens.get(i).getNome());
		
		
		String json =  new Gson().toJson(dados);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(json);
		}
		
		
		else if(soli==this.GET_ALL_MODELOS_MOTO)
		{
		
	
			
		//recupera o codigo do fabricante
		int cod_fab = Integer.parseInt(request.getParameter("fabricante"));	
		
		System.out.println("Cod fabricante: "+cod_fab);
			
		Map<Integer,String> dados = new LinkedHashMap<Integer, String>();
		
		
		ArrayList<ItemVeiculo> itens = new VeiculoDAO().getAllModelosMoto(cod_fab);
		
		for(int i=0;i<itens.size();i++)
		dados.put(itens.get(i).getCod(),itens.get(i).getNome());
		
		System.out.println("Dados: "+dados);
		
		String json =  new Gson().toJson(dados);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(json);
		}
		
		else if(soli==this.CLOSE_FRAME)
		response.getWriter().write("<script>parent.jQuery.fancybox.close();</script>");	
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
