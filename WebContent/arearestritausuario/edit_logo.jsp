<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Bean.UsuarioBean" %>
     <%@page import="util.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello JSF!</title>
	<!-- Page Title -->
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<link rel="stylesheet" type="text/css" href="../css/style980.css" />
	<link rel="stylesheet" type="text/css" href="../css/style800.css" />
	<link rel="stylesheet" type="text/css" href="../css/style700.css" />
	<link rel="stylesheet" type="text/css" href="../css/style600.css" />
	<link rel="stylesheet" type="text/css" href="../css/style500.css" />
	<link rel="stylesheet" type="text/css" href="../css/style400.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<% 


long id_user = ( (Usuario) session.getAttribute("usuario")).getId();


%>

<div class="message">
<p>Anterior</p>
<img width="90" src="../ServImg?SOLI=8&&mostra_step=0&&ID_USUARIO=<%=id_user%>" alt="">
</div>
<div class="message">

<h2><strong>Escolha uma imagem.</strong></h2>
<form action="uplogo.jsp" method="POST" enctype="multipart/form-data" name="form1" id="form1">
<input type="hidden" name="SOLI" value="9">
<input type="hidden" name="mostra_step" value="0">
<input type="hidden" name="id_usuario" value="<%=id_user%>">
<input class="btn red" name="file" type="file" id="file"/>
<input class="btn_2 blue" type="image" value="Enviar"/>
</form>

</div>

</body>
</html>