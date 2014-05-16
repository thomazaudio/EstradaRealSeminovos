package util;



import java.sql.Connection;
import java.sql.Statement;

import Modelo.AnuncioAdmDAO;
import Modelo.Banco;
import Modelo.FinanDAO;
import Modelo.PagamentoDAO;


public class Acao {
	
	
	Pagamento pg;
	
	


	//Executa uma ação referente a um pagamento
	public void executaAcaoPagamento(Pagamento pagamento){
		
		this.pg = pagamento;
		
		if(pg.getStatus()!=Pagamento.CONFIRMADO)
		{
			
		System.out.println("O código do pagamento é: "+pg.getCod());
		System.out.println("O tipo é"+pg.getTipo());
		System.out.println("O id do veículo  é"+pg.getIdVeiculo());
		System.out.println("A prioridade é"+pg.getPrioridade());
		
		//MARCA O PAGAMENTO COMO APROVADO
		new PagamentoDAO().aprovaPagamento(pg.getCod());


		//VERIFICAÇÃO DO TIPO 
		switch(pg.getTipo()){

		case Transacao.CREDITO: executAcaoCredito();
		break;

		case Transacao.DEBITO_CREDITO: executaAcaoDebitoCredito();
		break;

		case Transacao.CREDITO_DEBITO: executaAcaoCreditoDebito();
		break;

		case Transacao.ALT_PLANO: executaAcaoAltPlano();
		break;


		case Transacao.REEMITE_PAGAMENTO : executaAcaoReemitePagamento();
		break;

		}
		
	}
	else 
	Debug.gerarDebugPagamento("","Acao","executaAcaoPagamento","O pagamento já está confimado", pg);
		
		
}

	




	//Caso : alteração do plano de um veículo já cadastrado(Boleto ou Pag-seguro)
	public void executaAcaoAltPlano(){


		//Mudança da prioridade do anúncio	
		try{

			
			
			
			Connection con = Banco.abreBanco();
			Statement stm =  con.createStatement();
			stm.executeUpdate("UPDATE veiculo SET PRIORIDADE_ANUNCIO="+pg.getPrioridade()+" WHERE ID_VEICULO="+pg.getIdVeiculo());
            stm.close();  
		

		}catch(Exception e){

			Debug.gerar("","Acao","executaAcaoAltPlano",e.getMessage());

		}

		//Liberaçao finaceira do anúncio
		new  AnuncioAdmDAO().confirmaPagamento(pg.getIdVeiculo());
		
		
		//Se o método de pagamento for Débito em conta já debita o valor da conta de usuário
		System.out.println("Pagamento escolhido: "+pg.getFormaPagamento());
		if(pg.getFormaPagamento()==Pagamento.PAGAMENTO_CREDITO_CONTA)
		new FinanDAO().debitar(pg.getCodUser(),pg.getValor());
    

	}


	//CASO:  uso do dinheiro em caixa para pagar um anúncio
	public void executaAcaoDebitoCredito(){


		//Liberaçao finaceira do anúncio
		new  AnuncioAdmDAO().confirmaPagamento(pg.getIdVeiculo());

		//Debitar valor correspondente da conta do usuário
		new FinanDAO().debitar(pg.getCodUser(),pg.getValor());


	}


	//Caso: Pagamento de veículo no ato de cadastro
	public void executaAcaoCreditoDebito(){

		//Liberaçao finaceira do anúncio
		new  AnuncioAdmDAO().confirmaPagamento(pg.getIdVeiculo());

	}

	//Caso: Adicionar crédito na conta
	public void executAcaoCredito(){

		//Adiciona o valor na conta
		new FinanDAO().depositar(pg.getCodUser(),pg.getValor());

	}


	public void executaAcaoReemitePagamento(){

		//Liberaçao finaceira do anúncio
		new  AnuncioAdmDAO().confirmaPagamento(pg.getIdVeiculo());
	}


}
