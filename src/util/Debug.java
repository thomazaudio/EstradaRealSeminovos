package util;

/**
 * Classe para acompanhamento de soluções de erros
 */
public class Debug {

	
	
	public static void gerar(String pasta,String classe,String metodo,String erro){
		
		System.out.printf("\nNovo erro--Classe: %s,Metodo: %s, Erro: %s",classe,metodo,erro);
	
		
	}
	
	
	public static void gerarDebugPagamento(String pasta,String classe,String metodo,String erro,Pagamento pg){
		
		System.out.printf("NOVO ERRO DE PAGAMENTO!!!\n");
		
		System.out.println("Descricao: "+erro);
		System.out.println("Classe: "+classe);
		System.out.println("Método: "+metodo);
		
		
	}
	

}