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
import Modelo.PagamentoDAO;
import Modelo.TrocaEmailDAO;
import Modelo.UsuarioDAO;
import Modelo.VeiculoDAO;


public class Main {

	
	public static void main(String[] args){

		
		//new VerificadorDeIntegridade().verificaIntegridadeAnuncio();
		
		
		
		Pagamento pg = new Pagamento();
		
		pg.setCodUser(37);
		pg.setValor(50.30);
		pg.setIdVeiculo(260);
		pg.setTipo(Pagamento.DEBITO_CREDITO);
		pg.setDescricao("Pagamento de anúncio 'Mega'");
		pg.setData(Calendar.getInstance());
		
		new PagamentoDAO().insert(pg);
		
		
	
	}
	
	
	
}
