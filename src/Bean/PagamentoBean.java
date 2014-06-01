package Bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import Modelo.FinanDAO;
import Modelo.PagamentoDAO;
import Modelo.VeiculoDAO;
import util.Pagamento;
import util.Plano;
import util.Transacao;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;


@ManagedBean
public class PagamentoBean {

	FacesContext contexto;
	FacesMessage msg;


	public PagamentoBean(){

		//Configura o contexto
		contexto = FacesContext.getCurrentInstance();

		msg =  new FacesMessage();
	}


	//ESCOLHA DE TRANSAÇÃO
	//REEMISSÃO DE UM PAGAMENTO
	public void reemitirPagamento(){

		//RECUPERA APARTIR DO REQUEST
		long cod_veiculo = Long.parseLong(getParametros().get("cod_veiculo"));

		//VERIFICA SE TEM ALGUM PAGAMENTO JÁ LANÇADP PARA ESTE VEÍCULO E PEGA O ULTIMO
		Pagamento pg =  new PagamentoDAO().getLastPagamentVeiculo(cod_veiculo);

		//reseta o pagamento

		//Se não houver pagamentos ou se não estiver confirmado
		if(pg==null || pg.getStatus()!=0){

			//dados para geração do pagamento
			pg =  new Pagamento();
			int prioridade =  new VeiculoDAO().getPrioridadeVeiculo(cod_veiculo);
			pg.setIdVeiculo(cod_veiculo);
			pg.setCodUser(UsuarioBean.getUserSession().getId());
			pg.setTipo(Transacao.REEMITE_PAGAMENTO);
			pg.setData(Calendar.getInstance());
			pg.setDescricao("Reemissão de pagamento de anúncio");
			pg.setValor(Plano.getPrecoPlano(prioridade));
			pg.setStatus(0);
			pg.setPrioridade(prioridade);

			new PagamentoDAO().insert(pg);





		}



		//REDIRECIONA O USUÁRIO PARA PÁGINA DE ESCOLHA DE PAGAMENTO
		try {

			FacesContext.getCurrentInstance().getExternalContext().redirect(new ContextoBean().getContextoInicial()+"/esc_pagamento.jsf?tipo_transcao="+pg.getTipo()+"&&cod_pagamento="+pg.getCod());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	



	//APÓS ESCOLHER TIPO DE PAGAMENTO

	//PAGAMENTO VIA BOLETO
	public void pagamentoBoleto(){

		//ID DO PAGAMENTO
		long id =  Integer.parseInt(getParametros().get("cod_pagamento"));

		System.out.println("Opção escolhida: Pagamento via booleto");
		System.out.println("Id do pagamento: "+id);

		//RECEBE OS DADOS REFERENTES AO PAGAMENO SOLICITADO
		Pagamento pg = new PagamentoDAO().getPagamento(id);


		//PAGAMENTO VIA BOLETO
		pg.setFormaPagamento(Pagamento.PAGAMENTO_BOLETO);

		//ATUALIZA O PAGAMENTO NO SISTEMA
		new PagamentoDAO().update(pg);

		//REDIRECIONA O USUÁRIO PARA PÁGINA DE GERAÇÃO DO BOLETO INFORMANDO O CÓDIGO DO PAGAMENTO LANÇADO
		try {

			FacesContext.getCurrentInstance().getExternalContext().redirect("download_boleto.jsf?cod_pagamento="+id);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




	//PAGAMENTO VIA CRÉDITO NA CONTA
	public void pagamentoCreditoConta(){


		//ID DO PAGAMENTO
		long id =  Integer.parseInt(getParametros().get("cod_pagamento"));

		System.out.println("Opção escolhida: Pagamento via booleto");
		System.out.println("Id do pagamento: "+id);

		//RECEBE OS DADOS REFERENTES AO PAGAMENO SOLICITADO
		Pagamento pg = new PagamentoDAO().getPagamento(id);
		
		//Atualiza a forma de pagamento escolhida, no caso Credito na conta
		pg.setFormaPagamento(Pagamento.PAGAMENTO_CREDITO_CONTA);
		new PagamentoDAO().update(pg);

		//Verifica se o tipo de transação é diferente de CREDITO
		if(pg.getTipo()!=Transacao.CREDITO){


			//VERIFICA SE O USUÁRIO POSSUI CRÉDITO DISPONÍVEL EM CONTA
			if(new FinanDAO().getSaldo(UsuarioBean.getUserSession().getId())>=pg.getValor())
			{
				pg.aprovar();
			   
				
				//REDIRECIONA PARA PÁGINA DE SUCESSO
				try {

					FacesContext.getCurrentInstance().getExternalContext().redirect(new ContextoBean().getContextoInicial()+"/arearestritausuario/alt_sucesso.jsf");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
			else
			{

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary("Saldo insuficiente para realizar esta operação.");
				contexto.addMessage(null,msg);	
			}

		}

		else 
		{
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary("Você não pode utilizar esse método de pagamento para esse tipo de transação.");
			contexto.addMessage(null,msg);
		}




	}

	//PAGAMENTO VIA PAG SEGURO
	public void pagamentoPagSeguro(){

	}


	private Map<String,String> getParametros(){

		Map<String,String> params = contexto.getExternalContext().getRequestParameterMap();

		return params;
	}



}
