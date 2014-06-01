package util;



import java.sql.Connection;
import java.sql.Statement;

import Modelo.AnuncioAdmDAO;
import Modelo.Banco;
import Modelo.FinanDAO;
import Modelo.PagamentoDAO;


public class Acao {
	
	
	Pagamento pg;
	
	


	//Executa uma a��o referente a um pagamento
	public void executaAcaoPagamento(Pagamento pagamento){
		
		this.pg = pagamento;
		
		if(pg.getStatus()!=Pagamento.CONFIRMADO)
		{
			
		System.out.println("O c�digo do pagamento �: "+pg.getCod());
		System.out.println("O tipo �"+pg.getTipo());
		System.out.println("O id do ve�culo  �"+pg.getIdVeiculo());
		System.out.println("A prioridade �"+pg.getPrioridade());
		
		//MARCA O PAGAMENTO COMO APROVADO
		new PagamentoDAO().aprovaPagamento(pg.getCod());


		//VERIFICA��O DO TIPO 
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
		
		default :Debug.gerar("","","","O tipo de transação do pagamento é inválido, pagamento: "+pg.getIdVeiculo()); ;

		}
		
	}
	else 
	Debug.gerarDebugPagamento("","Acao","executaAcaoPagamento","O pagamento j� est� confimado", pg);
		
		
}

	




	//Caso : altera��o do plano de um ve�culo j� cadastrado(Boleto ou Pag-seguro)
	public void executaAcaoAltPlano(){

		
		System.out.println("Iníncio da execução de alteração de plano");

		//Mudanã na prioridade do anúncio
		try{

			
			
			
			Connection con = Banco.abreBanco();
			Statement stm =  con.createStatement();
			stm.executeUpdate("UPDATE veiculo SET PRIORIDADE_ANUNCIO="+pg.getPrioridade()+" WHERE ID_VEICULO="+pg.getIdVeiculo());
            stm.close();  
		

		}catch(Exception e){

			Debug.gerar("","Acao","executaAcaoAltPlano",e.getMessage());

		}

		//Libera�ao finaceira do an�ncio
		new  AnuncioAdmDAO().confirmaPagamento(pg.getIdVeiculo());
		
		
		//Se o m�todo de pagamento for D�bito em conta j� debita o valor da conta de usu�rio
		System.out.println("Pagamento escolhido: "+pg.getFormaPagamento());
		if(pg.getFormaPagamento()==Pagamento.PAGAMENTO_CREDITO_CONTA)
		new FinanDAO().debitar(pg.getCodUser(),pg.getValor());
    
		System.out.println("Final da execução de alteração de plano");

	}


	//CASO:  uso do dinheiro em caixa para pagar um an�ncio
	public void executaAcaoDebitoCredito(){


		//Libera�ao finaceira do an�ncio
		new  AnuncioAdmDAO().confirmaPagamento(pg.getIdVeiculo());

		//Debitar valor correspondente da conta do usu�rio
		new FinanDAO().debitar(pg.getCodUser(),pg.getValor());
		
		
		System.out.println("Valor: "+pg.getValor()+" Debitado da conta do usuário: "+pg.getCodUser());


	}


	//Caso: Pagamento de ve�culo no ato de cadastro
	public void executaAcaoCreditoDebito(){

		//Libera�ao finaceira do an�ncio
		new  AnuncioAdmDAO().confirmaPagamento(pg.getIdVeiculo());
		
		//Se o m�todo de pagamento for D�bito em conta j� debita o valor da conta de usu�rio
		System.out.println("Pagamento escolhido: "+pg.getFormaPagamento());
		if(pg.getFormaPagamento()==Pagamento.PAGAMENTO_CREDITO_CONTA)
		new FinanDAO().debitar(pg.getCodUser(),pg.getValor());

	}

	//Caso: Adicionar cr�dito na conta
	public void executAcaoCredito(){

		//Adiciona o valor na conta
		new FinanDAO().depositar(pg.getCodUser(),pg.getValor());

	}


	public void executaAcaoReemitePagamento(){

		//Libera�ao finaceira do an�ncio
		new  AnuncioAdmDAO().confirmaPagamento(pg.getIdVeiculo());
	}


}
