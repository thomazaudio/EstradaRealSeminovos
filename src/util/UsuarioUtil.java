package util;

import Modelo.UsuarioDAO;

public class UsuarioUtil {
	
	
	public static String getDoc(Usuario user){
		
		String doc;
		
		int tipo_user = new UsuarioDAO().getTipoUser(user.getId());
		
		
		if(tipo_user==Usuario.PESSOA)
		doc  = ((Pessoa) user).getCpf();
		else
		doc  = ((Empresa) user).getCnpj();	
		
		return  doc;
	}
	
	

}
