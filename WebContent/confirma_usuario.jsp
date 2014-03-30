<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Modelo.UsuarioDAO" %>
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



//Recebe a pagina posterior a confirmação
String page_post = request.getParameter("page_post");

if(page_post==null)
page_post = "ini_usuario.jsp";	


//recebe o id do usuário
long id_user = (Long.parseLong(request.getParameter("id_usuario")));

//Recupera o Usuário presente e joga na sessão
Usuario user = new UsuarioDAO().getUser(id_user);

session.setAttribute("usuario", user);
 
//Realiza a confirmação
try{
new UsuarioDAO().confirmaUsuario(id_user);

out.write("<h2>Olá <strong>"+user.getNome()+"</strong>!</h2>");
out.write("<h3>Confirmação realizada com <strong>sucesso</strong>! <a href=\"index.jsp?page="+page_post+"\"><strong>Clique aqui</strong></a> para continuar.<h3>");


}catch(Exception e){
	
}

%>
</div>
</body>
</html>