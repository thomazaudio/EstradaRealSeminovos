package Controle;

import java.io.IOException;





import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import util.Debug;
import Modelo.ImgDAO;



public class ServImg extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public final int SALVAR_IMGS = 1;
	public final int GET_TUMB = 2;
	public final int GET_TUMBS = 3;
	public final int GET_IMG = 4;
	public final int DELETE_IMG = 5;
	public final int SET_CAPA=6;
	public final int GET_CAPA =7;
	public final int GET_LOGO =8;
	public final int SET_LOGO =9;
	public final int GET_IMG_TEMP_BANNER =10;
	public int soli; 
	public int mostra_step=0;


	public ServImg() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try{ 
			soli = Integer.parseInt(request.getParameter("SOLI"));
			mostra_step = Integer.parseInt(request.getParameter("mostra_step"));
		}
		catch(Exception e){
			mostra_step=1;	 
		}

		//Solicitação para recuperação de imagens em tamanho TUMB
		if(soli==this.GET_TUMB)
		{

			long id_img = 0;
			try{ 
			id_img = Long.parseLong(request.getParameter("ID_IMG"));
			
			byte[] tumb = IOUtils.toByteArray(new ImgDAO().getTumb(id_img).getImg());

			response.setContentType("image/jpeg");
			response.setContentLength(tumb.length);
			response.getOutputStream().write(tumb);	
			}
			
			catch(Exception e){
			
				Debug.gerar("Controle","ServImg","soli=GET_TUMB", e.getMessage());
			
				
			}
			
		

		}


		//Solicitação para recuperação de imagens em tamanho IMAGEM
		else if(soli==this.GET_IMG)
		{
			long id_img = Long.parseLong(request.getParameter("ID_IMG"));
			byte[] img = IOUtils.toByteArray(new ImgDAO().getImg(id_img).getImg());

			response.setContentType("image/jpeg");
			response.setContentLength(img.length);
			response.getOutputStream().write(img);	

		}


		//Imagem de capa de um veiculo
		else if(soli==this.GET_CAPA){

			long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
			byte[] img = IOUtils.toByteArray(new ImgDAO().getImgCapa(id_veiculo).getImg());

			response.setContentType("image/jpeg");
			response.setContentLength(img.length);
			response.getOutputStream().write(img);	
		}

		
		//Logo de um usuário
		else if(soli==this.GET_LOGO){
			
			long id_usuario = Long.parseLong(request.getParameter("ID_USUARIO"));
			byte[] img = IOUtils.toByteArray(new ImgDAO().getLogo(id_usuario).getImg());

			response.setContentType("image/jpeg");
			response.setContentLength(img.length);
			response.getOutputStream().write(img);	
			
			
		}
		
		//Imagem temporaria de banner destaque
		else if(soli==this.GET_IMG_TEMP_BANNER){
			
			//Recebe o id do veiculo
			long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
			byte[] img = new ImgDAO().getImgTempBanner(id_veiculo);
			response.setContentType("image/jpeg");
			response.setContentLength(img.length);
			response.getOutputStream().write(img);	
			
		}
		
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		try{ 
			soli = Integer.parseInt(request.getParameter("SOLI"));
			mostra_step = Integer.parseInt(request.getParameter("mostra_step"));
		}
		catch(Exception e){

			mostra_step=1;
		}

		//Solicitação para deletar uma imagem
		if(soli==this.DELETE_IMG){


			long id_img = Long.parseLong(request.getParameter("ID_IMG"));
			long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));

			deletImg(id_img);



			//Atualiza a pagina
			response.sendRedirect("edit_veiculo.jsp?page=edit_img.jsp?id_veiculo="+id_veiculo+"&&mostra_step="+mostra_step);
		

		}

		else if(soli==this.SET_CAPA)
		{
			long id_img = Long.parseLong(request.getParameter("ID_IMG"));
			long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
			setCapa(id_img,id_veiculo);
			//Atualiza a pagina
			response.sendRedirect("edit_veiculo.jsp?page=edit_img.jsp?id_veiculo="+id_veiculo+"&&mostra_step="+mostra_step);;

		}

		
		
		
		
		
		//Seta a logo do usuário
		else if(soli==this.SET_LOGO){
			
			
			
			System.out.println("Chegou no SET_LOGO");
			
			 long id_user;
			 
			  id_user = Long.parseLong(request.getParameter("id_usuario"));
			   
			  
			  
			 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			 if (!isMultipart) {
			 } else {
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
						 
								
						
						    
						    new ImgDAO().updateLogo(id_user, IOUtils.toByteArray(item.getInputStream()));
						    
						   //Fecha o frame
						   response.getWriter().write("<script> parent.jQuery.fancybox.close(); </script>");
						  
					   } catch (Exception e) {
						   e.printStackTrace();
					   }
				   }
				   
				  }
			   }
			
			
		}
		
		

		
		
	}


	public boolean deletImg(long id_img) {

		return new ImgDAO().delete(id_img);
	}

	public boolean setCapa(long id_img,long id_veiculo){

		return new ImgDAO().marcaCapa(id_img, id_veiculo);
	}



}
