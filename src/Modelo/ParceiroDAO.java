package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.Debug;
import util.Parceiro;

public class ParceiroDAO {

	
	    
	     public ArrayList<Parceiro> getAllParceiros(){
	    	 
	    	 ArrayList<Parceiro> parceiros = new ArrayList<Parceiro>();
	    	 Parceiro parceiro;
	    	 
	    	 try{
	 			
	 			Connection con = Banco.abreBanco();
	 			Statement stm = con.createStatement();
	 			ResultSet res = stm.executeQuery("Select ID_USUARIO,NOME from usuario WHERE TIPO_USUARIO='PESSOA_JURIDICA'");
	 			
	 			while(res.next()){
	 				
	 			parceiro = new Parceiro();	
	 			parceiro.setCod(res.getLong("ID_USUARIO"));
	 			parceiro.setNome(res.getString("NOME"));
	 			parceiros.add(parceiro);
	 				
	 				
	 			}
	 			}catch(Exception e){
	 				
	 				Debug.gerar("Modelo", "ParceiroDAO","getAllParceiros", e.getMessage());
	 				
	 			}
	    	 
	    	 
	    	 return parceiros;
	     }
	
	
}
