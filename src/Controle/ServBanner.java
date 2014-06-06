package Controle;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;

import util.Debug;
import util.Destaque;
import util.ImgUtil;
import util.Plano;
import Modelo.DestaqueDAO;
import Modelo.ImgDAO;
import Modelo.VeiculoDAO;

public class ServBanner extends HttpServlet  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CADASTRA_BANNER=1;
	public static final int EDIT_BANNER=2;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		     //Id do ve�culo
             long id_veiculo = Long.parseLong(req.getParameter("ID_VEICULO"));
		
		     //Verifica se o pagamento do ve�culo est� ok
		     boolean  pagamento_ok = new VeiculoDAO().pagamentoOK(id_veiculo); 
		
		     Destaque d = new Destaque();
		
		
		      //Recebe a solicita��o
		      int soli = Integer.parseInt(req.getParameter("soli"));
		      
		        
		    	  
		      if(pagamento_ok)	  
		      d.setStatus(1);
		      
		      else d.setStatus(0);
		      
  
		       //P�gina a ser encaminhada ap�s cadastro do banner
			   String page_pos = req.getParameter("page_pos");
				
			 	
		
		
	           //CADASTRO DO BANNER DESTAQUE COM A IMAGEM J� ALTERADA
	            try{
			 
	            
 
	         
	          
	            
	            //Recupera a imagem salva temporariamente
	            byte[] img_temp =  new ImgDAO().getImgTempBanner(id_veiculo);
	            
	            System.out.println("Chegou aqui 3");
	            
	            InputStream in = new ByteArrayInputStream(img_temp);
	          
	           
	            
	           
	            BufferedImage outImage=ImageIO.read(in);
	            BufferedImage cropped=null;
	            
	           
	            	
	            //Deixa a imagem no seu estado original
	            cropped = outImage;
	         
	            
	         
	            
	            
	            
	            //Converte a imagem transformada para byte[]
	            byte img[];
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(cropped, "jpg", baos);
				baos.flush();
				img = baos.toByteArray();
				baos.close();
	            
	 
				
	         
	           
	            d.setTipoDestaque(Destaque.DESTAQUE_BANNER);
	            d.setCodVeiculo(id_veiculo);
	            d.setDataIni(Calendar.getInstance());
	            d.setDataFim(Destaque.getDataFimDestaque(Calendar.getInstance(),Plano.PRIORIDADE_ULTRA));
	           
	           //Lan�a o destaque no sistema 
	            if(soli==CADASTRA_BANNER)
	            new DestaqueDAO().insert(d);
	            
	            
	            //Apos inserir o destaque, altera a imagem logo em sequida
	            new ImgDAO().updateImgBannerDestaque(img, id_veiculo);
	            
	            
	            //Encaminha para page_pos
	            response.sendRedirect(page_pos);
	            
	         
	            
	        }
	        catch(Exception ex){
	          
	        	
	        	
	        	Debug.gerar("","ServBanner","doGet",ex.getMessage());
	        }
		
		
		
	}
	
	@Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	                   HttpSession sessao =  request.getSession();
	                   long id_veiculo = (Long) sessao.getAttribute("id_veiculo");
	                   
	                   long soli = (Integer) sessao.getAttribute("soli");
				
					   FileItemFactory factory = new DiskFileItemFactory();
					   ServletFileUpload upload = new ServletFileUpload(factory);
					   List items = null;
					   try {
						   items = upload.parseRequest(request);
					   } catch (FileUploadException e) {
						   e.printStackTrace();
					   }
					   Iterator itr = items.iterator();
					   while (itr.hasNext()) {
					   FileItem item = (FileItem) itr.next();
					   if (item.isFormField()) {
					   } else {
						   try {
							   
				
						  //Salva uma imagem de banner tempor�ria no sistema	
							   
						  InputStream img = item.getInputStream();	   
						  
						  
						  //CONVERTE A IMAGEM
						  InputStream n_img =  new ImgUtil().alteraTamanho(img,ImgUtil.MIN_WIDTH_BANNER,ImgUtil.MIN_HEIGHT_BANNER);
						  
						  
						  //Insere a imagem convertida
							   
				          new ImgDAO().insertImgTempBanner(IOUtils.toByteArray(n_img), id_veiculo);
							     
				          if(soli==1)
				          response.sendRedirect("cad_veiculo/crop_imagem.jsp?ID_VEICULO="+id_veiculo);	
				          
						  //Redireciona para p�gina de crop
				          else
						  response.sendRedirect("crop/crop_imagem.jsp?ID_VEICULO="+id_veiculo);	
							
						   } catch (Exception e) {
							   e.printStackTrace();
						   }
					   }
					   
					  }
				   
	}
}
