package util;



import java.sql.Connection;
import java.sql.Statement;

import Modelo.AnuncioAdmDAO;
import Modelo.Banco;
import Modelo.FinanDAO;


public class Acao {
	
    
	
	//Caso : alteração do plano de um veículo já cadastrado(Boleto ou Pag-seguro)
	public void executaAcaoAltPlano(long id_veiculo,int prioridade){
		
		
		
		//Liberaçao finaceira do anúncio
		new  AnuncioAdmDAO().confirmaPagamento(id_veiculo);

		try{
			
			Connection con = Banco.abreBanco();
			Statement stm =  con.createStatement();
			stm.executeUpdate("UPDATE veiculo SET PRIORIDADE_ANUNCIO="+prioridade+" WHERE ID_VEICULO="+id_veiculo);
			
			System.out.println("Plano do veiculo "+id_veiculo+" alterado com sucesso!");
			
		}catch(Exception e){
			
			Debug.gerar("", "Acao","executaAcaoAltPlano", e.getMessage());
			
		}
		
	}
	
	//Caso : alteração do plano de um veículo já cadastrado(Credito em conta)
		public void executaAcaoAltPlanoCredito(long id_veiculo,int prioridade,double valor,long user){
			
			
			
			
			//Liberaçao finaceira do anúncio
			new  AnuncioAdmDAO().confirmaPagamento(id_veiculo);
			
			//Debitar valor correspondente da conta do usuário
			new FinanDAO().debitar(user, valor);
			
			try{
				
				Connection con = Banco.abreBanco();
				Statement stm =  con.createStatement();
				stm.executeUpdate("UPDATE veiculo SET PRIORIDADE_ANUNCIO="+prioridade+" WHERE ID_VEICULO="+id_veiculo);
				
				System.out.println("Plano do veiculo "+id_veiculo+" alterado com sucesso!");
				
			}catch(Exception e){
				
				Debug.gerar("", "Acao","executaAcaoAltPlano", e.getMessage());
				
			}
			
		}
	
	
	//CASO:  uso do dinheiro em caixa para pagar um anúncio
	public void executaAcaoDebitoCredito(long id_usuario,long id_veiculo,double valor){
		
		System.out.println("Usuario: "+id_usuario);
		System.out.println("IdVeiculo: "+id_veiculo);
		System.out.println("Valor: "+valor);
		
		//Liberaçao finaceira do anúncio
		new  AnuncioAdmDAO().confirmaPagamento(id_veiculo);
		
		//Debitar valor correspondente da conta do usuário
		new FinanDAO().debitar(id_usuario, valor);
		
		
	}
	
	
	//Caso: Pagamento de veículo no ato de cadastro
	public void executaAcaoCreditoDebito(long id_veiculo){
		
		//Liberaçao finaceira do anúncio
		new  AnuncioAdmDAO().confirmaPagamento(id_veiculo);
		
	}
	
	//Caso: Adicionar crédito na conta
	public void executAcaoCredito(long id_usuario,double valor){
		
		//Adiciona o valor na conta
		new FinanDAO().depositar(id_usuario, valor);
		
	}
	
	
	public void executaAcaoReemitePagamento(long id_veiculo){
		

		//Liberaçao finaceira do anúncio
		new  AnuncioAdmDAO().confirmaPagamento(id_veiculo);
	}
	

}
