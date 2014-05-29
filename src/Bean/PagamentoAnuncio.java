package Bean;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import util.BoletoUtil;
import util.Debug;
import util.Pagamento;
import util.Plano;
import util.Transacao;
import util.Usuario;
import util.UsuarioUtil;
import Modelo.FinanDAO;
import Modelo.PagamentoDAO;
import Modelo.UsuarioDAO;


//CLASSE PARA PAGAMENTO DE AN�NCIO NO ATO DE CADASTRO

@ManagedBean
@SessionScoped
public class PagamentoAnuncio {
	
	
	private int prioridade_anuncio;
	private int tipo_transacao;
	private double valor;
	private long codVeiculo;
	private long codPagamento;
	private boolean isReemissao;
	
	
	
	    public long getCodPagamento() {
		return codPagamento;
	}


	public void setCodPagamento(long codPagamento) {
		this.codPagamento = codPagamento;
	}


		//Escolha do tipo de pagamento (Caso: pagamentoo logo ap�s cadastramento dos dados do an�ncio)
		 public void escTipoPagamentoCadastro(){
			    
			     isReemissao = false;
			 
				Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
				
				//TIPO DE PAGAMENTO: BOLETO, PAG-SEGURO, CREDITO EM CONTA
				int tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
				
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        
		        
		        
		         //Tipo de plano
		         //COMO EST� NO ATO DE CADASTRO O DADO SE ENCONTRA EM SESS�O
		         prioridade_anuncio = (Integer) sessao.getAttribute("prioridade_anuncio");
		          
		         //Codigo do veiculo
		         codVeiculo = (Long) sessao.getAttribute("cod_veiculo");
		         
		         
		        
		         //Valor 
		         valor = Plano.getPrecoPlano(prioridade_anuncio);
	
		     	
		         
		        //verifica��o do tipo de pagamento escolhido
				switch(tipo_pagamento){
				
				case Pagamento.PAGAMENTO_BOLETO: this.pagamentoBoleto();
				break;
				
				case Pagamento.PAGAMENTO_CREDITO_CONTA: this.debitoConta();
				break;
				
				case Pagamento.PAGAMENTO_PAG_SEGURO: this.pagamentoPagSeguro();
				break;
			 
				
				
				}
			  
				
				
			}
		 
		 
		 //Escolha do tipo de pagamento
		 public void reemitePagamento(){
			 
			 
			 isReemissao = true;
			 
			 Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
			  
				
		        
		        //Codigo do veiculo
		        codVeiculo = Long.parseLong(p.get("cod_veiculo"));

		        //Prioridade do  anuncio 
		        prioridade_anuncio = Integer.parseInt(p.get("prioridade_anuncio"));
		        
		        
		    
		        //Valor 
		        valor = Plano.getPrecoPlano(prioridade_anuncio);
		     
		        
		        //Tipo de transa��o (Reemiss�o de pagamento)
		        tipo_transacao = Transacao.REEMITE_PAGAMENTO;
		        
		 

				//Encaminha para a pagina de  escolha de pagamento
			    try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("esc_pagamento_reemissao.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		 }
		 
		 //Reemiss�o de pagamento para este ve�culo
		 public void escTipoPagamentoReemissao(){
				
				Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
				int tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
				
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
		        
		       
  
		        //Valor 
		        valor = Plano.getPrecoPlano(prioridade_anuncio);
		        
		        
		        //verifica��o do tipo de pagamento escolhido
				switch(tipo_pagamento){
				
				case Pagamento.PAGAMENTO_BOLETO: this.pagamentoBoleto();
				break;
				
				case Pagamento.PAGAMENTO_CREDITO_CONTA: this.debitoConta();
				break;
				
				case Pagamento.PAGAMENTO_PAG_SEGURO: this.pagamentoPagSeguro();
				break;
				
				}
		  
		       
						
					
					
				
				
				
			}
		 
		 
		 

		    //M�todo escolhido  = PAGAMENTO_BOLETO
			//Tipo de Transa��o  = CREDITO_DEBITO
			//Tipo de pagamento  = PAGAMENTO_BOLETO
		    public void pagamentoBoleto(){
		    	

		    	//Lan�a o pagamento no sistema
		    	//Defini��o do pagamento:boleto banc�rio
				int tipo_pagamento = Pagamento.PAGAMENTO_BOLETO;
				
				
				//Recupera usuario pra preenchimento das informa��es
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
		    	

				//Lan�a um novo pagamento no sistema
				Pagamento pag = new Pagamento();
				pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
				pag.setValor(Plano.getPrecoPlano(prioridade_anuncio));
				pag.setPrioridade(prioridade_anuncio);
				pag.setTipo(Transacao.CREDITO_DEBITO);//Pagamento de an�ncio
				pag.setCodUser(idUser);
				pag.setIdVeiculo(codVeiculo);
				pag.setDescricao(Transacao.getTextoTransacao(Transacao.CREDITO_DEBITO));
				pag.setFormaPagamento(tipo_pagamento);
			    pag.setData(Calendar.getInstance());
				
				new PagamentoDAO().insert(pag);
		    	
			
				
				
		    	//Atribui��o do codigo do pagamento para sessao
				codPagamento = pag.getCod();
		    	
				
		    	
				//Encaminha para a pagina de download de boleto
			    try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(new ContextoBean().getContextoInicial()+"/cad_veiculo/download_boleto_anuncio.jsf?id_pagamento="+pag.getCod());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		 
		    
		    
		    
		    //M�todo escolhido = PAGAMENTO_PAG_SEGURO
			//Tipo de Transa��o  = CREDITO_DEBITO
	    	//Tipo de pagamento  = PAGAMENTO_PAG_SEGURO
		    public void pagamentoPagSeguro(){
		    	
	             //Lan�a o pagamento no sistema
		    	
		    	//Atribui��o do codigo do pagamento para sessao
		    	
		    	//Redireciona para a p�gina definida
		    	
		    }
		    
		    
		    
		
		    //M�todo escolhido = Debito na conta
		    //Tipo de Transa��o  = ALT_PLANO_CREDITO
		    //Tipo de pagamento  = PAGAMENTO_CREDITO_CONTA
		    public void debitoConta(){
		    	
		    	FacesMessage msg = new FacesMessage();
		    	
		    	
		    	System.out.println("Tipo de pagamento escolhido:Credito na conta");
		        
				
		    	//Defini��o do pagamento: utilizando cr�dito existente em conta
				int tipo_pagamento = Pagamento.PAGAMENTO_CREDITO_CONTA;
				
				
				
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        Usuario user = (Usuario)sessao.getAttribute("usuario");
		    	

				//Lan�a um novo pagamento no sistema
				Pagamento pag = new Pagamento();
				pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
				pag.setValor(Plano.getPrecoPlano(prioridade_anuncio));
				pag.setPrioridade(prioridade_anuncio);
				pag.setTipo(Transacao.CREDITO_DEBITO);//Altera��o de plano
				pag.setCodUser(user.getId());
				pag.setIdVeiculo(codVeiculo);
				pag.setDescricao(Transacao.getTextoTransacao(Transacao.CREDITO_DEBITO));
				pag.setFormaPagamento(tipo_pagamento);
			    pag.setData(Calendar.getInstance());
				
				new PagamentoDAO().insert(pag);
				
				
				//Verifica se o saldo do usu�rio � suficiente
				double saldo =  new FinanDAO().getSaldo(user.getId());
				
				if(saldo>=pag.getValor())
				{
					
				//Aprova o pagamento	
				pag.aprovar();
			
				
				//Encaminha para página adequada

			    try {
			    	
			    	//Anúncio já cadastrado mas o pagamento foi realizado novamente
			    	if(isReemissao)
					FacesContext.getCurrentInstance().getExternalContext().redirect(new ContextoBean().getContextoInicial()+"/arearestritausuario/alt_sucesso.jsf");
			    	
			    	
			    	//Pagamento no ato de cadastro do anúncio
			    	else
			    	FacesContext.getCurrentInstance().getExternalContext().redirect(new ContextoBean().getContextoInicial()+"/cad_veiculo/cad_anuncio_sucesso.xhtml");	
			    		
			    	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} 
				else
				{
	
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary("Saldo insuficiente pada realizar esta opera��o.");
					FacesContext.getCurrentInstance().addMessage(null,msg);
				}
		    	
		    }
		 
		 
		  
			
			
			//M�todo para gera��o de boleto de um pagamento para dowload
	    	public void geraBoleto(long codPagamento){
	    		
	    		
	    		
	    		
	    		HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        Usuario user = (Usuario)sessao.getAttribute("usuario");
	    		
	    		//O c�digo do pagamento lan�ado deve estar em sess�o
	    		if(codPagamento==0)
	    		Debug.gerar("", "AltPlanoBean","geraBoleto","C�digo do pagamento  = 0");	
	    		
	    		
	    		//Recupera��o do pagamento
	    		Pagamento pg = new PagamentoDAO().getPagamento(codPagamento);
	    		
	    		if(pg.getCodUser()==0)
	    		Debug.gerarDebugPagamento("","AltPlanoBean","geraBoleto","O C�digo do usu�rio no pagamento � = 0", pg);	
	    		
	    		
	    		
	    		//Recupera��o do documento, cpf ou cnpj
	    		String doc = UsuarioUtil.getDoc(user);
	    		
	    	
	    	    //Preenchimento dos dados do Boleto
	    		Boleto bol  =  new BoletoUtil().getBoleto(user, doc,pg.getValor(),pg.getCod(),0);
	    		
	    		

	    		BoletoViewer boletoViewer = new BoletoViewer(bol);
	    		
	    		
	            
	            byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();
	            
	           

	            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

	            try {
	                
	            	//DOWNLOAD DO BOLETO
	            	
	                 response.setContentType("application/pdf");
	                 response.setHeader("Content-Disposition", "attachment; filename=boleto_"+user.getNome()+".pdf");

	                 OutputStream output = response.getOutputStream();
	                 output.write(pdfAsBytes);
	                 response.flushBuffer();

	                 FacesContext.getCurrentInstance().responseComplete();

	            } catch (IOException e) {
	                    e.printStackTrace();
	            }
	    		
	    		
	    	}
			
			
			
			

}
