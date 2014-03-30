<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="util.Contato"%>
<%@ page import="util.Usuario"%>
<%@ page import="util.Anuncio"%>
<%@ page import="util.Empresa"%>
<%@ page import="util.Pessoa"%>
<%@ page import="util.Usuario"%>
<%@ page import="Modelo.ContatoDAO"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

.iframe{

background-color:#FFFFFF;
border-bottom-color: orange;



}

</style>>
</head>
<body>

	<% 
Usuario user = (Usuario) session.getAttribute("usuario");

if(user==null)
{
//vai para a pagina de login	
}



int mostra_step=1;
String nome_botao="";
mostra_step = Integer.parseInt(request.getParameter("mostra_step"));

Anuncio an  = (Anuncio) session.getAttribute("anuncio");

session.setAttribute("STEP",2);


//Verifica se ja existe contato cadastrado para o anuncio
 
%> 
 <div class="main_wrapper">
					<div class="steps">
					
					<% 
					
					
					
					
					if(mostra_step==1)
					{
					//recupera o step
					StringBuffer step = (StringBuffer) session.getAttribute("step");
					out.write(step.toString());
					
					//Define o botão com o próximo
					nome_botao = "images/bt_proximo.png";
					}
					else
					nome_botao = "images/bt_salvar.png";	
					
					

					
					String nome_user="";
					
					if(user!=null)
					nome_user = user.getNome();
					%>	
						
					</div>
					</div>
    <div class="content">
    
    <div class="banners">
    <br>
    </br>
    
    <h1><strong>Contato</strong></h1>  
      
                   <div class="message">
						
					<% 
					
					//if(user.getClass()==Empresa.class)
					//out.write("<p>Você pode adicionar o contato do vendedor responsável por esse veículo dentro da <strong>"+user.getNome()+".</p>");
					
					
					%> 
				
						<!-- Adicionar a mensagem aqui -->
						<p>Este é o contato que irá aparecer na visualização do anúncio, você poderá editar essas informações no painel do usuário.</p>
						
					</div>  
					
					   
					      
                     
                       <% 
                       
                       String email="@example.com";
                       String nome="";
                       String cel="";
                       String  tel1="";
                       String tel2="";
                       long id_contato = 0;
                       
                       
                       String email_veiculo="@example.com";
                       String nome_con_veiculo="";
                       String cel_veiculo="";
                       String  tel1_veiculo="";
                       String tel2_veiculo="";
                       long id_contato_veiculo = 0;
                       int comp_veiculo = 0;
                       
                       //Recupera o contato principal do usuário
                       if(user!=null)
                       {
                    	   
                       if(user.getContato()!=null)
                       {
                    	   
                    	//Atualiza o contato
                    	Contato con = new ContatoDAO().getContato(user.getContato().getId());
                    	
                    
                    	
                    	email =con.getEmail();  
                    	id_contato = con.getId();
                    	nome =  con.getNome();
                    	cel = con.getCel();
                    	tel1 = con.getTel1();
                    	tel2 = con.getTel2();
                    	
                       }
                       }
                       
                       if(an.getVeiculo().getContato()!=null){
                    	Contato con;
                    	   
                       	//Atualiza o contato 
                       	try{
                        con = new ContatoDAO().getContato(an.getVeiculo().getContato().getId());
                       	email_veiculo =con.getEmail();  
                       	id_contato_veiculo = con.getId();
                       	nome_con_veiculo =  con.getNome();
                       	cel_veiculo = con.getCel();
                       	tel1_veiculo = con.getTel1();
                       	tel2_veiculo = con.getTel2();
                       	comp_veiculo = con.getComp();
                       	
                      
                       	
                       	}catch(Exception e){
                       		
                       		//Cadastra um novo contato generico para o veiculo
                       		con = new Contato();
                       		new ContatoDAO().insert(con);
                       		an.getVeiculo().setContato(con);
                       		
                       	 	//Seta novamente o id do contato do veículo
                           	id_contato_veiculo = an.getVeiculo().getContato().getId();
                       	}
                       	
                       	
                    	   
                    	   
                       }
                       
                       %>
                       
                     
                       
	                   <div  class="message">
						<div class="text_wrapper">
							<h2><strong>Contato</strong> Principal</h2>
							<p class="desc"><strong>Email: </strong><%=email %></p>
							<p class="desc"><strong>Nome: </strong><%=nome%></p>
							<p class="desc"><strong>Cel: </strong><%=cel%></p>
							<p class="desc"><strong>Tel1: </strong><%=tel1%></p>
							<p class="desc"><strong>tel2: </strong><%=tel2%></p>
							 
						<!--  	<p class="desc"><input href="edit_contato.jsp?id_contato=<%=id_contato%>" type="submit" class="iframe" value="Editar Contato"></p> -->
						
					
						     
					  <br>
					    
						
						</div>
						</div>
					 
					    
						
						
						
						<%
						
						//Mostra as informações do contato cadastrado
						if(comp_veiculo==1)
						{
						
							  
								
						
						out.write("<div  class=\"message\">");	
						out.write("<div class=\"text_wrapper\">");
						out.write("<h2><strong>Contato</strong> exclusivo (Contato do vendedor responsável pelo veículo)</h2>");	
						out.write("<p class=\"desc\"><strong>Email: </strong>"+email_veiculo+"</p>");
						out.write("<p class=\"desc\"><strong>Nome: </strong>"+nome_con_veiculo+"</p>");
						out.write("<p class=\"desc\"><strong>Cel: </strong>"+cel_veiculo+"</p>");
						out.write("<p class=\"desc\"><strong>Tel1: </strong>"+tel1_veiculo+"</p>");
						out.write("<p class=\"desc\"><strong>tel2: </strong>"+tel2_veiculo+"</p>");
						out.write("<input  class=\"iframe\" href=\"edit_contato.jsp?id_contato=0&&id_contato_veiculo="+id_contato_veiculo+"\" type=\"submit\" value=\"Editar Contato\">");
						out.write("<form action=\"ServContato\" method=\"POST\">");
						out.write("<input type=\"hidden\" name=\"SOLI\" value=\"5\">");
						out.write("<input type=\"hidden\" name=\"page_pos\" value=\"ServAnuncio?STEP=2&&mostra_step="+mostra_step+"\">");
						out.write("<input type=\"hidden\" name=\"id\" value=\""+id_contato_veiculo+"\">");
						out.write("<input  type=\"submit\" value=\"Excluir Contato\">");
						out.write("</form>");
						out.write("</div>");
						out.write("</div>");
						}
						
						else
						{
						
						//Disponibiliza botão para cadastro de contato	
						    //out.write("<div class=\"message\">");
							
		                  
		                    //out.write("<a class=\"iframe\" href=\"edit_contato.jsp?id_contato=0&&id_contato_veiculo="+id_contato_veiculo+ "\"><h2 class=\"desc\">Cadastre o contato do vendedor responsável por este veículo.</h2></a>");
		                  
		                  
							//out.write("<--");
		                   
		                  
							
							
						}
						
						
						%>
						
						

					
					     
					   
					
					
					    <form action="ServAnuncio" method="POST">    
					    <input type="hidden" name="STEP" value="3">
					    <input type="hidden" name="id_contato" value="<%=id_contato%>">
					    <input type="hidden" name="id_contato_veiculo" value="<%=id_contato_veiculo%>">
					     <input type="hidden" name="mostra_step" value="<%=mostra_step%>">
						<input align="left" type="image" src="<%=nome_botao%>" />	
						
						</form>
					 
					 
					</div>
					
	<script>
	
	
	$(document).ready(function() {
		
		
		
		
		$(".iframe").fancybox({
		    'width'         : '100%',
		    'height'        : '100%',
		    'autoScale'     : false,
		    'transitionIn'  : 'none',
		    'transitionOut' : 'none',
		    'type'          : 'iframe',
		    'onClosed': function() {
		         
		    	window.location.reload();
		    	
		        }
		      	
		});	
	});
	
	
	
	</script>
	
					
					 
					
</body>
</html>