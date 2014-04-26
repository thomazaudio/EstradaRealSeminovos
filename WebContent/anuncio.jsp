<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Modelo.VeiculoDAO" %>
    <%@page import="util.Carro" %>
    <%@page import="util.Veiculo" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>

<%

//Recupera o id do veiculo
long id_veiculo = Integer.parseInt(request.getParameter("id_veiculo"));

Veiculo v = (Veiculo) new VeiculoDAO().getVeiculo(id_veiculo,Veiculo.class);


if(v==null)
request.getRequestDispatcher("anuncio_erro.jsp").forward(request, response);	
else
{
if(v.getClass()==Carro.class)
request.getRequestDispatcher("detalhe_carro.jsp?id_veiculo="+v.getId()).forward(request,response);
}

%>


</title>
</head>
<body>

</body>
</html>