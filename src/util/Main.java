package util;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import org.primefaces.model.StreamedContent;

import Bean.UsuarioBean;
import Modelo.AnuncioDAO;
import Modelo.Banco;
import Modelo.ContatoDAO;
import Modelo.EmpresaDAO;
import Modelo.ItemDAO;
import Modelo.LocalizacaoDAO;
import Modelo.TrocaEmailDAO;
import Modelo.UsuarioDAO;
import Modelo.VeiculoDAO;


public class Main {

	
	public static void main(String[] args){

		
		
		
		
		
		//VerificadorDeIntegridade.verificaIntegridadeAnuncio();
		
		
		Connection con = Banco.abreBanco();
		Statement stm;
		try {
			stm = con.createStatement();
			stm.executeUpdate("UPDATE contato SET CONFIRMADO=1 WHERE ID_CONTATO=967");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
}
