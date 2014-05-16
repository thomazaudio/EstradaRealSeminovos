package Bean;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import Modelo.PagamentoDAO;
import Modelo.UsuarioDAO;
import util.BoletoUtil;
import util.Debug;
import util.Pagamento;
import util.Plano;
import util.Transacao;
import util.Usuario;
import util.UsuarioUtil;


@ManagedBean
@SessionScoped
public class AltPlanoBean {
	
	private long codVeiculo;
	private int prioridade_anunio;//Nova prioridade escolhida

	//C�digo do ve�culo que ser� alterado o plano
	public long getCodVeiculo(){
		
		return codVeiculo;
	}
	
	private long codPagamento;
	
	
	public long getCodPagamento() {
		return codPagamento;
	}


	public void setCodPagamento(long codPagamento) {
		this.codPagamento = codPagamento;
	}


	

	
	
	//Inicio da altera��o
	public void alteraPlano(){
		
	Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
	codVeiculo= Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod_veiculo"));
        
	
		
		//Encaminha pra pagina de escolha do plano a ser alterado
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt_plano.jsf");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
			
		}
		
		
		
	
	
	 //Ap�s escolha do plano, escolha do pagamento
	 public void escPagamento(){
		
		//Recebe a prioridade que � de acordo com o plano escolhido
		prioridade_anunio = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prioridade_anuncio"));
		
		String page_pos;
		
		if(prioridade_anunio==Plano.PRIORIDADE_GRATIS)
		{
			
			
			
			
			
			page_pos="alt_sucesso.jsf";
			
		}
		
		else
		page_pos="esc_pagamento_alt_anuncio.jsf";	
			
		
		//Encaminha para a pagina de  escolha de pagamento
	    try {
	    	
			FacesContext.getCurrentInstance().getExternalContext().redirect(page_pos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	    
	
	       //M�todo recebedor dos parametros do pagamento
	       //Encaminha para pagina de acordo com o m�todo escolhido 
	        public void pagar(){
	        	
	        //Transacao: ALT_PLANO
	        
	        	
	        //M�todos de pagamento(tipo_pagamento)
	        //PAGAMENTO_CREDITO_CONTA
	        //PAGAMENTO_PAG_SEGURO
	        //PAGAMENTO_BOLETO	
	        	
	    	
	    	//Configura��o dos parametros externos
	    	 Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    	 
	    	//Recebe o tipo de pagamento
	    	int tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
	    	
	    	System.out.println("O tipo escolhido para pagamento �: "+tipo_pagamento);
	    	 
	    	 
	    	 switch(tipo_pagamento){
	    	 
	    	 
	    	 //Altera�ao via boleto banc�rio
	    	 case Pagamento.PAGAMENTO_BOLETO :
	    	 this.pagamentoBoleto();
	    	 break;
	    	 
	    	 //Altera��o via pag-seguro
	    	 case Pagamento.PAGAMENTO_PAG_SEGURO :
	    	 this.pagamentoPagSeguro();
	    	 break; 
	    	 
	    	 //Altera��o via cr�dito em conta
	    	 case Pagamento.PAGAMENTO_CREDITO_CONTA :
	    	 this.debitoConta();
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
			pag.setValor(Plano.getPrecoPlano(prioridade_anunio));
			pag.setPrioridade(prioridade_anunio);
			pag.setTipo(Transacao.ALT_PLANO);//Altera��o de plano
			pag.setCodUser(idUser);
			pag.setIdVeiculo(this.getCodVeiculo());
			pag.setDescricao(Transacao.getTextoTransacao(Transacao.ALT_PLANO));
			pag.setFormaPagamento(tipo_pagamento);
		    pag.setData(Calendar.getInstance());
			
			new PagamentoDAO().insert(pag);
	    	
		
			
			
	    	//Atribui��o do codigo do pagamento para sessao
			codPagamento = pag.getCod();
	    	
			//Gera o belo 
			
			
			
	    	
			//Encaminha para a pagina de download de boleto
		    try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("download_boleto_alt_plano.jsf");
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
	    	
	    	
	    	System.out.println("Tipo de pagamento escolhido:Credito na conta");
	        
			
	    	//Defini��o do pagamento: utilizando cr�dito existente em conta
			int tipo_pagamento = Pagamento.PAGAMENTO_CREDITO_CONTA;
			
			
			
			HttpSession sessao;
	        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
	    	

			//Lan�a um novo pagamento no sistema
			Pagamento pag = new Pagamento();
			pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
			pag.setValor(Plano.getPrecoPlano(prioridade_anunio));
			pag.setPrioridade(prioridade_anunio);
			pag.setTipo(Transacao.ALT_PLANO);//Altera��o de plano
			pag.setCodUser(idUser);
			pag.setIdVeiculo(this.getCodVeiculo());
			pag.setDescricao(Transacao.getTextoTransacao(Transacao.ALT_PLANO));
			pag.setFormaPagamento(tipo_pagamento);
		    pag.setData(Calendar.getInstance());
			
			new PagamentoDAO().insert(pag);
			
			
			//COMO O VALOR � DEBITADO INSTANTANEAMENTE DA CONTA DO USU�RIO, O PAGAMENTO � IMEDIATAMENTE APROVADO
			pag.aprovar();
			

		    try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("alt_sucesso.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	
	    }

        
      //M�todo para gera��o de boleto de um pagamento para dowload
    	public void geraBoleto(){
    		
    		
    		//O c�digo do pagamento lan�ado deve estar em sess�o
    		if(codPagamento==0)
    		Debug.gerar("", "AltPlanoBean","geraBoleto","C�digo do pagamento  = 0");	
    		
    		
    		//Recupera��o do pagamento
    		Pagamento pg = new PagamentoDAO().getPagamento(codPagamento);
    		
    		if(pg.getCodUser()==0)
    		Debug.gerarDebugPagamento("","AltPlanoBean","geraBoleto","O C�digo do usu�rio no pagamento � = 0", pg);	
    		
    		
    		//Recupera��o do usu�rio relacionado ao pagamento (sacado)
    		Usuario user = new UsuarioDAO().getUser(pg.getCodUser());
    		
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
