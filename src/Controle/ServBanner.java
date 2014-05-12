package Controle;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import util.Debug;
import util.Destaque;
import util.Plano;
import Modelo.DestaqueDAO;
import Modelo.ImgDAO;

public class ServBanner extends HttpServlet  {

	
	public static final int CADASTRA_BANNER=1;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		       
		       
		      //Página a ser encaminhada após cadastro do banner
				String page_pos = req.getParameter("page_pos");
				
			 	
		
		
	           //CADASTRO DO BANNER DESTAQUE COM A IMAGEM JÁ ALTERADA
	            try{
			 
               double t,l,w,h;
	            t = Double.parseDouble(req.getParameter("t"));
	            l=  Double.parseDouble(req.getParameter("l"));
	            w=  Double.parseDouble(req.getParameter("w"));
	            h=  Double.parseDouble(req.getParameter("h"));
	            
 
	            //Id do veículo
	            long id_veiculo = Long.parseLong(req.getParameter("ID_VEICULO"));
	            
	            System.out.println("Chegou aqui 2");
	            
	            //Recupera a imagem salva temporariamente
	            byte[] img_temp =  new ImgDAO().getImgTempBanner(id_veiculo);
	            
	            System.out.println("Chegou aqui 3");
	            
	            InputStream in = new ByteArrayInputStream(img_temp);
	          
	            //Inicio das transformações 
	            BufferedImage outImage=ImageIO.read(in);
	            BufferedImage cropped=outImage.getSubimage((int)l, (int)t,(int) w,(int) h);
	            
	            System.out.println("Chegou aqui 4");
	           
	            
	            //Converte a imagem transformada para byte[]
	            byte img[];
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(cropped, "jpg", baos);
				baos.flush();
				img = baos.toByteArray();
				baos.close();
	            
	 
				 System.out.println("Chegou aqui 5");
	            
	            //Lança o banner detaque no sistema
	             Destaque d =  new Destaque();
	            d.setTipoDestaque(Destaque.DESTAQUE_BANNER);
	            d.setCodVeiculo(id_veiculo);
	            d.setDataIni(Calendar.getInstance());
	            d.setDataFim(Plano.getDataFim(Plano.PRIORIDADE_ULTRA));
	            d.setStatus(0);//Não pago
	            
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
							   
				
						  //Salva uma imagem de banner temporária no sistema			
				          new ImgDAO().insertImgTempBanner(IOUtils.toByteArray(item.getInputStream()), id_veiculo);
							     
				          if(soli==1)
				          response.sendRedirect("cad_veiculo/crop_imagem.jsp?ID_VEICULO="+id_veiculo);	
				          
						  //Redireciona para página de crop
				          else
						  response.sendRedirect("crop/crop_imagem.jsp?ID_VEICULO="+id_veiculo);	
							
						   } catch (Exception e) {
							   e.printStackTrace();
						   }
					   }
					   
					  }
				   
	}
}
