package util;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import org.primefaces.model.StreamedContent;

import Bean.DestaqueBean;
import Bean.UsuarioBean;
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

		/*
		Pagamento pg =  new Pagamento();
		
		pg.setCodUser(37);
		pg.setData(Calendar.getInstance());
		pg.setDescricao("Depósito");
		pg.setTipo(Pagamento.ADICIONAR_CREDITO);
		pg.setValor(500);
		*/
		
		new FinanDAO().depositar(37,900);
		
		//System.out.println("Saldo: "+new FinanDAO().getSaldo(38));
		
		/*
		VerificadorDeIntegridade.verificaIntegridadeAnuncio();
		
		
		Destaque d =  new Destaque();
		d.setCodVeiculo(248);
		d.setDataIni(Calendar.getInstance());
		d.setTipoDestaque(Destaque.DESTAQUE_INFERIOR);
		
		
		new DestaqueDAO().insert(d);
		
		*/
		
		//new DestaqueDAO().setAllDestaqueInferior(Calendar.getInstance());
		
	
	}
	
	
	
}
