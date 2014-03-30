<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="util.Contato"%>
    <%@page import="util.Usuario"%>
    <%@page import="util.Anuncio"%>
    <%@page import="Modelo.ContatoDAO"%>
    
    <!-- Page Title -->
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<link rel="stylesheet" type="text/css" href="../css/style980.css" />
	<link rel="stylesheet" type="text/css" href="../css/style800.css" />
	<link rel="stylesheet" type="text/css" href="../css/style700.css" />
	<link rel="stylesheet" type="text/css" href="../css/style600.css" />
	<link rel="stylesheet" type="text/css" href="../css/style500.css" />
	<link rel="stylesheet" type="text/css" href="../css/style400.css" />
	<link rel="stylesheet" type="text/css" href="../css/jquery.fancybox-1.3.4.css" media="screen" />
	<!--[if IE]>
	<link href="css/style_ie.css" rel="stylesheet" type="text/css">
	<![endif]-->
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="../js/jquery.bxslider.js"></script>
	<script type="text/javascript" src="../js/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="../js/jquery.selectik.js"></script>
	<script type="text/javascript" src="../js/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="../js/jquery.fancybox-1.3.4.pack.js"></script>
	<script type="text/javascript" src="../js/jquery.countdown.js"></script>
	<script type="text/javascript" src="../js/jquery.checkbox.js"></script>
	<script type="text/javascript" src="../js/js.js"></script>
    
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
Contato c=null;
boolean email_enable=true;
String nome="",cel="",tel1="",tel2="",email="";

id_contato = Long.parseLong(request.getParameter("id_contato"));






if(id_contato!=0)
{
	
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

//Cadastro de contato excluisvo para o carro no caso de empresa

else
{
	//Cadastro de contato para veiculo
	soli=3;
	
	id_contato = Long.parseLong(request.getParameter("id_contato_veiculo"));	
	
}

%>

<div class="content">
<div class="sell_box sell_box_5">
						<h1><strong>Contato</strong></h1>
						
						<div class="message">
						<p>Por favor, preencha os campos corretamente, a partir dos dados informados será possível que o comprador se comunique com você.</p>
						</div>
						
							<form action="../ServContato" method="POST">
						<div class="input_wrapper">
							<label><span>*</span><strong>Nome:<%=nome%></strong></label>
							
						</div>
						<div class="input_wrapper">
							<label><span>*</span><strong>Cel:</strong></label>
							<input name="cel" type="text" class="txb" value="<%=cel %>" />
						</div>
						<div class="input_wrapper">
							<label><span> </span><strong>Tel-1: </strong></label>
							<input name="tel1" type="text" class="txb" value="<%=tel1%>" />
						</div>
						<div class="input_wrapper last">
							<label><span> </span><strong>Tel-2:</strong></label>
							<input name="tel2" type="text" class="txb" value="<%=tel2%>" />
						</div>
						
						
						<div class="input_wrapper">
						    
						    <% 
						    if(email_enable)
						    {
						  
						     } 
						    else
						    {
						    out.write("<p><strong>E-mail: </strong>"+email+"</p>");	
						    out.write("<input name=\"email\"  type=\"hidden\" class=\"txb\" value=\""+email+"\">");
						    }
						    
						      %>
						</div>
						
						
						
						<div class="clear"></div>
						
						
					
						<input type="hidden" name="SOLI" value="<%=soli%>">
						<input type="hidden" name="id" value="<%=id_contato%>">
						<input  type="image" src="../images/bt_salvar.png" />
						</form>
					
						
					</div>
					</div>

</body>
</html>