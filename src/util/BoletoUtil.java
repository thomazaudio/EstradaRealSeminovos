package util;

import java.math.BigDecimal;
import java.util.Date;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;


public class BoletoUtil {
	
	
	public  String LOCAL_PAGAMENTO = "asdasdsd";
	
	
	  public String getLocalPagamento(){
		  
		  return "Pagável em qualquer agência bancária até o vencimento";
	  }
	
	//Recuperação do "Nosso Número" (DEFINIR AINDA)
		public static String getNossoNumero( long cod_pagamento){
			
			return "0000";
		}
		
		public static String getDigitoNossoNumero(){
			
			return "5";
		}
		
		
		
		//Recuperação de um Boleto de acordo com os dados
    	public Boleto getBoleto(Usuario user, String doc,double valor,long cod_pagamento,double desconto){
    		
    		Cedente cedente = new Cedente("Estrada Real - Seminovos", "00.000.208/0001-00");
    		
    		Sacado sacado = new Sacado(user.getNome(),doc);

    		// Informando o endereço do sacado.
    		Endereco enderecoSac = new Endereco();
    		enderecoSac.setUF(UnidadeFederativa.RN);
    		enderecoSac.setLocalidade("Belo Vale");
    		enderecoSac.setCep(new CEP("35473-000"));
    		enderecoSac.setBairro("Centro");
    		enderecoSac.setLogradouro("Av Tancredo Neves");
    		enderecoSac.setNumero("90");
    		sacado.addEndereco(enderecoSac);
    		
    		SacadorAvalista sacadorAvalista = new SacadorAvalista("Estrada Real - seminovos", "00.000.000/0001-91");

    		// Informando o endereço do sacador avalista.
    		Endereco enderecoSacAval = new Endereco();
    		enderecoSacAval.setUF(UnidadeFederativa.MG);
    		enderecoSacAval.setLocalidade("Belo Vale");
    		enderecoSacAval.setCep(new CEP("35473-000"));
    		enderecoSacAval.setBairro("Centro");
    		enderecoSacAval.setLogradouro("Av Tancredo Neves");
    		enderecoSacAval.setNumero("90");
    		sacadorAvalista.addEndereco(enderecoSacAval);
    		
    		
    		// Informando dados sobre a conta bancária do título.
    		ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
    		contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
    		contaBancaria.setCarteira(new Carteira(30));
    		contaBancaria.setAgencia(new Agencia(1234, "1"));
    		
    		
    		Titulo titulo = new Titulo(contaBancaria, sacado, cedente, sacadorAvalista);
    		titulo.setNumeroDoDocumento("123456");
    		titulo.setNossoNumero("99345678912");
    		titulo.setDigitoDoNossoNumero("5");
    		titulo.setValor(BigDecimal.valueOf(valor));
    		titulo.setDataDoDocumento(new Date());
    		titulo.setDataDoVencimento(new Date());
    		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
    		//titulo.setAceite(new Aceite());
    		titulo.setDesconto(new BigDecimal(0.05));
    		titulo.setDeducao(BigDecimal.ZERO);
    		titulo.setMora(BigDecimal.ZERO);
    		titulo.setAcrecimo(BigDecimal.ZERO);
    		titulo.setValorCobrado(BigDecimal.valueOf(10));
    		
    		Boleto boleto = new Boleto(titulo);
            
    		boleto.setLocalPagamento("Pagável preferencialmente nas agências do Banco do Brasil ou em " +
    		                "qualquer Banco até o Vencimento.");
    		return boleto;
    		
    	}

}
