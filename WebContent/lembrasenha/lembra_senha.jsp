<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Modelo.UsuarioDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%
//Recebe o email
String email =  request.getParameter("email");




//Inicia o processo de recuperação da senha

if(new UsuarioDAO().recuperaSenha(email))
out.write("Email enviado com sucesso, verifique seu email e siga as instruções para recuperação de senha.");

else
out.write("A senha não pode ser recuperada para esse email");	

%>

</body>
</html>