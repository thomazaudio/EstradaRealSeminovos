<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<%

String page_pos = request.getParameter("page_pos");
String text_page_pos = request.getParameter("text_page_pos");

if(page_pos==null)
page_pos = "inicio.jsf";	

if(session.getAttribute("usuario")==null)
response.sendRedirect("esc_login_simples.jsf?page_pos="+page_pos+"&&text_page_pos="+text_page_pos);
else
response.sendRedirect(page_pos);	


%>

</body>
</html>