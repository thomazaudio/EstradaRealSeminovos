<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Modelo.VeiculoDAO"%>
    <%@page import="util.Item"%>
    <%@page import="util.Paginacao"%>
    <%@page import="Modelo.ItemDAO"%>
    <%@page import="java.util.ArrayList"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>




</head>
<body>





<% 

ArrayList itens = (ArrayList<Item>) new ItemDAO().getAllItensCarro();

ArrayList<Item> n_lista;

Paginacao p = new Paginacao(itens,6);


for(int i=1;i<=p.getTotalPaginas();i++)
{    

	out.write("<div class=\"chb_group\">");
	 
	
	n_lista = (ArrayList<Item>) p.getPaginaElements(i);
	out.write("");
	for(int j=0;j<n_lista.size();j++){
		
	%>	
	
		 
	     <span class="custom_chb_wrapper">
	     <span>
	     <input type="checkbox" name="idAcessorio" value="<%=n_lista.get(j).getId()%>"/>
	     </span><%=n_lista.get(j).getItem()%></span>
	 <%   
		
	}
	
	out.write("</div>");
   	
}


%>
	

</body>
</html>