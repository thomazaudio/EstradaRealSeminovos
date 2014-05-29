<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Modelo.UsuarioDAO" %>
     <%@page import="Modelo.ContatoDAO" %>
      <%@page import="util.Usuario" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<div class="message">
<% 





//recebe o id do usuário
long id_user = (Long.parseLong(request.getParameter("id_usuario")));

//Recupera o Usuário presente e joga na sessão
Usuario user = new UsuarioDAO().getUser(id_user);



 
//Realiza a confirmação
try{
new ContatoDAO().confirmaContato(user.getContato().getId());




response.sendRedirect("arearestritausuario/confirmado_sucesso.jsf");


}catch(Exception e){
	
}

%>
</div>
</body>
</html>