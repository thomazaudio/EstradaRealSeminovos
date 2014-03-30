<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/style980.css" />
	<link rel="stylesheet" type="text/css" href="css/style800.css" />
	<link rel="stylesheet" type="text/css" href="css/style700.css" />
	<link rel="stylesheet" type="text/css" href="css/style600.css" />
	<link rel="stylesheet" type="text/css" href="css/style500.css" />
	<link rel="stylesheet" type="text/css" href="css/style400.css" />
</head>
<body>
<%


String email = request.getParameter("email");
%>

<jsp:include page="header.jsp"></jsp:include>
<div class="content">
<h2>Ops! Parece que você ainda não confirmou seu email.</h2>

<div class="message">

<p>Enviamos um link de confirmação para <strong><%= email%></strong>, por favor verifique sua caixa de entrada.</p>
<p><a href="#">Reenviar link.</a></p>

</div>
</div>

<jsp:include page="rodape.jsp"></jsp:include>

</body>
</html>