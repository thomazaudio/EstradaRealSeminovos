<%@page import="util.Debug"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="java.util.List" %>
  <%@ page import="java.util.Enumeration" %>
  <%@ page import="util.Imagem" %>
   <%@ page import="java.util.Iterator" %>
   <%@ page import="java.io.File" %>
   <%@ page import="IO.ImgIO" %>
   <%@ page import="Modelo.ImgDAO" %>
   <%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
   <%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
   <%@ page import="org.apache.commons.fileupload.*"%>  
   <%@page import="java.io.InputStream" %>
   <%@page import="org.apache.commons.io.IOUtils"%>
   <%@page import="org.apache.commons.fileupload.util.Streams;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
  
 
   String ajaxUpdateResult = "";
   
   Long id_veiculo = Long.parseLong(request.getParameter("id_veiculo"));
   
	
	try {
		List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		
		for (FileItem item : items) {
			
			if (item.isFormField()) {
				ajaxUpdateResult += "Field " + item.getFieldName() + " with value: " + item.getString() + " is successfully read\n\r";
			} else {
				String fileName = item.getName();
				//InputStream content = item.getInputStream();
                
				
				Imagem img =  new Imagem();
				img.setId_veiculo(id_veiculo);
				img.setCapa(0);
				img.setImg(item.getInputStream());
				
				new ImgDAO().insert(img);
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
  
				// Do whatever with the content InputStream.
				//System.out.println(Streams.asString(""));

				ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";
				
			}
		}
	} catch (FileUploadException e) {
		throw new ServletException("Parsing file upload failed.", e);
	}

	Debug.gerar("MSG: ",ajaxUpdateResult,"","");
   
 %>
   
</body>
</html>