package Bean;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

import util.Empresa;
import util.Pagamento;
import util.Pessoa;
import util.Plano;
import util.Usuario;
import util.Veiculo;
import Modelo.PagamentoDAO;
import Modelo.UsuarioDAO;
import Modelo.VeiculoDAO;


@ManagedBean
@SessionScoped
public class PagamentoAnuncio {
	
	
	private int prioridade_anuncio;
	private int tipo_transacao;
	private double valor;
	private long codVeiculo;
	
	
	
	    //Escolha do tipo de pagamento (Caso: pagamentoo logo após cadastramento dos dados do anúncio)
		 public void escTipoPagamentoCadastro(){
				
				Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
				int tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
				
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
		        
		        
		        //Tipo de plano
		         prioridade_anuncio = (Integer) sessao.getAttribute("prioridade_anuncio");
		         
		         //Codigo do veiculo
		         codVeiculo = (Long) sessao.getAttribute("cod_veiculo");
		         
		         System.out.println("Id do veiculo na sessoa: "+codVeiculo);
		        
		         //Valor 
		         valor = Plano.getPrecoPlano(prioridade_anuncio);
		        
		        
		        //Tipo de transação
		         tipo_transacao = Integer.parseInt(p.get("tipo_transacao"));
		        
		         //Lança um
				
				 if(tipo_pagamento==Pagamento.PAGAMENTO_BOLETO){
					
					
					//Lança um novo pagamento no sistema
					Pagamento pag = new Pagamento();
					pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
					pag.setValor(valor);
					pag.setTipo(tipo_transacao);
					pag.setCodUser(idUser);
					pag.setDescricao(Pagamento.getTextoTransacao(tipo_transacao));
					pag.setIdVeiculo(codVeiculo);
					pag.setData(Calendar.getInstance());
					
					new PagamentoDAO().insert(pag);
					
					System.out.println("Detalhes do pagamento");
					System.out.println("Tipo: "+pag.getTipo());
					System.out.println("Valor: "+pag.getValor());
					System.out.println("Usuário: "+pag.getCodUser());
					System.out.println("Veículo: "+pag.getIdVeiculo());
					
					
					
					//Encaminha para a pagina de download de boleto
				    try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("download_boleto_anuncio.jsf");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					
					
				}
				
				
			}
		 
		 
		 //Escolha do tipo de pagamento
		 public void escTipoPagamentoReemissao(){
			 
			 
			 
			 Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
			  
				
		        
		        //Codigo do veiculo
		        codVeiculo = Long.parseLong(p.get("cod_veiculo"));

		        //Prioridade do  anuncio 
		        prioridade_anuncio = Integer.parseInt(p.get("prioridade_anuncio"));
		        
		        System.out.println("PLano: "+prioridade_anuncio);
		        System.out.println("Preco: "+Plano.getPrecoPlano(prioridade_anuncio));
		        
		    
		        //Valor 
		        valor = Plano.getPrecoPlano(prioridade_anuncio);
		     
		        
		        //Tipo de transação (Reemissão de pagamento)
		        tipo_transacao = Pagamento.REEMITE_PAGAMENTO;
			 
			 

				//Encaminha para a pagina de  escolha de pagamento
			    try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("esc_pagamento_reemissao.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		 }
		 
		 //Reemissão de pagamento para este veículo
		 public void reemitePagamento(){
				
				Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				
				int tipo_pagamento = Integer.parseInt(p.get("tipo_pagamento"));
				
				HttpSession sessao;
		        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		        long idUser = ((Usuario)sessao.getAttribute("usuario")).getId();
		        
		       
  
		        //Valor 
		        valor = Plano.getPrecoPlano(prioridade_anuncio);
		        
		        
		     
		  
		        
              	if(tipo_pagamento==Pagamento.PAGAMENTO_BOLETO){
					
					
					//Lança um novo pagamento no sistema
					Pagamento pag = new Pagamento();
					pag.setStatus(Pagamento.AGUARDANDO_APROVACAO);
					pag.setValor(valor);
					pag.setTipo(tipo_transacao);
					pag.setCodUser(idUser);
					pag.setDescricao(Pagamento.getTextoTransacao(tipo_transacao));
					pag.setIdVeiculo(codVeiculo);
					pag.setData(Calendar.getInstance());
					
					
					//Lança um novo pagamento no sistema
					new PagamentoDAO().insert(pag);
					
					System.out.println("Detalhes do pagamento");
					System.out.println("Tipo: "+pag.getTipo());
					System.out.println("Valor: "+pag.getValor());
					System.out.println("Usuário: "+pag.getCodUser());
					System.out.println("Veículo: "+pag.getIdVeiculo());
					

					//Encaminha para a pagina de download de boleto
				    try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("download_boleto_anuncio.jsf");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					
					
				}
				
				
			}
		 
		 
		 
		    //Emite um boleto para acionar crédito ao fincanceiro de um usuário
			public void geraPagamentoCreditoBoleto(){
				
				

				HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				
				//Recupera o usuário em sessão
				Usuario user = (Usuario) sessao.getAttribute("usuario");	System.out.println("Valor: "+valor);
				
				
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
				titulo.setValor(BigDecimal.valueOf(valor));
				titulo.setDataDoDocumento(new Date());
				titulo.setDataDoVencimento(new Date());
				titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
				//titulo.setAceite(new Aceite());
				titulo.setDesconto(new BigDecimal(0.05));
				titulo.setDeducao(BigDecimal.ZERO);
				titulo.setMora(BigDecimal.ZERO);
				titulo.setAcrecimo(BigDecimal.ZERO);
				titulo.setValorCobrado(BigDecimal.valueOf(valor));
				
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
