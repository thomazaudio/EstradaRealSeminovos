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

	
		Pagamento pg = new Pagamento();
		
		pg.setCodUser(37);
		pg.setValor(50);
		pg.setDescricao("Teste de pagamento");
		pg.setIdVeiculo(274);
		pg.setTipo(Pagamento.DEBITO_CREDITO);
		
		new PagamentoDAO().insert(pg);
		
		pg.aprovar();
		
	}
	
	
	
}
