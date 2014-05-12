<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="java.util.List" %>
   <%@ page import="java.util.Iterator" %>
    <%@ page import="Modelo.ImgDAO" %>
   <%@ page import="java.io.File" %>
      <%@ page import="util.Usuario" %>
       <%@ page import="util.Veiculo" %>
      <%@ page import="util.Imagem" %>
   <%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
   <%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
   <%@ page import="org.apache.commons.fileupload.*"%>  
   <%@page import="org.apache.commons.io.IOUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<center><table border="2">
        <tr><td><h1></h1></td></tr>
   <%

  
long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
  
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
			   

		 System.out.println("Chegou aqui 1");  
	   
		  //Salva uma imagem de banner temporária no sistema			
          new ImgDAO().insertImgTempBanner(IOUtils.toByteArray(item.getInputStream()), id_veiculo);
			     
		  	
          System.out.println("Chegou aqui 2"); 
		  
		  //Redireciona para página de crop
		  response.sendRedirect("crop_imagem.jsp?ID_VEICULO="+id_veiculo);	
			
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	  }
   }
   %>
    </table>
   </center>

</body>
</html>