package Bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class ParamBean {
	
	FacesContext contexto;
	
	public ParamBean(){
		
		//Configura o contexto
		contexto = FacesContext.getCurrentInstance();
		
	}
	
	
	public Long getCodPagamento(){
		
		return Long.parseLong(getParametros().get("cod_pagamento"));
		
	}

	
	

	private Map<String,String> getParametros(){

		Map<String,String> params = contexto.getExternalContext().getRequestParameterMap();

		return params;
	}
}
