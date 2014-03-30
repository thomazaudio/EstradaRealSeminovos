<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="util.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<div id="content">
<%
String email = request.getParameter("email");

//Cadastro em sessao
Usuario user = (Usuario) session.getAttribute("usuarioSessionCad");

%>
<div class="message">
<h3>Olá <strong><%=user.getNome() %></strong>!</h3>
<h3>Seja bem vindo ao Estrada Real seminovos!</h3>
<h3>Enviamos  um link de confirmação para seu email (<strong><%=email%></strong>).</h3>
<h3>Verifique o email para confirmar seu cadastro.</h3>

</div>

<jsp:include page="rodape.jsp"></jsp:include>
</div>

</body>

</html>