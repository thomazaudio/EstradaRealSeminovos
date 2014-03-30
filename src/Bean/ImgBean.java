package Bean;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import util.Debug;
import Modelo.ImgDAO;

@ManagedBean(name="imgBean")
@ApplicationScoped
public class ImgBean {

	
    

    public StreamedContent getcapaVeiculo() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        DefaultStreamedContent imgCapa;
        
        
        try{
        	

            String id = context.getExternalContext().getRequestParameterMap().get("idVeiculo");
          
        	
			imgCapa = new DefaultStreamedContent();
			imgCapa.setContentType("image/jpeg");
			imgCapa.setStream(new ImgDAO().getImgCapa(Long.parseLong(id)).getImg());
        	
        }catch(Exception e){
        	imgCapa = new DefaultStreamedContent();
        	imgCapa.setStream(null);
        	//Debug.gerar("Bean","ImgBean" , "Erro ao recuperar imagem de capa do veiculo",e.getMessage());
        }
        
     
        	
            
            
      
        
        return imgCapa;
    }

}
