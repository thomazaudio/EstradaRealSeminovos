<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Modelo.Banco" %>
    <%@ page import="Modelo.VeiculoDAO" %>
    <%@page import="util.Debug" %>
    <%@page import="util.Veiculo" %>
      <%@page import="Bean.ContextoBean" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<% 


int tam_img = Integer.parseInt(request.getParameter("tam_img"));

int tam_font = Integer.parseInt(request.getParameter("tam_font"));

long id_veiculo = Long.parseLong(request.getParameter("id_veiculo"));

Veiculo  v =(Veiculo) new VeiculoDAO().getVeiculo(id_veiculo,Veiculo.class);


%>
<style type="text/css">

.texto{

font-size: <%=tam_font%>pt;

}

</style>
</head>
<body>



<p class="texto"  ><a target="_blank" href="<%=this.getServletContext().getContextPath()%>/anuncio.jsp?id_veiculo=<%=id_veiculo%>"><%=v.getTitulo()%></a></p>
<p><img width="<%=tam_img%>" alt="" src="<%=this.getServletContext().getContextPath()%>/ServImg?SOLI=13&&mostra_step=0&&ID_VEICULO=<%=id_veiculo%>"></p>

</body>
</html>