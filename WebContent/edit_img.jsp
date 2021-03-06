<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Modelo.ImgDAO"%>
     <%@page import="util.Usuario"%>
      <%@page import="util.Anuncio"%>
      <%@page import="util.Plano"%>
      <%@page import="util.Debug"%>
         <%@page import="util.Plano"%>
        <%@page import="Controle.ServAnuncio"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<%

int mostra_step  = Integer.parseInt(request.getParameter("mostra_step"));

long id_veiculo = Long.parseLong(request.getParameter("id_veiculo"));

session.setAttribute("STEP",3);

//Autentica��o de usu�rio
Usuario user = (Usuario)session.getAttribute("usuario");

if(user==null)
request.getRequestDispatcher("login.jsp").forward(request,response);	

Anuncio an = (Anuncio) session.getAttribute("anuncio");



//Verifica se o veiculo pertence ao anuncio
if(an.getVeiculo().getId()!=id_veiculo)
request.getRequestDispatcher("index.jsp?page=erro_autorizacao.jsp").forward(request,response);

int prioridade_anuncio = (Integer) session.getAttribute("prioridade_anuncio");

%>

<body class="car">

<jsp:include page="simple_header.jsp"></jsp:include>

				<div  class="main_wrapper">
				
				
			
					<div class="steps">
					
					<a href="#"><span>1. Tipo de Ve�culo</span></a><a href="#"><span>2. Plano</span></a><a href="#"><span>3. Cadastro das Informa��es</span></a><span>4. Escolha de Imagens</span>
					
					<%
					
					String nome_bt="";
					
					if(mostra_step==1)
					{
					nome_bt = "Finalizar";
					}
					else
					nome_bt = "Salvar";	
					
					
					
					
					String nome_user="";
					
					if(user!=null)
					nome_user = user.getNome();
					
					%>	
						
					</div>
					
					<div class="content">
					
					
					
					
					<h1><strong>Imagens do </strong>do veiculo </h1>
					<div class="message">
					<h3>Instru��es:</h3>	
					<p>Use um editor de imagem para ocultar a placa do ve�culo.</p>
						
					
						
					</div>
					
					
					
					
					
					
	        
					
					<form action="ServAnuncio" method="GET" >
					<input type="hidden" name="STEP" value="13">
					<input type="hidden" name="mostra_step" value="<%=mostra_step%>">
					<input class="btn_2 blue" type="submit" value="<%=nome_bt%>">
					</form>
					
				
					
					<%
					
					long[] ids_imgs = new ImgDAO().getIdsImgs(id_veiculo);
					
					%>
					
					<% 
					if(ids_imgs.length<Plano.getQuantFotos(prioridade_anuncio))
					{
					%>
					
					
					<h3><a id="iframe" class="btn red"  href="upload_imagem/index.html?id_veiculo=<%=id_veiculo%>">+Adicionar Imagens</a></h3>
					
					
					<%}
					
					else{
					%>
					
					<h3>Voc� j� inseriu a quantidade m�xima de imagens para o plano escolhido.</h3>
					
					<%
					}
					
				    if(ids_imgs.length>0)
					out.write("<h3><strong>Imagens Cadastradas</strong></h3>");	
					
					%>
					
					
					<div  class="car_image_wrapper">
					
			
						<div>
						
						</div>
						
						
						
						<div  class="small_img">
                      
 <% 
 if(ids_imgs.length>0)
 {
 
	 
	 
 %>
 
 <div  class="teste">
<a href="ServImg?SOLI=4&&ID_IMG=<%=ids_imgs[0]%>" rel="car_group">
<img  src="ServImg?SOLI=2&&ID_IMG=<%=ids_imgs[0]%>" alt="car_group" />
</a>


<form action="ServImg"  method="POST">
<input type="hidden" name="SOLI" value="5">
<input type="hidden" name="mostra_step" value="<%=mostra_step%>">
<input type="hidden" name="ID_VEICULO" value="<%=id_veiculo%>">
<input type="hidden" name="ID_IMG" value="<%=ids_imgs[0]%>">
<p><input class="btn_img red" type="submit" value="X" alt=""></p>
</form>



</div>
 
 
 <% 
  }     
 
 %>
                      
<%




for(int i=1;i<ids_imgs.length;i++)
{
	
%>

<div  class="teste">
<a href="ServImg?SOLI=4&&ID_IMG=<%=ids_imgs[i]%>" rel="car_group">
<img src="ServImg?SOLI=2&&ID_IMG=<%=ids_imgs[i]%>" alt="car_group" />
</a>

<form action="ServImg" method="POST">
<input type="hidden" name="SOLI" value="6">
<input type="hidden" name="mostra_step" value="<%=mostra_step%>">
<input type="hidden" name="ID_VEICULO" value="<%=id_veiculo%>">
<input type="hidden" name="ID_IMG" value="<%=ids_imgs[i]%>">
<p><input type="submit" class="btn_img blue" Value="Capa" alt="definir esta imagem como capa"></p>
</form>

<form action="ServImg"  method="POST">
<input type="hidden" name="SOLI" value="5">
<input type="hidden" name="mostra_step" value="<%=mostra_step%>">
<input type="hidden" name="ID_VEICULO" value="<%=id_veiculo%>">
<input type="hidden" name="ID_IMG" value="<%=ids_imgs[i]%>">
<p><input class="btn_img red" type="submit" value="X" alt=""></p>
</form>



</div>

<% 

}

if(ids_imgs.length==0)
{
%>

<div class="teste">

<img src="ServImg?SOLI=15" alt="nenhuma imagem cadastrada" />

</div>


<%

}
%>
<input type="hidden" name="SOLI" value="6">
<br>


<script>


$(document).ready(function() {
	
	
	
	
	$("#iframe").fancybox({
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
	
	
		
	
	
	
$(".teste a").fancybox({'type' : 'image'});


});
//Selecionar apenas uma imagem como capa
$("input:checkbox").click(function() {
    if ($(this).is(":checked")) {
        var group = "input:checkbox[name='" + $(this).attr("name") + "']";
        $(group).prop("checked", false);
        $(this).prop("checked", true);
    } else {
        $(this).prop("checked", false);
    }
});



</script>
	</div>
	</div>
	</div>
	</div>

</body>
</html>