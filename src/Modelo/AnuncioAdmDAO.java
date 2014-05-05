package Modelo;

import java.sql.Connection;
import java.sql.Statement;

import util.Debug;
import util.Pagamento;

public class AnuncioAdmDAO {
	
	
	
	
	//CONFERE O PAGAMENTO DO VEÍCULO
    public void confirmaPagamento(long id){
    	
    	
    	
    	try{
    		
    	//Recupera o pagamento
    	Pagamento pag  =  new PagamentoDAO().getPagamento(id);
    	
    	//aprova a pagamento
    	pag.aprovar();
    		
    		
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
