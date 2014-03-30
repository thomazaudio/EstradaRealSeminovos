<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="util.Contato"%>
     <%@page import="util.Usuario"%>
      <%@page import="util.Anuncio"%>
    <%@page import="Modelo.ContatoDAO"%>
    
    <!-- Page Title -->
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/style980.css" />
	<link rel="stylesheet" type="text/css" href="css/style800.css" />
	<link rel="stylesheet" type="text/css" href="css/style700.css" />
	<link rel="stylesheet" type="text/css" href="css/style600.css" />
	<link rel="stylesheet" type="text/css" href="css/style500.css" />
	<link rel="stylesheet" type="text/css" href="css/style400.css" />
	<link rel="stylesheet" type="text/css" href="css/jquery.fancybox-1.3.4.css" media="screen" />
	<!--[if IE]>
	<link href="css/style_ie.css" rel="stylesheet" type="text/css">
	<![endif]-->
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider.js"></script>
	<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="js/jquery.selectik.js"></script>
	<script type="text/javascript" src="js/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="js/jquery.fancybox-1.3.4.pack.js"></script>
	<script type="text/javascript" src="js/jquery.countdown.js"></script>
	<script type="text/javascript" src="js/jquery.checkbox.js"></script>
	<script type="text/javascript" src="js/js.js"></script>
	
	
	
	
	<script src="dist/jquery.validate.js"></script>
		<script>
		$(document)
		.ready(
				function() {
	           
					
				
				$("#cadContato")
						.validate(
								{
									
									
									rules : {
										
										
							            
							            nome:{
							             	
							            	required: true
							            },
							            
							            
							           email:{
							        	 
							        	   required:true,
							        	   email:true
							           }, 
							            
										cel:{
							        	  
											number:true
							           },
							           
							           tel1:{
							        	   
							        	   number:true
							           },

							           tel2:{
							        	   
							        	   number:true
							           }
									},
									messages : {
										nome : "Por favor, preencha o nome.",
										email: "Por favor, preencha com um email válido.",
										cel:"Por favor, preencha com um número válido.",
										tel1:"Por favor, preencha com um número válido.",
										tel2:"Por favor, preencha com um número válido.",
										
									}
									
								});
				
				});
		
								</script>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<% 

int soli=0;
long id_contato=0;
long id_contato_veiculo=0;
Contato c=null;
boolean email_enable=true;
String nome="",cel="",tel1="",tel2="",email="";





//Contato do usuário
id_contato = Long.parseLong(request.getParameter("id_contato"));

//Verifica se o usuário tem autorização para editar este contato
Usuario user = (Usuario) session.getAttribute("usuario");

Anuncio an = (Anuncio) session.getAttribute("anuncio");




if(id_contato!=0)
{
	
	if(user.getContato().getId()!=id_contato && an.getContato().getId()!=id_contato)
	request.getRequestDispatcher("index.jsp?page=erro_autorizacao.jsp").forward(request, response);	
	
	
	email_enable=false;

	//if(!=null)
	
	c = new ContatoDAO().getContato(id_contato);
	
	
    
	//Seta os dados
	if(c.getNome()!=null)
	nome = c.getNome();
	
	if(c.getCel()!=null)
	cel = c.getCel();
	
	if(c.getTel1()!=null)
	tel1 = c.getTel1();
	
	if(c.getTel2()!=null)
	tel2 = c.getTel2();	
		
	if(c.getEmail()!=null)
	email = c.getEmail();	
		
	//Solicitação editar
	soli =2;
}

//Cadastro de novo contato para veiculo
else {
	
	//Contato excluisvo para o veiculo em caso de empresa
	id_contato_veiculo = Long.parseLong(request.getParameter("id_contato_veiculo"));
	
	
	if(user.getContato().getId()!=id_contato_veiculo && an.getVeiculo().getContato().getId()!=id_contato_veiculo)
	request.getRequestDispatcher("index.jsp?page=erro_autorizacao.jsp").forward(request, response);	
	
	soli=3; 
	
    c = new ContatoDAO().getContato(id_contato_veiculo);
	
	
    
	//Seta os dados
	if(c.getNome()!=null)
	nome = c.getNome();
	
	if(c.getCel()!=null)
	cel = c.getCel();
	
	if(c.getTel1()!=null)
	tel1 = c.getTel1();
	
	if(c.getTel2()!=null)
	tel2 = c.getTel2();	
		
	if(c.getEmail()!=null)
	email = c.getEmail();
	
}


%>

<jsp:include page="simple_header.jsp"></jsp:include>


<div class="content">
<canvas id="linha_titulo" width="1900" height="3"></canvas>
							<script>
      						var canvas = document.getElementById('linha_titulo');
     					    var context = canvas.getContext('2d');
      						var rectWidth = 1880;
     					    var rectHeight = 50;

      						

      						context.fillStyle = '#f28633';
     						 context.fillRect(rectWidth / -2, rectHeight / -2, rectWidth, rectHeight);
    					</script>
<div class="sell_box sell_box_5">
						<h2><strong>Contato</strong></h2>
						<div class="message">
						
						
						<% 
						
						//Cadastro de contato para o representante do veículo
						if(id_contato_veiculo!=0)
						{
						out.write("<p>Por favor, preencha o formulário com os dados do representante responsável pelo veículo.</p>");
						}
						
						//Contato principal
						else
						{
						
						out.write("<p>Este é o contato que irá aparecer na visualização do anúncio.</p>");	
							
						}
						
						%>
						</div>
							<form id="cadContato" action="ServContato" method="POST">
						<div class="input_wrapper">
							<label><span></span><strong>Nome: <%=nome%> </strong></label>
							
						</div>
						<div class="clear"></div>
						
						<div class="input_wrapper">
							<label><span></span><strong>Cel </strong></label>
							<input id="cel" name="cel" type="text" class="txb" value="<%=cel %>" />
						</div>
						<div class="input_wrapper">
							<label><span> </span><strong>Tel 1: </strong></label>
							<input id="tel1" name="tel1" type="text" class="txb" value="<%=tel1%>" />
						</div>
						<div class="input_wrapper last">
							<label><span> </span><strong>Tel 2:</strong></label>
							<input id="tel2"  name="tel2" type="text" class="txb" value="<%=tel2%>" />
						</div>
						<div class="clear"></div>
						
						
						<div class="sell_box sell_box_5"></div>
						<div class="input_wrapper">
						    
						    <% 
						    if(email_enable && id_contato_veiculo!=0)
						    {
						out.write("<label><span>* </span><strong>E-mail::: </strong></label>");
						out.write("<input id=\"email\" name=\"email\"  type=\"text\" class=\"txb\" value=\""+email+"\">");
						     } 
						    else
						    {
						    out.write("<label><span>* </span><strong>E-mail: </strong>"+email+"</label>");
						    out.write("<input id=\"email\" name=\"email\" class=\"txb\"  type=\"hidden\"  value=\""+email+"\">");
						    }
						    
						      %>
						</div>
						
						
						
						<div class="clear"></div>
						
						
					
						<input type="hidden" name="SOLI" value="<%=soli%>">
						<input type="hidden" name="id" value="<%=id_contato%>">
						<input type="hidden" name="id_contato_veiculo" value="<%=id_contato_veiculo%>">
						<input  type="submit"  style="color:blue;background-color:white;font-weight:bold;border-color:orange;"  value="Salvar"  />
						</form>
					
						
					</div>
					</div>

</body>
</html>