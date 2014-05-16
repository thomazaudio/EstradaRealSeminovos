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


//CLASSE PARA PAGAMENTO DE ANÚNCIO NO ATO DE CADASTRO

@ManagedBean
@SessionScoped
public class PagamentoAnuncio {
	
	
	private int prioridade_anuncio;
	private int tipo_transacao;
	private double valor;
	private long codVeiculo;
	private long codPagamento;
	
	
	
	    public long getCodPagamento() {
		return codPagamento;
	}


	public void setCodPagamento(long codPagamento) {
		this.codPagamento = codPagamento;
	}


		//Escolha do tipo de pagamento (Caso: pagamentoo logo após cadastramento dos dados do anúncio)
		 public void escTipoPagamentoCadastro(){
				
				Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
				
				//TIPO DE PAGAMENTO: BOLETO, PAG-SEGURO, CREDITO EM CONTA
				int tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
				
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        
		        
		        
		         //Tipo de plano
		         //COMO ESTÁ NO ATO DE CADASTRO O DADO SE ENCONTRA EM SESSÃO
		         prioridade_anuncio = (Integer) sessao.getAttribute("prioridade_anuncio");
		          
		         //Codigo do veiculo
		         codVeiculo = (Long) sessao.getAttribute("cod_veiculo");
		         
		         
		        
		         //Valor 
		         valor = Plano.getPrecoPlano(prioridade_anuncio);
		        
		        
		        //Tipo de transação
		         tipo_transacao = Integer.parseInt(p.get("tipo_transacao"));
		         
		         
		     	
		         
		        //verificação do tipo de pagamento escolhido
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
			 
			 
			 
			 Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
			  
				
		        
		        //Codigo do veiculo
		        codVeiculo = Long.parseLong(p.get("cod_veiculo"));

		        //Prioridade do  anuncio 
		        prioridade_anuncio = Integer.parseInt(p.get("prioridade_anuncio"));
		        
		        
		    
		        //Valor 
		        valor = Plano.getPrecoPlano(prioridade_anuncio);
		     
		        
		        //Tipo de transação (Reemissão de pagamento)
		        tipo_transacao = Transacao.REEMITE_PAGAMENTO;
		        
		 

				//Encaminha para a pagina de  escolha de pagamento
			    try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("esc_pagamento_reemissao.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		 }
		 
		 //Reemissão de pagamento para este veículo
		 public void escTipoPagamentoReemissao(){
				
				Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
				int tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
				
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
		        
		       
  
		        //Valor 
		        valor = Plano.getPrecoPlano(prioridade_anuncio);
		        
		        
		        //verificação do tipo de pagamento escolhido
				switch(tipo_pagamento){
				
				case Pagamento.PAGAMENTO_BOLETO: this.pagamentoBoleto();
				break;
				
				case Pagamento.PAGAMENTO_CREDITO_CONTA: this.debitoConta();
				break;
				
				case Pagamento.PAGAMENTO_PAG_SEGURO: this.pagamentoPagSeguro();
				break;
				
				}
		  
		       
						
					
					
				
				
				
			}
		 
		 
		 

		    //Método escolhido  = PAGAMENTO_BOLETO
			//Tipo de Transação  = CREDITO_DEBITO
			//Tipo de pagamento  = PAGAMENTO_BOLETO
		    public void pagamentoBoleto(){
		    	

		    	//Lança o pagamento no sistema
		    	//Definição do pagamento:boleto bancário
				int tipo_pagamento = Pagamento.PAGAMENTO_BOLETO;
				
				
				//Recupera usuario pra preenchimento das informações
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
		    	

				//Lança um novo pagamento no sistema
				Pagamento pag = new Pagamento();
				pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
				pag.setValor(Plano.getPrecoPlano(prioridade_anuncio));
				pag.setPrioridade(prioridade_anuncio);
				pag.setTipo(Transacao.CREDITO_DEBITO);//Pagamento de anúncio
				pag.setCodUser(idUser);
				pag.setIdVeiculo(codVeiculo);
				pag.setDescricao(Transacao.getTextoTransacao(Transacao.CREDITO_DEBITO));
				pag.setFormaPagamento(tipo_pagamento);
			    pag.setData(Calendar.getInstance());
				
				new PagamentoDAO().insert(pag);
		    	
			
				
				
		    	//Atribuição do codigo do pagamento para sessao
				codPagamento = pag.getCod();
		    	
				
		    	
				//Encaminha para a pagina de download de boleto
			    try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(new ContextoBean().getContextoInicial()+"/cad_veiculo/download_boleto_anuncio.jsf?id_pagamento="+pag.getCod());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		 
		    
		    
		    
		    //Método escolhido = PAGAMENTO_PAG_SEGURO
			//Tipo de Transação  = CREDITO_DEBITO
	    	//Tipo de pagamento  = PAGAMENTO_PAG_SEGURO
		    public void pagamentoPagSeguro(){
		    	
	             //Lança o pagamento no sistema
		    	
		    	//Atribuição do codigo do pagamento para sessao
		    	
		    	//Redireciona para a página definida
		    	
		    }
		    
		    
		    
		
		    //Método escolhido = Debito na conta
		    //Tipo de Transação  = ALT_PLANO_CREDITO
		    //Tipo de pagamento  = PAGAMENTO_CREDITO_CONTA
		    public void debitoConta(){
		    	
		    	FacesMessage msg = new FacesMessage();
		    	
		    	
		    	System.out.println("Tipo de pagamento escolhido:Credito na conta");
		        
				
		    	//Definição do pagamento: utilizando crédito existente em conta
				int tipo_pagamento = Pagamento.PAGAMENTO_CREDITO_CONTA;
				
				
				
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        Usuario user = (Usuario)sessao.getAttribute("usuario");
		    	

				//Lança um novo pagamento no sistema
				Pagamento pag = new Pagamento();
				pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
				pag.setValor(Plano.getPrecoPlano(prioridade_anuncio));
				pag.setPrioridade(prioridade_anuncio);
				pag.setTipo(Transacao.CREDITO_DEBITO);//Alteração de plano
				pag.setCodUser(user.getId());
				pag.setIdVeiculo(codVeiculo);
				pag.setDescricao(Transacao.getTextoTransacao(Transacao.CREDITO_DEBITO));
				pag.setFormaPagamento(tipo_pagamento);
			    pag.setData(Calendar.getInstance());
				
				new PagamentoDAO().insert(pag);
				
				
				//Verifica se o saldo do usuário é suficiente
				double saldo =  new FinanDAO().getSaldo(user.getId());
				
				if(saldo>=pag.getValor())
				{
					
				//Aprova o pagamento	
				pag.aprovar();
			
				
				//Encaminha para página adequada

			    try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("alt_sucesso.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} 
				else
				{
	
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary("Saldo insuficiente pada realizar esta operação.");
					FacesContext.getCurrentInstance().addMessage(null,msg);
				}
		    	
		    }
		 
		 
		  
			
			
			//Método para geração de boleto de um pagamento para dowload
	    	public void geraBoleto(long codPagamento){
	    		
	    		
	    		
	    		
	    		HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        Usuario user = (Usuario)sessao.getAttribute("usuario");
	    		
	    		//O código do pagamento lançado deve estar em sessão
	    		if(codPagamento==0)
	    		Debug.gerar("", "AltPlanoBean","geraBoleto","Código do pagamento  = 0");	
	    		
	    		
	    		//Recuperação do pagamento
	    		Pagamento pg = new PagamentoDAO().getPagamento(codPagamento);
	    		
	    		if(pg.getCodUser()==0)
	    		Debug.gerarDebugPagamento("","AltPlanoBean","geraBoleto","O Código do usuário no pagamento é = 0", pg);	
	    		
	    		
	    		
	    		//Recuperação do documento, cpf ou cnpj
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
