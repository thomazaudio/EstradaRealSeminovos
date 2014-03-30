<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="java.util.List" %>
   <%@ page import="java.util.Iterator" %>
    <%@ page import="Modelo.ImgDAO" %>
   <%@ page import="java.io.File" %>
      <%@ page import="util.Usuario" %>
   <%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
   <%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
   <%@ page import="org.apache.commons.fileupload.*"%>  
   <%@page import="org.apache.commons.io.IOUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<center><table border="2">
        <tr><td><h1></h1></td></tr>
   <%
   
  
  long id_user = ( (Usuario) session.getAttribute("usuario")).getId();
  
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
			   out.write("<script> parent.jQuery.fancybox.close(); </script>");
			  
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