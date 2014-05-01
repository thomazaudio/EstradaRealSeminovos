package util;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import org.primefaces.model.StreamedContent;

import Bean.DestaqueBean;
import Bean.UsuarioBean;
import Modelo.AnuncioAdmDAO;
import Modelo.AnuncioDAO;
import Modelo.Banco;
import Modelo.ContatoDAO;
import Modelo.DestaqueDAO;
import Modelo.EmpresaDAO;
import Modelo.FinanDAO;
import Modelo.ItemDAO;
import Modelo.LocalizacaoDAO;
import Modelo.TrocaEmailDAO;
import Modelo.UsuarioDAO;
import Modelo.VeiculoDAO;


public class Main {

	
	public static void main(String[] args){

		
		
		Transform t = new Transform();
		
		for(int i=2;i<=4;i++)
		{
		
		
		t.ler("C:\\Users\\Ultrabook\\Documents\\Modelos\\Motos\\"+i);
		
		ArrayList<String> modelos = t.getPalavras();
		
		new VeiculoDAO().inserirModelosMoto(modelos,i);
		
		}
	
	}
	
	
	
}
