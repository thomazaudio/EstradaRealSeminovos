<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    
    <%
    
    long id_veiculo = Long.parseLong(request.getParameter("ID_VEICULO"));
    
    session.setAttribute("id_veiculo",id_veiculo);
    
    //Cadastro de banner(Ato de cadastro de veiculo)
    session.setAttribute("soli",1);
    
    
    %>   


<h2><strong>Escolha uma imagem.</strong></h2>
<form action="../ServBanner" method="POST" enctype="multipart/form-data">
<input type="hidden" name="id_veiculo" value="<%=id_veiculo%>"/>
<input class="btn red" name="file" type="file" id="file"/>
<input class="btn_2 blue" type="image" value="Enviar<%=id_veiculo %>"/>
</form>
        
        
    </body>
</html>
