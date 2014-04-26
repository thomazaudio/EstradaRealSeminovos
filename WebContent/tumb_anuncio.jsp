<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="util.Anuncio" %>
    <%@ page import="util.Veiculo" %>
     <%@ page import="Modelo.VeiculoDAO" %>
    <%@ page import="Modelo.AnuncioDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<%

long cod = Long.parseLong(request.getParameter("id_anuncio"));



Veiculo v = new VeiculoDAO().getVeiculo(id, Veiculo.class);




%>

</body>
</html>