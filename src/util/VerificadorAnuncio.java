package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Modelo.Banco;
import Modelo.DestaqueDAO;
import Modelo.VeiculoDAO;

public class VerificadorAnuncio {

	
	
	
	public void desativaAnunciosEDestaques(){
		
		//Recupera todos os ids dos anúncio
		
		//recupera todos os ids dos destaque
		
		//verifica os anúncios
		
		//verifica os destaques
		
	}
	
	
	//VERIFICA O VENCIMENTO DE UM ANÚNCIO
	//SE O ANÚNCIO ESTIVER VENCIDO, ELE É DESATIVADO
	public void desativaAnuncioVencido(long idAnuncio){
		
		
		GregorianCalendar  data_ini= new GregorianCalendar();
		Calendar hoje = Calendar.getInstance();
		Calendar data_vencimento;
		long id_veiculo = 0;
		int prioridade_anuncio;
	
		
		try
		{
			
			Connection con = Banco.abreBanco();
			Statement stm =  con.createStatement();
			ResultSet res = stm.executeQuery("select DATA_INI from anuncio WHERE ID_ANUNCIO="+idAnuncio);
			
			if(res.next())
			{
			
			data_ini.setTime(res.getDate("DATA_INI"));
			
			id_veiculo =  res.getLong("ID_VEICULO");
			}
			
		    prioridade_anuncio = new VeiculoDAO().getPrioridadeVeiculo(id_veiculo);
		    
		   
		    
			//Data de vencimento do anúncio
			data_vencimento = Plano.getDataFimAnuncio(data_ini, prioridade_anuncio);
			
			if(hoje.after(data_vencimento))
			{
				
			System.out.println("Veículo vencido: "+id_veiculo);	
				
			//DESATIVA O Veículo
			new VeiculoDAO().desativaVeiculo(id_veiculo);
			
			}
			
		}catch(Exception e){
			
			Debug.gerar("util","VerificadorAnuncio","anuncioVencido",e.getMessage());
			
			
		}
		
		
	}



	//VERIFICA O VENCIMENTO DE UM DESTAQUE
	//SE O DESTAQUE ESTIVER VENCIDO, ELE É DESATIVADO
	public void desativaDestaqueVencido(long cod_veiculo){
		
		

		GregorianCalendar  data_ini= new GregorianCalendar();
		Calendar hoje = Calendar.getInstance();
		Calendar data_vencimento;
		int tipo_destaque;
		long id_destaque;
	
		
		try
		{
			
			Connection con = Banco.abreBanco();
			Statement stm =  con.createStatement();
			ResultSet res = stm.executeQuery("select DATA_INI from destaque WHERE COD_VEICULO="+cod_veiculo);
			
			//PODE HAVER MAIS DE UM TIPO DE DESTAQUE PARA O VEÍCULO(NO CASO DE DESTAQUE ULTRA)
			while(res.next()){
				
				data_ini.setTime(res.getDate("DATA_INI"));
				tipo_destaque =  res.getInt("TIPO_DESTAQUE");
				id_destaque =  res.getLong("ID_DESTAQUE");
				
				//Data de vencimento do destaque cadastrado
				data_vencimento = Destaque.getDataFimDestaque(data_ini,tipo_destaque);
				
				//VERIFICA SE ESTÁ VENCIDO
				if(hoje.after(data_vencimento))
				{
				
				
				System.out.println("Destaque vencido: "+id_destaque);
					
				//Desativa o destaque]
				new DestaqueDAO().desativaDestaque(id_destaque);
				}
			}
			
			
		}catch(Exception e){
			
			Debug.gerar("util","VerificarAnuncio","destaqueVencido", e.getMessage());
		}
		
	}
	
	
}




