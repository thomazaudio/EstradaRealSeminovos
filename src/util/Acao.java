package util;



import Modelo.AnuncioAdmDAO;
import Modelo.FinanDAO;


public class Acao {
	
    
	
	
	
	//CASO:  uso do dinheiro em caixa para pagar um an�ncio
	public void executaAcaoDebitoCredito(long id_usuario,long id_veiculo,double valor){
		
		System.out.println("Usuario: "+id_usuario);
		System.out.println("IdVeiculo: "+id_veiculo);
		System.out.println("Valor: "+valor);
		
		//Libera�ao finaceira do an�ncio
		new  AnuncioAdmDAO().confirmaPagamento(id_veiculo);
		
		//Debitar valor correspondente da conta do usu�rio
		new FinanDAO().debitar(id_usuario, valor);
		
		
	}
	
	//Caso: Adicionar cr�dito na conta
	public void executAcaoCredito(long id_usuario,double valor){
		
		//Adiciona o valor na conta
		new FinanDAO().depositar(id_usuario, valor);
		
	}
	
	

}
