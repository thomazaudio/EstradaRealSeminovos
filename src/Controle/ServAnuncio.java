package Controle;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.AnuncioDAO;
import Modelo.ContatoDAO;
import Modelo.ItemDAO;
import Modelo.LocalizacaoDAO;
import Modelo.UsuarioDAO;
import Modelo.VeiculoDAO;
import util.Anuncio;
import util.Caminhao;
import util.Carro;
import util.Contato;
import util.Debug;
import util.Empresa;
import util.Localizacao;
import util.Moto;
import util.Pagamento;
import util.Pessoa;
import util.Plano;
import util.Sessao;
import util.Usuario;
import util.Veiculo;

/**
 * Servlet implementation class ServAnuncio
 */
public class ServAnuncio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	//Páginas
	private final String PAGE_EDIT_CARRO="arearestritausuario/edit_carro.jsp";

	//Constantes com os endereços das paginas de cada etapa
	private  final String PAGE_CAD_SECESSO = "cad_veiculo/cad_anuncio_sucesso.jsf";
	private  final String PAGE_ESC_ANUNCIO="edit_veiculo.jsp?page=cad_veiculo/esc_anuncio.jsp";
	private  final String PAGE_CAD_INFO_CARRO="edit_veiculo.jsp?page=cad_veiculo/cad_info_carro.jsp";
	private  final String PAGE_CAD_INFO_MOTO="edit_veiculo.jsp?page=cad_veiculo/cad_info_moto.jsp";
	private  final String PAGE_STEP_2="edit_veiculo.jsp?page=cad_veiculo/esc_contato.jsp";
	private  final String PAGE_STEP_3="edit_veiculo.jsp?page=edit_img.jsp?id_veiculo=";
	private  final String PAGE_STEP_4="edit_veiculo.jsp?page=cad_veiculo/pre_carro.jsp";//Carro
	private  final String PAGE_STEP_5="edit_veiculo.jsp?page=cad_veiculo/pre_moto.jsp";//Moto
	private  final String PAGE_ESC_PAGAMENTO="cad_veiculo/esc_pagamento.jsf";

	//Constantes de etapas
	private final int STEP_0=0;
	private final int STEP_1 = 1;
	private final int STEP_2 = 2;
	private final int STEP_3 = 3;
	private final int STEP_4 = 4;
	private final int STEP_5 = 5;
	private final int EDIT_ANUNCIO=10;
	private final int DELETE_ANUNCIO=11;
	private int step;

	private int mostra_step;
	private String cadInfo;

	public String getCadInfo() {
		return cadInfo;
	}

	public void setCadInfo(String cadInfo) {
		this.cadInfo = cadInfo;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServAnuncio() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		HttpSession sessao = request.getSession();


		//RECEBE A SOLICITAÇÃO
		try{
			//Tenta resgatar no request	
			step = Integer.parseInt(request.getParameter("STEP"));
			mostra_step = Integer.parseInt(request.getParameter("mostra_step"));
		}
		catch(Exception e){

			//Tenta resgatar na sessão	
			step = (Integer) sessao.getAttribute("STEP");	
			mostra_step = 0;
		}


		//PRIMEIRA ETAPA DE CADASTRO(ESCOLHA DO PLANO E DO TIPO DE VEICULO)
		if(step==this.STEP_0)
		{

			//Reseta a sessão
			this.resetSessaoAnuncio(sessao);	

			//Encaminha para página de escolha o da plano e do tipo de veículo	
            request.getRequestDispatcher(this.PAGE_ESC_ANUNCIO).forward(request, response);

		}

		//PREENCHIMENTO DOS DADOS BASICOS DO VEICULO
		else if(step==this.STEP_1)
		{
			
			
			

			//Recupera os steps
			sessao.setAttribute("step",getStepsHtml(sessao,1));
			
			Anuncio an =  (Anuncio) sessao.getAttribute("anuncio");
			
			//Recebe o tipo de veiculo escolhido
		    int	tipoVeiculo = (Integer) sessao.getAttribute("tipo_veiculo");
		    
		    
		    
		    
		    if(tipoVeiculo==1)
			this.setCadInfo(this.PAGE_CAD_INFO_CARRO);	
		    else if(tipoVeiculo==2)
		    this.setCadInfo(this.PAGE_CAD_INFO_MOTO);	
		      
			request.getRequestDispatcher(this.getCadInfo()+"?mostra_step="+mostra_step).forward(request,response);
		}

		//ESCOLHA DE CONTATO
		else if(step==this.STEP_2)
		{

			//Recupera os steps
			sessao.setAttribute("step",getStepsHtml(sessao,2));

			request.getRequestDispatcher(this.PAGE_STEP_2+"?mostra_step="+mostra_step).forward(request,response);

		}

		
		//ESCOLHA DE FOTOS
		else if(step==this.STEP_3){

			//Recupera os steps
			sessao.setAttribute("step",getStepsHtml(sessao,3));

			//Recupera o veiculo em sessao para recuepração de id
			Veiculo carro = (Veiculo) sessao.getAttribute("carro");


			//Redireciona para a pagina de escolha de imagens
			request.getRequestDispatcher(this.PAGE_STEP_3+carro.getId()+"&&mostra_step="+mostra_step).forward(request,response);

		}

		//PRÉ- VISUALIZAÇÃO
		else if(this.step==this.STEP_4){

			//Permite voltar para essa pagina novamente
			sessao.setAttribute(""+this.STEP_4,1);
			
			int tipo = (Integer) sessao.getAttribute("tipo_veiculo");
			
			//Carro
			if(tipo==1)
			request.getRequestDispatcher(this.PAGE_STEP_4+"?mostra_step="+mostra_step).forward(request,response);
			
			//Moto 
			else if(tipo==2)
			request.getRequestDispatcher(this.PAGE_STEP_5+"?mostra_step="+mostra_step).forward(request,response);	
		}
		
		//ESCOLHA DE PAGAMENTO E FINALIZAÇÃO
		else if(this.step==this.STEP_5)
		{
			
		
	
			
	    Usuario user = (Usuario) sessao.getAttribute("usuario");	
		if(user!=null)	
		{
		 //Recupera o anuncio da sessão	
		Anuncio an = (Anuncio) sessao.getAttribute("anuncio");	
		
		
		
		
		//Joga o id do veiculo na sessão
		sessao.setAttribute("cod_veiculo",an.getVeiculo().getId());
		
		
	
		
		//Seta o usuário responsável pelo anúncio
		an.setIdUsuario(user.getId());
		
		String edit_mode = (String.format("%s",sessao.getAttribute("edit")));
		
		System.out.println("edit_mode: "+edit_mode);
		
		if(edit_mode.equals("true"))
		{
			
			
			
		new VeiculoDAO().update(an.getVeiculo());
		new AnuncioDAO().update(an);
	
		}
		else
		{
			
		//RECUPERA O TIPO DE PLANO INFORMADO NO INICIO
		int prioridade_anuncio = (Integer) sessao.getAttribute("prioridade_anuncio");	
			
		//----SE O PLANO FOR GRÁTIS----	
		//*OS DADOS DO VEÍCULOS SÃO SALVOS EM ESPERA PARA ANALISE DOS AGENTES DO SISTEMA
		//*REDIRECIONA PARA A PÁGINA DE ANÚNCIO CADASTRADO COM SUCESSO	
		if(prioridade_anuncio==Plano.PRIORIDADE_GRATIS)
		{
			
		//CONFIGURAÇÃO DOS STATUS PARA GRATIS
		an.getVeiculo().setStatusPagamento(Pagamento.CONFIRMADO);
		an.getVeiculo().setStatusValidacao(Pagamento.VALIDACAO_EM_ANALISE);
			
		new VeiculoDAO().update(an.getVeiculo());
		new AnuncioDAO().insert(an);	
		response.sendRedirect(this.PAGE_CAD_SECESSO);	
		}
		
		//----SE O PLANO FOR PAGO----		
		//*REDIRECIONA PARA A PÁGINA DE PAGAMENTO E CONFIRMAÇÃO DE PAGAMENTO
		//REDIRECIONA PARA A PÁGINA DE ANÚNCIO CADASTRADO MAS AGUARDANDO PAGAMENTO	
		else if(prioridade_anuncio==Plano.PRIORIDADE_MEGA){
		
			
			
			//Configuração dos dados para prioridade Meag
			
			an.getVeiculo().setStatusPagamento(Pagamento.AGUARDANDO_APROVACAO);
			an.getVeiculo().setStatusValidacao(Pagamento.VALIDACAO_EM_ANALISE);
				
			new VeiculoDAO().update(an.getVeiculo());
			new AnuncioDAO().insert(an);	
			response.sendRedirect(this.PAGE_ESC_PAGAMENTO);	
			
			
		}
			
			
			
     
		
		
		}
	
		
		
		}
		
		else 
		{
			
			Debug.gerar("Controle","ServAnuncio","SOLI=5(GET)","usuario na sesssão = null");
		}
		
		
		
		this.resetSessaoAnuncio(sessao);
			
		}

		
		
		
		//EDIÇÃO DE ANÚNCIO
		else if(this.step==this.EDIT_ANUNCIO)
		{
		
			//Recupera o usuário em sessão
			Usuario user = (Usuario) sessao.getAttribute("usuario");
			
			//Recupera o id do anúncio
			long id_veiculo = Integer.parseInt(request.getParameter("id_veiculo"));
			
			//Recupera o anuncio
			Anuncio an = new AnuncioDAO().getAnuncio(id_veiculo);
			
			
			//Recupera o tipo de veículo
			String tipo =  new VeiculoDAO().getTipo(id_veiculo);
			
			//Verifica se o anuncio pertence ao usário em sessão
			
			
			//Joga o anuncio em sessão para edição dos dados
			sessao.setAttribute("anuncio",an);
			
			
			
			this.resetSessaoAnuncioForEdit(sessao);
			
			
			System.out.println("Modelo: "+an.getVeiculo().getCodModelo());
			
			if(tipo.equals("CARRO"))
			{
				
			//Joga o veiculo em sessao
			sessao.setAttribute("carro",new VeiculoDAO().getVeiculo(id_veiculo,Carro.class));	
			sessao.setAttribute("tipo_veiculo",1);
			response.sendRedirect("ServAnuncio?STEP=4&mostra_step=0&x=82&y=24");
			
			}
			else
			response.getWriter().write("Página de destino não definida");	
			
			
		} 
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession sessao = request.getSession();


		//RECEBE A SOLICITAÇÃO
		step = Integer.parseInt(request.getParameter("STEP"));

		try{
			mostra_step = Integer.parseInt(request.getParameter("mostra_step")); 
		}catch(Exception e){
			mostra_step=1;
		}
		System.out.println("Step="+step);

		//PRIMEIRA ETAPA DE CADASTRO(ESCOLHA DO PLANO E DO TIPO DE VEICULO)
		if(step==this.STEP_0)
		{

			//Reseta a sessão
			this.resetSessaoAnuncio(sessao);	
			
			//Recebe o tipo de veiculo escolhido
			int tipoVeiculo = Integer.parseInt(request.getParameter("tipo_veiculo"));

			//Joga os dados na sessao para serem facilmente utilizados com get
			sessao.setAttribute("tipo_veiculo",tipoVeiculo);


			//Encaminha para página de escolha o da plano	
			request.getRequestDispatcher(this.PAGE_ESC_ANUNCIO+"?mostra_step=1").forward(request, response);

		}

		//PREENCHIMENTO DOS DADOS BASICOS DO VEICULO
		else if(step==this.STEP_1)
		{
			Anuncio an = (Anuncio) sessao.getAttribute("anuncio");
			
			int tipoVeiculo;


			//Permite voltar para essa pagina novamente
			sessao.setAttribute(""+this.STEP_1,1);

		
			int prioridade_anuncio = Integer.parseInt(request.getParameter("prioridade_anuncio"));

			//Recupera o tipo de vepiculo da sessão
			tipoVeiculo = (Integer)sessao.getAttribute("tipo_veiculo");

			//Joga os dados na sessao para serem facilmente utilizados com get
			sessao.setAttribute("prioridade_anuncio",prioridade_anuncio); 
			

			//Atribuição de valores de acordo com o plano escolhido

			//Recupera o buffer de passos

			//Atribuição do veiculo ao anuncio
			an.setVeiculo(this.getTipoVeiculo(tipoVeiculo));

			//Salva o anuncio em sessao
			sessao.setAttribute("anuncio",an);

			//Recupera os steps
			sessao.setAttribute("step",getStepsHtml(sessao,1));

			//Emcaimnha para a proxima etapa (Cadastro de informações basicas)
			if(tipoVeiculo==1)
		    this.setCadInfo(this.PAGE_CAD_INFO_CARRO);	
			else if(tipoVeiculo==2)
		    this.setCadInfo(this.PAGE_CAD_INFO_MOTO);	

             
			response.sendRedirect(this.getCadInfo()+"?mostra_step="+mostra_step);
			//request.getRequestDispatcher(this.getCadInfo()+"?mostra_step="+mostra_step).forward(request,response);
		}

		//AO FINAL ENCAMINHAR PARA PAGINA DE ESCOLHA DE CONTATO
		else if(step==this.STEP_2)
		{


			//Sessao.setSessao(sessao);	
             
			Usuario user = (Usuario) sessao.getAttribute("usuario");


			//Permite voltar para essa pagina novamente
			sessao.setAttribute(""+this.STEP_2,1);

			//
			//Recupera o anuncio da sessao
			Anuncio an =  (Anuncio) sessao.getAttribute("anuncio");

			//Verifica qual o tipo de veiculo
			int tipo =  (Integer) sessao.getAttribute("tipo_veiculo");
			
			//Carro
			if(tipo==1 || an.getVeiculo().getClass()==Carro.class)
			{
				
                 Carro carro = new Carro();

                 //Se os dados já estiverem em sessao
				if(sessao.getAttribute("carro")!=null)
				{	
					Carro car = (Carro) sessao.getAttribute("carro");	
					carro.setId(car.getId());	
					carro.setContato(new ContatoDAO().getContato(car.getContato().getId()));

				}

				carro.setCod_fabricante(Integer.parseInt(request.getParameter("fabricante")));
				carro.setModelo(request.getParameter("modelo"));
				carro.setCodModelo(Integer.parseInt(request.getParameter("modelo")));
			

				try{
					carro.setPreco_negociavel(Integer.parseInt(request.getParameter("preco_negociavel")));
				}catch(Exception e){
					carro.setPreco_negociavel(0);

				}


				//Completa os dados do carro
				carro =  this.compCarro(carro,request);
				carro.setQuilometragem(request.getParameter("quilometragem"));
				
			
				
				

				
				//preço
				String preco = request.getParameter("valor");
				preco = preco.replace(".","");
				preco = preco.replace(",",".");
				carro.setPreco(Double.parseDouble(preco));

				//Salva um contato generico para o veiculo
				Contato contato =  new Contato();
				if(carro.getContato()!=null)
				contato= new ContatoDAO().getContato(carro.getContato().getId());
				new ContatoDAO().saveOrUpdate(contato);
				carro.setContato(contato);
				
				UsuarioDAO d =  new UsuarioDAO();
				
				//Seta o tipo de venda
				if(d.getTipoUser(user.getId())==Usuario.PESSOA)
				carro.setTipoVenda(1);
				else 
				carro.setTipoVenda(2);

				//Salva o veiculo no banco de dados
				new VeiculoDAO().saveOrUpdate(carro);


				//Joga o carro na sessao
				sessao.setAttribute("carro",carro);

				//Atualiza o carro no anuncio
				an.setVeiculo(carro);
				
				System.out.println("Chegou aqui---");

			}
			
			
			System.out.println("O tipo de veículo é "+tipo);
			
			//Moto
			
			if(tipo==2 || an.getVeiculo().getClass()==Moto.class)
			{
				
                 Moto moto = new Moto();

                 //Se os dados  estiverem em sessao
				if(sessao.getAttribute("moto")!=null)
				{	
					Moto moto_session = (Moto) sessao.getAttribute("moto");	
					moto.setId(moto_session.getId());	
					moto.setContato(new ContatoDAO().getContato(moto_session.getContato().getId()));

				}

				moto.setCod_fabricante(Integer.parseInt(request.getParameter("fabricante")));
				moto.setModelo(request.getParameter("modelo"));
				moto.setCodModelo(Integer.parseInt(request.getParameter("modelo")));
			

				try{
					moto.setPreco_negociavel(Integer.parseInt(request.getParameter("preco_negociavel")));
				}catch(Exception e){
					moto.setPreco_negociavel(0);

				}


				//Completa os dados da  moto
				moto =  this.compMoto(moto,request);
				moto.setQuilometragem(request.getParameter("quilometragem"));
				
			
				
				

				
				//preço
				String preco = request.getParameter("valor");
				preco = preco.replace(".","");
				preco = preco.replace(",",".");
				moto.setPreco(Double.parseDouble(preco));

				//Salva um contato generico para o veiculo
				Contato contato =  new Contato();
				if(moto.getContato()!=null)
				contato= new ContatoDAO().getContato(moto.getContato().getId());
				new ContatoDAO().saveOrUpdate(contato);
				moto.setContato(contato);
				
				UsuarioDAO d =  new UsuarioDAO();
				
				//Seta o tipo de venda
				if(d.getTipoUser(user.getId())==Usuario.PESSOA)
				moto.setTipoVenda(1);
				else 
				moto.setTipoVenda(2);

				//Salva o veiculo no banco de dados
				new VeiculoDAO().saveOrUpdate(moto);


				//Joga a moto atualizada na sessao
				sessao.setAttribute("moto",moto);

				//Atualiza a moto no anuncio
				an.setVeiculo(moto);
				
				System.out.println("Chegou aqui---");

			}
			//FIM OP MOTO


			//Recupera os steps
			sessao.setAttribute("step",getStepsHtml(sessao,2));

			//Joga o anuncio atualizado na sessao
			sessao.setAttribute("anuncio",an);

			if(mostra_step==1)	 
				response.sendRedirect(this.PAGE_STEP_2+"?STEP=2&&mostra_step="+mostra_step);	 
			//request.getRequestDispatcher(this.PAGE_STEP_2+"?STEP=2&&mostra_step="+mostra_step).forward(request,response);
			else
			{
				response.setContentType("text/html");
				response.getWriter().write("<script> parent.jQuery.fancybox.close(); </script>");

			}
		}


		//Recupera os dados do contato
		//Ao final encaminha para pagina de escolha de fotos
		else if(step==this.STEP_3){

			//Permite voltar para essa pagina novamente
			sessao.setAttribute(""+this.STEP_3,1);

			//Recupera os steps
			sessao.setAttribute("step",getStepsHtml(sessao,3));

			
			//RECUPERA O TIPO DE VEÍCULO QUE ESTÁ SENDO CADASTRADO
			int tipo = (Integer) sessao.getAttribute("tipo_veiculo");
			
			//CARRO
			if(tipo==1)
			{
			//Recupera o veiculo em sessao
			Carro carro = (Carro) sessao.getAttribute("carro");



			//Recupera o anuncio em sessão
			Anuncio an = (Anuncio) sessao.getAttribute("anuncio");

			//Atualiza o carro em anuncio
			an.setVeiculo(carro);

			//Joga novamente o anuncio em sessão
			sessao.setAttribute("anuncio",an);

			//Redireciona para a pagina de escolha de imagens
			if(mostra_step==1)	
				response.sendRedirect(this.PAGE_STEP_3+carro.getId()+"&&mostra_step=1");	 

			//request.getRequestDispatcher(this.PAGE_STEP_3+carro.getId()).forward(request,response);
			else
			{
				response.setContentType("text/html");
				response.getWriter().write("<script> parent.jQuery.fancybox.close(); </script>");


			}
			}
			
			//MOTO
			else if(tipo==2)
			{
				
				
				//Recupera o veiculo em sessao (Moto)
				Moto moto = (Moto) sessao.getAttribute("moto");


				 System.out.println("Moto na sessão: "+moto);
				
				

				//Recupera o anuncio em sessão
				Anuncio an = (Anuncio) sessao.getAttribute("anuncio");

				//Atualiza a moto em anuncio
				an.setVeiculo(moto);

				//Joga novamente o anuncio em sessão
				sessao.setAttribute("anuncio",an);
				
				

				//Redireciona para a pagina de escolha de imagens
				if(mostra_step==1)	
					response.sendRedirect(this.PAGE_STEP_3+moto.getId()+"&&mostra_step=1");	 

				//request.getRequestDispatcher(this.PAGE_STEP_3+carro.getId()).forward(request,response);
				else
				{
					response.setContentType("text/html");
					response.getWriter().write("<script> parent.jQuery.fancybox.close(); </script>");


				}
				
			}

		}  
		
		
		else if(step== this.DELETE_ANUNCIO)
		{
			
			//Recebe o id do anúncio
			long id_anuncio =  Integer.parseInt(request.getParameter("id_anuncio"));
			
			Anuncio an = new Anuncio();
			an.setId(id_anuncio);
			new AnuncioDAO().delete(an);
		
			
		}

	}
	
	
	
	
	//Configura a sessão de anúncio para edição
	private void resetSessaoAnuncioForEdit(HttpSession sessao){

	
			sessao.setAttribute(""+this.STEP_0,1);
			sessao.setAttribute(""+this.STEP_1,1);
			sessao.setAttribute(""+this.STEP_2,1);
			sessao.setAttribute(""+this.STEP_3,1);
			sessao.setAttribute(""+this.STEP_4,1);
			sessao.setAttribute(""+this.STEP_5,1);	
			
			
			//Modo de edição
			sessao.setAttribute("edit","true");
		

	}

	//Define na sessão os parametros iniciais para anunciar um veículo
	private void resetSessaoAnuncio(HttpSession sessao){
		
		
		//Remove os atributos antigos caso existam
		sessao.removeAttribute("veiculo");
		sessao.removeAttribute("carro");
		sessao.removeAttribute("moto");
		
		
		

		//Define as paginas que já foram preenchidas
		{
			sessao.setAttribute(""+this.STEP_0,0);
			sessao.setAttribute(""+this.STEP_1,0);
			sessao.setAttribute(""+this.STEP_2,0);
			sessao.setAttribute(""+this.STEP_3,0);
			sessao.setAttribute(""+this.STEP_4,0);
			sessao.setAttribute(""+this.STEP_5,0);
		}
		
		//Modo de cadastro
		sessao.setAttribute("edit","false");

		Anuncio an = new Anuncio();
		
		//Define a data de cadastro do anúncio
		an.setDataIni(Calendar.getInstance());
		
		
		
		
	
		
		
		
		//Contato generico para o anuncio
		Contato con = new Contato();
		new ContatoDAO().insert(con);
		
		//Localização generica para o anuncio
		Localizacao loc = new Localizacao();
		new LocalizacaoDAO().insert(loc);

		an.setContato(con);
		an.setLocalizacao(loc);
		sessao.setAttribute("anuncio",an);

	}

	private Veiculo getTipoVeiculo(int tipoVeiculo){

		//Atribuição do veiculo ao anuncio
		switch(tipoVeiculo){

		//Carro
		case 1:return new Carro();

		//Moto
		case 2:return new Moto();

		//Caminhão	 
		case 3:return new Caminhao();

		default: return null;
		}

	}

	private StringBuffer  getStepsHtml(HttpSession sessao,int atual_page){

		StringBuffer html = new StringBuffer();

		String step1="",step2="",step3="",step4="",step5="";

		switch(atual_page){

		case 1:
			step1="<span>1. Informações básicas</span>";
			step2="<a href=\""+this.getLinkStep(2,sessao)+"\"><span>2. Contato</span></a>";
			step3="<a href=\""+this.getLinkStep(3,sessao)+"\"><span>3. Imagens</span></a>";
			step4="<a href=\""+this.getLinkStep(4,sessao)+"\"><span>4. Pré-Visualizar</span></a>";
			step5="<a href=\""+this.getLinkStep(5,sessao)+"\"><span>5. Concluído com sucesso!</span></a>";
			break;


		case 2:
			step2="<span>2. Contato</span>";
			step1="<a href=\""+this.getLinkStep(1,sessao)+"\"><span>1. Informações básicas</span></a>";
			step3="<a href=\""+this.getLinkStep(3,sessao)+"\"><span>3. Imagens</span></a>";
			step4="<a href=\""+this.getLinkStep(4,sessao)+"\"><span>4. Pré-Visualizar</span></a>";
			step5="<a href=\""+this.getLinkStep(5,sessao)+"\"><span>5. Concluído com sucesso!</span></a>";
			break;

		case 3:
			step3="<span>3. Imagens</span>";
			step2="<a href=\""+this.getLinkStep(2,sessao)+"\"><span>2. Contato</span></a>";
			step1="<a href=\""+this.getLinkStep(1,sessao)+"\"><span>1. Informações básicas</span></a>";
			step4="<a href=\""+this.getLinkStep(4,sessao)+"\"><span>4. Pré-Visualizar</span></a>";
			step5="<a href=\""+this.getLinkStep(5,sessao)+"\"><span>5. Concluído com sucesso!</span></a>";   
			break;	

		case 4:
			step4="<span>4. Pré-Visualizar</span>";
			step2="<a href=\""+this.getLinkStep(2,sessao)+"\"><span>2. Contato</span></a>";
			step1="<a href=\""+this.getLinkStep(1,sessao)+"\"><span>1. Informações básicas</span></a>";
			step3="<a href=\""+this.getLinkStep(3,sessao)+"\"><span>3. Imagens</span></a>";
			step5="<a href=\""+this.getLinkStep(5,sessao)+"\"><span>5. Concluído com sucesso!</span></a>";    
			break;	

		case 5:
			step5="<span>5. Concluído com sucesso!</span>";
			step2="<a href=\""+this.getLinkStep(2,sessao)+"\"><span>2. Contato</span></a>";
			step1="<a href=\""+this.getLinkStep(1,sessao)+"\"><span>1. Informações básicas</span></a>";
			step3="<a href=\""+this.getLinkStep(3,sessao)+"\"><span>3. Imagens</span></a>";
			step4="<a href=\""+this.getLinkStep(4,sessao)+"\"><span>4. Pré-Visualizar</span></a>";    
			break;		

		}

		html.append(step1);
		html.append(step2);
		html.append(step3);
		html.append(step4);
		html.append(step5);
		return html;

	}

	//RECUPERA O ESTADO DE PREENCHIMENTO DE UMA PÁGINA
	private int pagePre(int step,HttpSession sessao){

		return (Integer) sessao.getAttribute(""+step);

	}

	private String getLinkStep(int step,HttpSession sessao){

		//Verifica se a pagina já foi preenchida
		if(pagePre(step,sessao)==1)
			return	getLinkForStep(step,sessao);

		else
			return "#";
	}

	//Recupera o link atraves de um step
	private String getLinkForStep(int step,HttpSession sessao){

		switch(step){

		case 1:return "ServAnuncio?STEP=1&&mostra_step=1";


		case 2:return "ServAnuncio?STEP=2&&mostra_step=1";


		case 3:return "ServAnuncio?STEP=3&&mostra_step=1";


		case 4:return this.PAGE_STEP_4;


		case 5:return this.PAGE_STEP_5;

		default :return "#n";

		}

	}

	//Tranforma um vetor de String e um vetor de Integer (Para obtenção dos acessórios do veículo)
	private long[] toLong(String vet[]){

		long itens[] =  new long[vet.length];

		for(int i=0;i<vet.length;i++)
		itens[i] = Long.parseLong(vet[i]);


		return itens;
	}


	//COMPLETA OS DADOS DO CARRO A PARTIR DO REQUEST
	private Carro compCarro(Carro carro,HttpServletRequest request){

		carro.setQuilometragem(request.getParameter("quilometragem"));
		carro.setCombustivel(request.getParameter("combustivel"));
		carro.setEstadoConservacao(request.getParameter("estadoConservacao"));
		carro.setAnoFabricacao(Integer.parseInt(request.getParameter("anoFabricacao")));
		carro.setAnoModelo(Integer.parseInt(request.getParameter("anoModelo")));
		carro.setQuantPortas(Integer.parseInt(request.getParameter("quantPortas")));
		carro.setCondicaoTroca(request.getParameter("condicaoTroca"));
		carro.setQuantValvulas(Integer.parseInt(request.getParameter("valvulas")));
		carro.setMotor(request.getParameter("motor"));
		carro.setPlaca(request.getParameter("placa"));
		carro.setVersao(request.getParameter("versao"));
		System.out.println("Versão: "+carro.getVersao());
		carro.setTransmissao(request.getParameter("transmissao"));
		carro.setCor(request.getParameter("cor"));
		carro.setDescricao(request.getParameter("descricao"));
		
		
		


		//recupera os itens do carro	
		String itens[]  = request.getParameterValues("idAcessorio");

		if(itens!=null)
			carro.setItens(new ItemDAO().getItens(this.toLong(itens)));

		return carro;
	}

	
	//COMPLETA OS DADOS DA MOTO A PARTIR DO REQUEST
		private Moto compMoto(Moto carro,HttpServletRequest request){

			carro.setQuilometragem(request.getParameter("quilometragem"));
			carro.setCombustivel(request.getParameter("combustivel"));
			carro.setEstadoConservacao(request.getParameter("estadoConservacao"));
			carro.setAnoFabricacao(Integer.parseInt(request.getParameter("anoFabricacao")));
			carro.setAnoModelo(Integer.parseInt(request.getParameter("anoModelo")));
			//carro.setQuantPortas(Integer.parseInt(request.getParameter("quantPortas")));
			carro.setCondicaoTroca(request.getParameter("condicaoTroca"));
			//carro.setQuantValvulas(Integer.parseInt(request.getParameter("valvulas")));
			//carro.setMotor(request.getParameter("motor"));
			carro.setPlaca(request.getParameter("placa"));
			carro.setVersao(request.getParameter("versao"));
			System.out.println("Versão: "+carro.getVersao());
			//carro.setTransmissao(request.getParameter("transmissao"));
			carro.setCor(request.getParameter("cor"));
			carro.setDescricao(request.getParameter("descricao"));
			carro.setCilindradas(request.getParameter("cilindradas"));
			
			
			


			//recupera os itens do carro	
			String itens[]  = request.getParameterValues("idAcessorio");

			if(itens!=null)
				carro.setItens(new ItemDAO().getItens(this.toLong(itens)));

			return carro;
		}




}
