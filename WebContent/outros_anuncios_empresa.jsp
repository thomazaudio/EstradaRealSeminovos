<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Modelo.UsuarioDAO"%>
     <%@page import="java.util.ArrayList"%>
     <%@page import="Modelo.VeiculoDAO"%>
     <%@page import="Modelo.AnuncioDAO"%>
      <%@page import="util.Anuncio" %>
      <%@page import="java.util.Collections" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<%

long id_atual  = Integer.parseInt(request.getParameter("id_atual"));

long id_user = Integer.parseInt(request.getParameter("id_user"));



ArrayList<Anuncio> ans =  new AnuncioDAO().getAnunciosForUser(id_user);


Collections.shuffle(ans);


%>

<div class="recent_cars">
<h2><strong><%=ans.size()%></strong> veículo(s)</h2>
<ul>

<%

for(int i=0;(i<ans.size()&& i<8);i++)
{

%>
						<li><a target="_blank" href="anuncio.jsp?id_veiculo=<%=ans.get(i).getVeiculo().getId()%>"> <img 
								src="ServImg?SOLI=7&&ID_VEICULO=<%=ans.get(i).getVeiculo().getId()%>" alt="" />
								<div class="description">
									<%=ans.get(i).getVeiculo().getAnoFabricacao()+"/"+ans.get(i).getVeiculo().getAnoModelo()%><br /><%=ans.get(i).getVeiculo().getCombustivel()%><br /><%=ans.get(i).getVeiculo().getQuilometragem()%> km<br /><%=ans.get(i).getVeiculo().getCor()%>
									<br />
								</div>
								<div class="title">
									<p><%=ans.get(i).getVeiculo().getTitulo()%></p>
									<div class="clear"></div>
									<p><span class="price">R$ <%=ans.get(i).getVeiculo().getPreco() %></span></p>
								</div>
						</a>
						</li>
					
					
					<%} %>
						
					</ul>
				
</div>

</body>
</html>