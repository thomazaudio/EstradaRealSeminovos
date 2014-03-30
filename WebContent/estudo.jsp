<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>


<jsp:useBean id="Pessoa" class="util.Pessoa" scope="page"></jsp:useBean>
<jsp:setProperty property="id" name="Pessoa" value="1"/>
<jsp:setProperty property="nome" name="Pessoa" value="Thomaz"/>

${Pessoa.nome}
<p>${Pessoa.id}</p>
</body>
</html>