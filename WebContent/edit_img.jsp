<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Modelo.ImgDAO"%>
     <%@page import="util.Usuario"%>
      <%@page import="util.Anuncio"%>
      <%@page import="util.Plano"%>
      <%@page import="util.Debug"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<%

int mostra_step  = Integer.parseInt(request.getParameter("mostra_step"));

long id_veiculo = Long.parseLong(request.getParameter("id_veiculo"));

session.setAttribute("STEP",3);

//Autenticação de usuário
Usuario user = (Usuario)session.getAttribute("usuario");

if(user==null)
request.getRequestDispatcher("login.jsp").forward(request,response);	

Anuncio an = (Anuncio) session.getAttribute("anuncio");



//Verifica se o veiculo pertence ao anuncio
if(an.getVeiculo().getId()!=id_veiculo)
request.getRequestDispatcher("index.jsp?page=erro_autorizacao.jsp").forward(request,response);



%>

<body class="car">

				<div  class="main_wrapper">
				
				
			
					<div class="steps">
					
					<% 
					
					String nome_bt="";
					
					if(mostra_step==1)
					{
					//recupera o step
					StringBuffer step = (StringBuffer) session.getAttribute("step");
					if(step==null)
					step = new StringBuffer();	
					out.write(step.toString());
					nome_bt = "Continuar";
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
					<h3>Instruções:</h3>	
					<p>Use um editor de imagem para ocultar a placa do veículo.</p>
						
					
						
					</div>
					
					<% 
					
					//Se prioridade =  Ultra ->Vou para escolha de Destaque Banner
					int prioridade_anuncio =-1;
					try{
					 prioridade_anuncio = (Integer) session.getAttribute("prioridade_anuncio");
					}catch(Exception e){
						
						Debug.gerar("","edit_img.jsp","","A prioridade_anuncio não foi recuperada");
						
					}
					
					
					//Banner Destaque
					if(prioridade_anuncio==Plano.PRIORIDADE_ULTRA)
					{
					%>
				    
				    <form action="cad_veiculo/esc_img_banner.jsp" method="GET" >
					<input type="hidden" name="ID_VEICULO" value="<%=id_veiculo%>">
					<input class="btn_2 blue" type="submit" value="Continuar">
					</form>
					
					
					
					<% 
					}
					else {
					%>
					
					<form action="ServAnuncio" method="GET" >
					<input type="hidden" name="STEP" value="4">
					<input type="hidden" name="mostra_step" value="<%=mostra_step%>">
					<input class="btn_2 blue" type="submit" value="<%=nome_bt%>">
					</form>
					
					<% }%>
					
					<h3><a id="iframe" class="btn red"  href="upload_imagem/index.html?id_veiculo=<%=id_veiculo%>">+Adicionar Imagens</a></h3>
					<%
					
					long[] ids_imgs = new ImgDAO().getIdsImgs(id_veiculo);
					
					
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
<p><input class="btn_img red" type="submit" value="Deletar" alt=""></p>
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
<p><input class="btn_img red" type="submit" value="Deletar" alt=""></p>
</form>



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