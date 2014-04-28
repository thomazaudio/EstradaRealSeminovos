package Modelo;

import java.sql.Connection;
import java.sql.Statement;

import util.Debug;

public class AnuncioAdmDAO {
	
	
	
	
	//CONFERE O PAGAMENTO DO VEÍCULO
    public void confirmaPagamento(long id){
    	
    	
    	
    	try{
    		
    		Connection con = Banco.abreBanco();
    		Statement stm  =  con.createStatement();
    	    
    		stm.executeUpdate("UPDATE veiculo SET STATUS_PAGAMENTO=1 WHERE ID_VEICULO="+id);
    		stm.close();
    		
    		System.out.println("Pagamento do veiculo '"+id+"' confirmado com sucesso!");
    		
    		
    		
    	}catch(Exception e){
    		
    		Debug.gerar("", "AnuncioAdmDAO","confirmaPagamento", e.getMessage());
    	}
    	}
    
    //CONFERE OS DADOS DO VEÍCULO
    public void confereDados(long id){
    	
    	try{
    		
    		Connection con = Banco.abreBanco();
    		Statement stm  =  con.createStatement();
    	    
    		stm.executeUpdate("UPDATE veiculo SET STATUS_VALIDACAO=1 WHERE ID_VEICULO="+id);
    		stm.close();
    		
    		System.out.println("Dados do veiculo '"+id+"' confirmados com sucesso!");
    		
    		
    		
    	}catch(Exception e){
    		
    		Debug.gerar("", "AnuncioAdmDAO","confereDados", e.getMessage());
    	}
    	
    }

}
