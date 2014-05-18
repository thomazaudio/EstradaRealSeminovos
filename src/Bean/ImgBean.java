package Bean;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.ImgUtil;
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
			
			InputStream img = new ImgDAO().getImgCapa(Long.parseLong(id)).getImg();
			
			//Convert a imagem
			InputStream n_img = new ImgUtil().alteraTamanho(img,ImgUtil.WIDTH_TUMB,ImgUtil.HEIGHT_TUMB);
			
			
			imgCapa.setStream(n_img);
        	
        }catch(Exception e){
        	imgCapa = new DefaultStreamedContent();
        	imgCapa.setStream(null);
        	//Debug.gerar("Bean","ImgBean" , "Erro ao recuperar imagem de capa do veiculo",e.getMessage());
        }
        
     
        	
            
            
      
        
        return imgCapa;
    }
    
    
    
    public StreamedContent getImgBanner() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        DefaultStreamedContent imgCapa;
        
        
        try{
        	

            String id = context.getExternalContext().getRequestParameterMap().get("idDestaque");
          
        	
			imgCapa = new DefaultStreamedContent();
			imgCapa.setContentType("image/jpeg");
			imgCapa.setStream(new ImgDAO().getImgBanner(Long.parseLong(id)).getImg());
        	
        }catch(Exception e){
        	imgCapa = new DefaultStreamedContent();
        	imgCapa.setStream(null);
        	//Debug.gerar("Bean","ImgBean" , "Erro ao recuperar imagem de capa do veiculo",e.getMessage());
        }
        
     
        	
            
            
      
        
        return imgCapa;
    }
    
    
    
    
    
    //RECUPERA A CAPA DO VEÍCULO E TAMANHO TUMB
    public StreamedContent getTumbCapaVeiculo() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        DefaultStreamedContent imgCapa;
        
        
        try{
        	

            String id = context.getExternalContext().getRequestParameterMap().get("idVeiculo");
          
        	
			imgCapa = new DefaultStreamedContent();
			imgCapa.setContentType("image/jpeg");
			
			InputStream img_original = new ImgDAO().getTumbImgCapa(Long.parseLong(id)).getImg();
			
			
			//Altera oo tamanho da imagem
			imgCapa.setStream(new ImgUtil().alteraTamanho(img_original,ImgUtil.WIDTH_TUMB,ImgUtil.HEIGHT_TUMB));
        	
        }catch(Exception e){
        	imgCapa = new DefaultStreamedContent();
        	imgCapa.setStream(null);
        	//Debug.gerar("Bean","ImgBean" , "Erro ao recuperar imagem de capa do veiculo",e.getMessage());
        }
        
     
        	
            
            
      
        
        return imgCapa;
    }

    
  //RECUPERA A LOGO DO USUÁRIO (IMAGEM DE PERFIL)
    public StreamedContent getLogo() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        DefaultStreamedContent imgCapa;
        
        
        try{
        	

            String id = context.getExternalContext().getRequestParameterMap().get("id");
          
            System.out.println("Id:"+id );
        	
			imgCapa = new DefaultStreamedContent();
			imgCapa.setContentType("image/jpeg");
			
			
			imgCapa.setStream(new ImgDAO().getLogo(Long.parseLong(id)).getImg());
        	
        }catch(Exception e){
        	imgCapa = new DefaultStreamedContent();
        	imgCapa.setStream(null);
        	//Debug.gerar("Bean","ImgBean" , "Erro ao recuperar imagem de capa do veiculo",e.getMessage());
        }
        
     
        	
            
            
      
        
        return imgCapa;
    }

    
}
