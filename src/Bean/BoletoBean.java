package Bean;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;


import util.BoletoUtil;
import util.Empresa;
import util.Pessoa;
import util.Usuario;
import Modelo.UsuarioDAO;





@ManagedBean(name="boletoBean")
public class BoletoBean {
	
	
private long idPagamento;	
	
	
public long getIdPagamento() {
	
	Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	
	idPagamento = Long.parseLong(p.get("id_pagamento"));
	
	return idPagamento;
}


public void setIdPagamento(long idPagamento) {
	
	
		
	
	
	this.idPagamento = idPagamento;
}


public void geraBoleto(){
	
	     Map<String,String> p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	
	
	    //Recebe o código do pagamento
	    long id_pagamento = Long.parseLong(p.get("id_pagamento"));
	    
	    System.out.println("O id do pagamento é: "+id_pagamento);
		
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		//Recupera o usuário em sessão
		Usuario user = (Usuario) sessao.getAttribute("usuario");
		
		
		int tipo_user = new UsuarioDAO().getTipoUser(user.getId());
		
		
		String doc;

		
		if(tipo_user==Usuario.PESSOA)
		doc  = ((Pessoa) user).getCpf();
		else
		doc  = ((Empresa) user).getCnpj();	
		
		
		
		Boleto boleto =  new BoletoUtil().getBoleto(user, doc,50,id_pagamento,0);
		
		
		BoletoViewer boletoViewer = new BoletoViewer(boleto);
        
        byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();
        
       

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        try {
                                
             response.setContentType("application/pdf");
             response.setHeader("Content-Disposition", "attachment; filename=boleto_"+user.getNome()+".pdf");

             OutputStream output = response.getOutputStream();
             output.write(pdfAsBytes);
             response.flushBuffer();

             FacesContext.getCurrentInstance().responseComplete();

        } catch (IOException e) {
                e.printStackTrace();
        }
        
      
		
	}

}
