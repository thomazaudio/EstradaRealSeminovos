package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import util.Debug;
import util.Destaque;
import util.Pagamento;
import util.Plano;

public class AnuncioAdmDAO {
	
	
	
	
	//CONFERE O PAGAMENTO DO VEÍCULO
    public void confirmaPagamento(long id){
    	
    	
    	
    	try{
    		
    	Connection con = Banco.abreBanco();
    	Statement stm =  con.createStatement();
    	
    	int prioridade_anuncio;
    	
    	//Veririfica qual é o plano do veículo
    	ResultSet res = stm.executeQuery("SELECT PRIORIDADE_ANUNCIO FROM veiculo WHERE ID_VEICULO="+id);
    	
    	
    	if(res.next())
    	{
    		
    		prioridade_anuncio = res.getInt("PRIORIDADE_ANUNCIO");
    		
    		System.out.println("A prioridade do anúncio é:"+prioridade_anuncio);
    		
    		//Ativação do banner
    		new DestaqueDAO().ativaDestaque(prioridade_anuncio,id);
    		
    		
    	}
    	res.close();
    	
    	stm.executeUpdate("UPDATE veiculo SET STATUS_PAGAMENTO=1 WHERE ID_VEICULO="+id);
    	stm.close();
    		
    		
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
