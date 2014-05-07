package Bean;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

import Modelo.PagamentoDAO;
import Modelo.UsuarioDAO;
import util.Empresa;
import util.Pagamento;
import util.Pessoa;
import util.Plano;
import util.Usuario;


@ManagedBean
@SessionScoped
public class AltPlanoBean {
	
	private long codVeiculo;
	private int prioridade_anunio;//Nova prioridade escolhida
	private int tipo_pagamento;
	private int tipo_transacao;
	private String textoPlano;
	
	
	public String getTextoPlano() {
		
		textoPlano = Plano.getPlano(prioridade_anunio);
		
		return textoPlano;
	}


	public void setTextoPlano(String textoPlano) {
		this.textoPlano = textoPlano;
	}


	public int getTipo_transacao() {
		return tipo_transacao;
	}


	public void setTipo_transacao(int tipo_transacao) {
		this.tipo_transacao = tipo_transacao;
	}


	public AltPlanoBean(){
		
		

	}
	
	
	//Inicio da alteração
	public void alteraPlano(){
		
		
		
		Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		codVeiculo= Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cod_veiculo"));
        
		
		System.out.println("Codigo do veiculo="+codVeiculo);
		
		//Encaminha pra pagina de escolha do plano
		 try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt_plano.jsf");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
			
		}
		
		
		
	
	
	//Após escolha do plano, escolha do pagamento
	public void escPagamento(){
		
		prioridade_anunio = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prioridade_anuncio"));
		
		
		//Encaminha para a pagina de  escolha de pagamento
	    try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("esc_pagamento_alt_anuncio.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	    //Debito na conta
	    public void debitoConta(){
	    	
	    	
	    	
            Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			
			tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
			
			tipo_transacao = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tipo_transacao"));
			
			HttpSession sessao;
	        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
	    	

			//Lança um novo pagamento no sistema
			Pagamento pag = new Pagamento();
			pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
			pag.setValor(Plano.getPrecoPlano(prioridade_anunio));
			pag.setPrioridade(prioridade_anunio);
			pag.setTipo(tipo_transacao);
			pag.setCodUser(idUser);
			pag.setIdVeiculo(this.getCodVeiculo());
			pag.setDescricao(Pagamento.getTextoTransacao(tipo_transacao));
			
			new PagamentoDAO().insert(pag);
			
			pag.aprovar();
			

			
		    try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("alt_sucesso.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	
	    }
	 
	
	
	    //Escolha do tipo de pagamento 
		public void escTipoPagamento(){
			
			Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			
			tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
			
			tipo_transacao = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tipo_transacao"));
			
			HttpSession sessao;
	        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
			
			if(tipo_pagamento==Pagamento.PAGAMENTO_BOLETO){
				
				
				//Lança um novo pagamento no sistema
				Pagamento pag = new Pagamento();
				pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
				pag.setValor(Plano.getPrecoPlano(prioridade_anunio));
				pag.setPrioridade(prioridade_anunio);
				pag.setTipo(tipo_transacao);
				pag.setCodUser(idUser);
				pag.setIdVeiculo(this.getCodVeiculo());
				pag.setDescricao(Pagamento.getTextoTransacao(tipo_transacao));
				
				new PagamentoDAO().insert(pag);
				
				
				
				
				
				//Encaminha para a pagina de download de boleto
			    try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("download_boleto_alt_plano.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
				
			}
			
			//Pagamento através d credito existente em conta
			else if(tipo_pagamento==Pagamento.PAGAMENTO_CREDITO_CONTA)	
			{
				
				
				

				//Lança um novo pagamento no sistema
				Pagamento pag = new Pagamento();
				pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
				pag.setValor(Plano.getPrecoPlano(prioridade_anunio));
				pag.setPrioridade(prioridade_anunio);
				pag.setTipo(tipo_transacao);
				pag.setCodUser(idUser);
				pag.setIdVeiculo(this.getCodVeiculo());
				pag.setDescricao(Pagamento.getTextoTransacao(tipo_transacao));
				
				new PagamentoDAO().insert(pag);
				
				
				
				
				
				//Encaminha para a pagina de download de boleto
			    try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("download_boleto_alt_plano.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}
	
	//Código do veículo que será alterado o plano
	public long getCodVeiculo(){
		
		return codVeiculo;
	}
	
	
	//Emite um boleto para acionar crédito ao fincanceiro de um usuário
		public void geraPagamentoCreditoBoleto(){
			
			
			System.out.println("Prioridade do anúncio");
			

			HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			
			//Recupera o usuário em sessão
			Usuario user = (Usuario) sessao.getAttribute("usuario");	
			
			
			int tipo_user = new UsuarioDAO().getTipoUser(user.getId());
			
			
			String doc;
			
			
			
			if(tipo_user==Usuario.PESSOA)
			doc  = ((Pessoa) user).getCpf();
			else
			doc  = ((Empresa) user).getCnpj();	
			
			Cedente cedente = new Cedente("Grupo Estrada Real", "00.000.208/0001-00");
			
			Sacado sacado = new Sacado(user.getNome(),doc);

			// Informando o endereço do sacado.
			Endereco enderecoSac = new Endereco();
			enderecoSac.setUF(UnidadeFederativa.RN);
			enderecoSac.setLocalidade("Natal");
			enderecoSac.setCep(new CEP("59064-120"));
			enderecoSac.setBairro("Grande Centro");
			enderecoSac.setLogradouro("Rua poeta dos programas");
			enderecoSac.setNumero("1");
			sacado.addEndereco(enderecoSac);
			
			SacadorAvalista sacadorAvalista = new SacadorAvalista("JRimum Enterprise", "00.000.000/0001-91");

			// Informando o endereço do sacador avalista.
			Endereco enderecoSacAval = new Endereco();
			enderecoSacAval.setUF(UnidadeFederativa.DF);
			enderecoSacAval.setLocalidade("Brasília");
			enderecoSacAval.setCep(new CEP("59000-000"));
			enderecoSacAval.setBairro("Grande Centro");
			enderecoSacAval.setLogradouro("Rua Eternamente Principal");
			enderecoSacAval.setNumero("001");
			sacadorAvalista.addEndereco(enderecoSacAval);
			
			
			// Informando dados sobre a conta bancária do título.
			ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
			contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
			contaBancaria.setCarteira(new Carteira(30));
			contaBancaria.setAgencia(new Agencia(1234, "1"));
			
			
			Titulo titulo = new Titulo(contaBancaria, sacado, cedente, sacadorAvalista);
			titulo.setNumeroDoDocumento("123456");
			titulo.setNossoNumero("99345678912");
			titulo.setDigitoDoNossoNumero("5");
			titulo.setValor(BigDecimal.valueOf(Plano.getPrecoPlano(prioridade_anunio)));
			titulo.setDataDoDocumento(new Date());
			titulo.setDataDoVencimento(new Date());
			titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
			//titulo.setAceite(new Aceite());
			titulo.setDesconto(new BigDecimal(0.05));
			titulo.setDeducao(BigDecimal.ZERO);
			titulo.setMora(BigDecimal.ZERO);
			titulo.setAcrecimo(BigDecimal.ZERO);
			titulo.setValorCobrado(BigDecimal.valueOf(Plano.getPrecoPlano(prioridade_anunio)));
			
			Boleto boleto = new Boleto(titulo);
	        
			boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em " +
			                "qualquer Banco até o Vencimento.");
			
			
			
			BoletoViewer boletoViewer = new BoletoViewer(boleto);
	        
	        byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();
	        
	       

	        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

	        try {
	                                
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
