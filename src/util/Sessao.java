package util;

import javax.servlet.http.HttpSession;

public class Sessao {

	
	private static HttpSession sessao;

	public static HttpSession getSessao() {
		return sessao;
	}

	public static void setSessao(HttpSession sessao) {
		Sessao.sessao = sessao;
	}
	
	
}
