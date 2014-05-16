package util;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;

public class SystemEmpresa {

	
	
	
	//INFORMAÇOES DA EMPRESA
	public static final String NOME_EMPRESA="Estrada Real -Seminovos";
	public static final String CNPJ="00.000.208/0001-00";
	
	
	//INFORMAÇÕES DE BANCO -CEDENTE
	public static final Carteira CARTEIRA = new Carteira(30);
	public static final Agencia AGENCIA_BANCO=  new Agencia(1793,"0");
	public static final NumeroDaConta NUMERO_CONTA =new NumeroDaConta(25349,"9");
	public static final String DIGITO_CONTA_BANCO="9";
	public static final BancosSuportados BANCO =BancosSuportados.BANCO_DO_BRASIL; 
	
	//INFORMAÇÕES PARA GERAÇÃO DO "NOSSO NUMERO" NO BOLETO
	public static final int NUMERO_CONVENIO=0;
	
	
	
	//INFORMAÇÕES DE PRAZO
	public static final int PRAZO_BOLETO = 5;
}
